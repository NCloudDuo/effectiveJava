# 생성자 대신 정적 팩터리 메서드를 고려하라

- 요약
  - 클라이언트가 특정 클래스의 인스턴스를 얻는 방법
    - `생성자`
    - `정적 팩터리 메서드`
      - 장점
        1. 이름을 가질 수 있다.
        2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
        3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
        4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
        5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
      - 단점
        1. 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다. => 상속을 하려면 public이나 protected 생성자가 필요하기 때문
        2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
      - 네이밍
        - from
        - of
        - valueOf
        - instance / getInstance
        - create / newInstance
        - getType
        - newType
        - type
- 질문
  - 이펙티브 자바에서 말하는 **정적 팩터리 메서드**는 디자인 패턴에서의 **팩터리 메서드**와는 다르다?
    - 정적 팩터리 메서드
    - 팩터리 메서드
  - 플라이웨이트 패턴
  - [상속 vs 컴포지션](https://iyoungman.github.io/designpattern/Inheritance-and-Composition/)
  - [자바 8 interface 변경사항](https://beomseok95.tistory.com/272)
    - [funtional interface](https://beomseok95.tistory.com/277)
  - 서비스 인터페이스 등의 여러 컴포넌트 간의 관계 + 예시
  - 브리지 패턴
  - [ServiceLoader](https://alkhwa-113.tistory.com/entry/ServiceLoader)