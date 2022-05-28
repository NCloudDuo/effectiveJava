# 객체는 인터페이스를 사용해 참조하라

### 적합한 인터페이스만 있다면 매개변수뿐 아니라 반환값, 변수, 필드를 전부 인터페이스 타입으로 선언할 것

```java

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>(); // OK
        HashSet<String> stringHashSet = new HashSet<>(); //X
    }
}

```

### 인터페이스 타입을 사용해야지, 변경사항에 유연해진다. 

### 주의점
+ 인터페이스의 해당 구현체의 로직이 필요할 경우는 구현체로 사용해야한다.
  + ex) 순서가 보장되야하는 Set이라면 LinkedHashSet을 사용해야함

### 적합한 인터페이스가 없다면, 클래스 계층구조 중 필요한 기능을 만족하는 가장 덜 구체적인 클래스를 타입으로 사용하자.