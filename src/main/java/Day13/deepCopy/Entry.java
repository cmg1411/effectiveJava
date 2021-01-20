package Day13.deepCopy;

public class Entry {
    final Object key;
    Object value;
    Entry next;

    public Entry(Object key, Object value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Entry deepCopy() {
        System.out.println(key);
        return new Entry(key, value,
            next == null ? null : next.deepCopy());
    }
}
