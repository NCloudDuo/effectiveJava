package taejoong.chapter7.item42;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
        // 1단계 : 익명 클래스의 인스턴스를 함수 객체로 사용 - 낡은 기법
        anonymousClass();

        // 2단계 : 람다식을 함수 객체로 사용
        lambdaExpression();

        // 3단계 : 비교자 생성 메서드를 함수 객체로 사용
        comparatorGenerateMethod();

        // 함수형 인터페이스 관계를 살펴보기 위한 선언
        DoubleBinaryOperator dbo;
        BinaryOperator bo;
        BiFunction bf;
        Function f;
    }

    private static void comparatorGenerateMethod() {
        List<String> stringList = getList();

        printList(stringList);
        Collections.sort(stringList, Comparator.comparingInt(String::length));
        printList(stringList);
    }

    private static void lambdaExpression() {
        List<String> stringList = getList();

        printList(stringList);
        Collections.sort(stringList, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        printList(stringList);
    }

    private static void anonymousClass() {
        List<String> stringList = getList();

        printList(stringList);
        Collections.sort(stringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        printList(stringList);
    }

    private static void printList(List<String> stringList) {
        System.out.println("stringList = " + stringList);
    }

    private static List<String> getList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("aaaa");
        stringList.add("aaa");
        stringList.add("aa");
        stringList.add("aaaaa");
        return stringList;
    }
}
