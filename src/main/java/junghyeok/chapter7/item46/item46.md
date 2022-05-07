# 스트림에서는 부작용(side effect) 없는 함수를 사용하라

### 스트림은 함수형 프로그래밍에 기초한 패러다임이기 때문에, 패러다임을 잘 알아야한다.
+ 스트림 패러다임의 핵심은 계산을 일련의 변환으로 재구성하는 부분이다.
  + 각 변환 단계는 가능한 한 이전 단계의 결과를 받아 처리하는 `순수 함수`여야한다.
  + 순수함수란?
    + side effect를 없앤 함수. 어떤 함수에 동일한 인자를 주었을 때, 항상 같은 값을 리턴하는 함수
    + 외부의상태를 변경하지 않는 함수

## 부작용이 없는 스트림함수의 예시
```java
List<String> words = List.of("hi", "bye", "hi", "apple", "banana", "apple", "banana", "nhn", "naver", "kakao", "kakao", "naver", "KaKao", "Naver");

Map<String, Long> freq = words.stream().collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting())); 
```
+ 기존에 있던 Map에다가 words를 stream.forEach로 추가한 것이아니라(외부의 상태를 변경하지 않도록), 부작용이 없도록 새로운 Map collection을 만들어서 반환한다. 
+ 스트림을 함수형프로그래밍 처럼 사용하지 않는 예시는 forEach에서 외부상태를 건드릴때 주로 발생한다.
  + forEach 연산은 스트림 계산 결과를 보고할때만 사용하고, 계산하는데는 쓰지말 것
  + 물론 가끔은, 스트림 계산결과를 기존 컬렉션에 추가하는등 정도는 가능
---

  + forEach 연산에서 원소들을 받아서 컬렉션에 추가하는 것보다는, 수집기(Collectors)들을 사용해서 새로운 컬렉션을 만드는것이 좋다.
    + 수집기 연산은 총 세가지이다.
      + toList()
      + toSet()
      + toCollection(collectionFactory)
        + toMap
        + groupingBy
        + joining
      
### 수집기의 예시코드

+ 아래의 예시코드는 Collectors를 이용한 가장 간단한 예시이다.

````java

Map<String, Long> freq = words.stream().collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting())); 

List<String> topThree = freq.keySet().stream()
        .sorted(Comparator.comparing(freq::get).reversed()) //
        .limit(3)
        .collect(Collectors.toList()); //freq map의 key들을 stream으로 만든 후, map으로 빈도수를 가져와서 제일큰 순으로 정렬하고 3개를 뽑아내서 List로 반환
````

+ 두번째 예시는 toMap을 이용해, 문자열을 열거타입 상수에 매핑하는것

````java
private static final Map<String, Operation> map = Arrays.stream(values())
            .collect(Collectors.toMap(operation -> operation.symbol, Function.identity()));
//스트림의 각 원소가 고유한 키에 매핑되어 있을 때 적합. 다수가 같은 키를 사용한다면 파이프라인이 IllegalStateException을 던짐
````

### IllegalStateException을 해결하기 위한 방법들
  + toMap에 mergeFunction(`BinaryOperator<U>`)을 제공하기
    + ````java
      Map<Artist, Album> topHits = albumList.stream().collect(Collectors.toMap(album -> album.getArtist(), Function.identity(),
                  BinaryOperator.maxBy(Comparator.comparing(album -> album.getSales()))));//메서드 참조를 사용하지 않음. 람다가 아직은 익숙하기 때문에
      ````
  + last-write-win 혹은 first-write-win 수집기
    + ```java
      Map<Artist, Album> firstAlbumByArtist = albumList.stream().collect(Collectors.toMap(album -> album.getArtist(),
                Function.identity(),
                (oldAlbum, newAlbum) -> oldAlbum)); //first-write-win
      
      Map<Artist, Album> firstAlbumByArtist = albumList.stream().collect(Collectors.toMap(album -> album.getArtist(),
                Function.identity(),
                (oldAlbum, newAlbum) -> newAlbum)); //last-write-win
      ```

### toMap 함수인자에 맵 팩터리를 제공할 수 있다.
```java
ConcurrentHashMap<Artist, Album> collect = albumList.stream()
                .collect(Collectors.toMap(Album::getArtist,
                        Function.identity(),
                        (o1, o2) -> o1,
                        ConcurrentHashMap::new)); //map supplier를 제공합니다.
```

### groupingBy의 예시
+ 아래는 default로 List를 반환하는 groupingBy입니다.
  + ```java
    Map<String, List<String>> collectList = words.stream().collect(Collectors.groupingBy(word -> alphabetize(word))); //기본으로 key에 대응되는 value를 List로 만듬
    ```
+ 다운스트림 수집기를 이용해서, List말고 다른 컬렉션으로 value를 대체할 수 있습니다.
  + toSet
    + ````java
      Map<String, Set<String>> collectSet = words.stream().collect(Collectors.groupingBy(word -> alphabetize(word), Collectors.toSet())); //다운스트림 수집기를 명시한경우
      ````
  + toCollection
    + ````java
      Map<String, LinkedList<String>> collect = words.stream().collect(Collectors.groupingBy(CollectorExample::alphabetize, Collectors.toCollection(LinkedList::new)));// 다운스트립 수집기를 List, Set이외 말고 다른 컬렉션으로 만들고 싶은경우
      ````

+ mapFactory를 제공하는 것도 가능합니다.
  + ```java
    TreeMap<String, LinkedList<String>> collect1 = words.stream().collect(Collectors.groupingBy(word -> alphabetize(word), TreeMap::new, Collectors.toCollection(LinkedList::new))); //다운스트림 이전에 mapFactory를 제공합니다.
    ```
    
### partitioningBy의 예시
```java
Map<Boolean, List<String>> collect2 = words.stream().collect(Collectors.partitioningBy(word -> word.length() <= 3)); //partitioingBy의 예시
```

### 수집과 관련은 없지만 Collectors에 있는 메서드 BinaryOperator.minBy, BinaryOperator.maxBy이용
```java
Map<Artist, Album> topHits = albumList.stream().collect(Collectors.toMap(album -> album.getArtist(), Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(album -> album.getSales()))));
```

### joining을 이용한 예시
```java
String girlsGenerationAlbumName = albumList.stream().filter(album -> album.getArtist().getArtistName().equals("소녀시대"))
                .map(album -> album.getAlbumName())
                .collect(Collectors.joining(", "));
//좋은 예시는 아닙니다. 지금은 다 객체들이 final로 선언되어있고, 생성자에서 다 넣어줬기때문에 NPE가 나지 않지만, nullable일 경우 filter에서 null이 터질 수 있습니다.
```