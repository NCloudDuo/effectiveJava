package junghyeok.item2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutritionFactsTest {

    @Test
    void BuilderTest(){
        NutritionFacts coke = new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
        Assertions.assertEquals(240,coke.getServingSize());
        Assertions.assertEquals(8,coke.getServings());
        Assertions.assertEquals(100,coke.getCalories());
        Assertions.assertEquals(35,coke.getSodium());
        Assertions.assertEquals(27,coke.getCarbohydrate());

        //선택매개변수로 입력하지 않은 지방은 0이여야한다.
        Assertions.assertEquals(0, coke.getFat());

    }
}