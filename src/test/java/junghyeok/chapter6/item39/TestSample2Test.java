package junghyeok.chapter6.item39;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class TestSample2Test {

    @Test
    void test() throws ClassNotFoundException {
        int test=0;
        int passed=0;

        Class<?> testClass = Class.forName("junghyeok.chapter6.item39.TestSample2");
        for(var method : testClass.getDeclaredMethods()){

            if(method.isAnnotationPresent(ExceptionTest.class)){
                test++;

                try {
                    method.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", method);
                }
                catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;

                    Class<? extends Throwable>[] excTypes = method.getAnnotation(ExceptionTest.class).value();
                    for(var excType : excTypes){
                        if(excType.isInstance(exc)){
                            passed++;
                            break;
                        }
                    }

                    if(passed==oldPassed){
                        System.out.printf("테스트 실패 %s 실패: %s %n", method, exc);
                    }
                }
            }
        }
        Assertions.assertEquals(4, test);
        Assertions.assertEquals(2, passed);
    }
}