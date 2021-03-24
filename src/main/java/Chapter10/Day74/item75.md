# 예외의 상세 메세지에 실패 관련 정보를 담으라

 - 예외를 catch 하지 못해, 프로그램이 실패하면 자바는 예외의 스택을 추적하여 정보를 자동으로 출력한다.  
 - 스택 추적은 예외 객체의 toString().
 - 사후 분석을 위해서는, 실패 순간의 상황을 정확히 포착해 예외의 상세 메세지에 담아야한다.
 
## 실패 순간을 포착하는 방법
 - 예외에 고나여된 모든 매개변수와 필드의 값을 실패 메세지에 담아야한다.
 - IndexOutOfBoundsException
    - `범위의 최솟값, 범위의 최댓값, 빔위에서 벗어나서 예외가 일어난 인덱스값` 이 세가지를 담아야한다.
    
    
## 팁
 - 관련 데이터를 모두 담아야 하지만, 장황할 필요는 없다.
 - 문제를 분석하는 사람은 스택 추적 뿐 아니라 관련 문서와 소스코드를 함께 살펴보기 때문이다.
 - 문서와 소스코드에서 얻을 수 있는 정보는 중복해서 포함하지 말자.
 
## 예외의 상세 메세지 vs 최종 사용자가 보는 오류 메세지
 - 최종 사용자 : 친절한 안내 메세지.
 - 예외 메세지 : 가독성보다는 담긴 내용이 훨씬 중요.
 
## 실패 순간을 포착하는 다른 방법
 - 필요한 정보를 예외 생성자에서 모두 받아서 상세 메세지까지 미리 생성해놓는 방법.
 - 이후 메세지를 전달해주기만 하면 된다.
 ```java
 public class IndexBoundsOutOfEx extends RuntimeException {
 
     private final int lowerBound;
     private final int upperBound;
     private final int index;
 
     public IndexBoundsOutOfEx(int lowerBound, int upperBound, int index) {
         super(String.format("최솟값 :  %d, 최댓값 : %d, 인덱스 : %d", lowerBound, upperBound, index));
 
         this.lowerBound = lowerBound;
         this.upperBound = upperBound;
         this.index = index;
     }
 }
```
 결과 `Exception in thread "main" Chapter10.Day74.IndexBoundsOutOfEx: 최솟값 :  1, 최댓값 : 3, 인덱스 : 4`
 
## 접근자 메서드
 - 예외는 실패와 관련한 정보를 얻을 수 있는 접근자 메서드를 제공해야한다.
 - index, upperBound, lowerBound 에 접근할 수 있는 메서드는 검사 예외에서 예외처리를 복구하는데 유용하다.
 - 비검사 예외에도 상세 정보를 알려주는 접근자 메서드를 제공하면 좋다.