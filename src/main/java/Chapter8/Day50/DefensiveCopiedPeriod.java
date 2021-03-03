package Chapter8.Day50;

import java.util.Date;

public class DefensiveCopiedPeriod {
    private final Date start;
    private final Date end;

    public DefensiveCopiedPeriod(Date start, Date end) {
//        this.start = new Date(start.getTime());
//        this.end = new Date(end.getTime());
        this.start = (Date) start.clone();
        this.end = (Date) end.clone();

        if (start.compareTo(end) > 0) {
            throw new IllegalStateException(start + "가 " + end + " 보다 늦을 수 없습니다.");
        }
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
