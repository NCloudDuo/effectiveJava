package taejoong.chapter6.item36;

import java.util.*;

public class
Test {
    public static void main(String[] args) {
        // 비트 필드
        TextWithBitField twbf = new TextWithBitField();
        twbf.applyStyles(9);

        // EnumSet
        TextWithEnumSet twes = new TextWithEnumSet();
        twes.applyStyles(EnumSet.of(TextWithEnumSet.Style.BOLD, TextWithEnumSet.Style.STRIKETHROUGH));

        // 불변 EnumSet
        EnumSet<TextWithEnumSet.Style> enumSet = EnumSet.of(TextWithEnumSet.Style.BOLD, TextWithEnumSet.Style.STRIKETHROUGH);
        Set<TextWithEnumSet.Style> styles = Collections.unmodifiableSet(enumSet);
        try {
            styles.add(TextWithEnumSet.Style.UNDERLINE);
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException 발생");
        }
    }

    static class TextWithBitField{
        public static final int STYLE_BOLD          = 1<<0;
        public static final int STYLE_ITALIC        = 1<<1;
        public static final int STYLE_UNDERLINE     = 1<<2;
        public static final int STYLE_STRIKETHROUGH = 1<<3;

        public void applyStyles(int styles) {
            List<String> styleList = new ArrayList<>();

            if ((styles & STYLE_BOLD) != 0) {
                styleList.add("STYLE_BOLD");
            }
            if ((styles & STYLE_ITALIC) != 0) {
                styleList.add("STYLE_ITALIC");
            }
            if ((styles & STYLE_UNDERLINE) != 0) {
                styleList.add("STYLE_UNDERLINE");
            }
            if ((styles & STYLE_STRIKETHROUGH) != 0) {
                styleList.add("STYLE_STRIKETHROUGH");
            }

            System.out.println("styleList = " + styleList);
        }
    }

    static class TextWithEnumSet{
        public enum Style{ BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

        public void applyStyles(Set<Style> styleSet) {
            List<Style> styleList = new ArrayList<>(styleSet);
            System.out.println("styleList = " + styleList);
        }
    }
}