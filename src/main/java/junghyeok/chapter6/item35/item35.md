# ordinal 메서드 대신 인스턴스 필드를 사용하라
+ enum에 선언된 상수를 index로 mapping시키는 ordinal method는 사용하면안된다.
  + enum상수의 선언 순서가 바뀌면, 오류를 내기 쉽기 때문
  + 그렇기때문에, 상수를 저장할 일이 있다면, enum상수의 필드로 저장을 해야한다.
  + ordinal method는 EnumSet과 EnumMap을 위한 메서드이다.