package junghyeok.chapter4.item24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Outer2Test {

    @Test
    void test(){
        Outer2 outer2 = new Outer2(10);
        Outer2.Inner2 inner2 = outer2.createInner2(); //비정적 멤버클래스 인스턴스만들기
        inner2.outputOuter2Number();
    }
}