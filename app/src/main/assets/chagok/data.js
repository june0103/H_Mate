// 차곡 데모 고정 데이터 (PRD 7-5 "데모 고정 모드"). 실제 서비스에서는 TourAPI·경로 API·주차장 데이터로 바뀐다.
// toll: 수도권에서 갈 때 편도 통행료 추정(원), parking: 하루 주차비 추정(원). 모두 데모용 근사값이다.

const THEMES = [
  { id: 'sea', name: '바다', c: ['#0b4f7a', '#3fb6d9'] },
  { id: 'lake', name: '호수', c: ['#1f5b5b', '#7cc9b8'] },
  { id: 'river', name: '강', c: ['#21456b', '#8fb8de'] },
  { id: 'mountain', name: '산', c: ['#2f4a25', '#a3c46b'] },
  { id: 'park', name: '공원', c: ['#3b5a1e', '#d6d36a'] },
  { id: 'city', name: '도시', c: ['#3d2a55', '#e09a6c'] },
  { id: 'island', name: '섬', c: ['#174a5c', '#f1d48a'] },
  { id: 'drive', name: '드라이브길', c: ['#4a2a1f', '#e5a35a'] },
];

const PLACES = [
  // 바다
  { name: '을왕리해수욕장', theme: 'sea', lat: 37.4475, lng: 126.3725, toll: 6600, parking: 4000 },
  { name: '궁평항', theme: 'sea', lat: 37.1157, lng: 126.6797, toll: 1800, parking: 0 },
  { name: '대천해수욕장', theme: 'sea', lat: 36.3053, lng: 126.5141, toll: 9100, parking: 3000 },
  { name: '강릉 경포해변', theme: 'sea', lat: 37.8055, lng: 128.9087, toll: 11500, parking: 3000 },
  { name: '양양 서피비치', theme: 'sea', lat: 38.0273, lng: 128.7175, toll: 9800, parking: 5000 },
  { name: '속초해수욕장', theme: 'sea', lat: 38.1906, lng: 128.6003, toll: 10500, parking: 4000 },
  // 호수
  { name: '청평호', theme: 'lake', lat: 37.7310, lng: 127.4250, toll: 2400, parking: 2000 },
  { name: '산정호수', theme: 'lake', lat: 38.0688, lng: 127.3108, toll: 3000, parking: 2000 },
  { name: '예당호 출렁다리', theme: 'lake', lat: 36.6290, lng: 126.8070, toll: 5600, parking: 0 },
  { name: '충주호', theme: 'lake', lat: 36.9940, lng: 128.0240, toll: 6700, parking: 2000 },
  // 강
  { name: '양평 두물머리', theme: 'river', lat: 37.5340, lng: 127.3180, toll: 1200, parking: 3000 },
  { name: '여주 신륵사', theme: 'river', lat: 37.2940, lng: 127.6550, toll: 2800, parking: 2000 },
  { name: '춘천 소양강 스카이워크', theme: 'river', lat: 37.8930, lng: 127.7230, toll: 5600, parking: 0 },
  { name: '단양 도담삼봉', theme: 'river', lat: 36.9997, lng: 128.3440, toll: 8100, parking: 3000 },
  // 산
  { name: '북한산 우이동', theme: 'mountain', lat: 37.6590, lng: 127.0110, toll: 0, parking: 5000 },
  { name: '가평 유명산', theme: 'mountain', lat: 37.5915, lng: 127.4920, toll: 1500, parking: 3000 },
  { name: '치악산 구룡사', theme: 'mountain', lat: 37.4110, lng: 128.0510, toll: 5900, parking: 4000 },
  { name: '오대산 월정사', theme: 'mountain', lat: 37.7316, lng: 128.5925, toll: 8600, parking: 5000 },
  { name: '설악산 소공원', theme: 'mountain', lat: 38.1736, lng: 128.4900, toll: 10500, parking: 5000 },
  // 공원
  { name: '서울숲', theme: 'park', lat: 37.5444, lng: 127.0374, toll: 0, parking: 3000 },
  { name: '화담숲', theme: 'park', lat: 37.3800, lng: 127.3330, toll: 900, parking: 3000 },
  { name: '물향기수목원', theme: 'park', lat: 37.1680, lng: 127.0630, toll: 1300, parking: 2000 },
  { name: '국립수목원 광릉숲', theme: 'park', lat: 37.7550, lng: 127.1690, toll: 1100, parking: 3000 },
  { name: '아침고요수목원', theme: 'park', lat: 37.7440, lng: 127.3530, toll: 2400, parking: 0 },
  // 도시
  { name: '수원 화성 행궁', theme: 'city', lat: 37.2820, lng: 127.0140, toll: 0, parking: 2000 },
  { name: '인천 차이나타운', theme: 'city', lat: 37.4757, lng: 126.6178, toll: 1500, parking: 3000 },
  { name: '공주 공산성', theme: 'city', lat: 36.4640, lng: 127.1250, toll: 7200, parking: 2000 },
  { name: '전주 한옥마을', theme: 'city', lat: 35.8150, lng: 127.1530, toll: 13500, parking: 5000 },
  { name: '경주 황리단길', theme: 'city', lat: 35.8380, lng: 129.2100, toll: 19800, parking: 4000 },
  // 섬
  { name: '대부도 방아머리해변', theme: 'island', lat: 37.2847, lng: 126.5778, toll: 1500, parking: 0 },
  { name: '제부도', theme: 'island', lat: 37.1650, lng: 126.6230, toll: 1800, parking: 3000 },
  { name: '영흥도 십리포해변', theme: 'island', lat: 37.2580, lng: 126.4720, toll: 1500, parking: 3000 },
  { name: '강화 석모도', theme: 'island', lat: 37.6920, lng: 126.3500, toll: 1000, parking: 0 },
  { name: '가평 남이섬', theme: 'island', lat: 37.7910, lng: 127.5250, toll: 3200, parking: 6000 },
  // 드라이브길
  { name: '북한강변 서종 드라이브', theme: 'drive', lat: 37.6070, lng: 127.3550, toll: 1000, parking: 0 },
  { name: '시화방조제', theme: 'drive', lat: 37.3090, lng: 126.6100, toll: 1200, parking: 0 },
  { name: '청풍호반 드라이브', theme: 'drive', lat: 37.0040, lng: 128.1720, toll: 7000, parking: 0 },
  { name: '대관령 옛길', theme: 'drive', lat: 37.6880, lng: 128.7590, toll: 9000, parking: 0 },
  { name: '한계령', theme: 'drive', lat: 38.1090, lng: 128.4380, toll: 9600, parking: 0 },
];

// 경유지 후보(F8, 최대 2곳). 출발지와 목적지 사이에서 돌아가는 거리가 가장 짧은 곳을 고른다.
const REST_AREAS = [
  { name: '죽전휴게소', lat: 37.3297, lng: 127.1117 },
  { name: '여주휴게소', lat: 37.2958, lng: 127.6075 },
  { name: '덕평휴게소', lat: 37.2340, lng: 127.3920 },
  { name: '횡성휴게소', lat: 37.4790, lng: 127.9660 },
  { name: '가평휴게소', lat: 37.7140, lng: 127.4580 },
  { name: '화성휴게소', lat: 37.1360, lng: 126.9430 },
  { name: '행담도휴게소', lat: 36.9580, lng: 126.7930 },
  { name: '천안삼거리휴게소', lat: 36.8016, lng: 127.1640 },
  { name: '내린천휴게소', lat: 37.8920, lng: 128.2690 },
];

// 설정 화면의 차종 표(PRD 5-3: 탱크 용량 API가 없어 차종으로 정한다). 연비 km/L, 전비 km/kWh
const CARS = [
  { id: 'ioniq5', name: '아이오닉 5 (77.4kWh)', ev: true, capacity: 77.4, eff: 5.0 },
  { id: 'ev6', name: 'EV6 (77.4kWh)', ev: true, capacity: 77.4, eff: 5.1 },
  { id: 'avante-hev', name: '아반떼 하이브리드 (42L)', ev: false, capacity: 42, eff: 20.0 },
  { id: 'grandeur', name: '그랜저 2.5 (60L)', ev: false, capacity: 60, eff: 11.0 },
  { id: 'tucson', name: '투싼 1.6T (54L)', ev: false, capacity: 54, eff: 12.5 },
];

// 데모 기준 출발 위치(에뮬 내비가 주는 위치와 같은 성남 수정구). 내비 위치를 받으면 그 값으로 바뀐다.
const DEFAULT_ORIGIN = { lat: 37.4127, lng: 127.0947, name: '성남 수정구' };
