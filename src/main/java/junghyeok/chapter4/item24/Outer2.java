package junghyeok.chapter4.item24;

public class Outer2 {

    private final int number;

    public Outer2(final int number) {
        this.number = number;
    }

    void outputNumber(){
        System.out.println(number);
    }

    Inner2 createInner2(){
        return new Inner2();
    }

    class Inner2{

        public void outputOuter2Number(){
            Outer2.this.outputNumber();
        }

    }

}
