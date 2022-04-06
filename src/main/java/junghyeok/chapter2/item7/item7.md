# 다 쓴 객체 참조를 해제하라

다 쓴 객체의 참조를 해제하는 방법은 null 처리를 해주는 방법이 있다.

그러나, null처리 남발은 코드를 지저분하게 만들고, 그럴필요도 없다.

다 쓴 참조를 해제하는 가장 좋은 방법은 그 참조를 담은 변수를 유효범위 밖으로 밀어내는 방법이 제일좋다.  
변수의 범위를 최소가 되게 정의한다면 자연스럽게 해결된다.  

그럼에도 불구하고, 주의해야할 곳은, 자기 메모리를 직접 관리하는 클래스 라면, 메모리 누수에 주의해야한다.

예를들면 벡터 클래스와같다.

```java
public class Vector<E>
    extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    protected Object[] elementData;
    
    ...

    public synchronized void removeElementAt(int index) {
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
        modCount++;
        elementCount--;
        elementData[elementCount] = null; /* to let gc do its work */
    }
}
```

캐시 역시 메무리 누수를 일으키는 주범이다. 객체 참조를 캐시에 넣고 나서, 이걸 잊어먹으면 객체를 다 쓴 뒤로도 한참을 그냥 나두면 메모리 누수가 발생한다.

캐시에서 백그라운드 스레드(Scheduled ThreadPoolExecutor)를 활용해서 사용하지 않는 엔트리를 청소해줘야한다.  
혹은, 부수작업을 통해서 메모리 누수를 해결하기도 한다. (LinkedHashMap의 removeEldestEntry)  

메모리 누수의 세번째 주범은 리스너혹은 콜백이다.  
클라이언트가 콜백을 등록만하고, 해지하지않으면 콜백이 계속 쌓일 것이다. 해결방법은 콜백을 약한참조(weak reference)로 저장하면 gc가 즉시 수거해간다.

# 질문사항
1. 약한참조(Weak Reference)란?
```java
WeakReference<Sample> wr = new WeakReference<Sample>( new Sample());  
Sample ex = wr.get();  
...
ex = null;  
```
위와같이, 객체를 캡슐화한 것을 WeakReference라한다.
WeakReference로만 참조된 객체를 weak reachable객체라 한다.  
gc가 동작할때, unreachable 객체뿐만아니라, weak reachable객체도 gc의 대상이된다.

더욱 자세한 내용은 네이버 d2를 참조..

[gc와 reference](https://d2.naver.com/helloworld/329631)