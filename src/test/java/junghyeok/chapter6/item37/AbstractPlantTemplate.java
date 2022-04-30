package junghyeok.chapter6.item37;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract public class AbstractPlantTemplate {

    @Test
    void templateMethod(){
        List<Plant> garden = createGarden();

        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = getMapHook(garden);

        Set<Plant> annualPlants = Set.of(garden.get(0), garden.get(3));
        Set<Plant> biennialPlants = Set.of(garden.get(1));
        Set<Plant> perennialPlants = Set.of(garden.get(2), garden.get(4));

        assertEquals(plantsByLifeCycle.get(Plant.LifeCycle.ANNUAL), annualPlants);
        assertEquals(plantsByLifeCycle.get(Plant.LifeCycle.BIENNIAL), biennialPlants);
        assertEquals(plantsByLifeCycle.get(Plant.LifeCycle.PERENNIAL), perennialPlants);
    }

    private List<Plant> createGarden(){
        return List.of(new Plant("식물1", Plant.LifeCycle.ANNUAL), new Plant("식물2", Plant.LifeCycle.BIENNIAL), new Plant("식물3", Plant.LifeCycle.PERENNIAL),
                new Plant("식물4", Plant.LifeCycle.ANNUAL), new Plant("식물5", Plant.LifeCycle.PERENNIAL));
    }

    protected abstract Map<Plant.LifeCycle, Set<Plant>> getMapHook(List<Plant> garden);
}
