package junghyeok.chapter7.item46;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlbumCollectorExample {

    public static void main(String[] args) {

        List<Album> albumList = List.of(
                new Album(new Artist("소녀시대"), 100, "1집"),
                new Album(new Artist("소녀시대"), 150, "2집"),
                new Album(new Artist("빅뱅"), 300, "1집"),
                new Album(new Artist("빅뱅"), 400, "2집")
        );

        Map<Artist, Album> topHits = albumList.stream().collect(Collectors.toMap(album -> album.getArtist(), Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(album -> album.getSales()))));//메서드 참조를 사용하지 않음. 람다가 아직은 익숙하기 때문에

        System.out.println(topHits); //Artist에서 equalsAndHashCode를 정의해놓았기때문에, [{소녀시대,150,2집},{빅뱅,400,2집}] 이나옵니다.

        Map<Artist, Album> firstAlbumByArtist = albumList.stream().collect(Collectors.toMap(album -> album.getArtist(),
                Function.identity(),
                (oldAlbum, newAlbum) -> oldAlbum));

        System.out.println(firstAlbumByArtist); // 첫앨범이 출력됩니다. [{소녀시대,100,1집}, {빅뱅,300, 1집}]

        ConcurrentHashMap<Artist, Album> collect = albumList.stream()
                .collect(Collectors.toMap(Album::getArtist,
                        Function.identity(),
                        (o1, o2) -> o1,
                        ConcurrentHashMap::new)); //map supplier를 제공합니다.

        String girlsGenerationAlbumName = albumList.stream().filter(album -> album.getArtist().getArtistName().equals("소녀시대"))
                .map(album -> album.getAlbumName())
                .collect(Collectors.joining(", "));//좋은 예시는 아닙니다. 지금은 다 객체들이 final로 선언되어있고, 생성자에서 다 넣어줬기때문에 NPE가 나지 않지만, nullable일 경우 filter에서 null이 터질 수 있습니다.

        System.out.println(girlsGenerationAlbumName);
    }
}
