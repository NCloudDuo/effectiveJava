package taejoong.chapter5.item28;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Object[] objectArray = new Long[1];
        objectArray[0] = "타입이 달라 넣을 수 없다."; // 런타임에 ArrayStoreException 발생

//        List<Object> objectList = new ArrayList<Long>(); // 컴파일 불가
    }
}
