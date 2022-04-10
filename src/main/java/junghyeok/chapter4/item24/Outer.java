package junghyeok.chapter4.item24;

public class Outer {
    private final int number;

    public Outer(final int number) {
        this.number = number;
    }

    static class Inner{

        void outputOuterClassField(){
            System.out.println(new Outer(10).number);
        }
    }
}
