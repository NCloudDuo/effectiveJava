# 타입 안전 이종 컨테이너를 고려하라

### 예를들어서, 데이터베이스의 행은 `임의의 갯수` 열을 가질 수 있는데, 열을 type - safe하게 사용할 수 있으면 좋을 것이다.
+ 쉬운해법은, 컨테이너 대신 키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공하면 된다.
+ 이렇게하면, 제네릭 타입 시스템이 값의 타입이 키와 같음을 보장해줄 것이다.

### 타입 안전 이종 컨테이너 패턴

```java
import java.util.Objects;

public class Favorites {
    public <T> void putFavorite(Class<T> type, T instance);

    public <T> T getFavorite(Class<T> type);
} //API

public static void main(String[] args) {
    Favorites f = new Favorites();
    f.putFavorite(String.class, "Java");
    f.putFavorite(Integer.class, 0xcafebabe);
    f.putFavorite(Class.class, Favorites.class);

    String favoriteString = f.getFavorite(String.class);
    int favoriteInteger = f.getFavorite(Integer.class);
    Class<?> favoriteClass = f.getFavorite(Class.class);
} // 클라이언트

public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }
} //구현
```

+ Favorites인스턴스는 type-safe하다.
+ ex) String을 요청했는데, Integer를 반환할일은 없다.

+ field인 favorites Map은 key가 와일드카드 타입 중첩이라서-> 모든 키가 서로 다른 매개변수화 타입일 수 있다.
  + ex) `Class<String>`, `class<Integer>`
+ Map의 값 타입은 Object
  + 이 맵은 키와 값 사이의 타입 관계를 보장하지 않고, 자바의 타입 시스템에서는 이관계를 명시할 방법이 없다.
+ getFavorite 메서드에서 주의할점
  + 첫번째로, Map에서 값을 꺼내오면 타입이 Object
  + 이 Object 타입을 T로 type - cast한다.
+ cast메서드는 형변환 연산자의 동적버전이다.
  + 이 메서드는 단순히 주어진 인수가 Class객체가 알려주는 타입의 인스턴스인지를 검사한 다음
  + 맞다면 그 인수를 그대로 반환하고, 아니면 ClassCastException을 던진다.

### Favorites클래스의 두가지 제약이 존재한다.
+ put method에서 로우타입을 넘기면 type-safe가 깨지게된다.
  + 물론, 클라이언트 코드에서 컴파일 시 비검사 경고가 뜰 것
  + 경고말고, type-safe를 좀 더 확실히 보장하려면 다음과 같이 하면된다. (cast 메서드 활용)
  + ```java
    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }
    ```
  + cast를 활용한 실제 java api는 Collections.checkedSet, checkedList등이 있다.
+ 실체화 불가 타입에는 사용할 수 없다.
  + 예를들어 String이나 String[]은 저장가능하다
  + List<String>은 저장이 불가능하다.
  + 이 두번째 제약에 대해서는 완벽히 만족스러운 우회로는 없다.

+ Favorites클래스는 어떠한 클래스도 받아들이는데, 이를 제한하기 위해서 `한정적 타입토큰` (`한정적 타입 매개변수` 혹은 `한정적 와일드카드`를 사용)해 타입을 제한하는 타입토큰이다..

### 애너테이션 API는 한정적 타입토큰을 적극적으로 사용한다.
+ AnnotatedElement 인터페이스에 선언된 `public <T extends Annotation> T getAnnotation(Class<T> annotationType);`
+ 위 메서드는대상요소에 달려있는 애너테이션을 런타임에 읽어 오는 기능을한다.
+ 여기서 annotationType 인수는 애너테이션 타입을 뜻하는 한정적 타입토큰이다.
+ 이 메서드는 토큰으로 명시한 타입의 애너테이션이 대상요소에 달려 있다면 그 애너테이션을 반환하고, 없다면 null을 반환한다.
+ 즉, 애너테이션된 요소는 크 기가 애너테이션 타입인, 타입 안전 이종컨테이너인 것이다.

### Class<?> 타입의 객체가 있고(비한정적 와일드카드), 이를 한정적 타입토큰을 받는 메서드에 넘기려면 어떻게해야할까?
+ Class 클래스의 asSubclass 메서드를 활용하면된다.
+ 호출된 인스턴스 자신의 Class 객체를 인수가 명시한 클래스로 형변환한다.

+ asSubclass를 사용해 컴파일 시점에는 타입을 알 수 없는 애너테이션을 asSubclass 메서드를 사용해 런타임에 읽어내는 예시이다.
```java
static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName){
    Class<?> annotationType=null;
    try{
        annotationType=Class.forName(annotationTypeName);
    } cat (Exception ex){
        throw new IllegalArgumentException(ex);    
    }
    return element.getAnnotation(annotationType.asSubclass(Annotation.class))
}
```