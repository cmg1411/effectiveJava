package Chapter8.Day50;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubDate extends Date implements Cloneable {

    private List<Date> hackersList = new ArrayList<>();

    public List<Date> getHackersList() {
        return hackersList;
    }

    @Override
    public Object clone() {
        Date badDate = (Date) super.clone();
        hackersList.add(badDate);
        return badDate;
    }
}
