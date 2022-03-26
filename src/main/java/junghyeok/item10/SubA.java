package junghyeok.item10;

import java.util.Objects;

public class SubA implements Super{

    private final int price;
    private final int count;

    public SubA(final int price, final int count) {
        this.price = price;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubA subA = (SubA) o;
        return price == subA.price && count == subA.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, count);
    }
}
