# 익명 클래스보다는 람다를 사용하라

- 요약
  - 예전에는 자바에서 함수 타입을 표현할 때 추상 메서드를 하나만 담은 인터페이스(드물게는 추상 클래스)를 사용했다. 이런 인터페이스의 인스턴스를 함수 객체라고 하였다. 익명 클래스 방식은 코드가 너무 길기 때문에 자바는 함수형 프로그래밍에 적합하지 않았다.
  - 자바 8에 와서 추상 메서드 하나짜리 인터페이스는 특별한 의미를 인정받아 특별한 대우를 받게 되는데, 지금은 함수형 인터페이스라 부르는 이 인터페이스들의 인스턴스를 람다식을 사용해 만들 수 있게 된 것이다.
  - 함수형 인터페이스 관계
    - DoubleBinaryOperator
      - Represents an operation upon two double-valued operands and producing a double-valued result. This is the primitive type specialization of `BinaryOperator` for double.
    - BinaryOperator
      - Represents an operation upon two operands of the same type, producing a result of the same type as the operands. This is a specialization of `BiFunction` for the case where the operands and the result are all of the same type.
    - BiFunction
      - Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of `Function`.
    - Function
      - Represents a function that accepts one argument and produces a result.
  - 람다는 이름이 없고 문서화도 못해서 코드 자체로 동작이 명확히 설명되지 않거나 코드 줄 수가 많아지면 람다를 쓰지 말아야 한다.
  - 람다의 시대가 열리면서 익명 클래스는 설 자리가 크게 좁아진게 사실이다. 하지만 람다로 대체할 수 없는 곳이 있다. 람다는 함수형 인터페이스에서만 쓰인다.익명 클래스는 함수형 인터페이스가 아닌 타입의 인스턴스를 만들 때만 사용해라.
    - 추상 클래스의 인스턴스를 만들 때
    - 추상 메서드가 여러 개인 인터페이스의 인스턴스를 만들 때
    - 함수 객체가 자신을 참조해야 할 경우(람다는 자신을 참조할 수 없다. 람다에서의 this는 바깥 인스턴스를 가르킨다.)
- 질문