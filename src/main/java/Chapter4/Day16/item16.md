## public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라.

```java
public class A {
    public int X;
    public int Y;
}
```

 - 위의 코드는 데이터 필드에 직접 접근 가능하므로, 캡슐화의 장점을 이용할 수 없다.
 - API 를 수정하지 않고는 내부 표현을 바꿀 수 없다. 불변식도 보장할 수 없다.
 
 따라서 이런 필드를 private 으로 바꾸고, 접근자 (보통 getter)를 이용하여 값을 가져온다.
 
 ### 원칙
```
    public 클래스 : 필드를 private, 접근자로 필드에 접근

    package-private or private 중첩 클래스 : public 으로 써도 괜찮음. 
                                          오히려 private 은 더 코드를 복잡하게 함. (불변이든 가변이든)

    protected : 클래스에서는 사용 불
```