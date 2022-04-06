package junghyeok.chapter4.item19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CookingRamenTest {

    @Test
    void TemplateMethodPattenTest(){
        CookingRamen shinRamen = new ShinRamen(); //상위클래스가 흐름의 주체
        Assertions.assertEquals("물을 올립니다.분말스프를넣습니다.그릇에내어서 맛있게 먹습니다.",shinRamen.cookingRamen());

        CookingRamen bibimRamen = new BibimRamen();
        Assertions.assertEquals("물을 올립니다.액상소스를넣습니다.그릇에내어서 맛있게 먹습니다.",bibimRamen.cookingRamen());
    }
}