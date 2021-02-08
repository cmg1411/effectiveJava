package Chapter5.Day27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @SuppressWarnings("unchecked") 는 비검사경고를 없애준다.
 * 경고를 제거하도록 노력했지만, 경고를 제거할 수 없는 상황에
 * 타입이 안전하다고 확신할 수 있을 떄 이 에노테이션으로 경고를 제거한다.
 *
 * 안전함을 검증하지 않은 채 이 에노테이션으로 경고를 무시하는 것은 컴파일은 되겠지만 런타임에 예외를 던질 수 있다.
 *
 * 반면 안전한데도 에노테이션을 쓰지 않으면 다른 경고가 의미가 없어진 비검사경고에 가려져 새로운 경고를 놓칠 수 있다.
 */
public class SuppressWarning {
    @SuppressWarnings("unchecked")
    private List<Integer> list = new ArrayList();

    Object[] elements;

    /**
     * unchecked warning 이 발생한다.
     *
     * 어노테이션은 선언에만 달 수있으므로 새로운 지역변수를 하나 선언해야 한다.
     *
     * 이 지역변수는 그만한 값어치를 한다.
     * @SuppressWarning 으로 경고를 무시한다면, 그 이유를 주석으로 꼭 남긴다.
     */
//    public <T> T[] toArray(T[] a) {
//        if (a.length < 10) {
//           return (T[]) Arrays.copyOf(elements, 10, a.getClass());
//        }
//        System.arraycopy(elements, 0, a, 0, 10);
//        if (a.length > 10) {
//            a[10] = null;
//        }
//        return a;
//    }

    public <T> T[] toArray(T[] a) {
        if (a.length < 10) {
            // 생성한 배열과 매개변수로 받은 배열의 타입이 모두 T[] 로 같으므로
            // 올바른 형 변환이다.
            @SuppressWarnings("unchecked")
            T[] result = (T[]) Arrays.copyOf(elements, 10, a.getClass());
            return result;
        }
        System.arraycopy(elements, 0, a, 0, 10);
        if (a.length > 10) {
            a[10] = null;
        }
        return a;
    }
}
