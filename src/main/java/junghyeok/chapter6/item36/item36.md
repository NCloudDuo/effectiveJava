# 비트필드 대신 EnumSet을 사용하라

### 비트필드 열거 상수를 사용하면 안되는 이유

+ 기존의 정수 열거 상수의 단점을 그대로 갖고있다.
+ 비트들의 조합으로 이루어진 비트필드값의 해석이 매우어렵다.
+ 비트필드 하나에 어떤 비트들이 포함되어있는지도 까다롭다.
+ 최대 몇비트가 필요한지 api작성시, 미리예측해서 type을 선택해야한다.

### EnumSet의 장점

+ Set Interface를 implements했기 때문에, removeAll, retainAll, add등과 같은 method들을 사용가능하다.

```java
import java.util.EnumSet;

public class Text {
    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }

    public void applyStyles(Set<Style> styleSet) {
        //...
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.UNDERLINE));
    }
}
```