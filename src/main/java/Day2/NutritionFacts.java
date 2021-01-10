package Day2;

public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 인스턴스
        private final int servingSize;
        private final int servings;

        // 선택 인스턴스 (기본값으로 초기화)
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        // 필수 인스턴스를 포함한 생성자
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        // setter, this(자신의 클래스) 를 반환하여 메소드 체이닝이 가능
        public Builder setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder setFat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder setSodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder setCarbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        // 자신 객체(빌더객체)를 인수로 던져서 객체 생성
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    // private 으로 선언
    private NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }
}
