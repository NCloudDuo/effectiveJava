# 예외를 무시하지 말라

### API 설계자가 메서드 선언에 예외를 명시하는 까닭은, 그 메서드를 사용할 때 적절한 조치를 취해달라고 말하는 것이다.

```java
try{
    //do something
}catch(SomeException e){
    //비우지 말고, 어떠한 조치를 취할 것
```

### 예외를 무시하기로 했다면, catch문의 exception 변수를 `ignored`로 선언하고, 왜 무시하는지 주석을 남길 것