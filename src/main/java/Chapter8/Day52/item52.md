# 다중정의는 신중히 사용하라.

```java

public class BadOverloading {

    public static String classify(Set<?> s) {
        return "집합";
    }

    public static String classify(List<?> s) {
        return "리스트";
    }

    public static String classify(Collection<?> s) {
        return "그 외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
            new ArrayList<Double>(),
            new HashSet<Integer>(),
            new HashMap<String, String>().values()
        };

        for (Collection<?> collection : collections) {
            System.out.println(classify(collection));
        }
    }
}

```

## 오버로딩은 컴파일타임에 어떤 메서드를 사용할지 정해진다.
 - 런타임에는 타입이 각각 정해지지만
 - 컴파일타임에는 호출할 메서드를 정하는데 영향을 주지 못한다.
 
## 오버라이딩 : 동적으로 선택 vs 오버로딩 : 정적으로 선


## 다중정의로 인해 혼동을 가져오는 상황을 피해야 한다.
 1. 매개변수 수가 같은 다중정의는 만들지 말라.
 1. 다중정의 대신 메서드 이름을 다르게 짓는 방법도 있다.
 1. 생성자는 이름을 다르게 지을 수 없다.
    1. 정적 팩터리 메서드를 제공하는 방법이 있다.


## 다중정의를 해야 한다면 ?
 1. 매개변수의 수가 같더라도 매개변수들이 근본적으로 다르다면 컴파일타임에 올바르게 정한다.
    1. 오토박싱이 생기기 이전의 int 와 Object 는 근본적으로 달랐다.
        1. 따라서 method(int a), method(Object a) 라는 다중정의는 안전하다.
        1. 하지만 오토박싱 이후 List 의 remove 같은 경우, int 를 Integer 로 변환하여 넣으면 Object 형의 메서드가 실행될 것이다.
 1. 메서드를 다중 정의할 때, 서로 다른 함수형 인터패이스라도 같은 위치의 인수로 받아서는 안된다.