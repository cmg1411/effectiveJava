package Chapter8.Day52;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodOverloading {

    String name() {
        return "company";
    }

}

class Kakao extends GoodOverloading {
    @Override
    String name() {
        return "카카오";
    }
}

class Google extends GoodOverloading {
    @Override
    String name() {
        return "구글";
    }
}

class Coupang extends GoodOverloading {
    @Override
    String name() {
        return "쿠팡";
    }
}

class Logic {
    public static void main(String[] args) {
        List<GoodOverloading> list = List.of(new Google(), new Coupang(), new Kakao());
        for (GoodOverloading goodOverloading : list) {
            System.out.println(goodOverloading.name());
        }
    }
}
