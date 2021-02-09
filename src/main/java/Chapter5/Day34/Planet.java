package Chapter5.Day34;

import java.util.List;

public enum  Planet {
    SUN(1),
    EARTH(2),
    MARS(3);

    private int num;
    private List<Planet> list;
    private Planet(int num) {
        /**
         * 열거 타입 생성자가 실행되는 시점은 정적 필드들이 아직 초기화 되기 전이다.
         * 필드의 초기화는 생성자에서 이루어지는데, 필드의 초기화가 끝나야 생성자가 끝났다고 말할 수 있다.
         * 즉, this 를 사용할 수 있는 시점은 생성자가 다 실행된 후이다.
         * 따라서 생성자에서 다른 상수도 접근할 수 없다.
         */
        // list.add(this);
        // list. add(MARS);
        this.num = num;
    }
}


class main {
    public static void main(String[] args) {
        Planet.values();
    }
}