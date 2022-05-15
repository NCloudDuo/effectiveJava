# 가변인수는 신중히 사용하라

### 가변인수 메서드는 명시한 타입의 인수를 0개 이상 받을 수 있다.
+ 가변인수는 인수 개수가 정해지지 않았을 때, 아주 유용하다.
+ 성능이 민감한 상황이면, 걸림돌이 될 수 있다.
  + 가변인수 메서드는 호출될 때마다 배열을 새로 하나 할당하고 초기화한다.
  + 그래서, 성능을 위해 동일한 매개변수를 0개이상 갖는 메서드를 4개 만들어놓고, 5번째메서드에는 마지막 인수를 가변인자로 받는 것을 만들면 좋다.
    + 이렇게 할경우, 앞에 4개 메서드가 95%사용되기 때문에, 성능하락을 그나마 막을 수 있다.