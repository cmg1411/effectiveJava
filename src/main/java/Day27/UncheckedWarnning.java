package Day27;

import java.util.HashSet;
import java.util.Set;

public class UncheckedWarnning {
    public static void main(String[] args) {
        // Set<Integer> set = new HashSet(); // Unchecked assignment: 'java.util.HashSet' to 'java.util.Set<java.lang.Integer>'
        Set<Integer> set = new HashSet<>();
    }
}
