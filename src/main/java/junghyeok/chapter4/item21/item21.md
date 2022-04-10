# 인터페이스는 구현하는 쪽을 생각해 설계하라

자바 8 이전에는 기존의 구현체를 깨뜨리지 않고, 인터페이스에 메서드를 추가할 방법이 존재하지않았다.  
이를 보완하기위해, 디폴트메서드가 등장했지만, 인터페이스의 디폴트메서드는 구현체입장에서 아무것도 모른채 디폴트메서드가 삽입되는것이다.  

자바 8에서는 핵심 컬렉션 인터페이스들에 람다식을 활용하기위해서, 많은 디폴트메서드가 추가 되었다.  
라이브러리에 작성된 디폴트 메서드는 대부분 잘 동작하지만서도, 생각할 수 있는 모든상황에서 불변식을 해치지않는 디폴트 메서드를 작성하기는어렵다.

### 예를들어서, Collection의 removeIf가 default메서드로 정의되어있다.
`Apache.commons의 SynchronizedCollection`은 `Collections.synchronizedCollection` 정적 팩터리 메서드가 반환하는 클래스와 비슷하지만, 클라이언트가 제공한 객체로 락을 거는 능력을 추가적으로 제공한다.  

즉, 모든 메서드에서 주어진 락 객체로 동기화한 후, 내부 컬렉션 객체에 기능을 위임하는 래퍼클래스이다.  
근데, SynchronizedCollection 클래스는 책 작성시점에서 removeIf를 재정의하고 있지않아서, 메서드 호출을 알아서 동기화하지 않고있다.  

그래서 메서드호출시 `ConcurrentModificationException` 발생하거나 다른 예기치못한 결과로 이어질 수 있다.

### 이러한 문제점을 해결하기위해서, 자바 플랫폼라이브러리에서 일련의 조치를 취했다.
예를들어, Collections.synchronizedCollection이 반환하는 package-private 클래스들은 removeIf를 재정의하고, 이를 호출하는 다른 메서드들은 디폴트 구현을 호출하기 전에 동기화를 하도록했다.

```java
static class SynchronizedCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 3053995032091335093L;

    final Collection<E> c;  // Backing Collection
    final Object mutex;     // Object on which to synchronize

    SynchronizedCollection(Collection<E> c) {
        this.c = Objects.requireNonNull(c);
        mutex = this;
    }

    SynchronizedCollection(Collection<E> c, Object mutex) {
        this.c = Objects.requireNonNull(c);
        this.mutex = Objects.requireNonNull(mutex);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        synchronized (mutex) {
            return c.removeIf(filter);
        }
    }
}
```

그렇지만, 아닌것도 있다.

### 디폴트메서드는, 컴파일에 성공해도 기존 구현체에 런타임 오류를 일으킬 수 있으므로, 꼭 필요한 경우가 아니라면, 디폴트메서드는 피해야한다.

### 핵심은, 디폴트메서드가 추가됐다하더라도, 인터페이스의 설계는 신중해야한다.

