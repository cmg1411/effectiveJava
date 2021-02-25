# 스트림은 주의해서 사용하라

## 스트림의 핵심 추상 개념
 1. 스트림은 데이터 원소의 유한 혹은 무한 시퀀스이다.
 1. 스트림 파이프라인은 이 원소들로 수행하는 연산 단계를 표현하는 개념이다.
 
 스트림의 원소는 컬렉션, 배열, 파일, 정규표현식 패턴 matcher, 난수 생성기, 다른 스트림 에서 만들 수 있다.  
 스트림은 기본타입 int, long, double 을 지원하고, 객체 참조형을 지원한다.
 
 
## 스트림 파이프라인
 - 스트림 파이프라인은, 스트림 생성 + 중단연산 + 종단연산 으로 이루어진다.

 - 중간 연산 (intermedidate operation) : 한 스트림을 다른 스트림으로 변환(transform)한다.
    - filter(Predicate predicate) : predicate 함수에 맞는 요소만 사용하도록 필터
    - map(Function function) : 요소 각각의 function 적용
    - flatMap(Function function) : 스트림의 스트림을 하나의 스트림으로 변환
    - distinct() : 중복 요소 제거
    - sort() : 기본 정렬
    - sort(Comparator comparator) : comparator 함수를 이용하여 정렬
    - skip(long n) : n개 만큼의 스트림 요소 건너뜀
    - limit(long maxSize) : maxSize 갯수만큼만 출력
 - 종단 연산 (terminal operation) : 중간 연산이 내놓은 스트림에 최후의 연산을 가한다. 원소를 정렬해 컬렉션에 담기, 특정 원소 하나를 선택하기, 모든 원소를 출력하기 등
    - forEach(Consumer consumer) : Stream의 요소를 순회
    - count() : 스트림 내의 요소 수 반환
    - max(Comparator comparator) : 스트림 내의 최대 값 반환
    - min(Comparator comparator) : 스트림 내의 최소 값 반환
    - allMatch(Predicate predicate) : 스트림 내에 모든 요소가 predicate 함수에 만족할 경우 true
    - anyMatch(Predicate predicate) : 스트림 내에 하나의 요소라도 predicate 함수에 만족할 경우 true
    - noneMatch(Predicate predicate) : 스트림 내에 모든 요소가 predicate 함수에 만족하지않는 경우 true
    - sum() : 스트림 내의 요소의 합 (IntStream, LongStream, DoubleStream)
    - average() : 스트림 내의 요소의 평균 (IntStream, LongStream, DoubleStream)
    
## 스트림의 특징
 - 지연 평가 : 종단 연산이 없으면 스트림 자체가 실행되지 않는다. 평가는 종단연산이 호출될 때 이루어지기 때문이다. 이는 무한 스트림을 다룰 수 있게 하는 열쇠이다.
    - 종단 연산이 없는 스트림은 아무것도 없는 것과 같으니 종단연산을 꼭 넣자.
 - 메서드 체이닝 지원


## 람다와 마찬가지로, 스트림도 과하게 사용하면 가독성과 유지보수가 힘들어진다.
 - 스트림이 너무 길어진다면, 도우미 메서드를 이용하여 적당히 줄이는 방법을 고민해야 한다.
 - 람다 매개변수의 이름을 잘 짓는 것은 가독성에 매우 도움이 된다.

## 기본 타입인 int, long, double 외에는 (예를 들어 char) 스트림을 사용하지 않는 편이 낫다.

## 스트림은 할 수 없지만 코드 블록은 할 수 있는 경우
 1. **스트림은 final, 사실상 final 변수만 다룰 수 있다.** 하지만 코드 블록은 범위 안의 지역변수를 읽고 수정할 수 있다.
 1. **코드 블록에서는 return, break, continue, 예외처리** 의 작업이 가능하지만 스트림은 아니다.
 
 
## 스트림은 처음의 스트림이 연산 단계가 거쳐감에 따라 사라지는 구조.
 - 이전의 값이 필요하다면, (가능한 상황에 한해) 현재 가지고 있는 정보로 이전의 값을 구해낸다.
 - 예시로, 지수로 소수를 계산하여 소수를 가지고 있다면, 소수를 이용하여 다시 지수를 구할 수 있다.
 
 
## flatMap
 - 평탄화
 - 스트림으로 만들 수 있는 형태가 2중으로 만들어 져 있을 때, (ex. 2차원 배열) 스트림 원소 각각에 스트림 하나씩을 부여한 다음, 하나의 스트림으로 합친다.