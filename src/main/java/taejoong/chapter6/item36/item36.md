# 비트 필드 대신 EnumSet을 사용하라

- 요약
  - 열거할 수 있는 타입을 한데 모아 집합 형태로 사용한다고 해도 비트 필드를 사용할 이유는 없다. EnumSet 클래스가 비트 필드 수준의 명료함과 성능을 제공하고 열거 타입의 장점까지 선사하기 때문이다.
  - 불변 EnumSet을 이용해야 할 경우, Collections.unmodifiableSet으로 EnumSet을 감싸 사용하자.
- 질문