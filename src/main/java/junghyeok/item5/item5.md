# 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

클래스가 하나이상의 자원에 의존하는데, 이 의존하는 객체를 명시하지말고 생성자에 필요한 자원을 넘겨주는 방식으로 구성하는것이 좋다.  
```java
public class SpellChecker{
    private final Lexicon dictionary;
    
    public SpellChecker(final Lexicon dictionary){
        this.dictionary = dictionary;
    }
    
    public boolean isValid(String word){...}
    public List<String> suggestions(String type){...}
}
```

이 패턴의 변형으로는, 생성자에 자원 팩터리를 넘겨주는 방식이 있다.  
팩터리란 호출할 때마다 특정 타입의 인스턴스를 반복해서 만들어주는 객체를 말한다.  
자바 8에서 소개한 Supplier<T> 인터페이스가 팩터리를 표현한 예이고 다음과 같이 예시코드를 만들어봤다.

```java
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

public interface Tile {
}

public class SubTileB implements Tile {
}

public class SubTileA implements Tile {
}

class MosaicTest {

    @Test
    void 팩터리_메서드의존성주입_테스트(){
//        Supplier<? extends Tile> tileSupplier = SubTileA::new;
        Supplier<? extends Tile> tileSupplier = ()->new SubTileA();
        Mosaic mosaic = new Mosaic(tileSupplier);
        List<Tile> tiles = mosaic.getTiles();
    }
}
```

이거 아닐수도있다.. 그냥 해본건데 맞는지 모르겠음

# 이 Item의 중요한 부분
1. 클래스가 내부적으로 하나 이상의 자원에 의존하고, 그자원이 클래스 동작에 영향을 준다면 싱글턴과 정적 유틸리티클래스는 사용하지 않는 것이좋다.  
2. 클래스가 직접 자원들을 만들게 해서도 안된다.  
3. 의존성을 주입받게 되면 모킹객체를 넘겨줄수도있고, 인터페이스형을 필드로 선언해놓고 인터페이스의 구현체를 각 필요에따라 다른것을 넘겨줄 수 있으므로 굉장히 유연해진다.

+ 스프링 사용하다보면 3번이 제일중요한 것 같음. 생성자를 통해서 의존성을 주입받으므로 모킹객체를 넘겨줘서 단위테스트도 가능하고, 인터페이스의 구현체도 갈아끼워넣을 수 있기 때문