# 비검사 경고를 제거하라

- 요약
  - 비검사 경고는 중요하니 무시하지 말자. 모든 비검사 경고는 런타임에 ClassCastException을 일으킬 수 있는 잠재적 가능성을 뜻하니 최선을 다해 제거하라. 경고를 없앨 방법을 찾지 못하였다면 그 코드가 타입 안전함을 증명하고 가능한 한 범위를 좁혀 @SuppressWarnings("unchecked") 애너테이션을 달아 경고를 숨기자. 그런 다음 경고를 숨기기로 한 근거를 주석으로 남겨라
  - 경고 종류
    - 비검사 형변환 경고
    - 비검사 메서드 호출 경고
    - 비검사 매개변수화 가변인수 타입 경고
    - 비검사 변환 경고