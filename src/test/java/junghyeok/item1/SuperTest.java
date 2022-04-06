package junghyeok.item1;

import junghyeok.chapter2.item1.SubA;
import junghyeok.chapter2.item1.SubB;
import junghyeok.chapter2.item1.Super;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SuperTest {

    @Test
    void getSub() {
        Super subA = Super.getSubA();
        Assertions.assertEquals(subA.getClass(), SubA.class);

        Super subB = Super.getSubB();
        Assertions.assertEquals(subB.getClass(), SubB.class);
    }

}