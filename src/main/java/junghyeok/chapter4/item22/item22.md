# 인터페이스는 타입을 정의하는 용도로만 사용하라

### 인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.
```java
interface Sup{
    
}

class Sub implements Sup{
    
}

class Main{
    public static void main(String[] args) {
        Sup Sup = new Sub(); //자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입역할을한다.
    }
}
```

### 이 지침에 맞지않는 예로 상수 인터페이스가 있다.
메서드없이, 상수를 뜻하는 static final 필드로만 가득 찬 인터페이스를 말한다.   

이것은 안티패턴이다. 이렇게 사용하지말고, 상수용 유틸리티클래스를 정의해서 사용하는것이 좋다.

```java
public class PhysicalConstants{
    
    private PhysicalConstants(){} //인스턴스화 방지
    
    public static final double AVOGADROS_NUMBER=6.022_140_857e23;
}
```
