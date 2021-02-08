package Chapter4.Day25;


/**
 * 클래스에서 톱레벨 클래스를 둘 이상 선언해도 컴파일에러가 안난다. 몰랐다.
 *
 */
class TopLevel {
    static final String NAME = "pan";
}

class Toplevel2 {
    static final String NAME = "cake";
}