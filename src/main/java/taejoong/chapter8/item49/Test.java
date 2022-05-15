package taejoong.chapter8.item49;

import java.util.Objects;

public class Test {

    /**
     * description
     *
     * @param
     * @return
     * @throws
     */

    public void test(Integer idx) {
        Objects.requireNonNull(idx);
        if(idx<0) throw new IndexOutOfBoundsException();
    }
}
