package Chapter4.Day17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {
    BigDecimal tenUnder1 = null;
    BigDecimal tenUnder2 = null;
    BigDecimal tenOver1 = null;
    BigDecimal tenOver2 = null;

    @BeforeEach
    public void 설정() {
        tenUnder1 = BigDecimal.valueOf(9);
        tenUnder2 = BigDecimal.valueOf(9);
        tenOver1 = BigDecimal.valueOf(12);
        tenOver2 = BigDecimal.valueOf(12);
    }

    @Test
    public void BigDecimal에서_10이하는_정적팩터리사용() {
        assertThat(tenUnder1).isEqualTo(tenUnder2);
        assertThat(System.identityHashCode(tenUnder1))
            .isEqualTo(System.identityHashCode(tenUnder2));
    }

    @Test
    public void BigDecimal에서_10초과는_새로운객체생성() {
        assertThat(tenOver1).isEqualTo(tenOver2);
        assertThat(System.identityHashCode(tenOver1))
            .isNotEqualTo(System.identityHashCode(tenOver2));
    }
}