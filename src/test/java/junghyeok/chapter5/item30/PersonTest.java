package junghyeok.chapter5.item30;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void 재귀적한정타입테스트() {
        List<Person> people = List.of(new Person(10, "name1"), new Person(20, "name2"), new Person(50, "name3"), new Person(40, "name4"));
        Optional<Person> max = RecursiveTypeBoundMethod.max(people);
        assertTrue(max.isPresent(), "값이 존재합니다");
        Person person = max.get();
        assertEquals(person.getName(), "name3", "나이로 비교하기때문에 name3이 나옵니다.");

        List<Person> emptyList = Collections.emptyList();
        Optional<Person> max1 = RecursiveTypeBoundMethod.max(emptyList);
        assertTrue(max1.isEmpty(), "비어있기 때문에 Optional.empty를 반환합니다.");
    }
}