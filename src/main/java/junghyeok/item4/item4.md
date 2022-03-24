# 인스턴스화를 막으려거든 private 생성자를 사용하라

정적메서드, 정적필드만을 담고있는 유틸리티 혹은 상수용 클래스는 private생성자를 이용해서 인스턴스화를 방지하는게 좋다.

````java
public class Constants{
    public static final int EXAMPLE_CONSTANT=1;
    
    private Constants{
        
    }
}
````

```java
public class ValidationUtil{
    
    public static final void stringIsEmpty(String string){
        if(string.isEmpty()){
            throw new IllegalStateException("스트링이 비어있습니다.");
        }
    }
    
    private ValidationUtil{
        
    }
}
```

위 두 클래스들과 같이 스태틱변수, 스태틱함수만을 사용한다면 생성자를 private로 설정해서 인스턴스화를 방지하는것이 좋다.