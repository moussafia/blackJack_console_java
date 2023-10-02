package module;

public enum Value {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    HEIGHT("8"),
    NINE("9"),
    TEEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");


    public final String value;

    Value(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
