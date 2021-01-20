package Day14;

public class CaseSensitiveString implements Comparable<CaseSensitiveString> {

    private String s;

    @Override
    public int compareTo(CaseSensitiveString o) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, o.s); // String 에서 제공하는 비교자 사용
    }
    /**
     * Double.compare()
     * Float.compare()
     * Integer.compare()
     * 등의 비교자를 쓰자.
     *
     * 아니면 Comparator 사용
     */
}
