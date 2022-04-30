# 확장할 수 있는 열거타입이 필요하면, 인터페이스를 사용하라

### 열거타입 자체는 확장(extends)할 수 없지만, 인터페이스와 그 인터페이스를 구현하는 기본 열거 타입을 하께 사용해 같은 효과를 낼 수 있다.
+ 장점
  + 클라이언트는 인터페이스를 구현해 자신만의 enum을 만들 수 있다.
  + interface의 다형성 장점을 살릴 수 있다.

````java
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

````