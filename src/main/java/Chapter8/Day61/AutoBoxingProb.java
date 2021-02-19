package Chapter8.Day61;

public class AutoBoxingProb {

    public static void main(String[] args) {
        Long sum = 0L;

        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }
}
