# 메서드가 던지는 모든 예외를 문서화하라

### 메서드가 던지는 예외는 그 메서드를 올바로 사용하는데 아주 중요한 정보
+ 따라서, 각 메서드가 던지는 예외를 문서화하는데, 충분한 시간을 쏟아야한다.

### checked Exception은 항상 따로따로 선언하고, 각 예외가 발생하는 상황을 자바독의 @throws 태그를 사용하여 정호가히 문서화하자.
+ 따로 따로 선언하라는 의미 => Throwable 혹은 Exception으로 퉁치지 말란 이야기.
+ 이 규칙의 예는 main 메서드. main은 오직 JVM만이 호출하므로 Exception을 던지도록 선언해도 괜ㅊ낳다.

### unchecked Exception도 checked Exception처럼 정성껏 문서화해두면 좋다.
+ 그렇지만, unchecked Exception은 `메서드 선언의 throws 목록에 넣지 말 것`
  + 이는, cheked냐 unChecked냐에 따라서, API 사용자가 해야할 일이 달라지므로