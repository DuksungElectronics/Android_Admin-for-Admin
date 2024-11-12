# 무인매장 솔루션 관리자용 어플리케이션
> 무인매장 관리자를 위한 어플리케이션입니다.

## :smiley: 개발자 
<table>
  <tr>
    <td align="center">
      <strong>박해인</strong><br>
      <img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/5ff6986f-2767-44e6-9f41-bcb525d0361f" width="160"/><br>
      <a href="https://github.com/femmefatalehaein">@femmefatalehaein</a>
    </td>
    <td align="center">
      <strong>이수진</strong><br>
      <img src="https://github.com/user-attachments/assets/4da94270-5034-4152-a626-753d9f7b90a1" width="160"/><br>
<a href="https://github.com/Soojin-Lee-01">@Soojin-Lee-01</a>
    </td>
  </tr>
</table>


## 사용스택
<img src="https://img.shields.io/badge/AndroidStudio-green?style=for-the-badge&logo=AndroidStudio&logoColor=white"><img src="https://img.shields.io/badge/JAVA-orange?style=for-the-badge&logo=JAVA&logoColor=white"><img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=Firebase&logoColor=white">



## 주요기능

#### 1. 실시간 매장 내 수량 확인 기능 

<img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/73a7e3fb-e1b3-45da-a7d3-e50431d95a11"  width="200" />


   
#### 2. 도난의심 상황 발생시, 해당 시간대의 편집된 영상제공 ⇀ 도난상황 발생 시 빠른 대처가능

<img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/b0137c1f-3045-4118-9863-8e1280de16b3"  width="200" />
<img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/5900add2-7819-4e8a-bd23-b18795cce8f4"  width="194" />

   
#### 3. 고객 출입시간 확인 기능

<img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/f7ea391b-6558-4bb0-9156-c4a6c01ca3f6"  width="200" />

## Hard Things 

#### 실시간 도난의심상황 실시간 UI 반영 && 실시간 도난의심상황 알림 (안드로이드 스튜디오 생명주기와 상관없이)
<img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/6ff72fbc-628c-4d7d-932c-8babb5c72a86"  width="800" />
<img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/29cceabd-43d1-4f5a-84f9-bdc1336d70d8"  width="800" />
<img src="https://github.com/DuksungElectronics/Android_Admin/assets/75514808/38b49260-e932-44e3-aa9e-53b25a4046fc"  width="800" />


> Android Studio의 컴포넌트인 `Fragment`와 `Activity`의 차이점을 정확히 이해하고 사용자 친화적인 ui를 포기하지 않고 복잡한 과정이지만 이해하고 구현하였습니다.

→ 사용자에게 한 눈에 들어오는 UI를 제시하기 위해 `Activity` 내에 여러 `Fragment`를 배치하는 방식으로 탭메뉴를 구성하였습니다.

→ 하지만 `Fragmemt`는 `Activity`의 생명주기에 의존하기 때문에 실시간 데이터를 표시하기 위해 `Service`컴포넌트를 만들어 실시간으로 데이터를 수렴하게 구현하였습니다.

→ `foregroundService`의 `startForeground()` 메서드로 백그라운드에서도 계속 실행될 수 있도록 설정되기 때문에, `ForegroundService`는 시스템에 의해 종료되거나 명시적으로 `stopSelf()`가 호출될 때까지 종료되지 않도록 동작하게끔 하여, 어플이 종료되더라도, `foregroundSerivice` 는 종료되지 않아 실시간 연결이 유지되며, 도난의심상황이 발생하였을때 실시간으로 알림을 발생시킵니다. 

→ `fragment`로 보내 표시하기 위해, `Service`에서 서버와 통신해서 받은 데이터를 `Broadcast Receiver`를 통해 받아 실시간으로 UI를 재정의합니다.




