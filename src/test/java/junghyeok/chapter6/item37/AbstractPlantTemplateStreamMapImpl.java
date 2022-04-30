package junghyeok.chapter6.item37;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AbstractPlantTemplateStreamMapImpl extends AbstractPlantTemplate {

    @Override
    protected Map<Plant.LifeCycle, Set<Plant>> getMapHook(List<Plant> garden) {
        return garden.stream().collect(Collectors.groupingBy(Plant::getLifeCycle, Collectors.toSet())); //기본 구현체인 HashMap생성, EnumMap이점을 살리지 못한다.
    }
}
