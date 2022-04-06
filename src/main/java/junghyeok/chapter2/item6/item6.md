# 불필요한 객체 생성을 피하라
똑같은 기능의 객체를 매번 생성하기보다는 객체 하나를 재사용하는것이 나을때가 많다

```java
String s = new String("bikini"); //이렇게 할경우 인스턴스가 계속생성됨
String t = "bikini"; //이렇게 리터럴로 선언하는것이 좋다.
```

생성자 대신 정적 팩터리 메서드를 제공하는 불변(final) 클래스에서는 정적 팩터리 메서드를 사용해 불필요한 객체 생성을 피할 수 있다.  
생성자는 호출할 때마다 새로운 객체를 만들지만, 정적 팩터리 메서드는 그렇지 않다.

생성비용이 비싼 객체는 캐싱하여 재사용하는 것이 좋다.  
+ String.matches 정규표현식으로 문자열 형태를 확인하는 쉬운방법이지만, 성능상 좋지않다.  
+ 그렇기 때문에, Pattern 인스턴스를 캐싱해서 사용하는것이 좋다.

어댑터 패턴에서, 요청마다, 뒷단 객체를 만들때 인스턴스를 계속 만드는것이 아니라, 동일한 인스턴스를 돌려주게된다.
```java
@Test
void 어댑터테스트(){
    Map<Integer, Character> map = new HashMap<>();
    map.put(1, 'c');
    map.put(2, 'd');

    Set<Integer> set1 = map.keySet();
    Set<Integer> set2 = map.keySet();
    Assertions.assertEquals(set1, set2);
    Assertions.assertEquals(2, set1.size());

    //keySet 인스턴스에서 1을 지운다
    set1.remove(1);

    //그러고나서 map에서 keySet을 하나더 뽑아본다.
    Set<Integer> setAfterRemoveKey1 = map.keySet();

    //미리 뽑아놨던 set1에서 remove를 했음에도 불구하고, 새로뽑은 키셋의 사이즈가 1임을 알 수 있다.
    Assertions.assertEquals(setAfterRemoveKey1.size(),1);

    //1을 삭제하니 2만남아있음.
    Assertions.assertEquals(2,setAfterRemoveKey1.stream().findFirst().get());
}
```

불필요한 객체를 만들어내는 또 다른 예로 오토박싱이 있다.
그렇기 때문에, 박싱된 오토타입(Wrapper type)보단 기본타입(primitive type)을 사용해야한다.