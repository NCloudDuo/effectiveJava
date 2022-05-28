# 문자열 연결은 느리니 주의하라

### 간단한 문자열을 연결할때는 + 연산자를 사용해도 괜찮다.

### 그러나, 다수의 String을 연결할때는 StringBuilder를 사용하자.

## StringBuffer는 thread-safe, StringBuilder는 동기화를 보장하지 않음
+ thread-safe가 보장되는 상황이라면 StringBuilder를 사용할 것