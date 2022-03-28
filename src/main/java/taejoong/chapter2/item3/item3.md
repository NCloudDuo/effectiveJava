# private 생성자나 열거 타입으로 싱글텀임을 보증하라

- 요약
    - 싱글턴 : 인스턴스를 오직 하나만 생성할 수 있는 클래스
    - 싱글턴을 만드는 방법
      - private 생성자 + public static final 필드
      - private 생성자 + private static final 필드 + 정적 팩터리 메서드
      - 열거 타입 선언
    - 싱글턴 관련 이슈
      - 직렬화
      - 리플렉션 공격
- 질문
  - [transient 선언](https://nesoy.github.io/articles/2018-06/Java-transient)