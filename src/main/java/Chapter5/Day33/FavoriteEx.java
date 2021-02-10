package Chapter5.Day33;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FavoriteEx {
    private Map<Class<?>, Object> favoriteMap = new HashMap<>();

    /**
     * 그 값이 그 타입의 인스턴스라는 정보는 사라지나, get 에서 이 관계를 되살릴 수 있다.
     * 불변식을 보장하기 위하여 넣을떄도 동적 형변환을 통해 넣어준다.
     */
    public <T> void putFavorite(Class<T> type, T instance) {
        favoriteMap.put(Objects.requireNonNull(type), type.cast(instance));
    }

    /**
     * 객체 타입이 Object 이므로 키 타입으로 바꿔서 리턴해야 한다.
     * Class 의 캐스트 메서드 이용.
     *
     * 비검사 형변환이 아닌 이점을 이용할 수 있다.
     * 타입이 안맞으면 예외를 던지는 메서드이니까.
     */
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favoriteMap.get(type));
    }

    public static void main(String[] args) {
        FavoriteEx f = new FavoriteEx();
        f.putFavorite(String.class, "가");
        f.putFavorite(String.class, "나");
        f.putFavorite(Integer.class, 1);

        System.out.println(f.getFavorite(String.class));
        System.out.println(f.getFavorite(Integer.class));
    }
}
