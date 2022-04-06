# Comparable을 구현할지 고려하라

Comparable 인터페이스의 compareTo에 관한 아이템  
compareTo는 단순 동치성 비교에 더해 순서까지 비교 가능하며, 제네릭하다.  
Comparable을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서(natural order)가 있음을 뜻한다.  

자연적인 순서가 존재하므로, 정렬, 검색, min, max계산, 자동정렬되는 컬렉션 관리도 편하게 할 수 있다.  

compareTo 메서드의 일반규약은 equals의 규약과 비슷하다.  
반사성, 대칭성, 추이성을 충족해야함을 의미한다.  
추가적으로, (x.compareTo(y)==0) == (x.equals.(y))여야 한다. (권장)  
=> compareTo 메서드로 수행한 동치성 테스트의 결과가 equals와 같아야 한다는 것이다.
=> 이래야 compareTo로 줄지은 순서와 equals의 결과가 일관되게 된다.

compareTo와 equals가 일관되지 않은 BigDecimal 클래스의 예
```java
@Test
void test() {
    Set<BigDecimal> set = new HashSet<>();
    set.add(new BigDecimal("1.0"));
    set.add(new BigDecimal("1.00"));
    Assertions.assertEquals(2, set.size()); //equals가 다른 인스턴스로 인식해 HashSet의 두개의 원소가 있게 된다.

    Set<BigDecimal> treeSet = new TreeSet<>(set);
    Assertions.assertEquals(1, treeSet.size()); //compareTo가 같은 원소로 인식해 TreeSet의 인스턴스의 갯수는 하나이다.
}
```

## 주의해야할 점
1. Comparable은 타입을 인수로 받는 제네릭 인터페이스이므로 compareTo 메서드의 인수 타입은 컴파일 타임에 정해진다.
   + 즉, equals에서처럼 instanceof 를 이용할 필요가없다.
   + 객체 참조 필드를 비교하려면 compareTo를 재귀적으로 호출한다.
2. 박싱된 기본 타입 클래스들에 새로 추가된 compare를 이용하자
3. 클래스에 핵심필드가 여러개라면, 그중에서도 제일 중요한것부터 비교해 나가면된다.
4. `값의 차`를 기준으로 compareTo나 compare를 구현하면 안된다.
   + 그이유는 다음과 같다.
     + 부동소수점 계산방식에 따른오류
     + 오버플로우의 위험
   + 그렇기 때문에, compare 메서드를 활용하거나 comparing등을 활용하는것이 좋다.

## 구현시에 참고할만한 부분
1. Comparator를 이용해서 compareTo를 쉽게 구현할 수 있다. (그러나 성능상의 이슈가 있는듯)
    ````java
    public class PhoneNumber implements Comparable<PhoneNumber>{
        private final int areaCode;
        private final int prefix;
        private final int lineNum;
    
        public PhoneNumber(int areaCode, int prefix, int lineNum) {
            this.areaCode = areaCode;
            this.prefix = prefix;
            this.lineNum = lineNum;
        }
    
        private static final Comparator<PhoneNumber> COMPARATOR = Comparator.comparing((PhoneNumber pn) -> pn.areaCode)
                .thenComparingInt(pn -> pn.prefix)
                .thenComparingInt(pn -> pn.lineNum);
    
        @Override
        public int compareTo(PhoneNumber pn) {
            return COMPARATOR.compare(this, pn);
        }
    }

    ````

