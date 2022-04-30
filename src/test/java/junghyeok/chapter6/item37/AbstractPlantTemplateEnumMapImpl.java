package junghyeok.chapter6.item37;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AbstractPlantTemplateEnumMapImpl extends AbstractPlantTemplate {
    @Override
    protected Map<Plant.LifeCycle, Set<Plant>> getMapHook(List<Plant> garden) {
        return garden.stream().collect(Collectors.groupingBy(Plant::getLifeCycle, () -> new EnumMap<>(Plant.LifeCycle.class), Collectors.toSet())); //mapFactory에 EnumMap으로구현체를 넣어주는 방식
    }
}
