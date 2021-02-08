package Chapter3.Day13.deepCopy;

public class CustomHashTable implements Cloneable {
    private Entry[] buckets = new Entry[16];
    private int length = 0;

    /**
     *
     */
    @Override
    public CustomHashTable clone() {
        try {
            CustomHashTable result = (CustomHashTable) super.clone();
            result.buckets = new Entry[buckets.length];

            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            }

            return result;
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
