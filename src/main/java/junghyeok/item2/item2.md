# 생성자에 매개변수가 많다면 빌더를 고려하라

정적 팩터리 메서드와 생성자는 선택적 매개변수가 많다면 대응하기 어려운 동일한 문제점이 존재한다.  
점층적 생성자패턴, 자바빈즈 패턴을 활용해서 해결할 수 있겠지만 좋은방법이 아니다.

점층적 생성자 패턴은 매개변수가 많아질수록, 클라이언트가 읽기 어려운코드가 되어버린다.
자바빈즈 패턴은 세터를 이용하기 때문에, 클라이언트가 코드를 읽기는 쉽지만, 객체의 불변성을 보장할 수 없는것이 큰 단점이다.

위와 같은 단점들을 해결하기 위해서 빌더패턴이 있다.  
빌더패턴은 클라이언트가 필요한 객체를 직접 만드는 대신,
1. `필수 매개변수`만으로 생성자를 호출해 빌더 객체를 얻는다.
2. 빌더 객체가 제공하는 일종의 세터메서드들로 원하는 `선택 매개변수`들을 설정한다.
3. 매개변수가 없는 build 메서드를 호출해서 객체를 얻는다.

빌더패턴의 예시는 다음과 같다.

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    //내부 클래스
    public static class Builder{

        //필수 매개변수
        private final int servingSize;
        private final int servings;

        //선택 매개변수 - 기본값으로 초기화한다.
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val){
            this.calories = val;
            return this;
        }

        public Builder fat(int val){
            this.fat = val;
            return this;
        }

        public Builder sodium(int val){
            this.sodium = val;
            return this;
        }

        public Builder carbohydrate(int val){
            this.carbohydrate = val;
            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder){
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }
}
```

구현시 주의 깊게 볼만한 점은, 빌더의 세터 메서드들이 자기자신(Builder)를 반환해서 연쇄호출을 가능하게 한 점이다.  

NutritionFacts 의 인스턴스를 위와같이 빌더패턴으로 만들경우, 세터를 개방해놓지 않았기 때문에, 불변 객체로 활용할 수 있다.

빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.  
각 계층의 클래스에 관련 빌더를 멤버로 정의하면 된다.  `추상클래스는 추상빌더를 갖고, 구체클래스는 구체 빌더를 갖게하면된다.`

예시는 다음과 같다.

````java
public abstract class Pizza {

    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> topping;

    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // 하위 클래스는 이 메서드를 재정의해
        // this를 반환하도록 해야한다.
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        this.topping = builder.toppings.clone();
    }
}
````

Pizza.Builder 클래스는 재귀적 타입한정을 이용하는 제네릭 타입이다.  

다음 두개의 예시는 Pizza의 하위 클래스 2개를 나타내는 코드이다.  
뉴욕피자는 크기(size) 매개변수를 필수로 받고, 칼초네 피자는 소스선택(sauceInside)하는 매개변수를 필수로 받는다.

```java
public class NyPizza extends Pizza{

    public enum Size{SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder>{

        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        Pizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }
}
```

```java
public class Calzone extends Pizza{

    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder>{

        private boolean sauceInside = false; //기본값

        public Builder sauceInside(){
            sauceInside = true;
            return this;
        }

        @Override
        Pizza build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}


````

각 하위 클래스의 빌더가 정의한 build 메서드는 추상클래스의 구현 클래스를 반환하도록 선언한다.  
하위 클래스의 메서드가 상위 클래스의 메서드가 정의한 반환 타입이 아닌, 그 하위 타입(구현 클래스)을 반환하는 기능을 `공변반환` 타이핑이라 한다.

### 실제로는 Lombok을 사용하는 경우가 많기 때문에, Builder 어노테이션을 제공해줘서, 이를 활용해 코드짤 일이 있을까 싶긴한데, 어떻게 짰는지는 이 챕터를 보면 알 수 있을듯 

### 질문
1. 제네릭의 재귀적 한정타입(recursive type bound)란?
   + 타입 매개변수가 자신을 포함하는 수식에 `한정`됨을 뜻한다.
   + 주로, 타입의 자연적 순서를 정하는 Comparable 인터페이스와 함께 쓰인다.

    ```java
    <T extends Comparable<T>>
    ```
    + 위의 예시는, 유일하게 타입 T객체들을 비교할 수 있음을 보장한다.  