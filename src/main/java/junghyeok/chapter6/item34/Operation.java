package junghyeok.chapter6.item34;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Operation {
    PLUS("+"){
        @Override
        public double apply(double x, double y) {
            return x+y;
        }
    },
    MINUS("-"){
        @Override
        public double apply(double x, double y) {
            return x-y;
        }
    },
    TIMES("*"){
        @Override
        public double apply(double x, double y) {
            return x*y;
        }
    },DIVIDE("/"){
        @Override
        public double apply(double x, double y) {
            return x/y;
        }
    };

    private final String symbol;

    Operation(final String symbol) {
        this.symbol = symbol;
    }

    public abstract double apply(double x, double y);

    private static final Map<String, Operation> map = Arrays.stream(values())
            .collect(Collectors.toMap(operation -> operation.symbol, Function.identity())); //스트림의 각 원소가 고유한 키에 매핑되어 있을 때 적합. 다수가 같은 키를 사용한다면 파이프라인이 IllegalStateException을 던짐
}
