package Day6;

import java.util.regex.Pattern;

public class Match {
    private static final Pattern ROMAN = Pattern.compile("^(?=[MDCLXVI])M*D?C{0,4}L?X{0,4}V?I{0,4}$");

    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        String s = "hi";
        for (int i = 0; i < 20000000; i++) {
            isRoman(s);
        }
        System.out.println("정규식 반복 생성 : " + (System.currentTimeMillis() - before));

        before = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            isRomanRefactor(s);
        }
        System.out.println("정규식 상수 사용 : " + (System.currentTimeMillis() - before)); // 10배 빠름
    }

    /**
     * matches 메소드는 내부적으로 정규식을 가지고 Pattern 객체를 만들어 비교한다.
     * 아래의 메소드를 반복하여 실행하면, 같은 정규식을 가지고 같은 객체를 여러번 실행한다.
     */
    private static boolean isRoman(String s) {
        return s.matches("^(?=[MDCLXVI])M*D?C{0,4}L?X{0,4}V?I{0,4}$");
    }

    /**
     * 상수를 이용한 리펙토링 버전
     */
    private static boolean isRomanRefactor(String s) {
        return ROMAN.matcher(s).matches();
    }
}
