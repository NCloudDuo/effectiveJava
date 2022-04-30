package junghyeok.chapter6.item38;

import org.junit.jupiter.api.Test;

import java.util.function.DoubleBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedOperationTest {

    @Test
    void test(){
        DoubleBinaryOperator pow = ExtendedOperation.EXP;
        assertEquals(8, pow.applyAsDouble(2,3));

        DoubleBinaryOperator remainder = ExtendedOperation.REMAINDER;
        assertEquals(2 % (double) 3, remainder.applyAsDouble(2, 3));
    }
}