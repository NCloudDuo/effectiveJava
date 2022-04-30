package junghyeok.chapter6.item37;

import java.util.*;

public class AbstractPlantTemplateForLoop extends AbstractPlantTemplate {

    @Override
    protected Map<Plant.LifeCycle, Set<Plant>> getMapHook(List<Plant> garden) {
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
        Arrays.stream(Plant.LifeCycle.values()).forEach(lifeCycle -> plantsByLifeCycle.put(lifeCycle, new HashSet<>()));

        for(Plant p : garden){
            plantsByLifeCycle.get(p.getLifeCycle()).add(p);
        } // for loop 이용

        return plantsByLifeCycle;
    }
}
