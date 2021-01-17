package Day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * x.equals(y) true
 * y.equals(x) true
 */
public class 대칭성 {
    public static void main(String[] args) {
        CaseInsensitiveString str1 = new CaseInsensitiveString("Hi");
        String str2 = "hi";

        // 대칭성 위반
        System.out.println(str1.equals(str2));
        System.out.println(str2.equals(str1));

        List<CaseInsensitiveString> caseList = new ArrayList<>();
        caseList.add(str1);

        // 대칭섣을 위반하여 다른객체가 어떻게 반응하는지 알 수 없다.
        // contains 는 equals를 사용하기에 구현하기 나름으로 결과가 나옴
        System.out.println(caseList.contains(str2));
    }

    static class CaseInsensitiveString {
        private final String s;

        public CaseInsensitiveString(String s) {
            this.s = s;
        }


        /**
         * String 과 비교하면 항상 false 도록 구현하면
         * 대칭성이 지켜진다.
         */
        @Override
        public boolean equals(Object o) {
            return o instanceof CaseInsensitiveString &&
                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
        }

        /**
         * String 과 비교하려니 대칭성이 맞지 않다.
         * String 에 정의된 equals() 가 있기 때문에.
         */
//        @Override
//        public boolean equals(Object o) {
//            if (o instanceof CaseInsensitiveString) {
//                return s.equalsIgnoreCase(
//                    ((CaseInsensitiveString) o).s);
//            }
//            if (o instanceof String) {
//                return s.equalsIgnoreCase((String) o);
//            }
//            return false;
//        }

        @Override
        public int hashCode() {
            return Objects.hash(s);
        }
    }
}
