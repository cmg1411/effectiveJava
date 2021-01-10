# ITEM 1. 생성자 대신 정적 팩터리 메서드를 고려하라.

## 팩터리 메서드
'''
public class Book {
    private String name;
    private String author;
    private String publisher;
}
'''
클래스의 인스턴스를 얻는 방법



## 1. 이름을 가질 수 있다.
2. 하나의 시그니쳐로 여러가지 객체를 생성할 수 있다.
3. 호출 할 때마다 인스턴스를 새로 생성하지 않아도 된다.
4. 반환 타입의 하위 타입 객체를 반환할 수 있다.
5. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
6. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.


1. 상속을 하려면 public, protected 생성자가 필요하기 때문에, 정적 펙토리 메서드만 제공하면 하위 클래스를 만들 수 없다.
2. 정적 팩터리 메서드는 찾기가 어렵다.


 - from : 매개변수 하나로 하나의 인스턴스 만듦  
 - of : 여러 매개변수로 인스턴스 만듦  
 - valueOf : from 과 of의 자세한 버전
 - getInstance, instance : (매개변수를 갖거나 갖지 않거나) 같은 인스턴스임을 보장하지 않는 인스턴스 반환
 - create, newInstance : 매번 새로운 인스턴스를 생성하여 반환
 - getType : 인스턴스를 생성할 때, 해당 클래스가 아닌 다른 클래스에서 생성할 때 사용
 - newType : getType 과 동일
 - Type : getType, newType 의 간략버전