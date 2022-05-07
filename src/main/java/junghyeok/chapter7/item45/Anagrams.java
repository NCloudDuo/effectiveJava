package junghyeok.chapter7.item45;

import java.util.*;
import java.util.stream.Collectors;

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
