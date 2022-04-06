package junghyeok.chapter4.item19;

public abstract class CookingRamen {

    public final String cookingRamen(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("물을 올립니다.");
        stringBuilder.append(hookMethod());
        stringBuilder.append("그릇에내어서 맛있게 먹습니다.");
        return stringBuilder.toString();
    }

    abstract protected String hookMethod();
}
