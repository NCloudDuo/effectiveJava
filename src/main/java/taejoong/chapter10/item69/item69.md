# 예외는 진짜 예외 상황에만 사용하라

- 요약
  - 예외는 오직 예외상황에서만 써야 한다. 절대로 일상적인 제어 흐름용으로 쓰여선 안된다.
  - 잘 설계된 API라면 클라이언트가 정상적인 제어 흐름에서 예외를 사용할 일이 없게 해야 한다.
    - 상태 검사 메서드, 옵셔널, 특정 값 중 하나를 선택해서 제공한다.
- 질문