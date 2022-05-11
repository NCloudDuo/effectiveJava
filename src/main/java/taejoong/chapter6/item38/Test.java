package taejoong.chapter6.item38;

public class Test {
    public static void main(String[] args) {
        double x = 1.234;
        double y = 5.678;
        test(BasicOperation.class, x, y);
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
