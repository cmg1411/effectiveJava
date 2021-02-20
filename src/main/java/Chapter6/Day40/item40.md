# @Override 에너테이션을 일관되게 사용하라.

오버라이드 에너테이션을 사용하면 컴파일타임에 더해서 코드를 작성하는 시점에서 에러를 체크할 수 있다.  
오버라이드 에너테이션은 메서드에만 붙일 수 있으며, 해당 메서드가 오버라이딩한 에너테이션이 맞는지 체크해준다.  
만약 위 조건에 맞지 않으면 빨간줄로 에러를 띄워 컴파일조차 안될 것이다.

**상위 메서드를 재정의하는 메서드 모두에 @Override 를 달자 ! **


#### 예외
 - 구체클레스에서 상위 클래스의 추상 메서드를 재정의시는 달지 않아도 상관없다.
   - 추상메서드를 재정의하지 않으면 구현하지 않은 추상 메서드가 있다고 알려주기 때문이다.
   
   
#### 인터페이스의 default
 - 인터페이스에서 default 메서드는 재정의가 가능하다.
 - 하지만 default 메서드는 재정의해도 되고 안해도 되기 때문에, default 가 있는 경우라면 매번 @Override 를 다는 것이 시그니쳐 확인에 좋다.
 
 
#### Set 의 예외
 - Set 인터페이스는 Collection 인터페이스를 extends 했지만, 새로 구현한 메서드는 없다.
 - 따라서 모든 메서드에 @Override 를 달면서 시그니처를 실수로 다르게 적어서 새로 추가되는 메서드가 있는지 없는지를 확인했다.