package junghyeok.item11;

import java.util.Objects;

public class Foo {
    private final int value;
    private final Composition composition;

    public Foo(int value, Composition composition) {
        this.value = value;
        this.composition = composition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foo foo = (Foo) o;
        return value == foo.value && Objects.equals(composition, foo.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, composition);
    }
}
