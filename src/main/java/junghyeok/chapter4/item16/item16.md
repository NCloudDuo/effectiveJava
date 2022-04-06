# public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

public 클래스의 멤버변수들은 getter를 이용해서 접근을 하자.  

단, package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출해도 문제가 없다.

```java
public class ThreeDimension {
    private final TwoDimension twoDimension;
    private final int z;

    public ThreeDimension(TwoDimension twoDimension, int z) {
        this.twoDimension = twoDimension;
        this.z = z;
    }

    public void output(){
        System.out.println(twoDimension.x+", "+ twoDimension.y+", "+z);
    }

    private static class TwoDimension{
        int x;
        int y;
    }
}

```