package Chapter5.Day26;

import java.util.*;

public class RowBadExample {
    private Collection stamp = new ArrayList();

    public Collection getStamp() {
        return stamp;
    }

    static class Coin { }
    static class Paper { }

    public static void main(String[] args) {
        RowBadExample r = new RowBadExample();
        Collection stamps = r.getStamp(); // Raw use of parameterized class 'Collection'

        stamps.add(new Coin()); // Unchecked call to 'add(E)' as a member of raw type 'java.util.Collection'
        stamps.add(new Paper());

        for (Iterator i = stamps.iterator(); i.hasNext();) {
            Paper paper = (Paper) i.next();
        }
    }
}
