package taejoong.chapter8.item54;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Object> list = Collections.emptyList();
        Set<Object> set = Collections.emptySet();
        Map<Object, Object> map = Collections.emptyMap();

        try {
            // immutable한 빈 객체이므로 insert 시도시 exception 발생
            list.add(new Object());
            set.add(new Object());
            map.put(new Object(), new Object());
        } catch (Exception exception) {
            // exception 인스턴스 메서드 확인해보기
            System.out.println("exception.getMessage() = " + exception.getMessage());
            System.out.println("exception.getLocalizedMessage() = " + exception.getLocalizedMessage());
            StackTraceElement[] stackTrace = exception.getStackTrace();
            System.out.println("exception.getStackTrace() = " + Arrays.toString(stackTrace));
            System.out.println("exception.getClass() = " + exception.getClass());
            System.out.println("exception.getCause() = " + exception.getCause());
            Throwable[] suppressed = exception.getSuppressed();
            System.out.println("exception.getSuppressed() = " + Arrays.toString(suppressed));
            System.out.println("exception.toString() = " + exception.toString());
            System.out.println("exception.fillInStackTrace() = " + exception.fillInStackTrace());
        } finally{
            map.put(new Object(), new Object()); // print 되는 예외 확인해보기용
        }
    }
}
