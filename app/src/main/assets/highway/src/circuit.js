// Circuit mode: the highway car on one of Mission T's closed tracks, 3 laps against 3 AI rivals.
// Rivals are full HighwayCars driven by the reference course driver (course-driver.js), each capped
// at its own top speed. Everything runs on this device, so car-to-car knocks use the race-car
// impulses from car.js: each car's step pushes itself out and records the other half in car.bumps,
// which is handed straight to the car that was hit.
import { buildRover } from './rover.js';
import { buildVehicleModel } from './car-model.js';
import { getVehicle } from './vehicles.js';
import { HighwayCar, HALF_L, HALF_W, RIDE } from './highway/car.js';
import { courseInput } from './highway/course-driver.js';
import { loadCircuit } from './highway/circuit.js';

export const LAPS = 3;
const MIN_LAP = 15; // s; the shortest track (~2 km) can't be lapped faster than this
export const TRACKS = [
  { id: 'alpine-ring', name: '알파인 링', note: '침엽수 숲 S자 코너 · 능선 점프 지름길' },
  { id: 'canyon-flight', name: '캐니언 플라이트', note: '협곡 부스터 직선 · 고가 활강 지름길' },
  { id: 'harbor-run', name: '하버 런', note: '항만 드리프트 코너 · 부두 점프 지름길' },
  { id: 'ridgeline', name: '릿지라인', note: '7km 능선 장거리 코스' },
];
// top: speed cap in m/s; boost: whether it uses its boost; shortcut: takes the jump cut.
const RIVALS = [
  { id: 'vertigo-r', name: 'R', top: 56, boost: true, shortcut: true },
  { id: 'vertigo-apex', name: 'Apex', top: 51, boost: true, shortcut: false },
  { id: 'vertigo-arc', name: 'Arc', top: 46, boost: false, shortcut: false },
];
const ME = 'me';
const GRID_S = -10;                                   // the grid sits just behind the start line
const GRID = [[0, -2.6], [0, 2.6], [-12, -2.6], [-12, 2.6]]; // [s offset, d]: rivals 0–2, us last
const clamp = (v, lo, hi) => Math.max(lo, Math.min(hi, v));

const proxy = (id, kind) => ({ id, kind, player: true, s: 0, x: 0, z: 0, yaw: 0, v: 0, vx: 0, vz: 0, kvx: 0, kvz: 0, hl: HALF_L, hw: HALF_W, hgt: 1.25 });

export class CircuitRace {
  constructor(scene, highway) {
    this.highway = highway;
    this.track = null;      // entry of TRACKS
    this.length = 0;
    this.racing = false;
    this.t = 0;             // race clock, s (physics time since the start)
    this.rivals = RIVALS.map((r) => {
      const rover = buildRover();
      rover.root.visible = false;
      rover.setTheme(getVehicle(r.id).theme);
      scene.add(rover.root);
      buildVehicleModel(r.id)
        .then((b) => rover.useModel(b.holder, b.wheels, { ...b.opts, exhausts: b.exhausts, theme: b.vehicle.theme }))
        .catch((err) => console.warn(`Rival ${r.id} model failed to load`, err));
      return { ...r, rover, car: null, proxy: proxy(r.id, r.name), pose: { pos: {} }, finishT: 0 };
    });
    this.me = proxy(ME, 'me');
    this.byId = new Map();
    highway.beforeStep = () => this.active && this.syncProxies();
    highway.afterStep = (dt) => this.active && this.step(dt);
  }

  get active() {
    return !!this.track && this.rivals[0].car?.road === this.highway.road;
  }

  // Put every car on the grid of `id` (loads the track the first time).
  async load(id) {
    const track = TRACKS.find((t) => t.id === id) || TRACKS[0];
    const data = await loadCircuit(track.id);
    const [ds, d] = GRID[3];
    this.highway.reset({ track: data, traffic: false, s: GRID_S + ds, d });
    const road = this.highway.road;
    this.track = track;
    this.length = road.length;
    this.byId.clear();
    this.byId.set(ME, this.highway.car);
    this.rivals.forEach((r, i) => {
      r.car = new HighwayCar(road);
      r.car.reset(GRID_S + GRID[i][0], GRID[i][1]);
      r.car.savePrev();
      r.finishT = 0;
      r.rover.root.visible = true;
      this.byId.set(r.id, r.car);
    });
    this.highway.extraCars = this.rivals.map((r) => r.proxy);
    this.racing = false;
    this.t = 0;
    this.myLaps = [];      // finished lap times, s
    this.lapStartT = 0;
    this.myFinishT = 0;
    this.syncProxies();
    return track;
  }

  start() {
    this.racing = true;
  }

  // Collision stand-ins for every car, placed on the road relative to `forS` so the s-window test in
  // car.collide() works across the lap seam.
  syncProxies(forS = this.highway.car.s) {
    const L = this.length;
    const fill = (p, car) => {
      let ds = (car.s - forS) % L;
      if (ds > L / 2) ds -= L;
      if (ds < -L / 2) ds += L;
      p.s = forS + ds;
      p.x = car.pos.x; p.z = car.pos.z; p.yaw = car.yaw;
      p.vx = car.vx; p.vz = car.vz; p.v = car.speed;
      p.kvx = 0; p.kvz = 0;
      p.hgt = 1.25 + car.height; // a car in the air passes over
    };
    fill(this.me, this.highway.car);
    for (const r of this.rivals) fill(r.proxy, r.car);
  }

  // After our own step: pass on the knocks we gave, then drive each rival one step.
  step(dt) {
    this.handOver(this.highway.car, ME);
    if (!this.racing) return;
    this.t += dt;
    for (const r of this.rivals) {
      const car = r.car;
      this.syncProxies(car.s);
      const others = { cars: [this.me, ...this.rivals.filter((o) => o !== r).map((o) => o.proxy)] };
      car.savePrev();
      car.step(dt, this.drive(r), others);
      car.events.length = 0; // their sparks and sounds stay quiet
      this.handOver(car, r.id);
      if (!r.finishT && car.s >= LAPS * this.length) r.finishT = this.t;
    }
    this.trackLaps();
  }

  drive(r) {
    const car = r.car;
    const inp = courseInput(car, { shortcut: r.shortcut });
    if (car.speed > r.top) {
      inp.throttle = car.speed > r.top + 2 ? -1 : 0;
      inp.boost = false;
    }
    if (!r.boost) inp.boost = false;
    return inp;
  }

  // The other half of each knock `car` gave, straight onto the car it hit.
  handOver(car, fromId) {
    for (const b of car.bumps) {
      const to = this.byId.get(b.id);
      if (!to || to.wrecked) continue;
      to.vx += b.dvx;
      to.vz += b.dvz;
      to.spin = clamp(to.spin + b.dw, -3, 3);
      to.bumpedAt.set(fromId, to.time); // it shouldn't resolve the same hit again
    }
    car.bumps.length = 0;
  }

  trackLaps() {
    const s = this.highway.car.s;
    const done = Math.max(0, Math.floor(s / this.length));
    while (this.myLaps.length < Math.min(done, LAPS)) {
      this.myLaps.push(this.t - this.lapStartT);
      this.lapStartT = this.t;
    }
    if (!this.myFinishT && s >= LAPS * this.length) this.myFinishT = this.t;
  }

  get finished() {
    return !!this.myFinishT;
  }

  get lap() {
    return clamp(Math.floor(this.highway.car.s / this.length) + 1, 1, LAPS);
  }

  get lapTime() {
    return this.racing ? this.t - this.lapStartT : 0;
  }

  // A lap shorter than MIN_LAP can only come from a respawn glitch; it never counts as a record.
  get bestLap() {
    const real = this.myLaps.filter((t) => t >= MIN_LAP);
    return real.length ? Math.min(...real) : 0;
  }

  // Everyone in race order: finishers by time, then by distance covered.
  standings() {
    const rows = [{ id: ME, name: '나', car: 'vertigo-gt', me: true, s: this.highway.car.s, d: this.highway.car.d, route: this.highway.car.route, finishT: this.myFinishT }];
    for (const r of this.rivals) rows.push({ id: r.id, name: r.name, car: r.id, me: false, s: r.car.s, d: r.car.d, route: r.car.route, finishT: r.finishT });
    return rows.sort((a, b) => (a.finishT && b.finishT ? a.finishT - b.finishT : a.finishT ? -1 : b.finishT ? 1 : b.s - a.s));
  }

  get place() {
    return this.standings().findIndex((r) => r.me) + 1;
  }

  // Draw the rivals between the last two physics steps, like our own car.
  pose(t, alpha) {
    if (!this.active) return;
    for (const r of this.rivals) {
      const c = r.car, v = c.view(alpha, r.pose);
      r.rover.pose3d(v.pos, v.yaw, v.pitch, v.lean, v.roll, c.jets, t, c.ebrake, c.wheelAngle, c.lights,
        { squat: v.squat, height: Math.max(0, v.pos.y - c.groundY - RIDE) });
    }
  }
}
