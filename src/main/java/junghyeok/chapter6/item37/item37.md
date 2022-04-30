# ordinal 인덱싱 대신 EnumMap을 사용하라

### enum을 key로 사용하도록 설계한 Map이 EnumMap이다.
+ ordinal method와 배열을 사용할 경우, ArrayIndexOutOfBoundsException이나 이상한 예외들이 발생할 수 있으므로 EnumMap을 사용하도록하자
  + 배열, ordinal을 사용하면, 순서가 바뀔경우 에러가 나기 쉬우며, enum에 상수가추가되면 오류가 날 가능성이 커진다.
+ Phase, Plant와 테스트코드를 작성했으므로, 이를 참고할 것