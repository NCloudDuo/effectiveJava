# 스트림은 주의해서 사용하라

### 스트림 API란?
+ 다량의 데이터 처리 작업을 돕고자 자바 8에 추가
+ 스트림 API가 제공하는 중요한 추상 개념 두가지
  + 스트림은 데이터 원소의 유한 혹은 무한 시퀀스(sequence)를 뜻한다.
  + 스트림 파이프라인(stream pipeline)은 이 원소들로 수행하는 연산단계를 표현하는 개념
+ 스트림의 원소들은 컬렉션, 배열, 파일, 정규표현식 패턴 매처, 난수 생성기, 다른 스트림으로 부터 올 수 있다.
  + 스트림 안의 데이터 원소들은 객체 참조나 기본 타입 값(int, long ,double)
+ 스트림 파이프라인은 시작연산 -> (중간연산) -> 종단연산을 걸쳐서 연산을 완성한다.
  + 스트림 파이프라인은 `지연평가 (lazy evaluation)` 된다.
  + 종단 연산을 실행해야, evaluation이 되며, 종단연산이 없을경우 no-op이다.

### 스트림에 과도한 사용은 오히려 가독성이 안좋을 수 있으니, 적당히 사용할 것
+ String List로부터 원소들을 읽어들여, 사용자가 지정한 문턱값보다 원소 수가 많은 아나그랩 그룹을 출력하는 프로그램을 예시로보자.

```java
public class Anagrams {

    public static void main(String[] args) {

        int minGroupSize = 2;
        List<String> words = List.of("hi", "staple", "aelpst", "petals", "apple", "ppale", "ih");

        /**
         * 방법 1
         */
        Map<String, Set<String>> groups = new HashMap<>();
        for (String word : words) {
            groups.computeIfAbsent(alphabetize(word), unused-> new TreeSet<>()).add(word); //alphabetize(word)가 key로 있으면, key의 value를 반환하고, 없다면 key에 대응되는 매핑되는 값을 제공해준다.
        }

        for(var group : groups.values()){
            if(group.size()>= minGroupSize){
                System.out.println(group.size() + ": " + group);
            }
        }

        /**
         * 방법 3, 방법2는 너무 복잡해 스킵합니다.
         */

        System.out.println("-------------------------");

        words.stream().collect(Collectors.groupingBy(word-> alphabetize(word)))
                .values().stream()
                .filter(group -> group.size()>= minGroupSize)
                .forEach(filteredGroup -> System.out.println(filteredGroup.size() +": "+filteredGroup));
    }

    private static String alphabetize(String string){
        char[] chars = string.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}

```

+ 두번째 예시를 생략한 이유는, 스트림 떡칠을 해놔서 너무 복잡하기 때문이다.

### 람다에서는 타입을 생략하므로, 람다 매개변수의 이름을 정하는것은 알아보기 쉽도록 해야한다.
+ 예시에서 groupingBy에서 group, filter후에 forEach에서 filteredGroup

### 스트림 파이프라인에서, 적절한 help 메서드의 사용은 가독성을 좋게해주는 열쇠
+ alphabetize와 같은 메서드들을 적절히 사용하자

### char 값들을 처리할 때는 스트림을 삼가는 편이 낫다.
+ char형 스트림이 존재하지 않기 때문에 불편하기때문이다.

### 함수 객체(람다 혹은 메서드 참조)로는 할 수 없고, 코드블록으로만 할 수 있는 일
+ 코드 블록에서는 범위 안의 지역변수를 읽고 수정이 가능하다.
+ 람다에서는 final이거나 사실상 final인 변수만 읽을 수 있고, 지역변수 수정은 불가능하다.
+ 코드 블록에서는 return문을 사용해 메서드에서 빠져나가거나, break나 continue문으로 블록 바깥의 반복문을 종료하거나 건너띌 수 있지만 람다는 불가능
### 스트림과 반복 중 어느 쪽이 나은지 확신하기 어렵다면 둘 다 해보고, 더 나은쪽을 선택하자.