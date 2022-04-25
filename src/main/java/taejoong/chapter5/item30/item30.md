# 이왕이면 제네릭 메서드로 만들라

- 요약
  - 제네릭 타입과 마찬가지로, 클라이언트에서 입력 매개변수와 반환값을 명시적으로 형변환해야 하는 메서드보다 제네릭 메서드가 더 안전하며 사용하기도 쉽다.
  - 제너릭 메서드 : 타입 매개변수 목록은 메서드의 제한자와 반환 타입 사이에 온다. 
  - 제네릭 싱글턴 팩터리 : 제네릭은 런타임에 타입 정보가 소거되므로 하나의 객체를 어떤 타입으로든 매개변수화할 수 있다.
    - ```java
      public class Collections {
          // other codes ...
    
          @SuppressWarnings("unchecked")
          public static <T> Comparator<T> reverseOrder() {
          return (Comparator<T>) ReverseComparator.REVERSE_ORDER;
          }
      }
      ```
    - ```java
      @SuppressWarnings("unchecked")
      public static <T> UnaryOperator<T> customIdentityFunction() {
      return (UnaryOperator<T>) IDENTITY_FUNC;
      }
      ```
  - 재귀적 타입 한정 : 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정
    - `<E extends Comparable<E>>` : 모든 타입 E는 자신과 비교할 수 있다.