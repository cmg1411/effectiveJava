package Chapter4.Day20.templateMethod;

public class calImpl extends AbstractInterface {

    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        calImpl c = new calImpl();
        System.out.println(c.plus(1, 2));
        System.out.println(c.minus(2, 1));
        System.out.println(c.plusAndMinus(5, 3));
        System.out.println(c.getLiteral());
    }
}
