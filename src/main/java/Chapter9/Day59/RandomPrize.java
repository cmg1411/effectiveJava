package Chapter9.Day59;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPrize {

    enum DayPrice {
        SUN(1, "베스킨라빈스", 0.3),
        MON(2, "비타500", 1.0),
        THU(3, "비타500", 1.0),
        WEN(4, "비타500", 1.0),
        TUR(5, "비타500", 1.0),
        FRI(6, "비타500", 1.0),
        SAT(7, "스타벅스 아메리카노", 0.3);

        private int dayOfWeek;
        private String prize;
        private double winningRate;

        DayPrice(int dayOfWeek, String prize, double winningRate) {
            this.dayOfWeek = dayOfWeek;
            this.prize = prize;
            this.winningRate = winningRate;
        }
    }

    enum WinningStatus {
        WIN, NO;
    }

    private DayPrice dayPrice;

    public RandomPrize() {
        Calendar calendar = Calendar.getInstance();
        Optional<DayPrice> todayPrize = Arrays.stream(DayPrice.values())
            .filter(day -> day.dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK))
            .findAny();
        this.dayPrice = todayPrize.orElseThrow();
    }

    public WinningStatus draw() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int drawNum = random.nextInt(1000) + 1;
        if (drawNum <= (1000*dayPrice.winningRate/100)) {
            return WinningStatus.WIN;
        } else {
            return WinningStatus.NO;
        }
    }
}
