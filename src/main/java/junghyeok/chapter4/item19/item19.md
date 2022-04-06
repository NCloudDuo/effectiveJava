# 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라

이 아이템을 읽어보면 상속을 그냥 쓰지않는게 맞는듯함. 신경쓸게 너무많다. 컴포지션 활용이 나을듯.

### 상속을 고려한 설계와 문서화란 무엇인가?
  + 상속용 클래스는 재정의할 수 있는 메서드들을 내부적으로 어떻게 이용하는지 문서로 남겨야한다.
    + 재정의 가능 메서드(public과 protected 메서드중 final이 아닌 모든 메서드를 의미한다.)
  + 재정의 가능 메서드를 호출할 수 있는 모든상황을 문서로 남겨야한다.
  + 재정의 가능 메서드가 `어떻게` 동작하는지를 서술하기때문에, 캡슐화를 매우 해치게된다.

### 효율적인 하위 클래스를 큰 어려움 없이 만들 수 있게 하려면?
  + 클래스의 내부 동작 과정 중간에 끼어들 수 있는 훅(hook)을 잘 선별하여 protected 메서드 형태로 공개해야 할 수도 있다.
  + 어떤 메서드를 protected 메서드로 노출해야할지는 정답이 없다.
    + 방법은 세개 정도 하위클래스를 구현해보는 것이 좋다.
      + 꼭 필요한 protected 멤버를 놓쳤다면, 하위 클래스 작성시, 그 빈자리가 확연히 드러난다.
      + 거꾸로, 하위 클래스를 여러개 만들때까지, 전혀 쓰이지 않는 protected 멤버는 사실 private여야 하는 것이다.
      + 제일 중요한것은 protected로 노출시키면, 그 메서드를 영원히 책임져야한다.

### 상속용 클래스의 생성자는 직접적으로든, 간접적으로든 재정의가능 메서드를 호출해서는 한된다.
  + 상위 클래스의 생성자가 하위 클래스의 생성자보다 먼저 실행되므로 하위 클래스에서 재정의한 메서드가 하위 클래스의 생성자보다 먼저 호출된다.
  + 이때, 하위클래스에서 재정의한 메서드가 하위 클래스의 생성자에서 초기화하는 값에 의존한다면 의도대로 동작하지 않을 것이다.
    ```java
    public class Super{
        // 잘못된 예 - 생성자가 재정의 가능 메서드를 호출한다.
        public Super(){
            overrideMe();
        }
        
        public void overrideMe(){
    
        }
    }
    
    public final class Sub extends Super{
        //초기화되지 않은 final 필드. 생성자에서 초기화한다.
        private final Instant instant;
    
        Sub(){
            instant=Instant.now();
        }
    
        // 재정의 가능 메서드. 상위 클래스의 생성자가 호출한다.
        @Override public void overrideMe(){
            System.out.println(instant);
        }
    
        public static void main(String[] args){
          Sub sub = new Sub();
          sub.overrideMe();
        }
    }   
    ```
    + 이 프로그램의 실행결과는 null, instant 출력이된다.
    + 상위 클래스의 생성자에서 overrideMe()를 호출하는데, 하위클래스에서 instant가 초기화 되지않았으므로 null
    + 하위클래스의 생성자가 호출된다음, overrideMe()를 호출하기때문에 instant 출력
    
### Cloneable과 Serializable 인터페이스는 상속용 설계의 어려움을 한층 더해준다.
+ 상속용 클래스에서 Cloneable이나 Serializable을 구현할지 정해야한다면, clone과 readObject메서드에서 재정의 가능 메서드를 호출하면안된다.

### 상속용으로 설계하지 않은 클래스는 상속을 금해야한다.
+ 상속을 금하는 방법
  + class의 final keyword붙이기
  + 생성자를 private혹은 package-private으로 만들고, 객체 생성은 정적팩터리메서드를 만들면된다.

# 질문
+ template method 패턴과 hook method
  + 실행흐름이 동일한 클래스가 있다면, 그 실행흐름을 추상화시키고, 달라지는 부분만 protected메서드로 선언한 후, 구현체에서 protected 메서드만 재정의해주면된다.
  + 주의할점은, 실행흐름이 상위 클래스가 주도한다는 것이다.
