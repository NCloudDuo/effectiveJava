package junghyeok.item16;

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
