package module;

public enum Category {
    HART('1'),
    DIAMONDS('2'),
    CLUBS('3'),
    SPADES('4');

    public final char symbol;

    Category(char s) {
        this.symbol=s;
    }
    public char getSymbol(){
        return symbol;
    }
}
