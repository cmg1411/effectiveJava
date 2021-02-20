package Chapter6.Day40;

import java.util.HashSet;
import java.util.Set;

public class OverrideError {
    private final char first;
    private final char second;

    public OverrideError(char first, char second) {
        this.first = first;
        this.second = second;
    }

    //@Override
    public boolean equals(OverrideError o) {
        return o.first==this.first&&o.second==this.second;
    }

    public static void main(String[] args) {
        Set<OverrideError> s = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                s.add(new OverrideError(j, j));
            }
        }

        /**
         * Set 은 equals() 를 통해 중복을 제거하기때문에 equals() 를 사용했다.
         * 하지만 실수로 매개변수를 Object 로 받지 않아서 재정의가 아닌 오버로딩이 되어 버렸다.
         *
         * Set 원소들의 동일성 검사를 재정의했다고 생각한 equals 가 아니라 객체주소값을 비교하는 Object 클래스의 equals 가 사용된 것이다.
         *
         * 따라서 예상한 26이 아니라 260이 나왔다.
         *
         * 만약 equals 메서드에 @Override 를 달면 이는 정상적인 오버라이딩이 아니라는 것을 알려줬을텐데 말이다.
         */
        System.out.println(s.size());
    }
}
