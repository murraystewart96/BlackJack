public enum Rank {
    TWO(2, 0),
    THREE(3, 0),
    FOUR(4, 0),
    FIVE(5, 0),
    SIX(6, 0),
    SEVEN(7, 0),
    EIGHT(8, 0),
    NINE(9, 0),
    TEN(10, 0),
    JACK(10, 0),
    QUEEN(10, 0),
    KING(10, 0),
    ACE(11, 1);


    private final int value;
    private final int lowValue;

    Rank(int value, int lowValue){
        this.value = value;
        this.lowValue = lowValue;
    }

    public int getValue() {
        return value;
    }

    public int getLowValue() {
        return lowValue;
    }
}
