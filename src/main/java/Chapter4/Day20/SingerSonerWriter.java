package Chapter4.Day20;

/**
 * 인터페이스는 다중상속이 가능하다.
 *
 * Singer, SongWriter 모두를 확장하고 새로운 메서드까지 추가한 제 3의 인터페이스를 정의할 수 있다.
 */
interface SingerSongWriter extends Singer, SongWriter {
    void busking(String instrument);

    /**
     * 디폴트 메서드는 아래에서 오버라이딩이 가능한 메서드다.
     *
     * 따라서 상속에서와 마찬가지로 @implspec 을 통해 문서화를 한다.
     *
     * @implSpec documentation
     * Object 클래스의 equals, hashCode 는 default 로 재정의하면 안된다.
     */
    @Override
    default void printLog() {
        System.out.println("This is log implemented.");
    }

}
