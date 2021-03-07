package Chapter7.Day47;

import java.util.Iterator;
import java.util.stream.Stream;

public class ProcessEx {

    public static void main(String[] args) {
        for (ProcessHandle allProcess : iterableOf(ProcessHandle.allProcesses())) {
        }
    }

    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }
}
