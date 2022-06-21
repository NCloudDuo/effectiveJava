# 커스텀 직렬화 형태를 고려해보라

### 먼저 고민해보고 괜찮다고 판단될때만, 기본 직렬화 형태를 사용하라.

### 객체의 물리적 표현과 논리적 내용이 같다면 기본 직렬화 형태라도 무방하다.

```java
public class Name implements Serializable{
    
    private final String lastName;
    
    private final String firstName;
    
    private final String middleName;
}
```

+ 기본 직렬화 형태가 적합하다고, 결정했더라도 불변식 보장과 보안을 위해 readObject 메서드를 제공해야 할때가 많다.


### 객체의 물리적 표현과 논리적 표현의 차이가 클 때, 기본 직렬화 형태를 사용하면 크게 네가지 면에서 문제가 생긴다.
1. 공개 API가 현재의 내부 표현방식에 영구히 묶인다.
2. 너무 많은 공간을 차지할 수 있다.
3. 시간이 너무 많이 걸릴 수 있다.
4. 스택 오버플로를 일으킬 수 있다.

