package junghyeok.chapter5.item30;

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
