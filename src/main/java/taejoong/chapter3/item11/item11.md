# equals를 재정의하려거든 hashCode도 재정의하라

- 요약
  - equals를 재정의한 클래스 모두에서 hashCode도 재정의해야한다. 그렇지 않으면 hashCode 일반 규약을 어기게 되어 해당 클래스의 인스턴스를 HashMap이나 HashSet 같은 컬렉션의 원소로 사용할 때 문제를 일으킨다.
  - equals가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다.
  - HashMap같은 경우에는 해시코드가 다른 엔티티끼리는 동치성 비교를 시도조차 하지 않도록 최적화되어 있다.
  - equals 비교에 사용되지 않은 필드는 hashCode 계산시 제외한다.
  - Objects 클래스는 임의의 개수만큼 객체를 받아 해시코드를 계산해주는 정적 메서드인 hash를 제공한다.
  - hashCode 작성요령
  ```
  1. int 변수 result를 선언한 후 c(2.a 방법으로 계산한 해시코드)로 초기화  
  2. 해당 객체의 나머지 핵심 필드 f 각각에 대해 다음 작업 수행  
      a. 해당 필드의 해시코드 c를 계산
        i. 기본 타입 필드일 경우, Wrapper.hashCode(f)를 수행
        ii. 참조 필드일 경우, 필드의 equals 재귀적 호출, null이면 0사용
        iii. 배열 필드일 경우, 원소 각각을 계산, 원소가 없다면 0, 모든 원소가 핵심이라면 Arrays.hashCode이용
      b. 2.a에서 계산한 해시코드 c로 result 갱신 => result = 31*result + C;
  3. result 반환
  ```
- 질문
  - 해싱 알고리즘 및 해싱을 이용한 자료구조 동작
  - lombok @EqualsAndHashCode