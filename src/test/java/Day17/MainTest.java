package Day17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class MainTest {
    BigDecimal tenUnder1 = null;
    BigDecimal tenUnder2 = null;
    BigDecimal tenOver1 = null;
    BigDecimal tenOver2 = null;

    @Before
    public void 설정() {
        tenUnder1 = BigDecimal.valueOf(9);
        tenUnder2 = BigDecimal.valueOf(9);
        tenOver1 = BigDecimal.valueOf(12);
        tenOver2 = BigDecimal.valueOf(12);
    }

    @Test
    public void BigDecimal에서_10이하는_정적팩터리사용() {
        Assert.assertSame(tenUnder1, tenUnder2);
        Assert.assertEquals(System.identityHashCode(tenUnder1), System.identityHashCode(tenUnder2));
    }

    @Test
    public void BigDecimal에서_10초과는_새로운객체생성() {
        Assert.assertNotSame(tenOver1, tenOver2);
        Assert.assertNotEquals(System.identityHashCode(tenOver1), System.identityHashCode(tenOver2));
    }
}