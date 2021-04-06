package Chapter12.Day89;

import java.io.Serializable;
import java.util.Arrays;

public class Elvis implements Serializable {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { }
    private String[] favoriteSongs ={ "Hound Dog", "Heartbreak Hotel" };
    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }

    // 싱글톤으로 만들어주는 역할
    private Object readResolve() {
        return INSTANCE;
    }

    public static Elvis getInstance() {
        return INSTANCE;
    }
}
