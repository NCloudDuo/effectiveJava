# 지연 초기화는 신중히 사용하라

### 지연초기화란?
+ 필드의 초기화 시점을 그값이 처음 필요할때까지 늦추는 기법

### 대부분의 상황에서, 일반적인 초기화가 지연초기화보다 낫다.

### 지연초기화가 초기화 순환성을 깨뜨릴 것 같으면 `synchronized`를 단 접근자를 사용하자.

### 성능 때문에, `static field`를 지연 초기화해야한다면, 지연초기화 홀더 클래스 관용구를 사용하자.

```java
private static class FiledHolder{
    static final FiledType field = computeFieldValue();
}

private static FiledType getField() {return FieldHolder.field;}
```

+ getField가 처음 호출되는 순간 FieldHolder.field가 처음 읽히면서, 비로소 FieldHolder 클래스 초기화를 촉발한다.
+ 이 관용구의 멋진점은 getField 메서드가 필드에 접근하면서 동기화를 전혀 하지 않으니, 성능이 느려질 거리가 전혀 없다.

### 성능때문에 `인스턴스` 필드를 지연초기화해야 한다면, 이중검사 관용구를 사용하라.

```java
private volatile FieldType field;

private FieldType getField(){
    FieldType result= field;
    if(result!=null)
        return result;
    
    synchronized(this){
        if(field==null)
            field = computeFieldValue();
        return field;
    }
}
```
+ 필드가 초기화된 이후로는 동기화하지 않으므로, 해당필드는 반드시 volatile로 선언해야한다.

### 이따금 반복해서 초기화해도 상관없는 인스턴스 필드를 지연초기화해야 할때가 있는데, 이런경우면 이중검사에서 두번째 검사를 생략할 수 있다.