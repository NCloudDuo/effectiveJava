# 표준 함수형 인터페이스를 사용하라

- 요약
  - 자바가 람다를 지원하면서 API 작성시 템플릿 메서드 패턴보다는 함수 객체를 매개변수로 받는 정적 팩터리나 생성자를 제공하는 것이 낫다. 
  - 필요한 용도에 맞는게 있다면, 직접 구현하지 말고 표준 함수형 인터페이스를 활용하라. 
  - 기본 함수형 인터페이스(6개)

  인터페이스       | 함수 시그니처          | 예
  ------------- |---------------------| ---
  UnaryOperator | T apply(T t)        | String::toLowerCase
  BinaryOperator| T apply(T t1, T t2) | BigInteger::add
  Predicate     | boolean test(T t)   | Collection::isEmpty
  Function      | R apply(T t)        | Arrays::asList
  Supplier      | T get()             | Instant.now()
  Consumer      | void accept(T t)    | System.out::println

  - 다음의 세 가지 중 하나 이상을 만족한다면 전용 함수형 인터페이스를 구현해야하는 건 아닌지 고민해볼 필요가 있다.
    - 자주 쓰이며, 이름 자체가 용도를 명확히 설명해준다.
    - 반드시 따라야하는 규약이 있다.
    - 유용한 디폴트 메서드를 제공할 수 있다.
  - @FunctionalInterface 애너테이션을 사용하는 이유
    - 인터페이스가 람다용으로 설계된 것임을 명시
    - 해당 인터페이스가 추상 메서드를 오직 하나만 가질 수 있도록 강제
    - 실수로 메서드 추가하는 일을 방지
  - 서로 다른 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중 정의해서는 안된다. 클라이언트에게 불필요한 모호함만 안겨줄 뿐이다.
- 질문