# 가변인수는 신중히 사용하라.

## 가변인수를 사용할 때, 가변인수를 무조건 한개 이상받게 하는 법

```java
int min(int firstArg, int... remainArgs){}

```

 - 가변인수 외에 하나의 매개변수를 명시적으로 지정해주기 !
 
 
## 가변인수의 성능이슈
 - 가변인수는 호출될 떄 마다 배열을 새로 하나 할당하고 초기화한다.
 - 이 비용을 개선할 수 있는 방법은 아래와 같다.
    - 95% 가 3개이하의 가변인수를 사용하고 4개이상은 5% 일때
    ```java
    public void foo()
   public void foo(int a1)
   public void foo(int a1, int a2)
   public void foo(int a1, int a2, int a3)
   public void foo(int... args)
```