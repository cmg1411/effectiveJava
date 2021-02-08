package Chapter2.Day5;

import java.util.function.Supplier;

public class SpellCheckerSupplier {
    private final Lexicon dictionary;

    // 팩터리 매서드 패턴을 Supplier 함수형 인터페이스를 이용한 방식
    public SpellCheckerSupplier(Supplier<Lexicon> dictionary) {
        this.dictionary = dictionary.get();
    }
}
