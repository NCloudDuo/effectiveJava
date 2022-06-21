# wait와 notify보다는 동시성 유틸리티를 애용하라

### wait와 notify는 올바르게 사용하기가 아주 까다로우니, 고수준 동시성 유틸리티를 사용하자.

### `java.util.concurrent`의 고수준 유틸리티는 세 범주로 나눌 수 있다.
+ ExecutorService Framework
+ Concurrent Collection
+ Synchronizer

### 동시성 컬렉션이란?
+ List, Queue, Map 같은 표준 컬렉션 인터페이스에 도잇성을 가미해 구현한 고성능 컬렉션이다.
+ 동시성 컬렉션에서 동시성을 무력화하는건 불가능하며, 외부에서 락을 추가로 사용하면 오히려 속도가 느려진다.

### 여러 기본동작을 하나의 원자적 동작으로 묶는 `상태 의존적 수정`메서드들이 추가됐다.
+ 그 대표적인예가 Map.putIfAbsent

### Collections.synchronizedMap보다는 ConcurrnetHashMap을 사용하는것이 좋다.
+ 동기화한 컬렉션보다는 동시성 컬렉션을 사용하는것이 맞다.

### 컬렉션 인터페이스중 일부는 작업이 성공적으로 완료될때까지 기다리도록 확장되었다.
+ `BlockingQueue`의 `take`메서드는 큐의 첫원소를 꺼내는데, 없다면 추가될때까지 기다린다.
  + 이러한 특성덕에 `생산자-소비자큐`로 쓰기에 적합하다.
  + N(생산자):N(소비자)

### 동기화 장치는 스레드가, 다른스레드를 기다릴 수 있게하여, 서로 작업을 조율할 수 있게 해준다.
+ 가장 자주쓰이는 동기화 장치는 `CountDownLatch`와 `Semaphore`
+ `CyclicBarrier`와 `Exchanger`는 그보다 덜 쓰인다.
+ 가장 강력한 동기화 장치는 `Phaser`

### `CountDown Latch`는 일회성 장벽으로, 하나이상의 스레드가 또 다른 하나이상의 스레드 작업이 끝날때까지 기다리게한다.
+ 유일한 생성자는 int값을 받으며, 이 값이 래치의 CountDown 메서드를 몇번 호출해야 대기중인 스레드들을 깨우는지를 결정한다.

### wait, notify 사용하지말것