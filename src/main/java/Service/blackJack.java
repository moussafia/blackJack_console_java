package Service;

import module.Text;

import java.util.Scanner;

public class blackJack {
    public static final int maxCardsType=4;
    public static final int maxCardsValue=13;

    public static int[][] listCards(int cardsValue, int cardsType,int[][] card) {
        int[][] arr = new int[card.length + 1][2];
        System.arraycopy(card,0,arr,0,card.length);
        arr[card.length-1][0] = cardsValue;
        arr[card.length-1][1] = cardsType;
        if (cardsValue == maxCardsValue && cardsType == maxCardsType) {
            return card;
        }
        if (cardsValue == maxCardsValue) {
            cardsValue = 1;
            cardsType++;
        }else{
            cardsValue++;
        }
        return listCards(cardsValue, cardsType, arr);
    }

    public static int[][] createCards(){
        int[][] card = new int[1][2];
        return blackJack.listCards(1, 1, card);
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

    /**
     *
     * @param cards
     * @param indice
     * @return [cards split,cards of remaining]
     */
    public static int[][][] piocher_n_cartes(int [][]cards, int indice){
        int lengthOfCardsPioche=cards.length-indice;
        int[][] cardsPioche=new int[lengthOfCardsPioche][];
        int[][] cardsRemaining=new int[indice][];
        System.arraycopy(cards, indice, cardsPioche,0, lengthOfCardsPioche);
        System.arraycopy(cards,0, cardsRemaining,0, indice);
        int[][][] listCards={cardsPioche,cardsRemaining};
        return  listCards;
    }

}
