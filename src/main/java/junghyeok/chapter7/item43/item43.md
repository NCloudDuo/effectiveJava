# 람다보다는 메서드 참조를 사용하라

### 익명클래스에서 람다로 넘어오면서 간단해졌지만, 이보다 더 간단한방법이 있는데, 바로 메서드참조이다.

```java
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    Map<String, Integer> map = new HashMap<>();
    map.merge("hi", 1, (count, incr) -> count + incr);
    System.out.println(map.get("hi")); //1출력
    map.merge("hi", 1, (count, incr) -> count + incr);
    System.out.println(map.get("hi")); //2출력
    map.merge("hi", 1, Integer::sum);
    System.out.println(map.get("hi")); //3출력
  }
}
```
+ merge함수는 java8이후에 Map인터페이스에 도입된함수
+ 키, 값, 함수를 인수로 받으며, 주어진 키가 맵 안에 아직 없다면 [키, 값] 쌍을 저장한다.
  + 위에 예시에서 첫번째 merge함수 call은 map에 `"hi"` String이 없기때문에, ["hi",1]이 들어가는 것
+ 키가 있다면? 세번째 인수로받은 함수를 현재 값과 주어진 값(두번째 인수)에 적용한 다음, 그 결과로 현재 값을 덮어쓴다.
  + 두번째 merge함수 call에서는 이미 ["hi",1]이 존재하므로, 기존에 있던 값 1에다가 1을 더해주는 격이된다.
+ 세번째는 람다식을 메서드 참조를 사용해서 간단하게 표현한 것이다.

### 람다가, 매개변수의 이름도 지을 수 있어서 읽기 쉽고, 유지보수도 쉬울수있다.
+ 메서드 참조쪽이 짧고 명확하다면 메서드 참조를 사용하고, 그렇지 않으면 람다를 사용할 것

### 메서드 참조의 유형은 다섯가지
1. 정적메서드 참조
   + Integer::parseInt 
   + str -> Intger.parseInt(str) (람다식)
2. 한정적(인스턴스) : 수신객체를 특정
   + Instant.now()::isAfter 
   + Instant then = Instant.now(); t->then.isAfter(t); (람다식)
3. 비한정적(인스턴스) : 수신객체를 특정하지 않음
   + String::toLowerCase
   + str -> str.toLowerCase() (람다식)
4. 클래스 생성자 : 팩터리 객체로 사용
   + TreeMap<K,V>::new
   + () -> new TreeMap<K,V>() (람다식) 
5. 배열 생성자 : 팩터리 객체로 사용
   + int[]::new
   + len -> new int[len] (람다식)
