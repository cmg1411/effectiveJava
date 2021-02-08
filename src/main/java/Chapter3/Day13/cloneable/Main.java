package Chapter3.Day13.cloneable;

public class Main {
    public static void main(String[] args) {
        YesCloneable yesCloneable = new YesCloneable();
        NoCloneable noCloneable = new NoCloneable();

        try {
            yesCloneable.clone();
            System.out.println("Clone Complete");
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone failed");
        }

        try {
            noCloneable.clone();
            System.out.println("Clone Complete");
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone failed");
        }
    }
}
