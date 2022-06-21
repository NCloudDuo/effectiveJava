# 공유중인 가변 데이터는 동기화해 사용하라

### `synchronized` 키워드는 해당 메서드나 블록을 한번에 한 스레드씩 수행하도록 보장한다.

### `동기화`? => 배타적실행 + 스레드가 같은 락의 보호하에 수행된 최종 결과를 보게해줌

+ 배타적실행
    + 한 스레드가 변경하는중이라서, 상태가 일관되지 않은 순간의 객체를 다른 스레드가 보지 못하게 막는용도

### 동기화는 배타적 실행뿐 아니라, 스레드 사이의 안정적인 통신에 꼭 필요하다.

+ 한 스레드가 만든 변화가, 다른 스레드에게 `언제`, `어떻게` 보이는지가 매우 중요
+ 공유중인 가변 데이터를 비록 원자적으로 읽고 쓸 수 있을지라도, `어떻게 보이는지`가 달라지기 때문에 동기화를 하지 않으면 매우 위험하다.

```java
import java.util.concurrent.TimeUnit;

//잘못된 코드
public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) {
        new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

### 위의 예시는 1초후에 메인스레드에서 stopRequested를 true로 설정해서, 반복문을 빠져나올것 처럼보이지만 실제로는 그렇지않다.
+ 원인은 동기화에 있다.
+ 동기화가 빠지면 JVM이 다음과 같은 최적화를 수행할 수도 있다.
```java
if(!stopRequested){
    while(true){
        i++;
    }
}
```

### 이를 해결하는 방법
+ stopRequested를 true로바꾸고, 그 값을 반환하는 메서드를 각각 만들어(쓰기와 읽기메서드를 만듦) `synchronized` 키워드를 붙여주는 것
  + 쓰기와 읽기 메서드 둘 다 동기화를 해주어야함
+ stopRequested 값을 `volatile`로 선언
  + `volatile` 키워드는 가장 최근에 기록된 값을 읽게 보장한다.
  + 그렇지만, `++`연산자 사용시 주의해야한다.
    + 실제로 코드 한줄로보이지만, 먼저 값을 읽고, 값을 증가시키는 것이기 때문에, 그사이에 인터럽트가 걸릴 경우 유일한 정수값을 만드는데 실패한다.
+ `java.util.concurrent.atomic.AtomicLong`사용하기

### 제일좋은 방법
+ 가변데이터를 공유하지 않는것
+ 만약 가변데이터를 사용해야한다면, 단일쓰레드에서만 사용하자