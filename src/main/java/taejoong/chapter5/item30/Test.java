package taejoong.chapter5.item30;

import java.util.function.UnaryOperator;

public class Test {

    private static UnaryOperator<Object> IDENTITY_FUNC = (t) -> t;


    public static void main(String[] args) {
        String[] strings = {"str1", "str2", "str3"};
        UnaryOperator<String> unaryOperator1 = identityFunction();
        for (String s : strings) {
            System.out.println(unaryOperator1.apply(s));
        }

        Number[] numbers = {1, 2.0, 3L};
        UnaryOperator<Number> unaryOperator2 = identityFunction();
        for (Number n : numbers) {
            System.out.println(unaryOperator2.apply(n));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FUNC;
    }
}
