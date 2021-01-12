package Day5;

import java.util.Arrays;
import java.util.List;

public class SpellChecker {
    private final Lexicon dictionary;

    /**
     * 사용하는 자원에 따라 동작이 달라지는 클래스는 정적 유틸리티 클래스나 싱글톤방식이 적절하지 않다.
     *
     * 인스턴스를 생성할 때, 생성자에서 필요한 자원을 넘겨받아 설정한다.
     * Lexicon 인터페이스를 구현한 구체 클래스를 인수로 받는다.
     * Lexicon 을 구현한 어떤 클래스든 바꿔끼울 수 있다.
     *
     * 이 방식을 응용한 것이 팩토리 메서드 패턴이다.
     * 팩토리 메서드 패턴은 생성자에 팩토리 메서드를 전달한다.
     *
     * @param dictionary
     */
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValid(String word) {
        return false;
    }

    public List<String> suggestions(String type) {
        return Arrays.asList("hi", "my");
    }
}
