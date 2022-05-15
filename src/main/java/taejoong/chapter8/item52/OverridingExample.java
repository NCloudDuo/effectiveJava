package taejoong.chapter8.item52;

import java.util.ArrayList;
import java.util.List;

public class OverridingExample {
    static class Wine{
        String name() {return "포도주";}
    }

    static class SparklingWine extends Wine{
        @Override
        String name() {return "스파클링 포도주";}
    }

    static class Champagne extends Wine{
        @Override
        String name() {return "샴페인";}
    }

    public static void main(String[] args) {
        List<Wine> wineList = new ArrayList<>();
        wineList.add(new Wine());
        wineList.add(new SparklingWine());
        wineList.add(new Champagne());

        for (Wine wine : wineList) {
            System.out.println(wine.name());
        }
    }
}
