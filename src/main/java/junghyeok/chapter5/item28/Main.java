package junghyeok.chapter5.item28;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Sub[] subs = new Sub[5];
        Main.output(subs);

        List<Sub> subList = new ArrayList<>();
//        genericOutput(subList); 컴파일오류
    }

    static void output(Super[] arr){
        for(Super superItem : arr){
            System.out.println(superItem);
        }
    }

    static void genericOutput(List<Super> superList){
        superList.forEach(System.out::println);
    }
}
