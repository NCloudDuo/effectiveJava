package junghyeok.chapter6.item39;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class TestSampleTest {

    @Test
    void test() throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        int wrongTest=0;
        Class<?> testClass = Class.forName("junghyeok.chapter6.item39.TestSample");
        for(var method : testClass.getDeclaredMethods()){
            if(method.isAnnotationPresent(junghyeok.chapter6.item39.Test.class)){
                tests++;
                try{
                    method.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(method+" 실패: "+exc);
                } catch (Exception exc){
                    System.out.println("잘못 사용한 @Test: "+ method);
                }
            }
        }

        Assertions.assertEquals(4, tests, "전체테스트입니다.");
        Assertions.assertEquals(1, passed,"성공한테스트");
        Assertions.assertEquals(3, tests-passed,"실패한테스트");
    }
}