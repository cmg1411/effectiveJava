package Chapter12.Day90;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public class Period {

    private Date start;
    private Date end;

    public Period(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    // 내부 중첩 프록시 클래스를 직렬화하도록 함.
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    // 바깥 클래스의 readObject 메서드를 호출할 수 없게 막음.
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요합니다.");
    }

    private Object readResolve() {
        return new Period(start, end);
    }

    private static class SerializationProxy implements Serializable {
        private final Date start;
        private final Date end;

        // 바깥 클래스를 복사 (일관성 검사 or 방어적 복사 필요 x)
        SerializationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }
        private static final long serialVersionUID = 123451234L;
    }
}
