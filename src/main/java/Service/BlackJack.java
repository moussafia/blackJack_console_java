package Service;

public class BlackJack {

    public static int[][] listCards(int cardsValue, int cardsType,int[][] card) {
        int maxCardsType = 4;
        int maxCardsValue = 13;
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
        return BlackJack.listCards(1, 1, card);
    }

    public static int[][][] extraire_ieme_carte(int [][]cards,int indice){
        int lengthRows = cards.length;
        int[][] cardsExtracted = new int[1][2];
        int[][] reminingCards = new int[lengthRows-1][2];
        cardsExtracted[0] = cards[indice];
        int k = 0;
        for(int i = 0; i < cards.length; i++){
            if(i != indice){
                reminingCards[k] = cards[i];
                        k++;
                }
        }
        int[][][] cardsExtract = {cardsExtracted, reminingCards};
        return cardsExtract;

    }

    public static int[][][] tirer_une_carte(int[][] card){
        int lengthCards = card.length;
        int randomIndex = (int)Math.floor(Math.random()*(lengthCards));
        return extraire_ieme_carte(card,randomIndex);
    }

    public static int[][] melanger_jeu_cartes(int[][] card,int[][] cardsMixed){
        int[][][] cardsTrier = tirer_une_carte(card);
        if(cardsTrier[1].length == 0){
            return cardsMixed;
        }
        int rowLength = cardsMixed.length + 1;
        int[][] cardsMixednNEW = new int[rowLength][];
        if(cardsMixednNEW.length > 1){
            System.arraycopy(cardsMixed,0,cardsMixednNEW,0,cardsMixed.length);
            System.arraycopy(cardsTrier[0],0, cardsMixednNEW, cardsMixed.length,1);
        }else{
            cardsMixednNEW = cardsTrier[0];
        }
        int[][] cardsNoneMixed = new int[card.length - 1][2];
        int k=0;
        for(int i = 0; i < cardsNoneMixed.length; i++){
            cardsNoneMixed[i] = cardsTrier[1][k++];
        }
        return melanger_jeu_cartes(cardsNoneMixed, cardsMixednNEW);
    }

    /**
     *
     * @param cards
     * @return [array 2D of cards split,array 2D of cards of remaining]
     */
    public static int[][][] piocher_n_cartes(int [][]cards){
        int indice = (int) Math.floor(Math.random()*(28-20)+20);
        int lengthOfCardsPioche = cards.length-indice;
        int[][] cardsPioche = new int[lengthOfCardsPioche][];
        int[][] cardsRemaining = new int[indice][];
        System.arraycopy(cards, indice, cardsPioche,0, lengthOfCardsPioche);
        System.arraycopy(cards,0, cardsRemaining,0, indice);
        int[][][] listCards = {cardsPioche,cardsRemaining};
        return  listCards;
    }

    public static int score(int [][]array){

        int resultat = 0;
        int numberAce =0;

        for(int i = 0; i < array.length; i++){
            if(array[i][0] == 1){
                numberAce++;
            }
            resultat += array[i][0] > 10 ? 10 : 0;
            resultat += array[i][0] > 1 && array[i][0] < 11 ? array[i][0] : 0;
        }
        if((resultat + numberAce + 10) <= 21 && numberAce !=0){
            resultat += numberAce + 10;
        }else if((resultat + numberAce + 10) > 21 && numberAce !=0){
            resultat += numberAce;
        }
        return resultat;

    }

    /**
     *
     * @param cardsSplit
     * @param cardsPlayer
     * @return [array 2D of cards player, array 2D of remaining cards split]
     */
    public static  int[][][] hit(int [][]cardsSplit,int[][] cardsPlayer){
        int cardsGamer[][] = new int[cardsPlayer.length+1][];
        System.arraycopy(cardsPlayer,0,cardsGamer,0,cardsPlayer.length);
        cardsGamer[cardsPlayer.length] = cardsSplit[cardsSplit.length-1];
        int [][]cardsSplitRemining = new int[cardsSplit.length-1][];
        System.arraycopy(cardsSplit,0,cardsSplitRemining,0,cardsSplit.length-1);
        int [][][]cardsList = {cardsGamer, cardsSplitRemining};
        return cardsList;
    }
    public static boolean split(int[][] cardsPlayer){
        return cardsPlayer[0][0] == cardsPlayer[1][0];
    }

    /**
     *
     * @param score
     * @param cardsPlayer
     * @return null or [array 2D of cards Removed for dealer,array 2D of cards player with cardsPlayer[0][0]=0]
     */
    public static int[][][] assurance(int score,int[][] cardsPlayer){
        if(score == 21){
           int cardValueRemoved = cardsPlayer[1][0];
           int cardTypeRemoved = cardsPlayer[1][1];
           int[][] newCardsPlayer = {{cardsPlayer[0][0], cardsPlayer[0][0]}};
            int[][][] listCards = {{{cardValueRemoved, cardTypeRemoved}}, newCardsPlayer};
            return listCards;
        }
        return null;

    }

    /***
     *
     * @param playerData
     * @return [[player Index but don't use Index equal 0 ,Player is Winner if value == 1 else if value == 0]]
     */

    public static int[][] displayWinner(int[][] playerData){
        int[][] winner;
        if(playerData.length == 3){
            winner = new int[3][2];
        }else {
            winner = new int[2][2];
        }
        int countBlackJack = 0;
        for(int i = 0; i < playerData.length; i++){
            winner[i][0] = playerData[i][0];
        }
        for(int i = 0; i < playerData.length; i++){
            int dif = 21 - playerData[i][1];
            if( dif == 0){
                winner[i][1] = 1;
                countBlackJack++;
            }
        }
        if(countBlackJack == 0){
            int minDiff = 21;
            for(int i = 0, k = 0; i < playerData.length; i++){
                int dif = 21 - playerData[i][1];
                if( dif < minDiff && dif > 0){
                    minDiff = dif;
                }
            }
            for(int i = 0; i < playerData.length; i++){
                int dif = 21 - playerData[i][1];
                if( dif == minDiff){
                   winner[i][1] = 1;
                }
            }
        }
        return winner;
    }

}
