# 불필요한 객체 생성을 피하라

- 요약
    - `String str = new String("test");` 대신에 `String str = "test";`를 사용하자
    - 정적 팩터리 메서드를 제공한다면, 생성자 대신 정적 팩터리 메서드를 이용하자
    - String.matches() 보다는 Pattern 인스턴스를 캐싱해두고 사용하자
    - 오토박싱을 주의해라
- 질문
  - jvm 구조
  - 정규표현식
  - 어댑터 패턴
  - 오토박싱 : boxed<->boxed에서는 성능 저하가 없는지? 연산시 자동으로 primitive 전환이 된다고 들었던것같기도
  - 엔티티 같은 곳에서 primitive vs boxed => boxed 이점 null check 이외에 추가로 있는지?
