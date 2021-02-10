# 타입 안전 이종 컨테이너를 고려하라.
 - 여러 타입을 넣을 수 있는 컨테이너.
 - 컨테이너 대신 키를 매개변수화 하고 컨테이너에 값을 넣거나 뺄 때 이 키를 함께 제공한다.
 - Class<?> 키를 사용한다.
 - 이를 통해 제네릭 타임 시스템이 값의 타입이 키와 같음을 보장해 준다.




## Favorite 이종 컨테이너
```java
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();
 
    public <T> void putFavorite(Class<T> type, T instacne) {
        favorites.put(Object.requireNonNull(type), instance); 
    }
 
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type)); 
    }
}
```

## Class 의 static 메서드 : cast()
 - 반환 타입을 Class 변수의 타입 매개변수와 같게 만든다. 다르면 예외 던짐.
 
## Class 의 메서드 : asSubclass()
 - 호출된 인스턴스 자신의 Class 객체를 인수가 명시한 클래스로 형변환.
 - 형변환이 된다는 것은 해당 인스턴스가 인수 타입의 하위 타입이라는 것.
 - 하위타입이 아니면 예외 던짐.

## Favorites 클래스의 두가지 제약
 - 첫번째 제약
 - 위 코드에는 헛점이 있는데 바로 putFavorite 메서드이다. 메서드를 잘 보면 매개변수인 instance를 그대로 넣어주는데 이는 클라이언트가 악의를 갖고 class객체를 로타입으로 넘기면 타입의의 안전성이 쉽게 깨진다.
따라서 아래와 같은 방법으로 이를 해결 할 수 있다.
``` java
public class Favorites { 
    private Map<Class<?>, Object> favorites = new HashMap<>(); 
   
    public <T> void putFavorite(Class<T> type, T instacne) { 
        favorites.put(Object.requireNonNull(type), type.cast(instance)); // put할 때도 동적으로 캐스팅 해준다. 
    } 
   
    public <T> T getFavorite(Class<T> type) { 
        return type.cast(favorites.get(type)); 
    } 
}
```
 - 두번째 제약
 - 실체화 불가 타입에는 사용할 수 없다.
 - 앞서 설명하였듯이 List<String>.class는 런타임 시에 소거(ensure)에 따라 타입을 갖지 않는다. 즉 List<String>.class건 List<Integer>.class건 List.class를 공유하게 된다.
이 문제에 대해서는 만족스러운 우회로는 없으며 닐 개프터가 고안한 “슈퍼 타입 토큰(Super Type Token)”이 우회할 수 있는 실용적인 방법 중 하나라 할 수 있다.
(스프링에서는 ParameterizedTypeReference라는 클래스로 구현되어 있다.)

[제네릭 정리](https://medium.com/@joongwon/java-java%EC%9D%98-generics-604b562530b3)