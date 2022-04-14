package junghyeok.chapter5.item26;

import java.util.*;

public class GenericTest {

    static void unsafeAdd(List<Object> list, Object o){
        list.add(o);
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
//        GenericTest.unsafeAdd(strings,Integer.valueOf(42)); //실패

        List<Object> objects = new ArrayList<>();
        GenericTest.unsafeAdd(objects, Integer.valueOf(42));




    }
}
