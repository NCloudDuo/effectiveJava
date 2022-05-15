package taejoong.chapter8.item52;

import java.math.BigInteger;
import java.util.*;

public class OverloadingExample {

    public static String classify(Set<?> set) {
        return "집합";
    }

    public static String classify(List<?> list) {
        return "리스트";
    }

    public static String classifyV1(Collection<?> collection) {
        return "그 외";
    }

    public static String classifyV2(Collection<?> collection) {
        return collection instanceof Set ? "집합" :
                collection instanceof List ? "리스트" : "그 외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for(Collection<?> c : collections) System.out.println("classify(c) = " + classifyV1(c));
        System.out.println("========================================================");
        for(Collection<?> c : collections) System.out.println("classify(c) = " + classifyV2(c));
    }
}
