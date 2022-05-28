# 필요없는 검사 예외 사용은 피하라

### API를 제대로 사용해도 발생할 수 있는 예외이거나, 프로그래머가 의미 있는 조치를 취할 수 있는 경우라면, checked Exception 괜찮다.
+ 그러나, 둘 중 어디에도 해당하지 않는다면, unchecked Exception을 사용하는게 좋다.

### checkedExcpetion을 회피하는 가장 쉬운 방법은 적절한 결과 타입을 당믄 Optional을 반환하는 것
+ Checked Exception 대신, Optional.empty 반환
+ 이 방식의 단점은, 예외가 발생한 이유를 알려주는 부가정보를 담을 수 없다.