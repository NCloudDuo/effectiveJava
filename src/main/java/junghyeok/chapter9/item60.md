# 정확한 답이 필요하다면 float와 double은 피하라

### float와 double은 부동소수점 연산에 쓰이며, `근사치`로 계산하도록 설계되었다.
+ 금융 관련 계산과는 맞지않음

### 금융 계산에서는 BigDecimal, int 혹은 long을 사용할 것