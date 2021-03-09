package Chapter8.Day54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheeseContainer {

    private List<Cheese> cheeseBox = List.of(new Cheese());


    public List<Cheese> getCheeseBox() {
        return new ArrayList<>(cheeseBox);
    }

    public Cheese[] getCheeseArray() {
        return cheeseBox.toArray(new Cheese[0]);
    }
}

class Cheese {

    private String name = "까망베르";

    public String getName() {
        return name;
    }
}

class Run {
    public static void main(String[] args) {
        CheeseContainer c = new CheeseContainer();
        System.out.println(c.getCheeseBox());
        for (Cheese cheese : c.getCheeseArray()) {
            System.out.println(cheese);
        }
    }
}