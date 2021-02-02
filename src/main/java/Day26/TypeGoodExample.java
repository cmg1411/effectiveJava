package Day26;

import java.util.ArrayList;
import java.util.List;

public class TypeGoodExample {
    private List<Paper> stamp = new ArrayList<>();

    public List<Paper> getStamp() {
        return stamp;
    }

    static class Coin { }
    static class Paper { }

    public static void main(String[] args) {
        TypeGoodExample r = new TypeGoodExample();
        //r.getStamp().add(new Coin()); // 컴파일 타임에 오류를 알 수 있다.
    }
}
