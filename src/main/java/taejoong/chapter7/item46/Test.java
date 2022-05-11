package taejoong.chapter7.item46;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        // not good example --> forEach change map's state
        Map<String, Long> freq1 = getMapUsingLambdaUnProperly();
        System.out.println("freq1 = " + freq1);

        // good example
        Map<String, Long> freq2 = getMapUsingLambdaProperly();
        System.out.println("freq2 = " + freq2);

        // find the most frequent word
        Map<String, Long> freq3 = getMapUsingLambdaProperly();
        List<String> mostFreqWord = findMostFrequentWord(freq3);
        System.out.println("mostFreqWord = " + mostFreqWord);

        // toMap() collector : [mapBefore -> keySet -> mapAfter]
        Map<String, Long> mapBeforeUsingToMap = getMapUsingLambdaProperly();
        mapBeforeUsingToMap.put("AaaA", 5L);
        mapBeforeUsingToMap.put("ccCC", 0L);
        Set<String> keysUsingToMap = mapBeforeUsingToMap.keySet();
        Map<String, Long> mapAfterUsingToMapTwoArguments = getMapUsingToMapWithTwoArguments(mapBeforeUsingToMap, keysUsingToMap); // 2 arguments toMap()
        Map<String, Long> mapAfterUsingToMapWithThreeArgumentsV1 = getMapUsingToMapWithThreeArgumentsV1(mapBeforeUsingToMap, keysUsingToMap); // 3 arguments toMap()
        Map<String, Long> mapAfterUsingToMapWithThreeArgumentsV2 = getMapUsingToMapWithThreeArgumentsV2(mapBeforeUsingToMap, keysUsingToMap); // 3 arguments toMap()
        Map<String, Long> mapAfterUsingToMapWithFourArguments = getMapUsingToMapWithFourArguments(mapBeforeUsingToMap, keysUsingToMap); // 4 arguments toMap()
        System.out.println("mapAfterUsingToMapTwoArguments = " + mapAfterUsingToMapTwoArguments);
        System.out.println("mapAfterUsingToMapWithThreeArgumentsV1 = " + mapAfterUsingToMapWithThreeArgumentsV1);
        System.out.println("mapAfterUsingToMapWithThreeArgumentsV2 = " + mapAfterUsingToMapWithThreeArgumentsV2);
        System.out.println("mapAfterUsingToMapWithFourArguments = " + mapAfterUsingToMapWithFourArguments);
        System.out.println("getClass() : " + mapAfterUsingToMapTwoArguments.getClass() + " " + mapAfterUsingToMapWithThreeArgumentsV1.getClass() + " " + mapAfterUsingToMapWithFourArguments.getClass());

        // groupingBy() collector : [mapBefore -> keySet -> mapAfter]
        Map<String, Long> mapBeforeUsingGroupingBy = getMapUsingLambdaProperly();
        mapBeforeUsingGroupingBy.put("AaaA", 5L);
        mapBeforeUsingGroupingBy.put("ccCC", 0L);
        Set<String> keysV2 = mapBeforeUsingGroupingBy.keySet();
        Map<String, List<String>> mapAfterUsingGroupingByOneArguments = keysV2.stream().collect(groupingBy(e -> e.toLowerCase()));
        Map<String, Long> mapAfterUsingGroupingByTwoArguments = keysV2.stream().collect(groupingBy(e -> e.toLowerCase(), counting()));
        Map<String, Long> mapAfterUsingGroupingByThreeArguments = keysV2.stream().collect(groupingBy(e -> e.toLowerCase(), TreeMap::new, counting()));
        System.out.println("mapAfterUsingGroupingByOneArguments = " + mapAfterUsingGroupingByOneArguments);
        System.out.println("mapAfterUsingGroupingByTwoArguments = " + mapAfterUsingGroupingByTwoArguments);
        System.out.println("mapAfterUsingGroupingByThreeArguments = " + mapAfterUsingGroupingByThreeArguments);
        System.out.println("getClass() : " + mapAfterUsingGroupingByOneArguments.getClass() + " " + mapAfterUsingGroupingByTwoArguments.getClass() + " " + mapAfterUsingGroupingByThreeArguments.getClass());

    }

    private static Map<String, Long> getMapUsingToMapWithTwoArguments(Map<String, Long> map, Set<String> keys) {
        return keys.stream().collect(toMap(e -> e, map::get));
    }

    private static Map<String, Long> getMapUsingToMapWithThreeArgumentsV1(Map<String, Long> map, Set<String> keys) {
        return keys.stream().collect(toMap(e -> e.toLowerCase(), map::get, BinaryOperator.maxBy(Comparator.naturalOrder())));
    }

    private static Map<String, Long> getMapUsingToMapWithThreeArgumentsV2(Map<String, Long> map, Set<String> keys) {
        return keys.stream().collect(toMap(e -> e.toLowerCase(), map::get, (oldVal, newVal) -> newVal));
    }

    private static Map<String, Long> getMapUsingToMapWithFourArguments(Map<String, Long> map, Set<String> keys) {
        return keys.stream().collect(toMap(e -> e.toLowerCase(), map::get, BinaryOperator.maxBy(Comparator.naturalOrder()), TreeMap::new));
    }

    private static List<String> findMostFrequentWord(Map<String, Long> freq) {
        return freq.keySet().stream()
                .sorted(comparing(freq::get).reversed())
                .limit(1)
                .collect(toList());
    }

    private static Map<String, Long> getMapUsingLambdaUnProperly() throws FileNotFoundException {
        Map<String, Long> freq = new HashMap<>();

        File file1 = new File("src/main/java/taejoong/chapter7/item46/file.txt");
        try (Stream<String> words = new Scanner(file1).tokens()) {
            words.forEach(word -> {
                freq.merge(word.toLowerCase(), 1L, Long::sum);
            });
        }

        return freq;
    }

    private static Map<String, Long> getMapUsingLambdaProperly() throws FileNotFoundException {
        Map<String, Long> freq;

        File file2 = new File("src/main/java/taejoong/chapter7/item46/file.txt");
        try (Stream<String> words = new Scanner(file2).tokens()) {
            freq = words.collect(groupingBy(String::toLowerCase, counting()));
        }

        return freq;
    }
}
