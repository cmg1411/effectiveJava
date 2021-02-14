package Chapter6.Day37;

import static Chapter6.Day37.Phase.Transition.*;

public class UsingPhase {
    public static void main(String[] args) {
        System.out.println(from(Phase.SOLID, Phase.LIQUID));
    }
}
