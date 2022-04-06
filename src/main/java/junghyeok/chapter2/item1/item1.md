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
   + 하나의 시그니쳐로는 생성자를 하나만 만들 수 있다. 이 경우, 생성자는 메서드 이름이 클래스 이름으로 고정되어 버리므로, 한 시그니쳐로 다른 의미의 인스턴스를 생성할 수 없다.
     + 정적 팩토리 메서드로는 시그니쳐가 같아도, 메서드 이름을 변경하면 다른의미의 인스턴스를 반환함을 암시할 수 있다.

2. 호출될 때마다 인스턴스를 새로 생성하지`는` 않아도 된다.
   + 인스턴스를 미리 만들어놓고, 정적 팩토리 메서드로 인스턴스를 요청할때마다 동일한 인스턴스를 돌려주도록 할 수 있다. (인스턴스 통제)

3. 반환타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
    + 예시는 다음과 같다.
   ```java
   interface Super{
        static Super getSubA(){
            return new SubA();    
        }    
   
        static Super getSubB(){
            return new SubB();
        }
   }
   
   class SubA implements Super{
   
   }
   
   class SubB implements Super{
   
   }
   ```
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
   + EnumSet 클래스에서는 원소 수에 따라 RegularEnumSet 인스턴스 혹은 JumboEnumSet 인스턴스를 반환한다.
   ```java
   public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
        Enum<?>[] universe = getUniverse(elementType);
        if (universe == null)
            throw new ClassCastException(elementType + " not an enum");

        if (universe.length <= 64)
            return new RegularEnumSet<>(elementType, universe);
        else
            return new JumboEnumSet<>(elementType, universe);
    }
   
   class RegularEnumSet<E extends Enum<E>> extends EnumSet<E> {
    ...
   }
   
   class JumboEnumSet<E extends Enum<E>> extends EnumSet<E> {
    ...
   }
   ```
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
   + Jdbc에서, connection을 얻을때, 구현체를 몰라도, 리플렉션을 통해서 얻을 수 있음을 의미하는 듯한데.. 자세히는 모르겠음.
   ```java
   // DriveManager class
   @CallerSensitive
   public static Connection getConnection(String url,
    String user, String password) throws SQLException {
        java.util.Properties info = new java.util.Properties();
        if (user != null) {
            info.put("user", user);
        }
        if (password != null) {
            info.put("password", password);
        }
        return (getConnection(url, info, Reflection.getCallerClass()));
   }
   ```
## 정적 팩토리 메서드의 두가지 단점

1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드`만` 제공하면 하위 클래스를 만들 수 없다.
   ```java
   public class SuperClass {
    
    public static SuperClass of(){
        return new SuperClass();
    }
    
    private SuperClass(){
        
    }
   }
   
   public class SubClass extends SuperClass{ // 이 extends가 안된다. 상위클래스의 생성자가 private이기 때문에
   
   }

   ```
   + 이는 상속을 이용하지 않고 컴포지션으로 유도하도록해서 오히려 장점일 수 있다.
2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
   + 잘 알려진 규약들로 정적 팩터리 메서드를 만드는 것이 좋다.
     + of, valueOf, from, getInstance 등등