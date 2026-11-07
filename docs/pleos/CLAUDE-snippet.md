# 새 프로젝트 CLAUDE.md에 붙일 내용

`pleos/` 폴더를 새 프로젝트의 `docs/pleos/`로 복사한 뒤, 아래 구분선 사이의 내용을 그 프로젝트 루트의 `CLAUDE.md`에 붙입니다. 다른 위치에 두었다면 경로만 바꾸세요.

---

## Pleos SDK·플랫폼 문서

이 프로젝트는 Pleos Connect(AAOS 기반 차량 인포테인먼트) 앱이다. Pleos SDK(Vehicle, NaviHelper, TTS/STT, FusedLocation, LLM)를 쓰거나 차량 앱 정책(주행 규제, 오디오 포커스, CCP, 화면 모델, 성능 한도, 알림)이 걸린 작업을 할 때는 먼저 [docs/pleos/README.md](docs/pleos/README.md)를 읽는다. 원문은 https://document.pleos.ai 이며 2026-11-05에 수집하고 에뮬레이터로 검증했다.

- 원문 예제와 실제 SDK가 다르면 `docs/pleos/11-sdk-verification.md`와 `docs/pleos/examples/`의 실측 코드를 따른다.
- `docs/pleos/api/vehicle.md`, `docs/pleos/12-sdk-callbacks.md`처럼 큰 파일은 통째로 읽지 말고 이름으로 검색해 필요한 부분만 읽는다.
- README의 「확인되지 않은 것」에 해당하는 동작은 추측으로 구현하지 말고 먼저 사용자에게 알린다.
- 빌드가 JDK 오류(`jvm.cfg` 없음 등)로 실패하면 README의 「빌드 환경 함정」을 본다.

---
