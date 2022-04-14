# 이왕이면 제네릭 메서드로 만들라

### 클래스와 마찬가지로, 메서드도 제네릭으로 만들 수 있다.

+ 기존의 로타입으로 작성된 non-type-safe한 메서드의 예시는 다음과 같다.
    ```java
    public static Set union(Set s1, Set s2){
        Set result= new HashSet(s1);
        result.addAll(s2);
        return result;
    }
    ```

+ 메서드를 제네릭메서드로 바꾸는 방법은 메서드의 제한자와 반환형 앞에 타입매개변수를 표기한다.
    ```java
    public static <E> Set<E> union(Set<E> s1, Set<E> s2){
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }
    ```
  
+ 위의 타입매개변수를 이용한 메서드는 s1, s2, 반환형 전부 모두 타입이 모두 같아야하는데, 이를 한정적 와일드카드 타입(ex `List<? extends Number>` )을 사용해서 더 유연하게 개선할 수 있다.
  + item 31에서 계속..
+ 때때로, 불변 객체를 여러 타입으로 활용할 수 있게 만들어야 할 때가 있다.
  + 제네릭 싱글턴 팩터리라 한다.
  + Collections.emptySet이 그 예다
  + ```java
    public class Collections {
        ...
        public static final Set EMPTY_SET = new EmptySet<>();
    
        public static final <T> Set<T> emptySet() {
            return (Set<T>) EMPTY_SET; //제네릭 싱글턴팩터리 (요청한 타입 매개변수에 맞게 매번 그 객체의 타입을 바꿔주는 방식)
        }
    }
    ```

+ `자기자신이 들어간 표현식을 사용해`, 타입매개변수의 허용범위를 한정할 수 있다. => 재귀적 타입한정
  + ````java
    public class RecursiveTypeBoundMethod {

        public static <E extends Comparable<E>> Optional<E> max(Collection<E> collection) {
            if(collection.isEmpty()){
               return Optional.empty();
            }

            E result = null;
            for(E e : collection){
                if(result==null || e.compareTo(result)>0){
                    result = Objects.requireNonNull(e);
                }
            }

            return Optional.of(result);
        }
    }
    
    public class Person implements Comparable<Person>{

        private final int age;
        private final String name;

        public Person(final int age, final String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(age, o.age);
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
    
    class PersonTest {

        @Test
        void 재귀적한정타입테스트() {
            List<Person> people = List.of(new Person(10, "name1"), new Person(20, "name2"), new Person(50, "name3"), new Person(40, "name4"));
            Optional<Person> max = RecursiveTypeBoundMethod.max(people);
            assertTrue(max.isPresent(), "값이 존재합니다");
            Person person = max.get();
            assertEquals(person.getName(),"name3", "나이로 비교하기때문에 name3이 나옵니다.");

            List<Person> emptyList = Collections.emptyList();
            Optional<Person> max1 = RecursiveTypeBoundMethod.max(emptyList);
            assertTrue(max1.isEmpty(), "비어있기 때문에 Optional.empty를 반환합니다.");
        }
    }
    ````
  + 위의 예처럼 재귀적 타입한정을 사용하면 허용범위를 한정할 수있다.