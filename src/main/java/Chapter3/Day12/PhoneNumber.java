package Chapter3.Day12;

public class PhoneNumber {
    private String firstNumber;
    private String secondNumber;
    private String thirdNumber;

    public PhoneNumber(String firstNumber, String secondNumber, String thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
            "firstNumber='" + firstNumber + '\'' +
            ", secondNumber='" + secondNumber + '\'' +
            ", thirdNumber='" + thirdNumber + '\'' +
            '}';
    }
}
