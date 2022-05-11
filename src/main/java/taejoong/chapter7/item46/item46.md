# 스트림에서는 부작옹 없는 함수를 사용하라

- 요약
  - 스트림 패러다임의 핵심은 계산을 일련의 변환으로 재구성하는 부분이다. 스트림의 각 변환 단계는 가능한 한 이전 단계의 결과를 받아 처리하는 순수 함수여야한다. 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 말아야한다. 이렇게 하려면 스트림 연산에 건네는 함수 객체는 모두 부작용(side effect)이 없어야 한다.
  - forEach 연산은 스트림 계산 결과를 보고할 때만 사용하고, 계산하는 데는 쓰지 말자.
  - 자주 쓰이는 collector : toList, toSet, toCollection(collectionFactory), toMap, groupingBy(말그대로 grouping 용도), joining(string concatenation 용도)
  - [java.util.stream.Collectors API](http://bit.ly/2MvTOAR)
- 질문