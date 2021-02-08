## 클래스와 멤버의 접근 권한을 최소화 하라.

#### 캡슐화, 정보은닉의 장점.
1. 시스템 개발 속도를 높인다. 여러 컴포넌트를 병렬적으로 개발 가능해서.
1. 시스템 관리 비용을 낮춘다. 각 컴포넌트를 더 빨리 파악하여 디버깅할 수 있고, 교체도 용이.
1. 성능 최적화에 도움을 준다.
1. 소프트웨어 재사용성을 높인다. 독립성이 높으면 다른데서도 쓸 수 있따.
1. 큰 시스템을 제작할 때, 작은단위부터 동작을 검증할 수 있어 개발 난이도가 낮아진다.

> 모든 클래스와 멤버의 접근성을 가능한 좁혀야 한다.

#### 클래스 접근 지정자
 - public : 어디서든 쓸 수 있는 공개 API
 - default : package-private 패키지 안에서만 쓸 수 있음

#### 중첩 클래스
 - 한 클래스에서만 쓰는 클래스면 private static 클래스로 중첩 클래스로 만들어라.
 
#### 필드 접근지정자
 - private : 멤버를 선언한 톱레벨 클래스에서만 접근가능
 - package-private(default) : 같은 패키지에서만 접근가능
 - protected : default + 하위클래스
 - public : 공개
 
#### 설계 방식
 1. 모든 public 세심히 설계
 2. 나머지 모두 private 으로 만듦
 3. 패키지 공개가 필요하면 default 로 풀어주기

#### protected ?
 - public 안에서 접근지정자를 private 에서 protected 로 바꾸는 순간 범위가 엄청나게 늘어난다.
 - protected 는 공개 API 이다.
 - 내부 동작을 api 로 공개해야 할 수 도 있다.
 - 최대한 수를 줄이도록 한다.
 
#### 리스코프 치환 원칙
 - 리스코프 치환 원칙으로 인해 상위 클래스의 접근 지정보다 하위 클래스가 더 좁을 수 없다.
 - 인터페이스를 구현한 클래스의 추상메서드는 모두 public 이어야 한다.
 
#### 테스트를 위한 접근성 확장
 - private 을 default 까지만 허용
 
#### 클래스의 인스턴스는 private 이어야 한다.
 - 불변식을 보장받기 위해
 - Thread-safe 하기 위해
 
#### 예외 ) 추상개념을 완성하기 위한 상수 prsf 라면 public 으로 선언해도 괜찮다.
#### 예외의 예외 ) 가변 객체는 상수라도 public 하면 안된다.
 - 배열은 길이가 0 이 아니라면 모두 변경할 수 있다.
 - 따라서 클래스에서 public static final 배열 필드를 두거나 이 필드를 반환하는 접근자 메서드를 제공하면 안된다.
 
```java
public static final Thing[] VALUES = {...};
이렇게 쓰면 안된다.
```

그러면 public 으로 가변 객체 상수를 제공하고 싶으면?

- 불변 리스트 사용하는 방법
```java
private static final Thing[] PRIVATE_VALUES = {};
public static final List<Thing> VALUES = 
    Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```
- 방어적 복사
```java
private static final Thing[] PRIVATE_VALUE = {};
public static final Thing[] values() {
    return PRIVATE_VALUE.clone();
}
```
