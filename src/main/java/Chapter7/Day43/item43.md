# 람다보다는 메서드 참조를 사용하라.

함수객체를 정의하는 방법 (복잡 -> 간단)
 1. 익명 클래스
 1. 람다
 1. 메서드 참조 ! 
 
## 메서드 참조 쪽이 더 짧고 명확할 때만 사용하라. 그렇지 않으면 람다를 사용하라.
 - 람다로 쓸 수 없는 것은 메서드참조로도 쓸 수 없다.
 - 메서드와 람다가 같이 있는 경우, 클래스명을 불필요하게 쓰게 되는 메서드참조가 더 안좋을 수 있다.
 
| 메서드 레퍼런스 타입 | 예제                   | 동일한 표현식을 람다로 바꾸면?                      |
| -------------------- | ---------------------- | --------------------------------------------------- |
| 정적(Static)         | Integer::parseInt      | str -> Integer.parseInt(str)                        |
| 바운드(Bound)        | Instant.now()::isAfter | Instant then = Instant.now(); t -> then.isAfter(t); |
| 언바운드(UnBound)    | String::toLowerCase    | str -> str.toLowerCase();                           |
| 클래스 생성자        | TreeMap<K,V>::new      | () -> new TreeMap<K,V>();                           |
| 배열 생성자          | int[]::new             | len -> new int[len];                                |