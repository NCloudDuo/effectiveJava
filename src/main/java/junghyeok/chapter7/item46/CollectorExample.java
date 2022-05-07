package junghyeok.chapter7.item46;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorExample {
    public static void main(String[] args) {

        List<String> words = List.of("hi", "bye", "hi", "apple", "banana", "apple", "banana", "nhn", "naver", "kakao", "kakao", "naver", "KaKao", "Naver");

        Map<String, Long> freq = words.stream().collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting())); // static import를 일부로 하지 않음. 예시코드라 헷갈릴 수 있어서

        List<String> topThree = freq.keySet().stream()
                .sorted(Comparator.comparing(freq::get).reversed()) //
                .limit(3)
                .collect(Collectors.toList()); //freq map의 key들을 stream으로 만든 후, map으로 빈도수를 가져와서 제일큰 순으로 정렬하고 3개를 뽑아내서 List로 반환

        System.out.println(topThree); //kakao, naver, banana출력


        Map<String, List<String>> collectList = words.stream().collect(Collectors.groupingBy(word -> alphabetize(word))); //기본으로 key에 대응되는 value를 List로 만듬

        Map<String, Set<String>> collectSet = words.stream().collect(Collectors.groupingBy(word -> alphabetize(word), Collectors.toSet())); //다운스트림 수집기를 명시한경우

        Map<String, LinkedList<String>> collect = words.stream().collect(Collectors.groupingBy(CollectorExample::alphabetize, Collectors.toCollection(LinkedList::new)));// 다운스트립 수집기를 List, Set이외 말고 다른 컬렉션으로 만들고 싶은경우

        Map<String, Long> freq2 = words.stream().collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting())); // 다운스트림 수집기로 counting()을 건네는 경우

        TreeMap<String, LinkedList<String>> collect1 = words.stream().collect(Collectors.groupingBy(word -> alphabetize(word), TreeMap::new, Collectors.toCollection(LinkedList::new))); //다운스트림 이전에 mapFactory를 제공합니다.

        //위의 예시와 대응되는 ConcurrentHashMap을 만드는 groupingByConcurrent도 있다.

        Map<Boolean, List<String>> collect2 = words.stream().collect(Collectors.partitioningBy(word -> word.length() <= 3)); //partitioingBy의 예시

    }

    private static String alphabetize(String string){
        char[] chars = string.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
