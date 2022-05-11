package taejoong.chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        unsafeAdd1(stringList, Integer.valueOf(42));
//        unsafeAdd2(stringList, Integer.valueOf(42));  // 컴파일 불가
        System.out.println("stringList.get(0) = " + stringList.get(0));

        boolean isInstanceOfList1 = stringList instanceof List;
        boolean isInstanceOfList2 = stringList instanceof List<?>;
//        boolean isInstanceOfList3 = stringList instanceof List<String>;  // 컴파일 불가
    }

    private static void unsafeAdd1(List list, Object o) {
        list.add(o);
    }

    private static void unsafeAdd2(List<Object> list, Object o) {
        list.add(o);
    }
}