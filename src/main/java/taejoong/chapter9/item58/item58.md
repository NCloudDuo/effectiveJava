# 전통적인 for문보다는 for-each문을 사용하라

- 요약
  - 반복자와 인덱스 변수는 모두 코드를 지저분하게 할 뿐 우리에게 필요한건 원소들 뿐이다.
  - for-each문의 정식이름은 enhanced for statement이다.
  - for-each를 사용할 수 없는 세가지 상황이 존재한다.
    - destructive filtering
      - 컬렉션을 순회하면서 선택된 원소를 제거해야 한다면 반복자의 remove 메서드를 호출해야한다. 자바
      - 자바 8부터는 Collection의 removeIf 메서드를 이용해 컬렉션을 명시적으로 순회하는 일을 피할 수 있다.
    - transforming
      - 리스트나 배열을 순회하면서 그 원소의 값 일부 혹은 전체를 교체해야 한다면 리스트의 반복자나 배열의 인덱스를 사용해야한다.
    - parallel iteration
      - 여러 컬렉션을 병렬로 순회해야 한다면 각각 의 반복자와 인덱스 변수를 사용해 엄격하고 명시적으로 제어해야 한다.
  - for-each 문은 컬렉션과 배열은 물론 Iterable 인터페이스를 구현한 객체라면 무엇이든 순회할 수 있다.
  - 전통적인 for문과 비교했을 때 for-each문은 명료하고, 유연하고, 버그를 예방해준다. 성능 저하도 없다.
- 질문