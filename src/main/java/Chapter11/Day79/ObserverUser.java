package Chapter11.Day79;

import java.util.HashSet;

public class ObserverUser {

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

//        set.addObserver((a, e) -> System.out.println(e + "구독자"));
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
