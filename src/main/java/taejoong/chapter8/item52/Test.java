package taejoong.chapter8.item52;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        overloadingIssueWithAutoBoxing();

        overloadingIssueWithRunnableV1();
        overloadingIssueWithRunnableV2();
    }

    private static void overloadingIssueWithRunnableV2() {
        new Thread(() -> System.out.println("test"));

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(() -> System.out.println("test"));
    }

    private static void overloadingIssueWithRunnableV1() {
        new Thread(System.out::println).start();

        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.submit(System.out::println);   // compile error => println과 submit이 양쪽이 다중정의되어 다중정의 해소 알고리즘이 기대처럼 동작하지 않는다.
    }

    private static void overloadingIssueWithAutoBoxing() {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = -3; i < 3; i++) {
            set.add(i);
            list1.add(i);
            list2.add(i);
        }

        for (int i = 0; i < 3; i++) {
            // i는 원소 값 자체
            set.remove(i);
            // i가 idx
            list1.remove(i);
            // i를 Integer로 boxing
            list2.remove(Integer.valueOf(i));
        }

        System.out.println("set = " + set);
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
    }
}
