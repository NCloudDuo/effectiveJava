package junghyeok.chapter6.item38;

import org.junit.jupiter.api.Test;

import java.util.function.DoubleBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class BasicOperationTest {

    @Test
    void test(){
        DoubleBinaryOperator plus = BasicOperation.PLUS; //다형성 활용
        assertEquals(4, plus.applyAsDouble(1, 3));

        DoubleBinaryOperator minus = BasicOperation.MINUS;
        assertEquals(-2, minus.applyAsDouble(1, 3));

        DoubleBinaryOperator times = BasicOperation.TIMES;
        assertEquals(3, times.applyAsDouble(1,3));

        DoubleBinaryOperator divide = BasicOperation.DIVIDE;
        assertEquals(1/(double)3,divide.applyAsDouble(1,3));
    }
}