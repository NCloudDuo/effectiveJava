package taejoong.chapter9.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class Test {
    public static void main(String[] args) {

        String[] strArray = {"java.util.HashSet", "aaa","bbbb","aaa","ccc"};
//        String[] strArray = {"java.util.TreeSet", "aaa","bbbb","aaa","ccc"};

        Class<? extends Set<String>> cl = null;
        try{
            cl = (Class<? extends Set<String>> )Class.forName(strArray[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor<? extends Set<String>> cons = null;
        try{
            cons = cl.getDeclaredConstructor();
        }  catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Set<String> s = null;
        try{
            s = cons.newInstance();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        s.addAll(Arrays.asList(strArray).subList(1, strArray.length));
        System.out.println("s = " + s);
    }
}
