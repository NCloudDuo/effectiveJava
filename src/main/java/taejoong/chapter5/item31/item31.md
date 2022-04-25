# 한정적 와일드카드를 사용해 API 유연성을 높이라

- 요약
  - 한정적 와일드카드 타입
    - ```java
      public class ArrayList<E> extends AbstractList<E>
            implements List<E>, RandomAccess, Cloneable, java.io.Serializable
      {
          // other codes ...
  
          public boolean addAll(Collection<? extends E> c) {
              Object[] a = c.toArray();
              int numNew = a.length;
              ensureCapacityInternal(size + numNew);  // Increments modCount
              System.arraycopy(a, 0, elementData, size, numNew);
              size += numNew;
              return numNew != 0;
          }
      }
      ```
  - 유연성을 극대화하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입을 사용하라.
  - 반환 타입에는 한정적 와일드카드 타입을 사용하면 안된다.
  - 매개변수(parameter) vs 인수(argument) : 매개변수는 메서드 선언에 정의한 변수, 인수는 메서드 호출 시 넘기는 '실제값'0
  - PECS(Producer-Extends, Consumer-Super)를 기억하자
  - 메서드 선언에 타입 매개변수가 한번만 나오면 와일드카드로 대체하라. 비한정적 타입 매개변수라면 비한정적 와일드카드로 바꾸고, 한정적 타입 매개변수라면 한정적 와일드 카드로 바꾸면 된다.