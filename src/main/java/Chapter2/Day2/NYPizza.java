package Chapter2.Day2;

import java.util.Objects;

public class NYPizza extends Pizza {
    public enum Size {SMALL, MEDIUM, LARGE}
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NYPizza build() {
            return new NYPizza(this);
        }

        @Override
        Builder self() {
            return this;
        }
    }

    private NYPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }
}
