# 스레드 안정성 수준을 문서화하라

### 문서를 작성할 일은 없기때문에, 스레드 안정성에 대한 얘기만 작성

### 스레드 안정성이 높은 순으로 나열한 것이다.
+ immutatable: 이 클래스의 인스턴스는 마치 상수와 같아서 외부 동기화도 필요없다.
  + String
  + Long
  + BigInteger
+ unconditionally thread-safe: 이 클래스의 인스턴스는 수정될 수 있으나, 내부에서 충실히 동기화하여 별도의 외부 동기화 없이 동시에 사용해도 안전
  + AtomicLong
  + ConcurrentHashMap
+ conditionally thread-safe: 일부 메서드는 동시에 사용하려면 외부 동기화가 필요하다.
  + Collections.synchronized 래퍼 메서드가 반환한 컬렉션들이 여기 속한다.
+ not thread-safe: 이 클래스의 인스턴스는 수정될 수 있다. 동시에 사용하려면 각각의 메서드 호출을 클라이언트가 선택한 외부 동기화 메커니즘으로 감싸야한다.
  + ArrayList, HashMap
+ thread-hostile: 뭔짓을 해도, 멀티쓰레드 환경에서 안전하지 않음.