package junghyeok.item1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperTest {

    @Test
    void getSub() {
        Super subA = Super.getSubA();
        Assertions.assertEquals(subA.getClass(), SubA.class);

        Super subB = Super.getSubB();
        Assertions.assertEquals(subB.getClass(), SubB.class);
    }

}