package taejoong.chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {
//    public static void main(String[] args) throws ClassNotFoundException {
//        int tests = 0;
//        int passed = 0;
//        Class<?> testClass = Class.forName(Sample.class.getName());
//        for (Method m : testClass.getDeclaredMethods()) {
//            if (m.isAnnotationPresent(Test.class)) {
//                tests++;
//                try {
//                    m.invoke(null);
//                    passed++;
//                } catch (InvocationTargetException wrappedExc) {
//                    Throwable exc = wrappedExc.getCause();
//                    System.out.println(m + " 실패: " + exc);
//                } catch (Exception exception) {
//                    System.out.println("잘못 사용한 @Test: " + m);
//                }
//            }
//        }
//        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
//    }

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(Sample2.class.getName());
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
                    if (excType.isInstance(exc)) {
                        passed++;
                    }else{
                        System.out.printf("테스트 %s 실패: 기대한 예외 %s, 발생한 예외 %s%n", m, excType.getName(), exc);
                    }
                    continue;
                } catch (Exception exception) {
                    System.out.println("잘못 사용한 @Test: " + m);
                    continue;
                }
                System.out.println("예외가 발생하지 않은 케이스");
            }
        }
        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }
}
