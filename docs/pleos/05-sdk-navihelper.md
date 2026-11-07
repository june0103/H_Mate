# 05. SDK 사용 가이드 — NaviHelper SDK

경로 안내 제어·내비 정보 조회 SDK 사용 가이드 원문.

## 목차

- [Introduction](#introduction)
- [Importing NaviHelper SDK](#importing-navihelper-sdk)
- [Declaring permissions](#declaring-permissions)
- [Initialization](#initialization)
- [Destinations](#destinations)
- [Routes](#routes)
- [Route requests with waypoints](#route-requests-with-waypoints)
- [Waypoints](#waypoints)
- [Event listeners](#event-listeners)
- [Error handling](#error-handling)


---

## Introduction

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/intro

### NaviHelper SDK 소개

NaviHelper SDK는 경로 안내 제어와 내비게이션 정보 조회를 위한 API를 제공합니다. 3rd party 앱은 NaviHelper SDK를 통해 Pleos Connect의 Navi Service와 연동됩니다. 3rd party 앱이 목적지·경유지 설정, 경로 변경, 정보 조회 또는 Navigation 화면 표시를 요청하면 Navi Service가 이를 처리하고, 처리 결과와 주행 상태 변화를 이벤트로 전달합니다.

[이미지 설명 필요: 계층도 — 3rd Party Application 안의 NaviHelper SDK → (화살표) Pleos Connect OS 안의 Navi Service, 최하단 Android Automotive OS]

> [INFO]
>
> 이 가이드는 NaviHelper SDK v3.0.5를 기준으로 작성되었습니다.

### 제공 기능

NaviHelper SDK는 다음 기능을 제공합니다.

- 목적지 설정·확인·취소
- 경로 옵션 변경 및 재탐색
- 경유지 추가·조회·제거
- 경유지를 포함한 경로 요청 ([Route requests with waypoints](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/route-with-waypoints))
- 경로 탐색과 주행 상태 이벤트 수신
- 내비게이션 정보 조회 및 Navigation 화면 요청 (API Reference 참고)

NaviHelper 요청은 Pleos Connect Navigation 앱에 전달됩니다. 요청 결과와 주행 중 변경 사항은 `NaviHelperEventListener`의 콜백으로 전달됩니다. 실제로 전달되는 이벤트는 Navigation 앱의 구현과 현재 상태에 따라 달라질 수 있습니다.

### 진입점

`NaviHelper`가 SDK의 진입점입니다. 일반적인 사용 순서는 다음과 같습니다.

1. `NaviHelper` 객체를 생성합니다.
2. `initialize()`로 SDK 연결을 초기화합니다.
3. `addListener()`로 이벤트 리스너를 등록합니다.
4. 경로 요청 또는 정보 조회 API를 호출합니다.
5. 콜백으로 결과와 상태 변화를 처리합니다.
6. `removeListener()`와 `release()`로 리소스를 정리합니다.

패키지 구조와 전체 API 명세는 [NaviHelper API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/entry)에서 확인할 수 있습니다.

### Emulator setup

NaviHelper SDK를 사용하려면 Android Studio에 Pleos Connect Emulator를 설치하고 AVD를 설정해야 합니다.

1. [소프트웨어 개발 환경 요구사항 확인](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#소프트웨어-개발-환경)
2. [Pleos Connect Emulator 설치](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#installing-the-pleos-connect-emulator)
3. [AVD 생성 및 실행](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/setup-connect-sdk-emulator#setting-up-the-emulator)
4. [Pleos Connect Virtual Console 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/use-vehicle-emulator)
5. [마이크 접근 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#마이크-접근-설정)
6. [Cold boot 설정](https://document.pleos.ai/docs/connect/guide/getting-started/application-development/configure-pleos-connect-emulator#cold-boot-설정)

### 다음 단계

1. [NaviHelper SDK 가져오기](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/importing-sdk)
2. [권한 선언](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/declaring-permissions)
3. [SDK 초기화 및 해제](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/initialization-and-release)
4. [이벤트 리스너](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/event-listeners)
5. [목적지 설정](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/destinations)
6. [경로 설정](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/routes)
7. [경유지 설정](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/waypoint-settings)
8. [오류 처리](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/error-handling)
9. [NaviHelper API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/entry)


---

## Importing NaviHelper SDK

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/importing-sdk

### Maven 저장소 등록

프로젝트의 `settings.gradle.kts` 또는 `settings.gradle`에 NaviHelper SDK가 배포된 Maven 저장소를 추가합니다.

[탭: Kotlin DSL]

settings.gradle.kts

```kotlin
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://nexus-playground.pleos.ai/repository/maven-releases/")
        }
    }
}
```

[탭: Groovy]

settings.gradle

```groovy
dependencyResolutionManagement {
    repositories {
        maven {
            url "https://nexus-playground.pleos.ai/repository/maven-releases/"
        }
    }
}
```

### SDK 의존성 추가

앱 모듈의 빌드 파일에 NaviHelper SDK 의존성을 추가합니다.

[탭: Kotlin DSL]

build.gradle.kts

```kotlin
dependencies {
    implementation("ai.pleos.playground:NaviHelper:3.0.5")
}
```

[탭: Groovy]

build.gradle

```groovy
dependencies {
    implementation "ai.pleos.playground:NaviHelper:3.0.5"
}
```

의존성을 추가한 후 Gradle 프로젝트를 동기화합니다. 사용할 SDK 버전은 프로젝트의 배포 기준에 맞춰 지정합니다.


---

## Declaring permissions

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/declaring-permissions

NaviHelper API는 기능에 따라 Android 퍼미션을 요구합니다. 사용하는 API에 해당하는 퍼미션을 앱의 `AndroidManifest.xml`에 선언합니다.

### 퍼미션 종류

| Permission | 주요 API |
| --- | --- |
| `pleos.car.permission.NAVI_ROUTE` | `get*Info()`, `requestTrip()` |
| `pleos.car.permission.NAVI_ROUTE_SEARCH` | `requestRoute()`, `requestRouteWithWaypoints()`, `cancelRoute()`, `requestReRoute()`, `addWaypoint()`, `removeWaypoint()`, `changeRouteOption()` |
| `pleos.car.permission.NAVI_CUSTOM_MAP` | `requestNavigationScreen()` |

API별 정확한 요구 퍼미션은 API Reference의 `@RequiresPermission`에서 확인할 수 있습니다.

### 퍼미션 선언 예시

앱에서 사용하는 기능에 필요한 퍼미션을 선언합니다.

AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="pleos.car.permission.NAVI_ROUTE" />
    <uses-permission android:name="pleos.car.permission.NAVI_ROUTE_SEARCH" />
    <uses-permission android:name="pleos.car.permission.NAVI_CUSTOM_MAP" />

    <application>
        <!-- ... -->
    </application>
</manifest>
```


---

## Initialization

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/initialization-and-release

앱 시작 또는 생성 시점에 `NaviHelper` 객체를 생성하고 `initialize()`를 호출해야 NaviHelper API를 사용할 수 있습니다.

### Initialize the SDK

`initialize()`는 두 가지 오버로드를 제공합니다.

| Overload | Description |
| --- | --- |
| `initialize(onConnected, onFailed)` | 서비스 연결 결과를 콜백으로 받습니다. 최초 서비스 연결이 성공하면 `onConnected`가, 초기 연결 재시도가 모두 실패하면 `onFailed`가 호출됩니다. |
| `initialize()` | 인자 없이 SDK를 초기화합니다. |

초기화가 수행되어야 다른 API들이 정상 동작합니다. 연결 상태에 대한 콜백을 제공하는 API 사용을 권장합니다.

[탭: Kotlin]

```kotlin
import ai.pleos.playground.navi.helper.NaviHelper

val naviHelper = NaviHelper(applicationContext)
naviHelper.initialize(
    onConnected = {
        // 최초 서비스 연결이 성공했습니다. 리스너를 등록하고 API를 호출합니다.
    },
    onFailed = { throwable ->
        // 초기 연결 재시도가 모두 실패했습니다.
    }
)
```

[탭: Java]

```java
import ai.pleos.playground.navi.helper.NaviHelper;
import kotlin.Unit;

NaviHelper naviHelper = new NaviHelper(getApplicationContext());
naviHelper.initialize(
    () -> {
        // 최초 서비스 연결이 성공했습니다. 리스너를 등록하고 API를 호출합니다.
        return Unit.INSTANCE;
    },
    throwable -> {
        // 초기 연결 재시도가 모두 실패했습니다.
        return Unit.INSTANCE;
    }
);
```

인자 없는 `initialize()`도 호출할 수 있습니다.

```kotlin
naviHelper.initialize()
```

### Release the SDK

SDK가 더 이상 필요하지 않으면 등록한 리스너를 제거한 후 `release()`를 호출합니다.

[탭: Kotlin]

```kotlin
override fun onDestroy() {
    naviHelper.removeListener(naviEventListener)
    naviHelper.release()
    super.onDestroy()
}
```

[탭: Java]

```java
@Override
protected void onDestroy() {
    naviHelper.removeListener(naviEventListener);
    naviHelper.release();
    super.onDestroy();
}
```

`release()` 이후 SDK를 다시 사용하려면 객체를 생성하고 초기화 절차를 다시 수행합니다.


---

## Destinations

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/destinations

목적지 설정·확인·취소 방법을 안내합니다. 결과는 `NaviHelperEventListener` 콜백으로 확인합니다.

### 목적지 설정, 확인 및 취소

`requestRoute()`로 목적지를 설정하고, `getDestinationInfo()`로 확인한 뒤, `cancelRoute()`로 취소합니다. 목적지를 다시 설정할 때도 `requestRoute()`를 호출합니다.

[탭: Kotlin]

```kotlin
// 목적지 설정
naviHelper.requestRoute(
    RouteInfo(
        latitude = 37.48544722,
        longitude = 127.03636666,
        displayLongitude = null,
        displayLatitude = null,
        poiName = "42dot",
        poiId = "36khp37hh8gqcm5nqhe5",
        poiSubId = "0",
        address = "서울시 강남구 남부순환로 2621"
    )
)

// 목적지 확인
naviHelper.getDestinationInfo()

// onDestinationInfo callback으로 데이터 수신
override fun onDestinationInfo(destinationInfo: DestinationInfo) {
    Log.d(logTag, "onDestinationInfo $destinationInfo")
}

// 목적지 취소
naviHelper.cancelRoute()
```

[탭: Java]

```java
// 목적지 설정
naviHelper.requestRoute(new RouteInfo(
        127.03636666,
        37.48544722,
        null,
        null,
        "42dot",
        "36khp37hh8gqcm5nqhe5",
        "서울시 강남구 남부순환로 2621",
        "0",
        RouteOption.RECOMMENDED
));

// 목적지 확인
naviHelper.getDestinationInfo();

// onDestinationInfo callback으로 데이터 수신
@Override
public void onDestinationInfo(DestinationInfo destinationInfo) {
    Log.d(logTag, "onDestinationInfo = " + destinationInfo);
}

// 목적지 취소
naviHelper.cancelRoute();
```

경로 탐색 과정은 다음 콜백으로 확인할 수 있습니다.

| Callback | Description |
| --- | --- |
| `onRouteStarted(routeStartInfo)` | Navigation 앱이 경로 탐색을 시작하면 호출됩니다. |
| `onRoutingCompleted()` | 경로 탐색이 완료되면 호출됩니다. |
| `onDestinationChanged()` | 목적지가 설정되거나 제거되면 호출됩니다. |
| `onError(errorCode)` | 경로 요청을 처리하는 중 오류가 발생하면 호출됩니다. |

> [INFO]
>
> `requestRoute()` 호출이 정상적으로 수행되더라도 경로 탐색 완료를 의미하지 않습니다. 최종 결과는 이벤트 콜백으로 확인합니다. 정보 조회 API는 값을 직접 반환하지 않으며, 대응하는 이벤트 콜백에서 결과를 처리합니다.

경로 옵션 변경과 재탐색은 [Routes](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/routes)를 참조합니다.


---

## Routes

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/routes

`NaviHelper` API로 경로 옵션을 변경하고 재탐색을 요청합니다. 결과는 `NaviHelperEventListener` 콜백으로 확인합니다. 목적지 설정은 [Destinations](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/destinations)를 참조합니다.

### 경로 옵션 변경

`RouteOption`은 다음 값을 제공합니다.

| Value | Description |
| --- | --- |
| `RECOMMENDED` | 추천 경로 |
| `SHORT_DISTANCE` | 최단 거리 경로 |
| `SHORT_TIME` | 최단 시간 경로 |
| `FREE` | 무료 도로 우선 경로 |
| `HIGHWAY` | 고속도로 우선 경로 |
| `COMFORTABLE` | 편안한 경로 |
| `SCHOOL_ZONE` | 스쿨존 고려 경로 |

목적지를 처음 설정할 때 `RouteInfo.routeOption`으로 지정하거나, 안내 중 `changeRouteOption()`으로 변경할 수 있습니다.

[탭: Kotlin]

```kotlin
// 목적지 설정
naviHelper.requestRoute(
    RouteInfo(
        latitude = 37.48544722,
        longitude = 127.03636666,
        displayLongitude = null,
        displayLatitude = null,
        poiName = "42dot",
        poiId = "36khp37hh8gqcm5nqhe5",
        poiSubId = "0",
        address = "서울시 강남구 남부순환로 2621"
    )
)

// 경로옵션: 고속우선으로 변경
naviHelper.changeRouteOption(RouteOption.HIGHWAY)

// onRouteOptionChanged callback으로 변경 데이터 수신
override fun onRouteOptionChanged(routeOption: RouteOption) {
    Log.d(logTag, "onRouteOptionChanged $routeOption")
}

// 목적지 취소
naviHelper.cancelRoute()
```

[탭: Java]

```java
// 목적지 설정
naviHelper.requestRoute(new RouteInfo(
        127.03636666,
        37.48544722,
        null,
        null,
        "42dot",
        "36khp37hh8gqcm5nqhe5",
        "서울시 강남구 남부순환로 2621",
        "0",
        RouteOption.RECOMMENDED
));

// 경로옵션: 고속우선으로 변경
naviHelper.changeRouteOption(RouteOption.HIGHWAY);

// onRouteOptionChanged callback으로 변경 데이터 수신
@Override
public void onRouteOptionChanged(@NonNull RouteOption routeOption) {
    Log.d(logTag, "onRouteOptionChanged = " + routeOption);
}

// 목적지 취소
naviHelper.cancelRoute();
```

### 경로 재탐색

현재 위치와 목적지를 기준으로 경로 재탐색을 요청하려면 `requestReRoute()`를 호출합니다.

[탭: Kotlin]

```kotlin
// 목적지 설정
naviHelper.requestRoute(
    RouteInfo(
        latitude = 37.48544722,
        longitude = 127.03636666,
        displayLongitude = null,
        displayLatitude = null,
        poiName = "42dot",
        poiId = "36khp37hh8gqcm5nqhe5",
        poiSubId = "0",
        address = "서울시 강남구 남부순환로 2621"
    )
)

// 경로 재탐색 요청
naviHelper.requestReRoute()

// 목적지 취소
naviHelper.cancelRoute()
```

[탭: Java]

```java
// 목적지 설정
naviHelper.requestRoute(new RouteInfo(
        127.03636666,
        37.48544722,
        null,
        null,
        "42dot",
        "36khp37hh8gqcm5nqhe5",
        "서울시 강남구 남부순환로 2621",
        "0",
        RouteOption.RECOMMENDED
));

// 경로 재탐색 요청
naviHelper.requestReRoute();

// 목적지 취소
naviHelper.cancelRoute();
```

> [INFO]
>
> 경로 옵션 변경과 재탐색은 활성 경로가 있는 상태에서 요청합니다. 처리 결과와 실제로 전달되는 콜백은 Navigation 앱의 상태에 따라 달라질 수 있습니다.


---

## Route requests with waypoints

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/route-with-waypoints

경유지를 포함한 경로 요청 방법을 안내합니다. `requestRouteWithWaypoints()`는 최종 목적지와 하나 이상의 경유지를 포함한 경로를 요청합니다. 목적지는 `RouteInfo`, 경유지는 `RequestWaypointInfo`로 구성하고, 두 값을 `RouteWithWaypointsRequest`에 전달합니다.

### 경유지를 포함한 경로 요청

[탭: Kotlin]

```kotlin
naviHelper.requestRouteWithWaypoints(
    RouteWithWaypointsRequest(
        destination = RouteInfo(
            latitude = 37.48544722,
            longitude = 127.03636666,
            poiName = "42dot",
            poiId = "36khp37hh8gqcm5nqhe5",
            address = "서울시 강남구 남부순환로 2621",
            poiSubId = "0"
        ),
        waypoints = listOf(
            RequestWaypointInfo(
                latitude = 37.41275833,
                longitude = 127.09481944,
                poiName = "판교 현대백화점",
                waypointIndex = WaypointIndex.FIRST,
                poiId = "36khp2hdj2cfcm5nqhe3",
                address = "경기도 성남시 수정구 창업로 0번길 6",
                poiSubId = "0"
            )
        )
    )
)
```

[탭: Java]

```java
import java.util.Arrays;

naviHelper.requestRouteWithWaypoints(new RouteWithWaypointsRequest(
        new RouteInfo(
                37.48544722,
                127.03636666,
                null,
                null,
                "42dot",
                "36khp37hh8gqcm5nqhe5",
                "서울시 강남구 남부순환로 2621",
                "0"
        ),
        Arrays.asList(new RequestWaypointInfo(
                37.41275833,
                127.09481944,
                "판교 현대백화점",
                WaypointIndex.FIRST,
                "36khp2hdj2cfcm5nqhe3",
                "경기도 성남시 수정구 창업로 0번길 6",
                "0"
        ))
));
```

`requestRouteWithWaypoints()`에는 `pleos.car.permission.NAVI_ROUTE_SEARCH` 권한이 필요합니다. 경로 요청의 처리 결과는 등록된 `NaviHelperEventListener`의 `onRouteStarted`, `onRoutingCompleted`, `onError` 등의 이벤트로 확인합니다.

관련 API: [`requestRouteWithWaypoints`](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.helper/navi-helper/request-route-with-waypoints), [`RouteWithWaypointsRequest`](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-with-waypoints-request/-route-with-waypoints-request), [`RouteInfo`](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/route-info/-route-info), [`RequestWaypointInfo`](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/navi-helper/ai.pleos.playground.navi.data/request-waypoint-info/-request-waypoint-info)


---

## Waypoints

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/waypoint-settings

경유지 추가·조회·제거 방법을 안내합니다. 결과는 `NaviHelperEventListener` 콜백으로 확인합니다.

### 경유지 추가 및 제거

경유지는 최대 두 개까지 지정할 수 있으며, `WaypointIndex.FIRST`와 `WaypointIndex.SECOND`로 구분합니다. `addWaypoint()`로 경유지를 추가하고, `removeWaypoint()`로 삭제합니다.

[탭: Kotlin]

```kotlin
// 목적지 설정
naviHelper.requestRoute(
    RouteInfo(
        latitude = 37.48544722,
        longitude = 127.03636666,
        displayLongitude = null,
        displayLatitude = null,
        poiName = "42dot",
        poiId = "36khp37hh8gqcm5nqhe5",
        poiSubId = "0",
        address = "서울시 강남구 남부순환로 2621"
    )
)

// 경유지 추가
naviHelper.addWaypoint(
    RequestWaypointInfo(
        latitude = 37.41275833,
        longitude = 127.09481944,
        waypointIndex = WaypointIndex.FIRST,
        poiId = "36khp2hdj2cfcm5nqhe3",
        poiName = "소프트웨어드림센터",
        poiSubId = "0",
        address = "경기도 성남시 수정구 창업로40번길 6"
    )
)

// [실측 검증 2026-11-05] 실제 메서드 이름은 onWayPointInfo(대문자 P). 아래 원문 그대로는 컴파일되지 않음
// onWaypointInfo callback으로 데이터 수신
override fun onWaypointInfo(waypointInfo: List<WaypointInfo>) {
    Log.d(logTag, "onWaypointInfo $waypointInfo")
}

// 경유지 삭제
naviHelper.removeWaypoint(WaypointIndex.FIRST)

// 목적지 취소
naviHelper.cancelRoute()
```

[탭: Java]

```java
// 목적지 설정
naviHelper.requestRoute(new RouteInfo(
        127.03636666,
        37.48544722,
        null,
        null,
        "42dot",
        "36khp37hh8gqcm5nqhe5",
        "서울시 강남구 남부순환로 2621",
        "0",
        RouteOption.RECOMMENDED
));

// 경유지 추가
naviHelper.addWaypoint(new RequestWaypointInfo(
        127.09481944,
        37.41275833,
        "소프트웨어드림센터",
        WaypointIndex.FIRST,
        "36khp2hdj2cfcm5nqhe3",
        "경기도 성남시 수정구 창업로40번길 6",
        "0"
));

// onWaypointInfo callback으로 데이터 수신
@Override
public void onWayPointInfo(List<WaypointInfo> list) {
    Log.d(logTag, "onWayPointInfo = " + list);
}

// 경유지 삭제
naviHelper.removeWaypoint(WaypointIndex.FIRST);

// 목적지 취소
naviHelper.cancelRoute();
```

현재 경유지 목록을 요청하려면 `getWaypointInfo()`를 호출합니다. 결과는 `onWayPointInfo()`로 전달됩니다. 모든 경유지를 제거하려면 `removeWaypoint(WaypointIndex.ALL)`을 사용합니다.

```kotlin
naviHelper.getWaypointInfo()

// 모든 경유지 제거
naviHelper.removeWaypoint(WaypointIndex.ALL)
```

경유지 정보가 조회되면 onWayPointInfo가 호출될 수 있습니다. 경유지에 도착하면 `onWayPointArrived()`가 호출될 수 있습니다.


---

## Event listeners

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/event-listeners

`NaviHelperEventListener`를 등록하면 API 요청 결과와 내비게이션 상태 변화를 받을 수 있습니다.

### 이벤트 종류

| Category | Callback |
| --- | --- |
| 경로 탐색 | `onRouteStarted()`, `onRoutingCompleted()`, `onRouteCancelled()`, `onReRouteEnded()`, `onRouteOptionChanged()` |
| 목적지와 경유지 | `onDestinationChanged()`, `onDestinationArrived()`, `onWaypointChanged()`, `onWayPointArrived()` |
| 정보 조회 | `onBookmarkInfo()`, `onBookMarkChanged()`, `onRecentDestinationInfo()`, `onCurrentLocationInfo()`, `onDestinationInfo()`, `onWayPointInfo()`, `onRouteStateInfo()`, `onTBTInfo()`, `onChargerOperatorInfo()`, `onTripInfo()`, `onNavigationStatus()`, `onDrivingInfo()`, `onCurrentRoadSpeedLimit()`, `onCameraAlert()`, `onCautionPointInfo()` |
| 오류 | `onError()` |

Navigation 앱의 구현 시나리오에 따라 전달되는 이벤트가 달라질 수 있습니다.

### Add a listener

필요한 콜백만 재정의하고, SDK 초기화 후 리스너를 등록합니다.

[탭: Kotlin]

```kotlin
import ai.pleos.playground.navi.constants.NaviErrorCode
import ai.pleos.playground.navi.data.RouteStartInfo
import ai.pleos.playground.navi.helper.listener.NaviHelperEventListener

private val naviEventListener = object : NaviHelperEventListener {
    override fun onRouteStarted(routeStartInfo: RouteStartInfo) {
        // 경로 탐색 시작을 처리합니다.
    }

    override fun onRoutingCompleted() {
        // 경로 탐색 완료를 처리합니다.
    }

    override fun onError(errorCode: NaviErrorCode) {
        // 내비게이션 오류를 처리합니다.
    }
}

naviHelper.addListener(naviEventListener)
```

[탭: Java]

```java
private final NaviHelperEventListener naviEventListener =
        new NaviHelperEventListener() {
    @Override
    public void onRouteStarted(RouteStartInfo routeStartInfo) {
        // 경로 탐색 시작을 처리합니다.
    }

    @Override
    public void onRoutingCompleted() {
        // 경로 탐색 완료를 처리합니다.
    }

    @Override
    public void onError(NaviErrorCode errorCode) {
        // 내비게이션 오류를 처리합니다.
    }
};

naviHelper.addListener(naviEventListener);
```

### Remove a listener

리스너가 더 이상 필요하지 않거나 SDK를 해제하기 전에, 등록할 때 사용한 동일한 리스너 인스턴스를 제거합니다.

```kotlin
naviHelper.removeListener(naviEventListener)
```

오류별 처리 방법은 [Error handling](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/error-handling)을 참조합니다.


---

## Error handling

> 원문: https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/error-handling

NaviHelper에서는 초기 서비스 연결 실패와 Navigation 앱의 요청 처리 오류가 서로 다른 경로로 전달됩니다.

### 초기화 에러

초기 연결 결과가 필요하면 `initialize()`에 콜백을 전달합니다.

```kotlin
naviHelper.initialize(
    onConnected = {
        // 리스너를 등록하고 API를 호출합니다.
    },
    onFailed = { throwable ->
        // 연결 실패 원인을 기록하거나 사용자에게 안내합니다.
    }
)
```

### 내비게이션 에러

Navigation 앱에서 발생한 오류는 `NaviHelperEventListener.onError()`로 전달됩니다.

```kotlin
override fun onError(errorCode: NaviErrorCode) {
    when (errorCode) {
        NaviErrorCode.CAUSE_LOW_BATTERY -> {
            // 배터리 부족 상태를 안내합니다.
        }
        NaviErrorCode.CAUSE_SEARCH_ROUTE_OVER_DISTANCE -> {
            // 탐색 가능 거리를 초과했음을 안내합니다.
        }
        NaviErrorCode.CAUSE_SEARCH_ROUTE_TOO_SHORT_DISTANCE -> {
            // 목적지가 현재 위치와 너무 가까움을 안내합니다.
        }
        NaviErrorCode.CAUSE_SEARCH_ROUTE -> {
            // 경로 탐색 서버 오류를 처리합니다.
        }
    }
}
```

### Error codes

| Error code | Description |
| --- | --- |
| `CAUSE_LOW_BATTERY` | 배터리가 부족합니다. |
| `CAUSE_SEARCH_ROUTE_OVER_DISTANCE` | 경로 탐색 가능 거리를 초과했습니다. |
| `CAUSE_SEARCH_ROUTE_TOO_SHORT_DISTANCE` | 현재 위치와 목적지 사이의 거리가 너무 짧습니다. |
| `CAUSE_SEARCH_ROUTE` | 경로 탐색 서버에서 오류가 발생했습니다. |

오류가 발생하면 다음 항목도 확인합니다.

- SDK 연결이 완료된 후 API를 호출했는지
- 사용하는 API에 필요한 퍼미션을 선언했는지
- 좌표, POI ID 및 경유지 인덱스가 올바른지
- 현재 Navigation 앱 상태에서 요청을 처리할 수 있는지

오류 코드의 최신 목록은 [NaviHelper API Reference](https://document.pleos.ai/docs/pleos-only/connect-sdk-pleos/NaviHelper/entry)에서 확인합니다.
