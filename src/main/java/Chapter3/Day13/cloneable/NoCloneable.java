package Chapter3.Day13.cloneable;

public class NoCloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
