package junghyeok.chapter6.item38;

import java.util.function.DoubleBinaryOperator;

public enum BasicOperation implements DoubleBinaryOperator {
    PLUS("+"){
        @Override
        public double applyAsDouble(double left, double right) {
            return left + right;
        }
    },
    MINUS("-"){
        @Override
        public double applyAsDouble(double left, double right) {
            return left - right;
        }
    },
    TIMES("*"){
        @Override
        public double applyAsDouble(double left, double right) {
            return left * right;
        }
    },
    DIVIDE("/"){
        @Override
        public double applyAsDouble(double left, double right) {
            return left / right;
        }
    };

    private final String symbol;

    BasicOperation(final String symbol) {
        this.symbol = symbol;
    }
}
