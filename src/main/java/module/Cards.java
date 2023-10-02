package module;

public class Cards {

    public static Value value;
    public static Category category;

    public static void Cards(int cardsValue,int cardsType,boolean hide){
        category=Category.values()[cardsType-1];
        char symbol=category.getSymbol();
        value=Value.values()[cardsValue-1];
        String valueC=value.getValue();
        String[] cardLines = new String[]{
                "┌─────────┐",
                String.format("│ %s       │", hide ? "?" : valueC),
                "│         │",
                "│         │",
                String.format("│    %s    │", hide ? "?" : symbol),
                "│         │",
                "│         │",
                String.format("│       %s │", hide ? "?" : valueC),
                "└─────────┘"
        };

        for (String line : cardLines) {
            System.out.println(line);
        }

    }

}
