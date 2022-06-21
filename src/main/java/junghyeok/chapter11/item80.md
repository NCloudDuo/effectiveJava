# 스레드보다는 실행자, 태스크, 스트림을 애용하라

### `java.util.concurrent` 패키지의 `Executor Framework`의 등장으로 태스크 실행을 간단하게 할 수 있게됐다.

### ExecutorService에서 사용할 수 있는 기능들
1. 실행자를 생성하고, 태스크를 넘기는 방법
2. 실행자를 종료시키기 (shutdown 메서드)
3. 특정 태스크가 완료되기를 기다린다. (get Method)
4. 태스크 모음 중 아무것 하나(invokeAny Method) 혹은 모든 태스크(invoke All Method)가 완료되기를 기다린다.
5. 실행자 서비스가 종료하기를 기다린다(awaitTermination Method)
6. 완료된 태크스들의 결과를 차례로 받는다(ExecutorCompletionService 이용)
7. 태스크를 특정 시간에 혹은 주기적으로 실행하게한다(ScheduledThreadPoolExecutor 이용)

### 실행자 생성의 대부분은 Executors 패키지의 정적팩터리들을 이용해 생성할 수 있다.
+ 평범하지 않은 실행자를 원한다면, `ThreadPoolExecutor` 클래스를 직접 사용해도 된다.
  + 이 클래스로는 스레드풀 동작을 결정하는 거의 모든 속성을 설정할 수 있다.

### ExecutorService를 사용하기에 까다로운 애플리케이션에서 사용할 수 있는 방법
+ 작은 프로그램이나 가벼운 서버라면 `Executors.newCachedThreadPool`이 일반적으로 좋은 선택일 것이다.
  + `CachedThreadPool`은 무거운 Production서버에는 좋지 못하다.
    + `CachedThreadPool`에서는 요청받은 태크스들이 큐에 쌓이지 않고 즉시 스레드에 위임돼 실행된다.
    + 가용한 스레드가 없다면 새로하나를 생성하기때문에, CPU 이용률이 100%로 올라갈수있다.
+ Production서버에서는 스레드 개수를 고정한 `Executors.newFixedThreadPool`을 선택하거나 완전히 통제할 수 있는 `ThreadPoolExecutor`를 직접 사용하는 편이 훨씬낫다.

### ExecutorService Framework에서는 작업단위와 실행 메커니즘이 분리된다.
+ 작업 단위를 나타내는 핵심 추상개념이 task다.
  + `Runnable`과 `Callable`이존재
  + `Callable`은 `Runnable`과 다르게, 값을 반환하고 임의의 예외를 던질 수 있다.
+ task를 수행하는 일반적인 메커니즘이 바로 `ExecutorService
+ 핵심은 ExecutorFramework가 `작업수행`을 담당해준다는 것이다.

### Executor Framework는 ForkJoin task를 지원하도록 확장됐다.
+ fork-join task는 `fork-join pool`이라는 특별한 실행자 서비스가 실행해준다.
+ ForkJoinTask의 인스턴스는 작은 하위태스크로 나뉠 수 있고, ForkJoinPool을 구성하는 스레드들이 이 태스크들을 처리하며, 일을 먼저 끝낸 스레드는 다른 스레드의 남은 태스크를 가져와 대신 처리할수도있다.