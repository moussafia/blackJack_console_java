public class blackJack {
    public static final int maxCardsType=4;
    public static final int maxCardsValue=13;

    //method une : recursivite
    public static int[][] listCards(int cardsValue, int cardsType,int[][] card){
        if(cardsValue == maxCardsValue && cardsType == maxCardsType){
            return card;
        }
       if(cardsValue == maxCardsValue){
           cardsValue = 1;
           cardsType++;
       }else{
           cardsValue++;
       }

       int[][]  arr = new int[card.length+1][2];
       for(int i = 0;i < card.length;i++){
           arr[i][0] = card[i][0];
           arr[i][1] = card[i][1];
       }
        arr[card.length][0] = cardsValue;
        arr[card.length][1] = cardsType;
        return listCards(cardsValue, cardsType, arr);
    }

    public static int[][][] extraire_ieme_carte(int [][]cards,int indice){
        int lengthRows=cards.length;
        int[][] cardsExtracted=new int[1][2];
        int[][] reminingCards=new int[lengthRows-1][2];
        cardsExtracted[0]=cards[indice];
        int k=0;
        for(int i=0;i<cards.length;i++){
            if(i!=indice){
                reminingCards[k]=cards[i];
                        k++;
                }
        }
        int[][][] cardsExtract={cardsExtracted,reminingCards};
        return cardsExtract;

    }

    public static int[][][] tirer_une_carte(int[][] card){
        int lengthCards=card.length;
        int randomIndex=(int)Math.floor(Math.random()*(lengthCards));
        System.out.println(randomIndex);
        return extraire_ieme_carte(card,randomIndex);
    }

    public static int[][] melanger_jeu_cartes(int[][] card,int[][] cardsMixed){
        int[][][] cardsTrier = tirer_une_carte(card);
        if(cardsTrier[1].length == 0){
            return cardsMixed;
        }
        int rowLength=cardsMixed.length+1;
        int[][] cardsMixednNEW = new int[rowLength][];
        if(cardsMixednNEW.length > 1){
            System.arraycopy(cardsMixed,0,cardsMixednNEW,0,cardsMixed.length);
            System.arraycopy(cardsTrier[0],0, cardsMixednNEW, cardsMixed.length,1);
        }else{
            cardsMixednNEW = cardsTrier[0];
        }
        int[][] cardsNoneMixed = new int[card.length-1][2];
        int k=0;
        for(int i=0;i<cardsNoneMixed.length;i++){
            cardsNoneMixed[i] = cardsTrier[1][k++];
        }
        return melanger_jeu_cartes(cardsNoneMixed,cardsMixednNEW);
    }
    public static int[][][] piocher_n_cartes(int [][]cards, int indice){
        int lengthOfCardsPioche=cards.length-indice;
        int[][] cardsPioche=new int[lengthOfCardsPioche][];
        int[][] cardsRemining=new int[indice][];
        System.arraycopy(cards, indice, cardsPioche,0, lengthOfCardsPioche);
        System.arraycopy(cards,0, cardsRemining,0, indice);
        int[][][] listCards={cardsPioche,cardsRemining};
        return  listCards;
    }
}
