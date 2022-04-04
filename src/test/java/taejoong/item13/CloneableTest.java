package taejoong.item13;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CloneableTest {

    @AllArgsConstructor
    @Getter
    static class CloneableImpl implements Cloneable{
        private int a;
        private Object b;

        @Override
        public CloneableImpl clone() throws CloneNotSupportedException {
            return (CloneableImpl) super.clone();
        }
    }

    @AllArgsConstructor
    @Getter
    static class CloneableNotImpl{
        private int a;
        private Object b;

        @Override
        public CloneableNotImpl clone() throws CloneNotSupportedException {
            return (CloneableNotImpl) super.clone();
        }
    }

    @Test
    void clone의_기본_동작은_얕은_복사() throws CloneNotSupportedException {
        CloneableImpl cloneableImpl = new CloneableImpl(10, new Object());
        CloneableImpl clonedCloneableImpl = cloneableImpl.clone();

        assertEquals(cloneableImpl.getB(), clonedCloneableImpl.getB());
    }

    @Test
    void Cloneable를_Impl하지_않고_clone_메소드를_호출시_예외가_발생한다() {
        CloneableImpl cloneableImpl = new CloneableImpl(10, new Object());
        CloneableNotImpl cloneableNotImpl = new CloneableNotImpl(10, new Object());

        assertDoesNotThrow(cloneableImpl::clone);
        assertThrows(CloneNotSupportedException.class, cloneableNotImpl::clone);
    }
}
