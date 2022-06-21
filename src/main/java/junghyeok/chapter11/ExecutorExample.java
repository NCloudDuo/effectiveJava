package junghyeok.chapter11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(()-> System.out.println("태스크를 실행합니다."));
        exec.shutdown(); // 태스크를 넘기는 방법이다.
    }
}
