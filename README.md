# 🖊 이펙티브 자바 공부
 - 이펙티브 자바 3/E 를 읽고 공부하며 책에 나오는 예제 코드를 작성한 내용입니다.
 - 중간 중간에 공부하면서 배운 내용과 정리를 주석으로 표현하였습니다.
 - 스터디를 진행하면서  발표 내용은 해당 아이템의 패키지에 __MarkDown__ 문서로 추가합니다.

<br>
<br>
<br>

---
#### 2장 객체 생성과 파괴
1. [x] 생성자 대신 정적 펙토리 메서드를 고려하라.  
1. [x] 생성자에 매개변수가 많다면 빌더를 고려하라.  
1. [x] private 생성자나 열거 타입으로 싱글턴임을 보증하라.  
1. [x] 인스턴스화를 막으려거든 private 생성자를 사용하라.  
1. [x] 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라.
1. [x] 불필요한 객체 생성을 피하라.
1. [x] 다 쓴 객체 참조를 해제하라.
1. [x] Finalizer 와 Cleaner 사용을 피하라.
1. [x] try-finally 보다는 try-with-resources를 사용하라.

#### 3장 모든 객체의 공통 메서드
1. [x] equals 는 일반 규약을 지켜 재정의하라.
1. [x] equals 를 재정의하려거든 hashCode 도 재정의하라.
1. [x] toString을 항상 재정의하라.
1. [x] clone 재정의는 주의해서 진행하라.
1. [x] Comparable 을 구현할지 고려하라.

#### 4장 클래스와 인터페이스
1. [x] 클래스와 멤버의 접근 권한을 최소화하라.
1. [x] public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라.
1. [x] 변경 가능성을 최소화하라.
1. [x] 상속보다는 컴포지션을 사용하라.
1. [x] 상속을 고려하여 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라.
1. [x] 추상 클래스보다는 인터페이스를 우선하라.
1. [x] 인터페이스는 구현하는 쪽을 생각해 설계하라.
1. [x] 인터페이스는 타입을 정의하는 용도로만 사용하라.
1. [x] 태그 달린 클래스보다는 클래스 계층구조를 활용하라.
1. []
1. [x] 톱 레벨 클래스는 한 파일에 하나만 담으라.
<br>
<br>

---

 - [스터디 Github](https://github.com/Blog-Posting/book-effective-java)  
 - [Effective Java 공식 Github](https://github.com/WegraLee/effective-java-3e-source-code)  
 - [번역 용어 해설](https://github.com/WegraLee/effective-java-3e-source-code)
 - [백기선님과 같이 공부하기](http://bit.ly/2Lu4BGi)
 - [Effective Java 책 정보](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=171196410)
 - [JavaBom 스터디](https://github.com/Java-Bom/ReadingRecord/issues)
 - [쟈미블로그](https://github.com/Java-Bom/ReadingRecord/issues)