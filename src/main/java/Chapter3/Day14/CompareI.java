package Chapter3.Day14;

public class CompareI implements Comparable {

    /**
     * equals 와 같이 기존 클래스를 확장한 구체 클래스에서
     * 새로운 값 컴포넌트를 추가했다면
     * 객체지향적 추상화의 이점을 포기하지 않는한 규약을 지킬수없다.
     *
     * 우회법은 확장 대신 독립된 클래스를 만들고
     * 이 클래스에 원래 클래스의 인스턴스를 가리키는 필드를 추가한다.
     * 이후 내부 인스턴스를 반환하는 뷰 메서드 제공.
     *
     *
     * 정렬된 컬렉션들은 동치성을 비교할 때 equals 가 아닌 compareTo 를 사용한다.
     * */
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
