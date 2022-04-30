package junghyeok.chapter6.item39;

public class TestSample {
    @Test public static void m1(){

    }
    public static void m2(){}

    @Test public static void m3(){
        throw new RuntimeException("실패");
    }

    public static void m4(){

    }

    @Test public void m5(){} //정적메서드가 아니라서 잘못사용함
    public static void m6(){}
    @Test public static void m7(){
        throw new RuntimeException("실패");
    }

    public static void m8(){}
}
