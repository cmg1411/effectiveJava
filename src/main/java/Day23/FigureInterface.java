package Day23;

public interface FigureInterface {
    // 모든 하위 클래스에서 공통으로 사용하는 데이터 필드

    // 태그에 상관없이 동작이 일정한 메서드들을 default 로

    // 태그에 따라 동작이 달라지는 메서드들을 추상 메서드로
    double area();
}
