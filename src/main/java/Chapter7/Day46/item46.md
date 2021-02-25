# 스트림에서는 부작용 없는 함수를 사용하라

스트림은 각각의 변환하는 단계는 가능한 이전 단계의 결과를 받아 처리하는 **순수함수** 여야 한다.  

순수함수 : 다른 객체를 참조하지 않고, 다른 객체를 변경하지도 않는 함수. 입력만이 출력에 영향을 미치는 함수.  

순수함수를 만드는 방법 : Side Effect (부작용) 을 없애야 한다 !


## forEach 는 스트림 계산 결과를 보고할 떄만 사용하고, 계산에는 사용하지 말라 !
 - 가끔은 스트림 계산 결과를 기존 컬렉션에 추가하는 등의 용도에도 쓸 수 있긴 하다.
 
## Collectors 를 잘 이용하라.
 - 위 클래스는 수집기로, 스트림 원소를 쉽게 컬렉션으로 모을 수 있다.
 - 정적 임포트를 이용하면 가독성이 좋아진다.
 
## 자바 9 부터는 Scanner 에서 스트림을 얻는 방법으로 tokens() 를 쓰면 된다.


## Collectors 의 수집기들 : toMap
 1. toList() : 리스트로 바꾼다.
 1. toSet() : 셋으로 바꾼다.
 1. toMap(keyMapper, valueMapper) : 키로 사용할 함수, 벨류로 사용할 함수를 받아 함수들에 맞게 키벨류를 셋팅. 스트림 요소중 같은 키가 있으면 예외를 던진다.
 1. ```toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction)``` : 
   스트림의 요소들이 같은 키를 가져서 오류를 뱉는 상황에서 어떻게 merge 할 것인지에 대한 merge 함수 객체를 3번째 인수로 줄 수 있는 메서드  
   키와 그 키에 연관된 원소들 중 하나를 골라 연관 짓는맵을 만들때 유용.
   
 1. ```toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapFactory)``` : 원하는 맵 구현체를 직접 지정가능.
 
 
 ## Collectors 의 수집기들 : groupingBy
 입력으로 분류함수를 받고, 출력으로 원소들을 카테고리별로 모아 놓은 맵을 담은 수집기를 반환.  
 분류 함수는, 입력받은 원소가 속하는 카테고리를 반환. -> 키  
 맵 스트립의 벨류는 **리스트** 이다.
 1. groupingBy(word -> alphabetize(word)) : alphabetize 메서드의 결과가 카테고리이자 키. 그 키에 대해 수집을 한다.
 1. groupingBy(분류함수, 다운스트림 수집기) : 리스트가 아닌 벨류를 가지고 싶다면, 다운스트림을 직접 정의하여 넣어야 한다.
      1. 다운스트림 수집기 : toSet(), toCollection(CollectionFactory), count() ...
      1. ``` Map<String, Long> freq = words.collect(groupingBy(String::toLowerCase.counting()));``` 
 1. groupingBy(분류함수, 맵 팩터리, 다운스트림 수집기) : 어떤 맵을 쓸 지 지정 가능. 그 맵 안의 벨류에 어떤 컬렉션을 쓸지도 지정 가능.
 
 
 ## counting()
  - 위의 경우인 수집기로 쓸 떄 빼곤 잘 안쓴다.
  - Stream 의 count 가 있으니까.
  - 추가로 reducing, filtering, mapping, flatMapping, collecting, AndThen 이 있는데 잘 몰라도 된다.
  
 ## joining
  - 문자열 등의 CharSequence 에 적용할 수 있다.
  1. joining() : 문자들을 연결하는 수집기 반환
  1. joining(딜리미터 문자) : 딜리미터로 구분한 합친 문자열 수집기 반환
  1. joining(딜리미터, 프리픽스, 서픽스) : ('[', ',' ']') 로 줬다 치고 first, second 스트림이 들어오면 ```[first,second]``` 를 반환한다.