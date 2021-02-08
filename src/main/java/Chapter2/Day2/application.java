package Chapter2.Day2;

public class application {
    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new NutritionFacts.Builder(10, 20)
            .setCalories(50)
            .setCarbohydrate(20)
            .build();

        System.out.println(nutritionFacts.getClass());

        NYPizza nyPizza = new NYPizza.Builder(NYPizza.Size.LARGE)
            .addTopping(Pizza.Topping.HAM)
            .build();

        CalPizza calPizza = new CalPizza.Builder()
            .addTopping(Pizza.Topping.MUSHROOM)
            .build();
    }
}
