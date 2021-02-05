package Chapter9.Day63;

public class StringEx {

    private static final int LINE_WIDTH = 10000;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringEx stringEx = new StringEx();
        stringEx.statement();
        long stringTime = System.currentTimeMillis() - start;
        System.out.println("String 더하기 시간 : " + stringTime);

        start = System.currentTimeMillis();
        stringEx.statement2();
        long stringBuilderTime = System.currentTimeMillis() - start;
        System.out.println("StringBuilder append 시간 : " + stringBuilderTime);

        System.out.println("StringBuilder 가 String 보다 약 " + stringTime/stringBuilderTime + " 배 빠릅니다.");
    }

    private String statement() {
        String result = "";
        for (int i = 0; i < numItems(); i++) {
            result += lineForItem();
        }
        return result;
    }

    private String statement2() {
        StringBuilder sb = new StringBuilder(LINE_WIDTH * ALPHABET.length());
        for (int i = 0; i < numItems(); i++) {
            sb.append(lineForItem());
        }
        return sb.toString();
    }

    private int numItems() {
        return LINE_WIDTH;
    }

    private String lineForItem() {
        return ALPHABET;
    }
}
