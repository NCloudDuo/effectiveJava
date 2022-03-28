# 다 쓴 객체 참조를 해제하라

- 요약
  - 자바는 gc로 인해 직접 메모리를 관리하지는 않지만 신경쓰지 않아도 되는 것은 아니다.
  - 메모리 누수 주요 주범 및 방지법
    - 자기 메모리를 직접 관리하는 클래스
      - 원소를 다 사용한 즉시 그 원소가 참조한 객체들을 다 null 처리
    - 캐시
      - WeakHashMap
    - 리스너 혹은 콜백
      - weak reference
    
- java.uti.Stack의 pop()
```java
public class Stack<E> extends Vector<E> {

    // other codes ...
  
    public synchronized E pop() {
      E obj;
      int len = size();
  
      obj = peek();
      removeElementAt(len - 1);
  
      return obj;
    }
}

public class Vector<E>
        extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    
    // other codes ...

    public synchronized void removeElementAt(int index) {
      modCount++;
      if (index >= elementCount) {
        throw new ArrayIndexOutOfBoundsException(index + " >= " +
                elementCount);
      }
      else if (index < 0) {
        throw new ArrayIndexOutOfBoundsException(index);
      }
      int j = elementCount - index - 1;
      if (j > 0) {
        System.arraycopy(elementData, index + 1, elementData, index, j);
      }
      elementCount--;
      elementData[elementCount] = null; /* to let gc do its work */
    }
}


```
- 질문
  - 참조 담은 변수를 유효 범위 밖으로(?)
  - [강한 참조 vs 약한 참조](https://developer88.tistory.com/115)
