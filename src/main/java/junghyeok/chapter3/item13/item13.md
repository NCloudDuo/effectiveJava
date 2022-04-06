# clone 재정의는 주의해서 진행하라

이 아이템은.. 아이템 마지막의 주요부분만 정리하고 넘어갑니다.  

--- 
Cloneable을 이미 구현한 클래스를 확장한다면 어쩔 수 없이 clone을 잘 작동하도록 구현해야한다.
즉, Cloneable을 구현한 모든 클래스는 clone을 재정의해야한다.  

그렇지 않은 경우에는 복사 생성자와 복사 팩터리 메서드를 이용해서 객체를 복사하는것이 좋다.

```java
public TreeSet(Collection<? extends E> c) {
    this();
    addAll(c);
}

public  boolean addAll(Collection<? extends E> c) {
    // Use linear-time version if applicable
    if (m.size()==0 && c.size() > 0 &&
        c instanceof SortedSet &&
        m instanceof TreeMap) {
        SortedSet<? extends E> set = (SortedSet<? extends E>) c;
        TreeMap<E,Object> map = (TreeMap<E, Object>) m;
        Comparator<?> cc = set.comparator();
        Comparator<? super E> mc = map.comparator();
        if (cc==mc || (cc != null && cc.equals(mc))) {
            map.addAllForTreeSet(set, PRESENT);
            return true;
        }
    }
    return super.addAll(c);
}
```

복사생성자를 사용한 예시이다.  
이 방법의 장점은 해당클래스가 구현한 인터페이스 타입의 인스턴스를 인수로 받을 수 있다는 점이다.

예컨데 HashSet 객체 s를 TreeSet 타입으로 복제할 수 있다.