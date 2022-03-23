# 생성자 대신 정적 팩터리 메서드를 고려하라 

클라이언트가 인스턴스를 얻는 보편적인 방법은 public 생성자를 통해서 얻을 수 있다.  
생성자를 통해서 인스턴스를 얻는 방법 이외에도 정적 팩토리 메서드(static factory method)를 사용해서 인스턴스를 얻을 수 있다.  

다음과 같은 예시에서 정적 팩토리 메서드의 예시를 확인할 수 있다.

```java
public static Boolean valueOf(boolean b){
    return b ? Boolean.TRUE : Boolean.FALSE;    
}
```

## 정적 팩토리 메서드의 다섯가지 장점

1. 정적 팩토리 메서드는 이름을 가질 수 있기 때문에, 인스턴스의 특징을 보다 더 명확히 나타낼 수 있다.
   + BigInteger 클래스의 BigInteger(int, int, Random) 생성자와 BigInteger.probablePrime 중 후자가 소수를 반환하는 의미를 더 잘 담고 있다. 