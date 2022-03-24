package junghyeok.item6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdapterTest {

    @Test
    void 어댑터테스트(){
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'c');
        map.put(2, 'd');

        Set<Integer> set1 = map.keySet();
        Set<Integer> set2 = map.keySet();
        Assertions.assertEquals(set1, set2);
        Assertions.assertEquals(2, set1.size());

        //keySet 인스턴스에서 1을 지운다
        set1.remove(1);

        //그러고나서 map에서 keySet을 하나더 뽑아본다.
        Set<Integer> setAfterRemoveKey1 = map.keySet();

        //미리 뽑아놨던 set1에서 remove를 했음에도 불구하고, 새로뽑은 키셋의 사이즈가 1임을 알 수 있다.
        Assertions.assertEquals(setAfterRemoveKey1.size(),1);

        //1을 삭제하니 2만남아있음.
        Assertions.assertEquals(2,setAfterRemoveKey1.stream().findFirst().get());
    }
}
