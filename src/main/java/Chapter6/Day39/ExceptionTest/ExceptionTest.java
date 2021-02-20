package Chapter6.Day39.ExceptionTest;

import java.lang.annotation.*;

@Repeatable(ExceptionTestContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}
