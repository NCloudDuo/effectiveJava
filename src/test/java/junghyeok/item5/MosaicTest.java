package junghyeok.item5;

import junghyeok.chapter2.item5.Mosaic;
import junghyeok.chapter2.item5.SubTileA;
import junghyeok.chapter2.item5.Tile;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

class MosaicTest {

    @Test
    void 팩터리_메서드의존성주입_테스트(){
//        Supplier<? extends Tile> tileSupplier = SubTileA::new;
        Supplier<? extends Tile> tileSupplier = ()->new SubTileA();
        Mosaic mosaic = new Mosaic(tileSupplier);
        List<Tile> tiles = mosaic.getTiles();
    }
}