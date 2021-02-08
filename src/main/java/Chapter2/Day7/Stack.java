package Chapter2.Day7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * pop 코드는 가장 위의 원소를 반환하고 사이즈를 줄인다.
     *
     * 하지만, size 인스턴스 변수는 우리가 stack 클래스에서 사용할 크기를 나타낸다.
     *
     * 우리가 pop을 해서 stack 의 크기를 줄였다 하더라도, 넣었던 데이터는 그대로 있게 된다.
     *
     * 따라서 '다쓴 참조' 를 그대로 가지고 있게 되고, 이런 짓이 반복되면 엄청난 메모리 누수가 발생한다.
     *
     * 해법은 다 쓴 객체는 null 로 초기화를 해주면, gc 가 발견해서 해제하게 하도록 한다.
     *
     *
     * 이런 null 처리는 베스트 아이디어는 아니다.
     * null 처리는 이 클래스의 element[] 가 배열을 사용하듯이, 메모리를 직접 관리할 떄만 쓰도록 한다.
     *
     * 메모리를 직접 관리하는 클래스라면 항상 메모리 누수를 신경써야 한다.
     */
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    /*
     * 원소를 위한 공간을 적어도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // 코드 7-2 제대로 구현한 pop 메서드 (37쪽)
//    public Object pop() {
//        if (size == 0)
//            throw new EmptyStackException();
//        Object result = elements[--size];
//        elements[size] = null; // 다 쓴 참조 해제
//        return result;
//    }

    public static void main(String[] args) {
    }
}
