# 멤버 클래스는 되도록 static으로 만들라

- 요약
  - nested class
    - static member class : 바깥 클래스의 private 멤버에도 접근할 수 있다. 보통 바깥 클래스와 함께 쓰일 때만 유용한 public 도우미 클래스로 사용된다.
    - inner class
      - non-static member class
      - anonymous class : 코드의 어디서든 만들 수 있으며, 비정적인 문맥에서만 바깥 클래스의 인스턴스를 참조할 수 있다.
      - local class : 지역변수를 선언할 수 있는 어느 곳이든 선언이 가능하고, 유효 범위도 지역변수와 같다.
  - static member class와 non-static member class는 유의미한 차이가 존재하는데, non-static member class는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.
  - 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여서 정적 멤버 클래스로 만들자. static을 생략하면 바깥 인스턴스로의 숨은 외부 참조를 갖게 된다. 즉, 참조를 저장하기 위한 시간과 공간이 소비된다.
- 질문
  - json을 deserialize하는 requestDto등을 만들 때 어떤식으로 사용하는지? static 여부 등의 관점 + 나의 예를 들어서...