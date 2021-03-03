package Chapter8.Day50;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UsePeriod {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Date start = new Date();
        Date end = new Date();
        Period p1 = new Period(start, end);
        DefensiveCopiedPeriod p2 = new DefensiveCopiedPeriod(start, end);
        end.setTime(1000000000);

        System.out.println("<방어적 복사 안함>\n" + "시작시간 : " + dateFormat.format(p1.getStart()) + "\n끝시간 : " + dateFormat.format(p1.getEnd()));
        System.out.println("<방어적 복사 함>\n" + "시작시간 : " + dateFormat.format(p2.getStart()) + "\n끝시간 : " + dateFormat.format(p2.getEnd()));


        Date start2 = new SubDate();
        Date end2 = new SubDate();
        DefensiveCopiedPeriod period = new DefensiveCopiedPeriod(start2, end2);
        List<Date> hackersList = ((SubDate) (period.getEnd())).getHackersList();
        hackersList.get(0).setTime(100000);

        System.out.println("<방어적 복사 함>\n" + "시작시간 : " + dateFormat.format(period.getStart()) + "\n끝시간 : " + dateFormat.format(period.getEnd()));
    }
}
