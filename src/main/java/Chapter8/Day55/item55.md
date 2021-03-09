# 옵셔널 반환은 신중히 하라.

## JAVA 8 이후 메서드가 특정 조건에서 값을 반환할 수 없을 때 취할 수 있는 선택지
 1. 예외를 던진다. : 예외는 진짜 예외적인 상황에만 사용해야 한다. 예외 스택을 캡쳐하는 비용이 만만치 않다.
 1. null 을 반환한다. : null 처리를 해야 한다.
 1. Optional<T> 를 사용한다.
 
## Optional<T> : 원소를 최대 1개 가질 수 있는 불변컬렉션 (비거나 비지 않거나)
 - Collection 를 구현하진 않았지만 원칙적으로는 그렇다.
 - 보통은 T 를 반환해야 하지만, 특정 조건에서는 아무것도 반환하지 않을 떄 쓴다.
 
 
## Optional 을 만드는 정적 팩터리 메서드
 1. Optional.empty() : 빈 옵셔널 객체 반환
 1. Optional.of(value) : result 객체를 Optional<value 의 타입> 객체로 바꿈. value 가 null 이면 에러
 1. Optional.ofNullable(value) : result 객체를 Optional<value 의 타입> 객체로 바꿈. value 가 null 도 허용

## 옵셔널을 반환하는 메서드에는 절대 null 을 반환하지 말라.
 - null 반환을 더 안전하게 하기 위해 Optional 을 만들었다.
 - 메서드에서 Optional 을 반환한다는건, null 반환을 더 안전하게 하겠다는 것인데 그냥 null 을 반환하면 되겠는가 ?
 
## Stream 의 종단 연산 상당수가 옵셔널을 반환한다.
 - 예를 들어 max 종단연산도 Optional 을 반환한다.
 
## null 을 던지거나 예외를 발생시키는 대신 Optional 을 반환하는 기준
 - **옵셔널은 검사예외와 취지가 비슷하다**
 - 즉 반환값이 없을 수 있음을 API 사용자에게 명확하게 알려준다.
 - 검사 예외는 예외에 대한 처리를 하는 코드를 반드시 넣어야 한다.
 - 옵셔널도 비슷하게 값을 받지 못했을 떄 취할 생동을 선택해야 한다.
 
## Optional 객체를 처리하는 메서드들
 1. orElse(~) : Optional 안의 값이 null 일 경우 취할 기본값을 설정한다.
 1. orElseThrow(~) : Optional 안의 값이 null 일 경우 취할 예외 클래스를 설정한다.
 1. get() : 값이 비어있지 않다고 확신할 때, 값을 가져올 수 있다. 하지만 값이 비었다면 예외를 발생시킨다.
 1. orElseGet(~) : orElse() 와 비슷하지만 조금의 차이가 있다.
    1. orElse()
        1. Object 를 인수로 받는다.
        1. null 이던 말던 항상 불린다.
    1. orElseGet()
        1. Supplier 를 인수로 받는다.
        1. null 일때만 불린다.
        1. 따라서 필요할 때만 불리기 때문에 생성 비용을 아낄 수 있다.
 1. filter, map, flatmap, ifPresent 도 있다.
 1. isPresent() 를 쓰는 로직은 앵간하면 위의 것으로 만들 수 있다. 최종 보루로 사용하자.
 ```java
        ProcessHandle process = ProcessHandle.current();
        Optional<ProcessHandle> parentProcess = process.parent();
        // isPresent() 
        System.out.println("부모 PID : " + (parentProcess.isPresent() ? String.valueOf(parentProcess.get().pid()) : "N/A"));
        // isPresent() 안쓴것
        System.out.println("부모 PID : " + process.parent().map(h -> String.valueOf(h.pid())).orElse("N/A"));
```


## 컬렉션, 스트림, 배열, 옵셔널 같은 컨테이너 타입은 옵셔널로 감싸면 안된다.
 - Optional<List<T>> 는 List 가 null 일 수 있다는 뭐 이런 뜻.
 - 앞 아이템의 조언에 맞게 빈 List 가 훨씬 낫다.
 
 
## 기본 규칙 : 결과가 없을 수 있으며, 클라이언트가 이 상황을 특별하게 처리해야 한다면 Optional<T> 를 반환.
 - 옵셔널도 한번 더 감싸는 것이므로 성능상 안좋을 수 있다.
 - 특히 박싱 타입들은 두번 감싸는 것이므로 더 안좋다.
    - 이럴때를 대비하여 OptionalInt, OptionalLong, OptionalDouble 등이 있다.
    - 박싱된 기본 타입을 담은 옵셔널은 없도록 하자.
 - 옵셔널은 키, 값, 배열의 원소로 쓰면 절대 안된다.
 
## 결론 : 옵셔널은 반환값 이외의 용도로는 대부분 부적절하다.
 - 특히 필드로 쓰는 것은 확장한 클래스를 따로 만들어야 함을 암시할 수 있다.
 