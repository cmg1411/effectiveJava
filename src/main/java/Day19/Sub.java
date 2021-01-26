package Day19;

import java.time.Instant;

public class Sub extends Main {
    private final Instant instant;

    public Sub() {
        this.instant = Instant.now();
    }

    @Override
    public void method() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub s = new Sub(); // 책에서는 이 단계에서 Sub 클래스의 인스턴스를 생성하기 전에 상위 클래스의 생성자를 호출하여 null 을 출력한다.
        s.method();
    }
}
