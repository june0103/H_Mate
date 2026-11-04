// Pleos Connect Virtual Console 키보드 조작
// 사용법: http://127.0.0.1:8088 을 연 탭에서 개발자 도구(F12) → Console에 이 파일 내용을 붙여넣고 Enter.
//
//   ← →        핸들 (누르는 동안 돌아가고, 떼면 가운데로 돌아옴)
//   ↓ / Space  브레이크 (누르는 동안)
//   E / Q      방향지시등 오른쪽 / 왼쪽 (누르는 동안 켜짐 → 게임 기어 업 / 다운)
//   P / D      변속 레버 P / D
//
// 차가 움직이지 않도록 속도·엑셀은 항상 0으로 보낸다.
(() => {
  if (window.__vcKeyboard) window.__vcKeyboard.stop();

  const STEER_MAX = 180;      // 게임에서 최대 조향이 되는 각도
  const STEER_RATE = 360;     // 키를 누르고 있을 때 초당 회전 각도
  const RETURN_RATE = 540;    // 키를 떼면 가운데로 돌아오는 속도
  const SEND_MS = 50;

  const held = new Set();
  let steering = 0, brake = 0, sentKey = '', last = performance.now(), timer = 0;

  const put = path => fetch(path, { method: 'PUT' }).catch(e => console.warn('[vc-keyboard]', path, e));
  const typing = e => /^(INPUT|TEXTAREA|SELECT)$/.test(e.target.tagName);

  function onDown(e) {
    if (typing(e) || e.repeat) { if (!typing(e) && isOurs(e.code)) e.preventDefault(); return; }
    if (!isOurs(e.code)) return;
    e.preventDefault();
    held.add(e.code);
    if (e.code === 'KeyE') put('/vehicle/turn-signal?mode=BlinkRight');
    if (e.code === 'KeyQ') put('/vehicle/turn-signal?mode=BlinkLeft');
    if (e.code === 'KeyP') put('/vehicle/gear?gear=Park');
    if (e.code === 'KeyD') put('/vehicle/gear?gear=Drive');
  }

  function onUp(e) {
    if (!held.delete(e.code)) return;
    if ((e.code === 'KeyE' && !held.has('KeyQ')) || (e.code === 'KeyQ' && !held.has('KeyE'))) {
      put('/vehicle/turn-signal?mode=Off');
    }
  }

  function isOurs(code) {
    return ['ArrowLeft', 'ArrowRight', 'ArrowDown', 'Space', 'KeyE', 'KeyQ', 'KeyP', 'KeyD'].includes(code);
  }

  function tick() {
    const now = performance.now(), dt = (now - last) / 1000;
    last = now;
    const dir = (held.has('ArrowRight') ? 1 : 0) - (held.has('ArrowLeft') ? 1 : 0);
    if (dir) steering = Math.max(-STEER_MAX, Math.min(STEER_MAX, steering + dir * STEER_RATE * dt));
    else steering = Math.sign(steering) * Math.max(0, Math.abs(steering) - RETURN_RATE * dt);
    brake = held.has('ArrowDown') || held.has('Space') ? 100 : 0;

    const key = `${Math.round(steering)}|${brake}`;
    if (key !== sentKey) {
      sentKey = key;
      put(`/vehicle/controls?speed=0&accelerator=0&brake=${brake}&steering=${Math.round(steering)}`);
    }
  }

  // 창 밖으로 포커스가 나가면 누르고 있던 키를 모두 뗀 것으로 처리
  function onBlur() {
    if (held.has('KeyE') || held.has('KeyQ')) put('/vehicle/turn-signal?mode=Off');
    held.clear();
  }

  addEventListener('keydown', onDown, true);
  addEventListener('keyup', onUp, true);
  addEventListener('blur', onBlur);
  timer = setInterval(tick, SEND_MS);

  window.__vcKeyboard = {
    stop() {
      clearInterval(timer);
      removeEventListener('keydown', onDown, true);
      removeEventListener('keyup', onUp, true);
      removeEventListener('blur', onBlur);
      delete window.__vcKeyboard;
      console.log('[vc-keyboard] 꺼짐');
    },
  };
  console.log('[vc-keyboard] 켜짐: ←→ 핸들, ↓/Space 브레이크, E/Q 방향지시등, P/D 변속 레버. 끄려면 __vcKeyboard.stop()');
})();
