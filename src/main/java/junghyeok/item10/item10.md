# equals는 일반 규약을 지켜 재정의하라

객체는 equals를 오버라이딩 하지 않으면, 오직 자기 자신과만 같게된다. 잘못 재정의 할 경우 끔찍한 결과를 초래하기때문에, 아예 재정의를 하지 않는것도 좋다.  

## 다음과 같은상황일 경우, 재정의 하지 않는것이 최선이다.

1. 각 인스턴스가 본질적으로 고유한 경우.
   + 값을 표현하는게 아니라 동작하는 개체를 표현하는 클래스가 여기해당. Thread가 좋은예이다
2. 인스턴스의 `논리적 동치성`을 검사할일이 없을경우
3. 상위 클래스에서 재정의한 equals가 하위클래스에도 딱 들어맞을경우
     + 대부분의 Set구현체는 AbstractSet이 구현한 equals를 상속받아쓰고, List의 구현체들은 AbstractList로부터, Map구현체들은 AbstractMap으로부터 상속받아 사용한다.
4. 클래스가 private이거나 package-private이고 equals 메서드를 호출할일이 없는경우
5. 만약 equals가 실수라도 호출되는 것을 막고싶다면 다음과 같이 재정의 해주면된다.
    ```java
    @Override public boolean equals(Object o){
        throw new AssertionError();
    }
    ```

## 재정의를 해야할때는 언제인가?
+ 객체의 논리적 동치성을 확인해야 하는데, 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을때이다.
  + 주로 값 클래스들이 여기 해당한다.
  + 값 클래스는 Integer, String처럼 값을 표현하는 클래스를 말함
    + Integer, String의 상위클래스는 Object클래스인데, Object클래스의 equals메서드는 값 비교가 아니라, 주소값을 비교하도록 되어있다.
  + equals가 논리적 동치성을 확인하도록 재정의하면, 값을 비교할 수 있게되고, Map의 키와 Set의 원소로 사용할 수 있게된다.
  + 값 클래스라 해도, 값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장된다면 equals를 재정의할 필요가 없다.
    + 인스턴스 통제 클래스, Enum이 여기에 해당한다.

## equals 메서드를 재정의할 때는 반드시 일반규약을 따라야한다.
사실 ide가, equals를 편하게 재정의해주기 때문에, 원리만 알도록 슥슥 읽으면 될듯

+ 반사성(reflexivity): null이 아닌 모든 참조 값 x에 대해, x.equals(x)는 true이다.
+ 대칭성(symmetry): null이 아닌 모든 참조 값 x,y에 대해, x.equals(y)가 true면 y.equals(x)도 true이다.
+ 추이성(transitivity): null이 아닌 모든 참조 값 x,y,z에 대해 x.equals(y)가 true이고 y.equals(z)도 true면 x.equals(z)도 true이다.
+ 일관성(consistency): null이 아닌 모든 참조 값 x,y에 대해 x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.
+ null-아님: null이 아닌 모든 참조 값 x에 대해, x.equals(null)은 false다.

## equals 메서드 구현방법
1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인. 자기자신이라면 true를반환한다.
2. instanceof 연산자로 입력이 올바른 타입인지 확인한다. 만약 입력이 올바르지 않다면 false반환
   + instanceof의 오른쪽 피연산자는 equals가 정의된 클래스 인것이 보통이지만, 가끔은 그 클래스가 구현한 특정 인터페이스가 될 수 있다.
   + Set, List, Map, Map.Entry등이 여기에 해당한다. 해당하는 예시는 아래와 같다.
   + ````java
     public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Set))
            return false;
        Collection<?> c = (Collection<?>) o;
        if (c.size() != size())
            return false;
        try {
            return containsAll(c);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
     }
     ````
3. 입력을 올바른 타입으로 형변환한다.
4. 입력 객체와 자기 자신의 대응되는 '핵심'필드들이 모두 일치하는지 하나씩 검사한다.
   + 모든 필드가 일치하면 true를, 하나라도 다르면 false를 반환한다.
   + 2단계에서 인터페이스를 사용했다면 입력의 필드 값을 가져올때도 그 인터페이스의 메서드를 사용해야한다.
   + 타입이 클래스라면 해당 필드에 직접 접근할 수도 있다. 
   + float와 double은 정적메서드 Float.compare(float, float)와 Double.compare(double, double)로 비교한다. (부동 소수점때문에)
   + float, double을 제외한 primitive type은 ==로 비교
   + 참조 타입필드는 각각의 equals메서드로 비교한다.
   + 배열의 모든 원소가 핵심필드라면 Arrays.equals 메서드들 중 하나를 사용

# 질문
1. package-private는 무엇인가?
   + 메서드나 멤버변수에 아무런 접근제어자가 붙어있지않은경우(default)
   + 같은 패키지의 다른 클래스에서만 접근이가능하다.