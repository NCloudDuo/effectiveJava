package junghyeok.chapter7.item43;


import java.util.HashMap;
import java.util.Map;

public class MapMerge {


    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.merge("hi", 1, (count, incr) -> count + incr);
        System.out.println(map.get("hi")); //1출력
        map.merge("hi", 1, (count, incr) -> count + incr);
        System.out.println(map.get("hi")); //2출력
        map.merge("hi", 1, Integer::sum);
        System.out.println(map.get("hi")); //3출력
    }
}
