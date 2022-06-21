# Serializable을 구현할지는 신중히 결정하라

### 클래스의 인스턴스를 직렬화 할 수 있게 할려면, 클래스 선언에 `implements Serializable`만 덧붙이면된다.
+ 그렇지만, 신중히 고민해야한다.

### `Serializable`을 구현하면, 배포후에 수정하기 어렵다.
+ `Serializable`을 implements하면 바이트스트림도 하나의 API가 되기때문이다.
+ 커스텀 직렬화를 하지않을경우, `private`, `package-private`인스턴스 필드마저 API로 공개하게된다.

### 다 읽어봤는데, 그냥 JSON만들어서 내뱉어버리도록 하는게 맞는듯. Spring에서처럼