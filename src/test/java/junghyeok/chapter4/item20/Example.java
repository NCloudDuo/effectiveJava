package junghyeok.chapter4.item20;

import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Example {

    static List<Integer> intArrayList(int[] a){
        Objects.requireNonNull(a);

        return new AbstractList<>() {
            @Override
            public Integer get(int index) {
                return a[index];
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element;
                return oldVal;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    @Test
    void test(){
        List<Integer> list = intArrayList(new int[]{1, 2, 3, 4, 5});
        list.forEach(System.out::println);
    }
}
