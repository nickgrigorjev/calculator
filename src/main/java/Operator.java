enum Operator {
    PLUS("+"), MINUS("-"), MULTIPLICATION("*"), DIVISION("/");

    private final String symbol;
    Operator(String symbol){
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}