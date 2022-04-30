package junghyeok.chapter6.item34;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    void test(){
        Operation plus = Operation.valueOf("PLUS");
        assertEquals(plus,Operation.PLUS);

        assertThrows(IllegalArgumentException.class, () -> Operation.valueOf("XXXX"), "enum상수와 일치하지 않는 값을 인자로 넣으면 IllegalArgumentException이 발생합니다.");
    }
}