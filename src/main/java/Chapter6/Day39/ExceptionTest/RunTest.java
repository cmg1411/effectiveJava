package Chapter6.Day39.ExceptionTest;

import java.lang.reflect.Method;

public class RunTest {

    private Class<?> testClass;
    private int tests = 0;
    private int passed = 0;

    public RunTest(String path) {
        try {
            this.testClass = Class.forName(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public void run(Class<?> runTest) {
        for (Method method : runTest.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExceptionTest.class) || method.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;

                try {
                    method.invoke(null);
                    System.err.printf("**테스트 %s 실패 : 예외를 던지지 않았습니다 !**%n", method.getName());
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] exceptionTests = method.getAnnotationsByType(ExceptionTest.class);

                    for (ExceptionTest exceptionTest : exceptionTests) {
                        if (exceptionTest.value().isInstance(exc)) {
                            passed++;
                            System.out.printf("**테스트 %s 에서 예상대로 %s 예외가 발생하였습니다 !**%n", method.getName(), exc.getClass());
                            break;
                        }
                    }

                    if (passed == oldPassed) {
                        System.err.printf("**테스트 %s 실패 : 다른 예외 %s 가 발생하였습니다 !**%n", method.getName(), exc.getClass());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {

        RunTest runTest = new RunTest("Chapter6.Day39.ExceptionTest.Tests");
        Class<?> testClass = runTest.getTestClass();
        runTest.run(testClass);
    }
}
