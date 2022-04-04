# clone 재정의는 주의해서 진행하라

- 요약
  ```java
  package java.lang;

  public class Object {
  
        // other codes ...
  
        protected native Object clone() throws CloneNotSupportedException;
  }
  ```
  - clone 메서드의 일반적인 규약
    - x.clone()!=x
    - x.clone().getClass()==x.getClass()
    - x.clone().equals(x)
  - 대부분의 상황에서는 `복사 생성자`와 `복사 팩터리`라는 더 나은 객체 복사 방식을 제공할 수 있다. 
  - cloneable을 이미 구현한 클래스를 확장한다면 어쩔 수 없이 clone을 잘 작동하도록 구현해야한다.
    - 접근 제한자는 public
    - 반환 타입은 클래스 자신
    - x.clone().getClass() == x.getClass()를 만족하기 위해 메서드는 가장 먼저 super.clone()을 호출하고, 필요한 필드를 재귀 복사 등을 이용해 적절히 수정한다.
- 질문