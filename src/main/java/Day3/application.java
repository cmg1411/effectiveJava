
package Day3;

import java.util.function.Supplier;

public class application {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.INSTANCE;
        Singleton singleton2 = Singleton.INSTANCE;

        System.out.println(singleton1 == singleton2);

        /**
         *  정적 팩터리 방식
         *  1. 클라이언트 코드(API)를 바꾸지 않고 싱글턴이 아니게 바꿀 수 있다.
         *  2. 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다. (아이템 30에서 볼 것이다.)
         *  3. 정적 팩터리의 메서드 참조를 Supplier 함수형 인터페이스를 이용하여 사용할 수 있다.
         *
         *  4. 하지만 Java 직렬화에서 모든 인스턴스 필드에 transient 선언, 그리고 readResolve 메서드를 제공하지 않으면
         *     역직렬화 과정에서 새로운 인스턴스가 만들어진다.
         */
        Singleton2 singleton3 = Singleton2.getInstance();
        Singleton2 singleton4 = Singleton2.getInstance();

        System.out.println(singleton3 == singleton4);
        // 두 방법 모두 리플렉션에 의해 뚫릴 수 있다.
        // 객체를 생성하는 곳에서 갯수를 세어 예외를 던질 수 있다.

        Supplier<Singleton2> sup = Singleton2::getInstance;
        Singleton2 singleton5 = sup.get();
        Singleton2 singleton6 = sup.get();

        System.out.println("메서드 레퍼런스 : " + (singleton5 == singleton6));

        /**
         * Enum을 이용한 인스턴스 생성은 리플렉션에도 안전하고
         * 멀티 스레드 환경에서도 안전하고
         * 역직렬화 과정에서도 안전하다.
         *
         * Enum 은 상속이 불가능하므로 상속을 사용할 수 없는 단점이 있다.
         * Interface 구현은 가능하다.
         */
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        int sum = enumSingleton.add(1, 3);
        System.out.println(sum);
    }
}
