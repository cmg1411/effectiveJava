package Day17;

public final class Person implements Cloneable {
    private final String name;
    private final int age;
    private final String[] family;

    public Person(String name, int age, String[] family) {
        this.name = name;
        this.age = age;
        this.family = family;
    }

    public Person getOlder() {
        return new Person(this.name, this.age + 1, this.family);
    }

    public String[] getFamily() {
        return family.clone();
    }

//    @Override
//    protected Object clone() {
//        try {
//            Person result = (Person) super.clone();
//            result.family = this.family.clone();
//            return result;
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }
}
