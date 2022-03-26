package junghyeok.item14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class BigDecimalTest {

    @Test
    void test() {
        Set<BigDecimal> set = new HashSet<>();
        set.add(new BigDecimal("1.0"));
        set.add(new BigDecimal("1.00"));
        Assertions.assertEquals(2, set.size());

        Set<BigDecimal> treeSet = new TreeSet<>(set);
        Assertions.assertEquals(1, treeSet.size());
    }
}
