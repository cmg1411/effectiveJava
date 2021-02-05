package Chapter9.Day60;

public class IntLong {

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
}
