package Day20.templateMethod;

import java.util.AbstractList;

/**
 * 이 클래스는 이미 상속을 받았어서 추상 골격 구현을 사용할 수 없다.
 *
 * 그렇다면 private 내부 클래스를 만들어 흉내낼 수 있다.
 */
public class MusicianCannotExtend extends AbstractList implements Interface {
    private AbstractC abstractC = new AbstractC(); // 인스턴스를 통해 private 내부 클래스를 가져온다.

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int plus(int a, int b) {
        return abstractC.plus(a, b); // 내부 메서드 호출해서 사용
    }

    private class AbstractC extends AbstractInterface {

        @Override
        public int plus(int a, int b) {
            return a + b;
        }
    }
}
