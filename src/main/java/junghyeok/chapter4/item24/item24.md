# 멤버 클래스는 되도록 static으로 만들라

### 중첩클래스란?
+ 다른 클래스 안에 정의된 클래스
+ 중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야하며, 그 외의 쓰임새가 있다면 톱레벨 클래스로 만들어야한다.
+ 중첩 클래스의 종류
  + static 멤버 클래스
  + 멤버 클래스 (내부 클래스)
  + 익명 클래스 (내부 클래스)
  + 지역클래스 (내부 클래스)

+ static 멤버 클래스
  ````java
  public class Outer {
    private final int number;

    public Outer(final int number) {
        this.number = number;
    }

    static class Inner{

        void outputOuterClassField(){
            System.out.println(new Outer(10).number);
        }
    }
  }
  ````
  + 바깥 클래스의 private멤버에도 접근할 수있다.
  + static 멤버 클래스는 흔히 바깥 클래스와 `함께` 쓰일때만 유용한 public 도우미 클래스로 쓰인다.

---

+ non static(비정적) 멤버 클래스
  + 비정적 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.
  + 비정적 멤버 클래스의 인스턴스 메서드에서 정규화된 this를 사용해 바깥 인스턴스의 메서드를 호출하거나 바깥인스턴스의 참조를 가져올 수 있다.
  + 정규화된 this?
    + 클래스명.this 형태로 바깥 클래스의 이름을 명시하는 용법
  + 따라서 개념상 중첩 클래스의 인스턴스가 바깥 인스턴스와 독립적으로 존재할 수 있다면 정적 멤버 클래스로 만들어야한다.
  + 비정적 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없기 때문이다.

  ```java
  public class Outer2 {
  
      private final int number;
  
      public Outer2(final int number) {
          this.number = number;
      }
  
      void outputNumber(){
          System.out.println(number);
      }
  
      Inner2 createInner2(){
          return new Inner2();
      }
  
      class Inner2{
  
          public void outputOuter2Number(){
              Outer2.this.outputNumber();
          }
  
      }
  
  }
  
  class Outer2Test {

    @Test
    void test(){
        Outer2 outer2 = new Outer2(10);
        Outer2.Inner2 inner2 = outer2.createInner2(); //비정적 멤버클래스 인스턴스만들기
        inner2.outputOuter2Number();
    }
  }
  ```
  
  + 비정적 멤버 클래스는 어댑터를 정의할때 자주 쓰인다.
    + Map 인터페이스의 구현체들은 자신의 컬렉션 뷰(keySet, entrySet, values)를 구현할때 비정적 멤버 클래스를 사용한다
    + Set과 List 같은 다른 컬렉션 인터페이스 구현들도 자신의 반복자를 구현할때 비정적 멤버 클래스를 주로사용한다.
  
  + 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여서 정적 멤버 클래스로 만들자.
  + static을 생략하면 바깥 인스턴스로의 숨은 외부참조를 갖게된다.
  + 이 참조를 저장하려면 시간과 공간이 소비된다.
  + 더 심각한 문제는 gc가 바깥 클래스의 인스턴스를 수거하지못해서 메모리 누수가 생길 수 있다.

---

+ private static 멤버 클래스는, 흔히 바깥 클래스가 표현하는 객체의 한부분을 나타낼때 쓴다.
  + Map의 Entry객체가 예시
  + Entry는 Map을 직접 사용하지 않으니, 비정적 멤버클래스는 낭비
  + private 정적 멤버 클래스가 가장 알맞다.

---

+ 익명 클래스는 멤버와 달리, 쓰이는 시점에 선언과 동시에 인스턴스가 만들어진다.
  + 람다가 이를 대체하게 됐으니, 굳이 사용을 안해도될듯하다.

+ 지역 클래스 
  + 가장 드물게 사용된다.
  ```java
  public class Outer3 {

    class Inner3{
        private final int number;

        public Inner3(int number) {
            this.number = number;
        }
    }
  }
  ```
