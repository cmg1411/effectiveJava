package Day13.superProblem;

import org.junit.Test;

import static org.junit.Assert.*;


public class CarTest {
    @Test
    public void superTest() throws CloneNotSupportedException {
        var car = new Car("genesis");
        var clone = car.clone();

        assertNotSame(car, clone);

        assertEquals(car.getClass(), clone.getClass());

        // assertEquals(car, clone);
    }
}