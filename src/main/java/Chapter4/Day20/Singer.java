package Chapter4.Day20;

import java.applet.AudioClip;

/**
 *  인터페이스는 계층구조가 없는 타입 프레임워크를 만들 수 있다.
 */
public interface Singer {

    default void printLog() {
        System.out.println("This is log.");
    }

    AudioClip sing(String song);
}
