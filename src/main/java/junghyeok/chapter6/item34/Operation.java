package junghyeok.chapter6.item34;

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

}
