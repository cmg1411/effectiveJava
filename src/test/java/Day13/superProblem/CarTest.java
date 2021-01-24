package Day13.superProblem;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarTest {
    @Test
    public void superTest() throws CloneNotSupportedException {
        var car = new Car("genesis");
        var clone = car.clone();

        assertThat(car).isEqualTo(clone);

        assertThat(car.getClass()).isEqualTo(clone.getClass());

        // assertEquals(car, clone);
    }
}