# 로 타입은 사용하지 말라

### 제네릭 타입이란?
+ 클래스와 인터페이스 선언에 타입 매개변수가 쓰인경우

### 타입 매개변수?
+ 클래스(혹은 인터페이스)이름 이후에 꺽쐬로 실제 타입을 나타낸다.
+ ex) `List<String>`

### 제네릭타입을 정의하면, 그에딸린 로타입도 함께 정의된다.
+ ex) List<E>를 정의할시, 이에 로타입은 List이다.
+ 사용하지 않는것이 좋다. 로우타입은 컴파일타임에, 타입세이프를 보장해주지 않기 때문이다.

### 왜 언어적 차원에서, 로우타입을 허용했는가?
+ 자바가 제네릭이 생기기 까지 10년이 걸렸기 때문이다.

### List와 `List<Object>`는 다르다.
+ List는 제네릭 타입에서 완전 발을 뺀 것
+ `List<Object>`는 모든 타입을 허용한다는 의사를 컴파일러에 명확히 전달한 것
+ `List<String>`은 로우타입인 List의 하위타입이지만, `List<Object>`의 하위타입이 아니다.
+ String이 Object의 하위타입이라고해서, `List<String>`이 `List<Object>`의 하위타입이아니다.

### 제네릭 타입을쓰고 싶지만, 실제 타입 매개변수가 무엇인지 신경쓰고 싶지 않을때 물음표(?)를 사용하자
+ 여기에서 물음표는 비한정적 와일드카드타입(unbounded wildcard type)을 의미한다.
  ```java
  static int numElementsInCommon(Set<?> s1, Set<?> s2){...}
  ```
+ `Set<?>와 로타입인 Set의 차이는?
  + 로타입 컬렉션에는 아무원소나 넣는것이 가능하다. => 타입 불변식을 훼손하기 쉽다.
  + Collection<?>에는 어떤 원소도 넣을 수 없다. => 다른 원소를 넣으려 하면 컴파일할때, 에러메세지를 뱉는다.
  + 컬렉션에서 꺼낼 수 있는 객체의 타입도 전혀 알 수 없게 했다.
  ```java
  Set<?> set = new HashSet<>();
  set.add(1); //컴파일오류, 삽입불가능

  Map<Integer, Integer> map = new HashMap<>();
  map.put(1, 1);
  List<?> list= new ArrayList<>(map.values());
  Object o = list.get(0); //타입을 알 수 없게했다.
  ```

### 로 타입을 사용할 수 있는 예외
+ class 리터럴에는 로타입을 사용해야한다
+ List.class, String[].class, int.class는 허용
+ List<String>.class와 List<?>.class는 허용하지 않는다.

### 두번째 예외
+ instanceof 연산자와 관련이 있다.
+ `런타임에는 제네릭 타입정보가 지워진다` => type erasure
+ 그러므로, 로타입이나, 비한정적 와일드카드 타입(`?`)이나 instanceof가 똑같이 동작한다.


### 주의점
+ 잡을수 있는오류는 가장빠르게 잡는것이좋다.
  1. 컴파일오류
  2. 스프링의 경우 어플리케이션이 뜰때 잡히는 오류
  3. 런타임오류
+ 숫자가 커질수록 늦게 알게되는 오류임으로, 빨리 알 수 있도록 코드를 수정하는것이 좋다.

## 질문
+ type erasure란?
+ 제네릭의 타입매개변수를 컴파일 타임에만 활용하고, 런타임에는 지워버리는것을 의미한다.
  + [type-erasure](https://www.baeldung.com/java-type-erasure)
