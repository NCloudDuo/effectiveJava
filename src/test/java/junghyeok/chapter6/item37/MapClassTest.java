package junghyeok.chapter6.item37;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MapClassTest {

    List<Plant> garden = List.of(new Plant("식물1", Plant.LifeCycle.ANNUAL), new Plant("식물2", Plant.LifeCycle.PERENNIAL));

    @Test
    @DisplayName("Stream -> HasmMap으로 생성")
    void test1(){
        Map<Plant.LifeCycle, Set<Plant>> collect = garden.stream().collect(Collectors.groupingBy(Plant::getLifeCycle, Collectors.toSet()));
        Assertions.assertEquals(collect.getClass(), HashMap.class);
        Assertions.assertEquals(collect.size(), 2, "garden에서 LifeCycle이 2가지 종류밖에 없으므로");
    }

    @Test
    @DisplayName("Stream -> EnuimMap으로 생성")
    void test2(){
        Map<Plant.LifeCycle, Set<Plant>> collect = garden.stream().collect(Collectors.groupingBy(Plant::getLifeCycle, () -> new EnumMap<>(Plant.LifeCycle.class), Collectors.toSet()));
        Assertions.assertEquals(collect.getClass(), EnumMap.class);
        Assertions.assertEquals(collect.size(),2, "garden에서 LifeCycle이 2가지 종류밖에 없으므로.., 근데 책에서의 설명은 3가지의 Map이 나와야하는것 같은데 이상하다 이거 질문");
    }
}
