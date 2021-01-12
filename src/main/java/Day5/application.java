package Day5;

import java.util.function.Supplier;

public class application {
    public static void main(String[] args) {
        Lexicon lx = new koreaDic();
        SpellChecker sp = new SpellChecker(lx);

        SpellCheckerSupplier spc = new SpellCheckerSupplier(() -> lx);
    }
}
