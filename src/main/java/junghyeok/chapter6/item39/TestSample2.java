package junghyeok.chapter6.item39;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.List;

public class TestSample2 {

    @ExceptionTest(ArithmeticException.class)
    public static void m1(){
        int i=0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2(){
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(PartialResultException.class)
    public static void m3(){}

    @ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doublyBad(){
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
}
