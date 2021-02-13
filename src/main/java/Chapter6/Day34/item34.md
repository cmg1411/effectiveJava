# 상수 대신 열거 타입을 사용하라.

## enum
 - public static final 인스턴스로 상수를 만든다.
 - 생성자는 private.
 - 생성자가 private 이므로 열거 타입 인스턴스가 싱글턴으로 통제됨.
 - name() 밑 Object 오버라이딩도 잘 되어 있다.
 
#### 널리 쓰이는 열거타입은 톱레벨 클래스로 만들고, 아닌 것은 멤버 클래스로 만들라.

#### abstract 메서드로 각 상수의 동작을 정의할 수 있다.

#### toString() 을 재정의할거면 string 으로 enum 객체를 만드는 fromString 메서드 구현을 고려한다.

#### 열거 타입의 정적 필드 중 열거 타입의 생성자에서 접근 할 수 있는 것은 상수 변수 뿐이다.

#### 열거 타입 상수 일부가 같은 동작을 공유한다면 전략 열거 타입 패턴을 사용하자.

#### 추가하려는 메서드가 의미상 열거 타입에 속하지 않으면 그냥 switch 쓰라.

## 필요한 원소를 컴파일타임에 다 알수 있는 상수 집합이라면 항상 열거타입을 이용하라.


[StackOverflow](https://stackoverflow.com/questions/443980/why-cant-enums-constructor-access-static-fields)

[내가 정리한 enum](https://alkhwa-113.tistory.com/entry/11%EC%A3%BC%EC%B0%A8-enum)