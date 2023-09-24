package module;

public class Cards {
    public static int cardsValue;
    public static int cardsType;

    public static Category category;

    public static void Cards(int cardsValue,int cardsType,boolean hide){
        category=Category.getCategoryByIndex(cardsType);
        char symbol=category.getSymbol();
        String[] cardLines = new String[]{
                "┌─────────┐",
                String.format("│ %s       │", hide ? "?" : cardsValue),
                "│         │",
                "│         │",
                String.format("│    %s    │", hide ? "?" : symbol),
                "│         │",
                "│         │",
                String.format("│       %s │", hide ? "?" : cardsValue),
                "└─────────┘"
        };

        for (String line : cardLines) {
            System.out.println(line);
        }

    }

}
