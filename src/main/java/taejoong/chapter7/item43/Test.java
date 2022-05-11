package taejoong.chapter7.item43;

import java.nio.file.DirectoryStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("key", 10);

        String key = "key";

        // 람다
        System.out.println("map.get(key) = " + map.get(key));
        map.merge(key, 1, (count, incr) -> count + incr);
        System.out.println("map.get(key) = " + map.get(key));

        // 메서드 참조
        System.out.println("map.0get(key) = " + map.get(key));
        map.merge(key, 1, Integer::sum);
        System.out.println("map.get(key) = " + map.get(key));

        Instant now = Instant.now();
        DirectoryStream.Filter<Instant> isAfter = Instant.now()::isAfter;
    }
}
