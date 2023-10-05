package module;

public enum Category {
    HART('\u2764'),
    DIAMONDS('\u2666'),
    CLUBS('\u2663'),
    SPADES('\u2660');

    public final char symbol;

    Category(char s) {
        this.symbol=s;
    }
    public char getSymbol(){
        return symbol;
    }
}
