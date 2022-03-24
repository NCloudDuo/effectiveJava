package junghyeok.item5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Mosaic {

    private final List<Tile> tiles;

    public Mosaic(final Supplier<? extends Tile> tileFactory) {
        tiles = new ArrayList<>();
        for(int i=0; i<5; i++){
            tiles.add(tileFactory.get());
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
