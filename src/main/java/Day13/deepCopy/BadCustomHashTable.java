package Day13.deepCopy;

public class BadCustomHashTable implements Cloneable {

    private Entry[] buckets = new Entry[16];
    private int length = 0;

    /**
     * 배열도 가변 참조 객체
     * 배열의 안의 요소 Entry 도 가변 참조 객체
     *
     * 따라서 이렇게 clone 하면 다른 배열이 같은 연결리스트를 참조
     */
    @Override
    public BadCustomHashTable clone() {
        try {
            BadCustomHashTable result = (BadCustomHashTable) super.clone();
            result.buckets = buckets.clone();
            return result;
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    

}
