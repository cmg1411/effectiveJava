package Chapter10.Day75;

public class IndexBoundsOutOfEx extends RuntimeException {

    private final int lowerBound;
    private final int upperBound;
    private final int index;

    public IndexBoundsOutOfEx(int lowerBound, int upperBound, int index) {
        super(String.format("최솟값 :  %d, 최댓값 : %d, 인덱스 : %d", lowerBound, upperBound, index));

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }
}
