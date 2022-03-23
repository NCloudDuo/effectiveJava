package junghyeok.item1;

public interface Super {

    static Super getSubA(){
        return new SubA();
    }

    static Super getSubB(){
        return new SubB();
    }
}
