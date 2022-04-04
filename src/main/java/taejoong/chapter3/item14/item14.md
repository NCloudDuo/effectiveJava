# Comparable을 구현할지 고려하라

- 요약
  - Comparable을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서(natural order)가 있음을 뜻한다.
  - Comparable의 compareTo 메서드의 일반 규약
    - 두 객체 참조의 순서를 바꿔 비교해도 예상한 결과가 나와야 한다.
    - 첫번째가 두번째보다 크고 두번째가 세번쨰보다 크면, 첫번째는 세번쨰보다 커야한다.
    - 크기가 같은 객체들끼리는 어떤 객체와 비교하더라도 항상 같아야한다.
    - (x.compareTo(y)==0)==(x.equals(y))
  - compareTo에서 필드의 값을 비교할 때 <와 > 연산자는 쓰지 말아야 한다.
  - Comparable을 구현하지 않은 필드나 표준이 아닌 순서로 비교해야 한다면 아래를 이용하자.
    - 박싱된 기본 타입 클래스들에 존재하는 정적 메서드인 compare를 이용해라.
      - ```java
        package java.lang;
        
        public final class Long extends Number implements Comparable<Long> {
        
            // other codes ...
        
            public static int compare(long x, long y) {
                return (x < y) ? -1 : ((x == y) ? 0 : 1);
            }
        }
        ```
    - Comparator 인터페이스가 제공하는 비교자 생성 메서드를 사용하자.
      - ```java
        Comparator<PhoneNumber> comparator = Comparator.comparingInt((PhoneNumber pn) -> pn.areaCode)
                .thenComparingInt(pn -> pn.prefix)
                .thenComparingInt(pn -> pn.lineNum);
        ```
- 질문
  - BigInteger & BigDecimal
  - [Comparable vs Comparator 차이](https://st-lab.tistory.com/243)