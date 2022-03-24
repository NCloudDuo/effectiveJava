package junghyeok.item3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.AccessibleObject;

import static org.junit.jupiter.api.Assertions.*;

class ElvisTest {

    @Test
    void 싱글턴테스트(){
        Elvis singleTon1 = Elvis.INSTANCE;
        Elvis singleTon2 = Elvis.INSTANCE;
        Assertions.assertEquals(singleTon1,singleTon2); //주소값이 같아서 테스트가 통과한다.

    }
}