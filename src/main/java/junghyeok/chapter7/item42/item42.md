# 익명클래스보다는 람다를 사용하라

### 함수객체란?
+ `추상메서드를 하나`만 담은 인터페이스의 인스턴스
+ 특정 함수나 동작을 나타내는데 사용

### JDK1.1이 등장하면서, 함수 객체를 만드는 주요 수단은 익명클래스
+ 익명클래스 방식은, 코드가 너무 길기 떄문에, 적합하지 않다.

### 자바8부터, 추상메서드를 하나만 담은 인터페이스를 `함수형 인터페이스`라한다.
+ 함수형 인터페이스의 인스턴스를 람다식을 사용해 만들 수 있다.
+ 훨씬 더 간단해진다.

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = List.of("abc", "zxcqweqwe", "hihihi");
        Collections.sort(words, (s1,s2)-> Integer.compare(s1.length(), s2.length()));
    }
}
```
+ `(s1,s2)-> Integer.compare(s1.length(), s2.length())`이 람다식을 이용해서 함수형 인터페이스의 인스턴스를 만든부분
+ 컴파일러가 타입추론을 해주기떄문에, 컴파일 오류가 나는경우가아니라면, 타입을 생략해야한다.

위의 코드를 다음과 같은방법(비교자 생성메서드, List인터페이스에 추가된 sort메서드)들을 이용하면 좀 더 깔끔하게 정리가 가능하다.

```java
Collections.sort(words,comparingInt(String::length));
words.sort(comparingInt(String::length));
```

### 람다는 이름이없고 문서화도 못한다. 따라서 코드 자체로 동작이 명확히 설명되지 않거나, 코드 줄 수가 많아지면 람다를 쓰지 말아야한다.
+ item37의 Phase의 map을 만들때 사용하던방식이면, 좀 자제해야하지 않을까..

### 람다는 함수형 인터페이스에서만 쓰인다.
+ 추상클래스의 인스턴스를 만들때 람다를 쓸 수 없다. 즉 추상클래스의 인스턴스를 만들기 위해서는 익명클래스를 사용해야한다.
+ 추상 메서드가 여러개인 인터페이스(즉 함수형 인터페이스가 아닌것)를 만들때도, 익명클래스를 쓸 수 있다.
+ 람다는 자신을 참조할 수 없다.
  + 람다식에서의 this는 바깥인스턴스
  + 익명클래스에서는 자기자신을 가리킨다.
  + 그래서 함수객체가 자신을 참조해야한다면, 반드시 익명클래스 사용

### 람다도 직렬화 형태가 구현별로 다를 수 있어서, 람다를 직렬화 하는일은 하지말아한다.