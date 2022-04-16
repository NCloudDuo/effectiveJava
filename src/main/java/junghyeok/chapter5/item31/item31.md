# 한정적 와일드카드를 사용해 API 유연성을 높여라

### `List<Object>`와 `List<String>`은 아예 다른 타입이다.
+ 즉, 타입매개변수는 불공변이다.

### 그래서, 불공변 보다 유연한 무언가가 필요하다 => 한정적 와일드카드타입 

+ 이전의 Stack을 예로 들어보자.
+ ```java
    public class Stack<E>{
        public Stack();
        public void push(E e);
        public E pop();
        public boolean isEmpty();
    }
  ```
+ 여기서 pushAll을 좀 더 유연하게 추가하려면, 한정적 와일드카드를 사용해야한다.
  + ```java
     public void pushAll(Iterable<? extends E> src){ 
      for(E e : src)
          push(e);
     }
    ```
    + E의 하위타입의 Iterable (E 자기자신도 포함)
    + 위의 예시는 생산자 매개변수(src)에 와일드카드 타입을 적용한 것이다.

+ 이와 대응 되는 popAll 메서드를 작성해보자
  + ```java
    public void popAll(Collection<? super E> dst){
        while(!isEmpty()){
            dst.add(pop());
        }
    }
    ```
    + E의 상위타입의 Collection이여야한다.
    + 소비자 매개변수에 와일드카드 타입을 적용

### 메세지는 명확하다. 유연성을 극대화하기 위해서, 원소의 생산자나 소비자용 입력매개변수에 와일드카드 타입을 사용하라.
+ PECS : producer-extends, consume-super
  + 매개변수화 타입T가 생산자라면 <? extends T>를 사용
  + 소비자라면 <? super T>를 사용하라.

### 제네릭 메서드에서 반환타입은 와일드카드를 피해야한다.

### 매개변수(parameter)는 메서드 선언에 정의한 변수, 인수(argument)는 메서드 호출시 실제로 넘기는 값

### Comparable과 Comparator는 대개 소모를 하므로 <? super E>를 사용하는것이 좋다.
+ 예를들어서 Comparable을 사용하려면, 재귀적 타입한정을 사용해야한다.
+ <E extends Comparable<E>>
+ 근데 여기서 추가적으로, Comparable은 소비자이기 때문에 super를 적용해야한다.
+ <E extends Comparable<? super E>>

### 메서드를 정의할때 타입매개변수와 와일드카드를 선택하는 기준은 어떻게 되는가?
```java
public static <E> void swap (List<E> list, int i, int j);
public static void swap(List<?> list, int i, int j);
```

+ public Api라면 간단한 두번째가 낫다.
+ 기본규칙은 메서드 선언에 타입 매개변수가 `한번`만 나오면 와일드카드로 대체해야한다.
+ 사실 두번째 메서드는 private swapHelper가 필요하다. List<?>는 null이외에는 어떠한 값도 넣을 수 없기 때문이다.
```java
public static void swap(List<?> list, int i, int j){
    swapHelper(list,i,j);    
}

private static <E> void swapHelper(List<E> list, int i, int j){
    list.set(i, list.set(j,list.get(i)));    
}
```
+ swapHelper 메서드는 리스트가 List<E>임을 알 수 있으므로 값을 삽입할 수 있다.
+ 그래서 두번째 메서드는 퍼블릭 api로 공개하고, 내부에서는 swapHelper로 구현해서 제공하면된다.