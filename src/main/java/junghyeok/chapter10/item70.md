# 복구할 수 있는 상황에는 checked Excpetion을, 프로그래밍 오류에는 Runtime Exception을 사용하라

### throwable은 checked Exception, Runtime Exception, Error를 제공한다.

### 호출하는 쪽에서 복구하리라 여겨지는 상황이라면, 검사 예외(checked Exception)를 사용하라
+ 검사 예외를 던지면, 메서드를 호출하는 쪽에서, 그 예외를 catch로 잡아 처리하거나, 더 바깥으로 전파하도록 강제하게된다.
+ 비검사 throwable(unchecked Exception)은 RuntimeException, Error가 있다.
  + `이 둘은 프로그램에서 잡을 필요가 없거나, 혹은 통상적으로 잡지 말아야한다.`
  + 프로그램에서 비검사 예외나 에러를 던졌다는 것은 복구가 불가능하거나, 더 실행해봐야 득보다는 실이 많다.
  + 이런 throwable을 잡지 않은 스레드는 적절한 오류 메세지를 내뱉으며 중단된다.

### 프로그래밍 오류를 나타낼 때는, 런타임 예외(Runtime Exception)를 사용하자.
+ Runtime Excpetion의 대부분은 전제조건을 만족하지 못했을때 발생한다.
  + ex) 배열의 인덱스는 0부터 배열의크기 -1까지

### 에러는 보통 JVM이 자원부족, 불변식 깨짐 등 더이상 수행을 계속할 수 없는 상황을 나타낼때 사용한다.
+ Error클래스를 상속해 하위클래스를 만드는일은 자제할 것
+ 즉, CustomException은 모두 RuntimeException의 하위 클래스여야한다.