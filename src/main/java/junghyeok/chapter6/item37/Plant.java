package junghyeok.chapter6.item37;

public class Plant {

    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}

    private final String name;
    private final LifeCycle lifeCycle;

    public Plant(final String name, final LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    public String getName() {
        return name;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }
}
