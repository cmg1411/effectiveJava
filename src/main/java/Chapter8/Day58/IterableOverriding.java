package Chapter8.Day58;

import java.util.*;

public class IterableOverriding {

    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>(List.of("가", "나", "다"));

        list.removeIf(val -> val.equals("나"));

        for (Iterator<String> i = list.iterator(); i.hasNext(); ) {
            if (i.next().equals("다")) {
                i.remove();
            }
        }

        for (String s : list) {
            System.out.println(s);
        }
    }
}
