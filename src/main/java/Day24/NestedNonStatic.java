package Day24;

import java.util.TreeMap;

public class NestedNonStatic {
    private String school;
    private InnerClass in;

    private class InnerClass {
        private String name;
        private int age;

        public InnerClass(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void getName() {
            System.out.println(NestedNonStatic.this);
        }

        public int getAge() {
            return age;
        }
    }

    public void createInnerInstance() {
        in = new InnerClass("tomas", 10);
    }
}
