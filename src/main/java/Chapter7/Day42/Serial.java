package Chapter7.Day42;

import java.io.*;

public class Serial {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("test.txt");
//        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
//        Person p = new MeetCoder("me", 22);
//        o.writeObject(p);

        ObjectInputStream i = new ObjectInputStream(new FileInputStream(file));
        Person p = (Person) i.readObject();
        System.out.println(p.getName());

        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
        o.writeObject(p);

    }
}

class Person {
    public String getName(){
        return null;
    }
}


class MeetCoder extends Person implements Serializable {
    private final String name;
    private final int number;

    public MeetCoder(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String getName() {
        return name;
    }
}