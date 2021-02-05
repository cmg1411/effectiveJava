package Chapter9.Day60;

public class BuyCandy {

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
}
