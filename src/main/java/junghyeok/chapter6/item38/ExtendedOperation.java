package junghyeok.chapter6.item38;

import java.util.function.DoubleBinaryOperator;

public enum ExtendedOperation implements DoubleBinaryOperator {
    EXP("^"){
        @Override
        public double applyAsDouble(double left, double right) {
            return Math.pow(left, right);
        }
    },
    REMAINDER("%"){
        @Override
        public double applyAsDouble(double left, double right) {
            return left % right;
        }
    };

    private final String symbol;

    ExtendedOperation(final String symbol) {
        this.symbol = symbol;
    }
}
