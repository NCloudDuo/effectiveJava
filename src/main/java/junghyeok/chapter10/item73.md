# 추상화 수준에 맞는 예외를 던지라

### 수행하려는 일과 관련없어 보이는 예외가 튀어나오면, 당황스러울 것이다.
### 메서드가 lowlevel Exception을 처리하지 않고, 바깥으로 전파해버릴때, 종종 일어나는 일이다.

### 상위계층에서는, lowlevel Exception을 catch해서 자신의 추상화 수준에 맞는 예외로 바꿔 던져야한다.
+ 이를 예외 번역(exception translation)이라 한다.

```java
try{
    //저 수준 추상화를이용한다.    
}catch(LowerLevelException e){
    throw new HighterLevelException(...);    
}
```

### 다음은 List 인터페이스의 골격구현 클래스인 AbstractSequentialList에서 수행하는 `exception tranlation`의 예이다.
```java
//AbstractSequentialList의 get 메서드
public E get(int index){
    ListIterator<E> i = listIterator(index);
    try{
        return i.next();    
    } catch(NoSuchElementException e){
        throw new IndexOutOfBoundsException("인덱스: "+ index);    
    }
}
```

### 예외 번역시에, 기존 익셉션을, 새로 던지려는 익셉션의 마지막 파라미터에 넣어서 익셉션을 던지는것을 예외 연쇄(exception chaining)라 한다.
+ lowLevel Exception이 디버깅에 도움이 될 때 사용
+ `Throwable`의 `getCause 메서드`를 통해 lowLevel Exception을 꺼내볼 수 있다.
```java
//예외 연쇄의 예
try{
    restTemplate.exchange("파라미터넣었다고 가정합니다.");    
}catch(RestClientException e){
    throw new CustomException("통신중에 오류가발생했습니다.", e);
}
```

### 무턱대고, 예외를 전파하는 것보다는 exception translation이 우수한 방법이지만, 그렇다고 남용해서는 곤란하다.
+ 가능하다면, 저수준 메서드가 반드시 성공하도록하여, 아래 계층에서는 예외가 발생하지 않도록 하는 것이 최선이다.

### 차선책은, 아래 계층에서의 예외를 피할 수 없다면, 상위 계층에서 logging을 활용해 기록해두고, 조용히 처리하는 방법이 있다.

## 읽어보고 드는 Spring Framework에서의 Excpetion Translation의 예시
+ @ExceptionHandler에서 발생할 익셉션을 지정해놓고, CustomExcpetion으로 추상화해서 던지는게, 자신의 추상화수준에 맞게 던지는 예 인듯
+ 혹은 spring에서 db 접근시 `DataAccessException`로 추상화 한것이라든가.. 