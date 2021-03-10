# 정확한 답이 필요하다면 float 와 double 은 피하라.

## float, double
 - 공학과 과학을 위해 설계됨.
 - 넓은 범위의 수를 빠르게 정밀한 [근사치] 로 계산한다.
 - 따라서 float, double 은 금융관련 계산과 특히 맞지 않는다.
 
## 부동 소수점 방식의 문제
```java
 System.out.println(1.03 - 0.42);
 System.out.println(1.00 - 0.10*9);
```
위의 결과는 ?

![부동](./img/부동.png)


## 또다른 문제.
```java
public static void main(String[] args) {
    double funds = 1.00;
    int itemBought = 0;

    for (double price = 0.10;  funds >= price; price+=0.10) {
        funds -= price;
        itemBought++;
    }

    System.out.println(itemBought + "개 구입");
    System.out.println("잔돈 : " + funds);
}
```

 - 잔돈 1 달라로 매번 10 센트씩 오르는 사탕을 몇개 살 수 있는가 ?
 - 원래는 0.1, 0.2, 0.3, 0.4 이렇게 4개를 살 수 있을 것이고, 잔돈은 0원이 될 것이다.
 - 하지만 double 을 사용한 결과는 아래와 같다.
 
![문제](./img/doubleprob.png)

## 어떻게 고칠까 ?
 1. BigDecimal 을 이용한다.
    - 코드가 복잡해지고, 느리긴 하다.
    - 그래도 올바른 값이 나온다.
    ```java
    public static void main(String[] args) {
            final BigDecimal TEN_CENTS = new BigDecimal(".10");
    
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
    
        for (BigDecimal price = TEN_CENTS;  funds.compareTo(price) >= 0; price=price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
    
        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈 : " + funds);
    }
    ```
    
    ![빅데시멀](./img/BigDecimalGood.png)
    올바른 답이 나온다 !
    
 1. 소숫점 부분과 앞부분을 int 나 long 을 이용하여 표현한다.
    - BigDecimal 의 성능저하가 문제라면 쓸 수 있다.
    - 성능이 중요하고, 숫자가 너무 크지 않다면 사용한다.
    - int - 아홉자리 정수, long - 18자리 십진수 
    - 18 자리를 넘어가면 BigDecimal 을 사용해야 한다.
    ```java
    public static void main(String[] args) {
        int itemsBought = 0;
        int funds = 100; // 센트단위, 소숫점을 끌어올림

        for (int price = 10; price <= funds; price += 10) {
            funds -= price;
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈 : " + funds);
    }
   ```