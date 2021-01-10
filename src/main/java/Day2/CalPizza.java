package Day2;

public class CalPizza extends Pizza {
    private final boolean sourceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sourceInside = false;

        public Builder sourceGo() {
            this.sourceInside = true;
            return this;
        }

        public Builder sourceNo() {
            this.sourceInside = false;
            return this;
        }

        @Override
        public CalPizza build() {
            return new CalPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private CalPizza(Builder builder) {
        super(builder);
        this.sourceInside = builder.sourceInside;
    }
}
