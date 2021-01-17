package Day11;

import java.util.Objects;

public class PhoneNumber {
    private int firstNumber;
    private int secondNumber;
    private int thirdNumber;

    public PhoneNumber(int firstNumber, int secondNumber, int thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        return ((PhoneNumber) o).firstNumber == firstNumber
            && ((PhoneNumber) o).secondNumber == secondNumber
            && ((PhoneNumber) o).thirdNumber == thirdNumber;
    }


    /**
     * 적법하지만 최악의 구현.
     * 같은 hashCode 여야 HashMap 의 get() 이 찾아올 수 있음을 보여주기 위한 구현
     *
     * 이렇게 구현하면, 모든 객체가 같은 해쉬코드를 반환하므로, 버킷 하나에 연결리스트로 쭉 이어진 해쉬테이블이 생성됨.
     *
     * 그러면 찾는 시간이 평균 O(1) 인 테이블이 O(n) 까지 늘어남.
     */
//    @Override
//    public int hashCode() {
//        return 42;
//    }

    /**
     * 올바른 hashCode 구현
     *
     * Objects.hash() 는 내부에서 해줘서 한줄로 가능.
     * 하지만 입력인수를 위한 배열과 자동 박싱 언박싱 때문에 느리긴 함.
     */
    @Override
    public int hashCode() {
        int result = Integer.hashCode(firstNumber);
        result = 31 * result + Integer.hashCode(secondNumber);
        result = 31 * result + Integer.hashCode(thirdNumber);
        return result;
//        return Objects.hash(thirdNumber, secondNumber, firstNumber);
    }


}
