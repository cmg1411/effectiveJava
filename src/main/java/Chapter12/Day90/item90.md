# 직렬화된 인스턴스 대신 직렬화 프록시 사용을 검토하라.

이전장에서 계속 언급했듯, Serializable 을 구현한 순간, 언어의 정상 매커니즘인 생성자 이외에 객체를 생성한다.  
따라서 버그, 보안의 문제가 커질 수 있다.  
이 문제를 극적으로 완화시키는 기법이 직렬화 프록시 패턴이다.  
`불변식을 안정적으로 직렬화해주는 가장 쉬운 방`

## 직렬화 프록시 패턴 만들기
 1. 바깥 클래스의 논리적 상태를 정밀하게 표현하는 중첩 클래스를 private static 으로 선언 (바깥 클래스의 직렬화 프록시)
    1. 중첩 클래스의 생성자는 단 하나.
    1. 바깥 클래스를 매개변수로 받아야함.
    1. 이 생성자는 단순히 인수로 넘어온 인스턴스의 데이터를 복사.
 1. 바깥 클래스와 중첩 클래스 모두 Serializable 구현
     ```java
    // 내부 직렬화 프록시 클래스
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
    ```
 1. 바깥 클래스에 writeReplace() 구현
    - writeReplace : 직렬화시 해당 객체가 아니라 직렬화 할 객체를 정해줄 수 있음.
    - readResolve 와 반대되는 메서드
    ```java
    // 내부 중첩 프록시 클래스를 직렬화하도록 함.
    private Object writeReplace() {
        return new SerializationProxy(this);
    }
    ```
 1. readObject 메서드 정의
    - 바깥 클래스의 직렬화된 인스턴스 생성을 프록시를 통하지 않으면 불가능하게 할 수 있다.
    ```java
    // 바깥 클래스의 readObject 메서드를 호출할 수 없게 막음.
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요합니다.");
    }
    ```
 1. 바깥 클래스와 논리적으로 동일한 인스턴스를 반환하는 readResolve 메서드를 SerializationProxy 클래스에 추가.
    - 역직렬화시 다시 바깥의 클래스로 반환하도록 하는 역할.
    ```java
    private Object readResolve() {
        return new Period(start, end);
    }
    ```
    
## 직렬화 프록시 패턴의 장점
 - 나름대로 언어의 원래 방법인 생성자를 이용한 객체 생성을 하게 해준다.
 - 프록시 수준에서 공격을 차단한다.
 - 바깥 클래스를 final 로 선언해도 됨 -> 불변이 가능
 - 역직렬화한 인스턴스와 원래의 직렬화된 인스턴스가 달라도 정상 작동한다. (EnumSet 의 RegularEnumSet, JumboEnumSet)
    
## 직렬화 프록시 패턴의 한계
 - 클라이언트가 멋대로 확장 가능한 클래스에는 적용 불가.
 - 객체 그래프에 순환이 있으면 적용 불가.
 - 방어적 복사보다 조금 느림.