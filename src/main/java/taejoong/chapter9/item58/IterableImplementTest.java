package taejoong.chapter9.item58;

import java.util.Iterator;

public class IterableImplementTest {
    public static void main(String[] args) {
        CustomList<String> customList = new CustomList<>();

        customList.add("abc");
        customList.add("mno");
        customList.add("pqr");
        customList.add("xyz");

        for (String string : customList)
            System.out.print(string + " ");

        System.out.println();

        for (Iterator<String> it = customList.iterator(); it.hasNext(); ) {
            System.out.print(it.next() + " ");
        }
    }
}