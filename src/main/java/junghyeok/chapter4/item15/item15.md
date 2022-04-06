# 클래스와 멤버의 접근 권한을 최소화하라

## 모든 클래스와 멤버의 접근성을 가능한 한 좁혀야한다.

사실 오픈소스를 개발할 경우가 아니면, 그렇게 신경쓰지 않아도 될 것 같긴하다.  
그렇지만 실제 개발하면서도 접근범위를 최소화해야, 협업을 하는 사람이 함수콜을 외부해서 예상하지 않은 용도로 호출하지 않도록, 유도를 해줘야하기 때문에 중요한 챕터같기도함..

접근 가능성을 가능한 좁힌, 가장 간단한 예로는 다음과 같다.

1. 멤버변수들의 private로 선언하는 것
2. public function을 도와주는 역할의 함수는 private으로 선언해서 사용할 것

톱레벨 클래스와 인터페이스에 부여할 수 있는 접근수준은 package-private(default)과 public 두가지다.   
public으로 선언할 경우, 공개 API가되며, package-private으로 선언하면 패키지 안에서만 이용가능하다. 외부에서 사용할 일이 없다면 package-private으로 선언하는 것이 유지보수하기 좋다.

한 클래스에서만 사용하는 package-private 톱 레벨 클래스나 인터페이스는 이를 사용하는 클래스 안에 private static으로 중첩시키는 것이좋다.

대표적인 예로는 ArrayList의 SubList클래스가 해당된다. SubList는 list.subList(from, to)에서 사용되는 클래스이다.
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    
    ...

    private static class SubList<E> extends AbstractList<E> implements RandomAccess {
        
    }
}
```

그보다 더 중요한 일은 public일 필요가 없는 클래스의 접근 수준을 package-private 톱레벨 클래스로 좁히는 것도 중요하다.

## 클래스의 접근성을 설계하는 기준 

1. public인 것 선정
2. 그외에 모든 멤버는 private
3. 2번후에, 오직 같은 패키지의 다른 클래스가 접근해야 하는 멤버에 한하여 package-private(default)로 풀어주자.
4. protected는 하위클래스에서 상속을 할 경우, 접근이 가능하므로 신중하게 오픈해야한다. (default -> protected는 접근범위가 매우 커진다.)

테스트 위해서 클래스, 인터페이스, 멤버의 접근범위를 넓히려 할때는, package-private까지 풀어주고 그이상은 하면 안된다.  
테스트 대상과 테스트코드를 같은 패키지에 둬서, package-private요소에 접근할 수 있기 때문이다.

## public 클래스의 인스턴스 필드는 되도록 public이 아니어야한다
public필드로 선언되게 되면, 불변을 보장할 수없고, 필드가 수정될때 (락 획득 같은) 다른 작업을 할 수 없게 되므로 `public 가변 필드를 갖는 클래스는 일반적으로 스레드 세이프하지않다.`   
상수일경우 public static final 필드로 공개해도 좋으나, 기본타입 값이나 불변객체를 참조해야만한다.  
public static final 배열필드를 두거나, 이 멤버를 반환하는 접근자 메서드를 제공해서는 안된다. 배열의 원소를 변경할 수 있기 때문이다.

배열필드를 private로 숨기고 List를 이용해서 불변리스트를 제공하는것이 좋다.

```java
import java.util.Arrays;
import java.util.List;

class Example {
    private static final int[] PRIVATE_VALUES = {1, 2, 3, 4, 5};
    public static final List<Integer> VALUES = List.of(PRIVATE_VALUES);

    /**
     *  static <E> List<E> of(E e1) {
            return new ImmutableCollections.List12<>(e1);
        }
     */
} //List.of는 불변 객체를 반환하기 때문이다.
```

자바 9의 모듈얘기가 나오는데.. 책에서도 아직 사용하기는 이른감이 있다고한다.