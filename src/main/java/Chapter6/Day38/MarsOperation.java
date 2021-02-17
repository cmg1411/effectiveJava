package Chapter6.Day38;

public enum MarsOperation implements Operation {
    MARS_PLUS("++") {
        @Override
        public double apply(double x, double y) {
            return (x + y) * 2;
        }
    },
    MARS_MINUS("--") {
        @Override
        public double apply(double x, double y) {
            return (x - y) * 2;
        }
    };

    private final String symbol;

    MarsOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
