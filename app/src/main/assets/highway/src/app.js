// Mission T's highway engine, driven by the real car: steering wheel steers, the brake pedal
// brakes (and backs up once stopped), the throttle is automatic, right turn signal = boost,
// left turn signal = hop/hover. Only runs in P (the system blocks the app while driving anyway).
// Two modes on one page: the endless highway (default) and ?mode=circuit, 3 laps of a closed
// track against 3 AI rivals (src/circuit.js).
// MainActivity feeds signals through window.car.set({ steerDeg, brake, gear, signal }); in a
// desktop browser the keyboard stands in (← → steer, ↓/Space brake, E boost, Q hop, P gear).
import * as THREE from 'three';
import { RoomEnvironment } from 'three/addons/environments/RoomEnvironment.js';
import { Sparks } from './fx/sparks.js';
import { buildRover } from './rover.js';
import { loadCarModel } from './car-model.js';
import { Particles } from './fx.js';
import { Sound } from './audio.js';
import { getVehicle } from './vehicles.js';
import { HighwayMode } from './highway/index.js';
import { Gauge } from './gauge.js';
import { CircuitRace, TRACKS, LAPS } from './circuit.js';
import { Minimap } from './race/minimap.js';

const $ = (id) => document.getElementById(id);
const MODE = new URLSearchParams(location.search).get('mode') === 'circuit' ? 'circuit' : 'highway';
const CAR_ID = 'vertigo-gt';
const STEER_FULL_DEG = 180;   // wheel angle for full lock in the game
const SIGNAL_HOLD_MS = 800;   // a blinking signal reads OFF between flashes; hold it through the gaps
const COUNTDOWN = 3;
const BEST_KEY = 'carapp:highway:best';
const TRACK_KEY = 'carapp:circuit:track';
const lapKey = (id) => `carapp:circuit:${id}:lap`;

// ---------- input: the car, or the keyboard outside the car ----------
const input = {
  fromCar: false,
  steerDeg: 0,      // wheel angle, right +
  brake: false,
  gear: 'P',
  signal: 'OFF',
  lastDir: null,    // last signal direction seen and when it went OFF
  offSince: 0,
};
let brakeReleased = false; // set on a press → release of the brake pedal; consumed by the game

function setBrake(on) {
  if (input.brake && !on) brakeReleased = true;
  input.brake = on;
}
function setSignal(sig) {
  if (sig === 'OFF') {
    if (input.signal !== 'OFF') input.offSince = performance.now();
  } else {
    // A new flick of the lever (not the next flash of the same blink) is a press.
    const fresh = sig !== input.lastDir || performance.now() - input.offSince > SIGNAL_HOLD_MS;
    if (input.signal !== sig && fresh) onSignalPress(sig);
    input.lastDir = sig;
  }
  input.signal = sig;
}
// Held while the lever is on, including the dark half of each blink.
const signalHeld = (dir) => input.signal === dir
  || (input.signal === 'OFF' && input.lastDir === dir && performance.now() - input.offSince < SIGNAL_HOLD_MS);

window.car = {
  set(s) {
    input.fromCar = true;
    if (s.steerDeg != null) input.steerDeg = s.steerDeg;
    if (s.brake != null) setBrake(!!s.brake);
    if (s.gear != null) input.gear = s.gear;
    if (s.signal != null) setSignal(s.signal);
  },
};

const keys = new Set();
const keyCodes = ['ArrowLeft', 'ArrowRight', 'ArrowDown', 'Space', 'KeyE', 'KeyQ'];
addEventListener('keydown', (e) => {
  sound.start();
  if (input.fromCar) return; // the car's own signals win; keys would fight them
  if (keyCodes.includes(e.code)) e.preventDefault();
  if (e.repeat) return;
  keys.add(e.code);
  if (e.code === 'KeyP') input.gear = input.gear === 'P' ? 'D' : 'P';
  readKeys();
});
addEventListener('keyup', (e) => {
  if (input.fromCar) return;
  keys.delete(e.code);
  readKeys();
});
function readKeys() {
  input.steerDeg = ((keys.has('ArrowRight') ? 1 : 0) - (keys.has('ArrowLeft') ? 1 : 0)) * STEER_FULL_DEG;
  setBrake(keys.has('ArrowDown') || keys.has('Space'));
  setSignal(keys.has('KeyE') ? 'RIGHT' : keys.has('KeyQ') ? 'LEFT' : 'OFF');
}

// ---------- renderer & scene (Mission T look, without the post-processing passes) ----------
const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setPixelRatio(Math.min(1.5, window.devicePixelRatio));
renderer.shadowMap.enabled = true;
renderer.shadowMap.type = THREE.PCFSoftShadowMap;
renderer.toneMapping = THREE.ACESFilmicToneMapping;
renderer.toneMappingExposure = 0.95;
$('stage').appendChild(renderer.domElement);

const scene = new THREE.Scene();
scene.background = new THREE.Color('#f4f3f0');
scene.fog = new THREE.Fog('#f4f3f0', 90, 320);
const pmrem = new THREE.PMREMGenerator(renderer);
scene.environment = pmrem.fromScene(new RoomEnvironment(), 0.04).texture;
scene.add(new THREE.HemisphereLight('#ffffff', '#d9d6d0', 0.9));
const sun = new THREE.DirectionalLight('#ffffff', 2.2);
sun.castShadow = true;
sun.shadow.mapSize.set(2048, 2048);
sun.shadow.radius = 3;
sun.shadow.camera.left = -40;
sun.shadow.camera.right = 40;
sun.shadow.camera.top = 40;
sun.shadow.camera.bottom = -40;
sun.shadow.camera.far = 160;
sun.shadow.bias = -0.0005;
scene.add(sun, sun.target);
const rim = new THREE.DirectionalLight('#ffffff', 0.5);
rim.position.set(-30, 20, -40);
scene.add(rim);
// Far plane just past the fog's end (320 m): anything further is fog-coloured anyway, so don't draw it.
const camera = new THREE.PerspectiveCamera(52, 1, 0.1, 330);

const rover = buildRover();
scene.add(rover.root);
const particles = new Particles();
const sparkFx = new Sparks();
scene.add(particles.points, sparkFx.mesh);
const sound = new Sound();
const highway = new HighwayMode(scene);
highway.setActive(true);
const gauge = new Gauge($('gauge'), $('dash'));
const circuit = MODE === 'circuit' ? new CircuitRace(scene, highway) : null;
const minimap = circuit ? new Minimap($('minimap')) : null;

const hexRgb = (hex) => [0, 8, 16].map((b) => ((parseInt(hex.slice(1), 16) >> (16 - b)) & 255) / 255);
const car = getVehicle(CAR_ID);
sound.setVehicle(car.id);
rover.setTheme(car.theme);
gauge.setTheme(car.theme);
const jetColor = { boost: hexRgb(car.theme.flame), lift: hexRgb(car.theme.lift).map((c) => c * 0.7 + 0.69 * 0.3) };
loadCarModel(rover, car.id).catch((err) => console.warn('Car model failed to load; using the built-in rover.', err));

// ---------- game flow ----------
// highway: title → countdown → drive → wrecked → countdown …
// circuit: title (pick a track) → countdown → drive → finished → title …
let phase = 'title';
let countEnd = 0;
let shownCard = false;
let finishedAt = 0;
let hitStop = 0;
let grindSound = 0;
let elapsed = 0;
let runStartBest = 0;

const store = {
  get(key, fallback) {
    try { const v = localStorage.getItem(key); return v === null ? fallback : v; } catch { return fallback; }
  },
  set(key, value) {
    try { localStorage.setItem(key, String(value)); } catch { /* storage unavailable */ }
  },
};
const loadBest = () => Number(store.get(BEST_KEY, 0)) || 0;
const km = (m) => `${(m / 1000).toFixed(2)} km`;
const fmtTime = (sec) => `${Math.floor(sec / 60)}:${(sec % 60).toFixed(1).padStart(4, '0')}`;
function saveBest() {
  if (circuit) return;
  const d = highway.car.distance;
  if (d > loadBest() + 1) store.set(BEST_KEY, Math.round(d));
  $('best').textContent = loadBest() ? km(loadBest()) : '—';
}

function startCountdown() {
  if (phase === 'wrecked') {
    highway.reset();
    particles.clear();
    sparkFx.clear();
    $('card').hidden = true;
    shownCard = false;
  }
  runStartBest = loadBest();
  $('title').hidden = true;
  $('ctitle').hidden = true;
  phase = 'countdown';
  countEnd = elapsed + COUNTDOWN;
  sound.start();
}

function showWreck() {
  shownCard = true;
  const r = highway.summary();
  const kinds = { 'Head-on with': '정면 충돌', 'T-boned': '측면 충돌', 'Rear-ended': '추돌', 'Hit from behind by': '후방 추돌' };
  const others = { sedan: '세단', crossover: 'SUV', hatch: '해치백', van: '밴', car: '차량' };
  $('cardReason').textContent = r.info ? `${kinds[r.info.kind] || '충돌'} · ${others[r.info.other] || r.info.other}` : '충돌';
  const mins = Math.floor(r.time / 60), secs = String(Math.floor(r.time % 60)).padStart(2, '0');
  const lines = [
    ['최고 속도', `${Math.round(r.top)} km/h`],
    ['추월', `${r.passed}대`],
    ['주행 시간', `${mins}:${secs}`],
    ['충돌 속도', r.info ? `${Math.round(r.info.closing * 3.6)} km/h` : '—'],
  ];
  $('cardLines').innerHTML = lines.map(([k, v]) => `<li><span>${k}</span><b>${v}</b></li>`).join('');
  $('cardTotal').textContent = km(r.distance);
  saveBest();
  $('cardBest').textContent = r.distance > runStartBest + 1
    ? (runStartBest ? `신기록 · 이전 ${km(runStartBest)}` : '첫 기록이에요')
    : `최고 ${km(loadBest())}`;
  $('card').hidden = false;
  sound.chime(false);
}

// ---------- circuit: track picker, finish card ----------
let trackId = TRACKS.some((t) => t.id === store.get(TRACK_KEY, '')) ? store.get(TRACK_KEY, '') : TRACKS[0].id;
let trackReady = false;
const bestLapOf = (id) => Number(store.get(lapKey(id), 0)) || 0;

function renderTracks() {
  $('tracks').innerHTML = TRACKS.map((t) => {
    const best = bestLapOf(t.id);
    return `<button type="button" role="radio" data-track="${t.id}" aria-checked="${t.id === trackId}">
      <b>${t.name}</b><span>${t.note}</span><small>${best ? `최고 랩 ${fmtTime(best)}` : '기록 없음'}</small></button>`;
  }).join('');
  $('best').textContent = bestLapOf(trackId) ? `${fmtTime(bestLapOf(trackId))} (랩)` : '—';
  $('ctitleCue').innerHTML = trackReady
    ? '트랙을 고르고 브레이크 페달을 <b>밟았다 떼면</b> 출발!'
    : '트랙을 불러오는 중…';
}

function selectTrack(id) {
  if (phase !== 'title') return;
  trackId = id;
  trackReady = false;
  store.set(TRACK_KEY, id);
  renderTracks();
  circuit.load(id).then(() => {
    if (trackId !== id) return; // another track was picked meanwhile
    trackReady = true;
    particles.clear();
    sparkFx.clear();
    renderTracks();
    $('loading').classList.add('gone');
  }).catch((err) => {
    console.error(`Track ${id} failed to load`, err);
    $('ctitleCue').textContent = '트랙을 불러오지 못했어요. 다른 트랙을 골라 주세요.';
  });
}

// On the title screen a flick of the turn signal steps through the tracks.
function onSignalPress(dir) {
  if (!circuit || phase !== 'title') return;
  const i = TRACKS.findIndex((t) => t.id === trackId);
  selectTrack(TRACKS[(i + (dir === 'RIGHT' ? 1 : TRACKS.length - 1)) % TRACKS.length].id);
}

function backToTitle() {
  $('card').hidden = true;
  shownCard = false;
  phase = 'title';
  $('ctitle').hidden = false;
  selectTrack(trackId); // fresh grid on the same track
}

function showFinish() {
  shownCard = true;
  const place = circuit.place;
  const best = circuit.bestLap;
  const prevBest = bestLapOf(circuit.track.id);
  if (best && (!prevBest || best < prevBest)) store.set(lapKey(circuit.track.id), best.toFixed(2));
  $('cardTitle').textContent = `${place}위로 완주`;
  $('cardReason').textContent = `${circuit.track.name} · ${LAPS}랩 · 라이벌 3대`;
  const lines = circuit.myLaps.map((t, i) => [`${i + 1}랩`, fmtTime(t) + (t === best ? ' · 최고' : '')]);
  $('cardLines').innerHTML = lines.map(([k, v]) => `<li><span>${k}</span><b>${v}</b></li>`).join('');
  $('cardTotalLabel').textContent = '기록';
  $('cardTotal').textContent = fmtTime(circuit.myFinishT);
  $('cardBest').textContent = best && (!prevBest || best < prevBest)
    ? (prevBest ? `최고 랩 신기록 · 이전 ${fmtTime(prevBest)}` : '이 트랙 첫 기록이에요')
    : prevBest ? `이 트랙 최고 랩 ${fmtTime(prevBest)}` : '';
  $('cardCue').textContent = '브레이크를 밟았다 떼면 트랙 선택으로';
  $('card').hidden = false;
  sound.chime(place === 1);
}

if (circuit) {
  $('title').hidden = true;
  $('ctitle').hidden = false;
  $('minimap').hidden = false;
  document.querySelector('#loading .brand').textContent = 'CIRCUIT';
  $('kA').textContent = '랩'; $('uA').textContent = '';
  $('kB').textContent = '랩 타임'; $('uB').textContent = '';
  $('kC').textContent = '순위'; $('uC').textContent = '';
  $('tracks').addEventListener('click', (e) => {
    const b = e.target.closest('[data-track]');
    if (b) selectTrack(b.dataset.track);
  });
  selectTrack(trackId);
} else {
  saveBest();
  $('loading').classList.add('gone');
}

// ---------- effects (from Mission T's main.js) ----------
function burst3(p, n, opts, spread, up, carry = null) {
  for (let k = 0; k < n; k++) {
    particles.emit(
      p.x + (Math.random() - 0.5), p.y + Math.random() * 0.6, p.z + (Math.random() - 0.5),
      (carry ? carry.x : 0) + (Math.random() - 0.5) * spread, Math.random() * up, (carry ? carry.z : 0) + (Math.random() - 0.5) * spread,
      opts,
    );
  }
}

function emitSmoke(dt) {
  const c = highway.car;
  if (c.airborne || dt <= 0) return;
  const k = highway.skidLevel * 0.8;
  if (k <= 0) return;
  const floor = c.groundY + 0.05;
  for (const p of highway.rearPatches()) {
    if (Math.random() > k * dt * 60) continue;
    particles.emit(p.x, p.y + 0.1, p.z, c.vx * 0.45 + (Math.random() - 0.5) * 1.5, 0.4 + Math.random() * 0.8, c.vz * 0.45 + (Math.random() - 0.5) * 1.5, {
      life: 1.1 + Math.random() * 0.6, size: 1.6 + k * 1.6, color: [0.7, 0.7, 0.69], drag: 1.6, floor,
    });
  }
}

const sparks = (at, n, c, power = 8) => sparkFx.burst(at, n, { x: c.vx, z: c.vz }, power, c.groundY + 0.03);

function handleEvents() {
  const c = highway.car;
  for (const e of c.events) {
    if (e.type === 'hit' || e.type === 'bump') {
      sound.hit(Math.min(1, 0.3 + e.power / 20), 800);
      if (e.x !== undefined) sparks(e, Math.min(48, 10 + e.power * 2.5), c, e.power);
      highway.shake = Math.max(highway.shake, Math.min(0.85, e.power / 16));
      if (e.power > 8) hitStop = Math.max(hitStop, 0.04);
    } else if (e.type === 'wall') {
      sound.hit(Math.min(1, 0.3 + e.power / 18), 900);
      sparks(e, Math.min(70, 14 + e.power * 4), c, e.power);
      highway.shake = Math.max(highway.shake, Math.min(0.9, e.power / 14));
      if (e.power > 6) hitStop = Math.max(hitStop, 0.04 + Math.min(0.04, e.power * 0.002));
    } else if (e.type === 'grind') {
      sparks(e, 2, c, 4);
      if (elapsed - grindSound > 0.12) {
        grindSound = elapsed;
        sound.hit(0.16, 2400);
      }
    } else if (e.type === 'land') {
      sound.hit(Math.min(0.6, e.power / 12), 260);
      highway.kick('land', e.power);
    } else if (e.type === 'hop') {
      sound.hit(0.3, 400);
    } else if (e.type === 'wreck') {
      const p = { x: c.pos.x, y: c.pos.y - 0.2, z: c.pos.z };
      const floor = c.groundY + 0.05;
      const carry = { x: c.vx * 0.5, z: c.vz * 0.5 };
      burst3(p, 90, { life: 0.9, size: 0.35, color: [0.82, 0.2, 0.22], drag: 1, gravity: 9, floor }, 16, 9, carry);
      burst3(p, 40, { life: 1.6, size: 0.22, color: [0.16, 0.17, 0.19], drag: 0.6, gravity: 12, floor }, 12, 7, carry);
      burst3(p, 45, { life: 2.2, size: 2.4, color: [0.55, 0.54, 0.52], drag: 1.8, floor }, 6, 3, carry);
      highway.shake = 1;
      sound.hit(1, 900);
      sound.hit(0.9, 180);
    }
  }
  c.events.length = 0;
}

const tmp = new THREE.Vector3();
const jetDir = new THREE.Vector3();
const pipeMouths = [];
function emitJets(dt) {
  const c = highway.car;
  if (dt <= 0 || c.wrecked) return;
  const j = c.jets;
  if (!j.boost && !j.lift) return;
  rover.root.updateMatrixWorld(true);
  jetDir.setFromMatrixColumn(rover.root.matrixWorld, 0); // car forward
  const n = Math.max(1, Math.round(dt * 90));
  const floor = c.groundY + 0.05;
  const spark = (node, dx, dy, dz, speed, opts) => {
    node.getWorldPosition(tmp);
    for (let i = 0; i < n; i++) {
      particles.emit(tmp.x, tmp.y, tmp.z,
        c.vx * 0.88 + dx * speed + (Math.random() - 0.5) * 1.2,
        dy * speed + (Math.random() - 0.5) * 0.8,
        c.vz * 0.88 + dz * speed + (Math.random() - 0.5) * 1.2,
        { drag: 2, floor, ...opts });
    }
  };
  if (j.boost) {
    const mouths = rover.exhaustMouths(pipeMouths);
    if (mouths.length) {
      for (const m of mouths) {
        for (let i = 0; i < Math.max(1, n >> 1); i++) {
          particles.emit(m.x, m.y, m.z, c.vx * 0.9 - jetDir.x * 4 + (Math.random() - 0.5), (Math.random() - 0.3) * 0.8, c.vz * 0.9 - jetDir.z * 4 + (Math.random() - 0.5),
            { drag: 2, floor, life: 0.14, size: 0.18, color: jetColor.boost });
        }
      }
    } else {
      spark(rover.nozzles.boost, -jetDir.x, 0, -jetDir.z, 5, { life: 0.18, size: 0.42, color: jetColor.boost });
    }
  }
  if (j.lift) for (const pod of rover.nozzles.lift) spark(pod, 0, -1, 0, 6, { life: 0.22, size: 0.4, color: jetColor.lift });
}

// ---------- HUD ----------
const shown = {};
const write = (id, text) => {
  if (shown[id] === text) return;
  shown[id] = text;
  $(id).textContent = text;
};
let lastHint = '';
function setHint(html, sub = '') {
  if (html !== lastHint) {
    $('hint').innerHTML = html;
    $('hint').style.opacity = html ? 1 : 0;
    lastHint = html;
  }
  write('sub', sub);
}
function hint() {
  const c = highway.car;
  if (phase !== 'drive' || c.wrecked) return ['', ''];
  if (circuit) {
    if (circuit.t < 12) return ['<b>JUMP</b> 구간 앞에서 <b>방향지시등 왼쪽</b>으로 점프해요', '방향지시등 오른쪽은 부스트 · 부딪혀도 게임 오버는 없어요'];
    return ['', ''];
  }
  if (c.distance < 600) return ['<b>방향지시등 오른쪽</b> 부스트 · <b>왼쪽</b> 점프로 차를 넘어가요', '중앙 분리대도 점프로 넘을 수 있어요'];
  return ['', ''];
}
// The HUD is DOM (the speedometer an SVG): every change re-rasterises it, which on the emulator's
// WebView costs more than the 3D frame. Refresh it 10 times a second instead of every frame.
const HUD_EVERY = 0.1;
let hudDt = HUD_EVERY;
let fuelShown = '';
function updateHud(dt, locked) {
  hudDt += dt;
  if (hudDt < HUD_EVERY) return;
  dt = hudDt;
  hudDt = 0;
  const t = highway.telemetry();
  gauge.update(locked ? 0 : dt, t.kmh, !locked && !!highway.car.jets.boost);
  if (circuit) {
    const ready = circuit.active;
    write('gA', ready ? `${circuit.lap}/${LAPS}` : '—');
    write('gB', ready ? fmtTime(circuit.finished ? circuit.myLaps.at(-1) || 0 : circuit.lapTime) : '—');
    write('gC', ready ? `${circuit.place}/${circuit.rivals.length + 1}` : '—');
    if (ready) {
      minimap.draw(highway.road, circuit.track.id, 0, circuit.length, circuit.standings(), { lap: circuit.lap, laps: LAPS });
    }
  } else {
    write('gA', t.km.toFixed(2));
    write('gB', t.g.toFixed(1));
    write('gC', String(t.passed));
  }
  write('gFuelTxt', String(Math.round((t.fuel / highway.fuelMax) * 100)));
  const boost = (t.fuel / highway.fuelMax).toFixed(2);
  if (boost !== fuelShown) {
    fuelShown = boost;
    $('gFuel').style.transform = `scaleX(${boost})`;
    $('gFuel').classList.toggle('low', boost < 0.2);
  }
  write('source', input.fromCar ? '차량 연결됨' : '키보드');
  $('lock').hidden = !locked;
  const n = Math.ceil(countEnd - elapsed);
  $('count').hidden = phase !== 'countdown';
  if (phase === 'countdown') write('count', String(n));
  const [h, sub] = hint();
  setHint(h, sub);
}

// ---------- loop ----------
function resize() {
  const w = window.innerWidth, h = window.innerHeight;
  renderer.setSize(w, h);
  camera.aspect = w / h;
  camera.updateProjectionMatrix();
}
addEventListener('resize', resize);
resize();

const clock = new THREE.Clock();
function tick(dt) {
  elapsed += dt;
  // Anything but P (or an unknown gear) freezes the game, as Pleos requires for games.
  const locked = input.gear !== 'P';
  if (sound.ctx) {
    if (locked && sound.ctx.state === 'running') sound.ctx.suspend();
    else if (!locked && sound.ctx.state === 'suspended') sound.ctx.resume();
  }

  if (!locked && brakeReleased) {
    if (phase === 'title' && (!circuit || trackReady)) startCountdown();
    else if (phase === 'wrecked' && shownCard) startCountdown();
    else if (phase === 'finished' && shownCard) backToTitle();
  }
  brakeReleased = false;
  if (!locked && phase === 'countdown' && elapsed >= countEnd) {
    phase = 'drive';
    circuit?.start();
  }

  const c = highway.car;
  const driving = phase === 'drive' && !locked;
  const hwIn = driving
    ? {
      throttle: input.brake ? -1 : 1,
      spin: -Math.max(-1, Math.min(1, input.steerDeg / STEER_FULL_DEG)), // the game steers left on +
      lift: signalHeld('LEFT'),
      boost: signalHeld('RIGHT'),
      ebrake: false,
    }
    : { throttle: 0, spin: 0, lift: false, boost: false, ebrake: false };

  const hwDt = locked || hitStop > 0 ? 0 : dt;
  hitStop = Math.max(0, hitStop - dt);
  highway.update(hwDt, hwIn);
  if (highway.shift) {
    particles.shift(-highway.shift.dx, 0, -highway.shift.dz);
    sparkFx.shift(highway.shift.dx, highway.shift.dz);
    highway.shift = null;
  }
  handleEvents();
  if (c.wrecked && phase === 'drive') phase = 'wrecked';
  if (phase === 'wrecked' && !shownCard && c.wreckT > 1.6) showWreck();
  if (circuit?.finished && phase === 'drive') {
    phase = 'finished';
    finishedAt = elapsed;
  }
  if (phase === 'finished' && !shownCard && elapsed - finishedAt > 1.2) showFinish();

  highway.poseRover(rover, elapsed);
  circuit?.pose(elapsed, highway.alpha);
  highway.updateCamera(camera, dt, 'chase', 52);
  const p = c.pos;
  sun.position.set(p.x + 18, p.y + 40, p.z + 22);
  sun.target.position.set(p.x, p.y, p.z);
  emitSmoke(hwDt);
  emitJets(hwDt);
  particles.update(hwDt);
  updateHud(dt, locked);
  sound.update(highway.soundState, hwIn.throttle);
  sound.skid(locked ? 0 : highway.skidLevel, c.speed);
  sparkFx.update(locked ? 0 : dt, camera);
  renderer.render(scene, camera);
}

function frame() {
  tick(Math.min(0.1, clock.getDelta()));
  requestAnimationFrame(frame);
}
addEventListener('pagehide', saveBest);
setInterval(() => { if (phase === 'drive') saveBest(); }, 2000);
// Open with ?debug to reach the game from DevTools (chrome://inspect on a debug build).
if (new URLSearchParams(location.search).has('debug')) window.__dev = { highway, circuit, input, get phase() { return phase; } };
requestAnimationFrame(frame);
