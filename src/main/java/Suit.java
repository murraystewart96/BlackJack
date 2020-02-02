public enum Suit {
    HEARTS("hearts"),
    DIAMONDS("diamonds"),
    SPADE("spades"),
    CLUBS("clubs");


    private final String value;

    Suit(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
