package Chapter5.Day30;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Chapter5.Day30.LottePayCard.Sale.LOTTE_DEPT;
import static org.junit.jupiter.api.Assertions.*;

class LottePayCardTest {


    @DisplayName("시뮬레이트한 셀프타입 관용구")
    @Test
    public void simulatedTest() {
        LottePayCard lottePayCard = new LottePayCard.Builder(LOTTE_DEPT)
            .addBenefit(PayCard.Benefit.POINT)
            .build();
    }
}