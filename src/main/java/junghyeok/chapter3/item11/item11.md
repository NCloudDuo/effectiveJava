# equals를 재정의하려거든 hashCode도 재정의하라

이 아이템도 IDE에서 재정의해주긴 하지만, 원리는 알아야 좋을듯

equals를 재정의한 클래스 모두에서 hashCode도 재정의해야 한다. 그렇지 않으면 HashMap이나 HashSet 같은 컬렉션 원소로 사용할 때 문제를 일으킬 것이다.

```java
//in HashMap
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}

```

위와 같이 get메서드에서 key의 hashCode를 사용하기 때문에, 올바르게 재정의하지 않으면 HashMap, HashSet에서 올바른 동작을 하도록 기대할 수 없다.

Object 명세에서 발췌한 규약은 다음과 같다.

+ equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다.  단 애플리케이션 재실행시, 이 값이 달라져도 상관없다.
+ equals(Object)가 true를 리턴하면, 두 객체의 hashCode는 똑같은 값을 반환해야한다.
+ eqauls(Object)가 false를 리턴하더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는없다. 그러나 다른 객체에 대해서는 다른 값을 반호나해야 해시테이블의 성능이 좋아진다.

