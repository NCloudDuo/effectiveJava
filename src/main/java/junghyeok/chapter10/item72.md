# 표준 예외를 사용하라

### 표준예외를 재사용하면, API가 다른 사람이 익히고 사용하기 쉬워진다.

### 예외 클래스 수가 적을수록 메모리 사용량도 줄고, 클래스를 적재하는 시간도 적게 걸린다.

### 표준 예외들과 사용처
+ `IllegalArgumentException`
  + 호출자가 인수로 부적절한 값을 넘길 때 던지는 예외
    + 반복횟수를 지정하는 매개변수에 음수를 건넬 때
+ `IllegalStateException`
  + 대상 객체의 상태가 호출된 메서드를 수행하기에 적합하지 않을때 주로 던진다. 
    + 초기화되지 않은 객체를 사용하려 할 때
+ `NullPointerException`
  + null값을 허용하지 않는 메서드에 null을 건넬 때
+ `IndexOutOfBoundsException`
  + 시퀀스의 허용범위를 넘는 값을 건넬 때
+ `ConcurrentModificationException`
  + 단일 스레드에서 사용하려고 설계한 객체를 여러 스레드가 동시에 수정하려 할 때
+ `UnsupportedOperationException`
  + 클라이언트가 요청한 동작을 대상 객체가 지원하지 않을때