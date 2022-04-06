# private 생성자나 열거 타입으로 싱글턴임을 보증하라

싱글턴(singleton)이란 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다.  
클래스를 싱글턴으로 만들면, 클라이언트가 테스트하기가 어려워질 수 있다.  
타입을 인터페이스로 정의한 다음 그 인터페이스를 구현해서 만든 싱글턴이 아니면, 싱글턴 인스턴스를 모킹 구현체로 대체할 수 없기 때문이다.

싱글턴을 만드는 방식은 보통 두가지이다.

```java
public class Elvis{
    public static final Elvis INSTANCE = new Elvis();
    
    private Elvis() {}
    
    public void leaveTheBuilding(){...}
}
```

위의 예시에서는 private 생성자는 static final 필드인 Elvis.INSTANCE를 초기화할 때 딱 한번 호출된다.  
개방된 생성자가 없기때문에, 인스턴스가 싱글턴임을 보장한다.  
예외는 리플렉션 API AccessibleObject.setAccessible을 사용해 private 생성자를 호출하는 경우를 제외한다.  
리플렉션을 통한 공격을 방어 하려면 생성자를 수정하여 두번째 객체가 생성되려 할 때 예외를 던지게 하면 된다.

```java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis(){}
    public static Elvis getInstance(){
        return INSTANCE;
    }
    public void leaveTheBuilding(){...}
}
```

static field를 private로 숨기고, public getInstance()로 항상 같은 인스턴스를 반환하도록한다.  
물론 리플렉션을 통해 객체를 생성하려 시도하는 방법을 차단해야하긴한다.

```java
public enum Elvis{
    INSTANCE;
    public void leaveTheBuilding(){...}
}
```
마지막 방법은, 원소가 하나인 열거타입을 선언하는 방법이다.  
매우 간결하고, 첫번째, 두번째 방법에서 발생하는 직렬화문제를 해결하기 위해서 추가적인 노력을 할필요도 없고, 리플렉션 공격도 막아주기 때문에 세번째 방법이 제일 좋은방법이다.