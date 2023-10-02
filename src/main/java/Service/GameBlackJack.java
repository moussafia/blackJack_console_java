package Service;

import module.Cards;

import java.util.Scanner;

public class GameBlackJack {

    /***
     *
     * @return [array 2D of cards split and mixed,array 2D of cards of remaining and mixed]
     */
    public static int[][][] CardsSplit(){
        int[][] cards = BlackJack.createCards();
        int[][] cardsMixed = new int[0][2];
        int[][] cardsMix = BlackJack.melanger_jeu_cartes(cards, cardsMixed);
        int[][][] cardsSplit = BlackJack.piocher_n_cartes(cardsMix);
        return cardsSplit;
    }

    /***
     *
     * @param cardsForPlaying
     * @return
     */
    public static int[][][] initialGame(int[][] cardsForPlaying){
        int[][] cardsPlayer = new int[2][];
        int[][] cardsDealer = new int[2][];
        cardsDealer[0] = cardsForPlaying[cardsForPlaying.length -1];
        cardsDealer[1] = cardsForPlaying[cardsForPlaying.length -2];
        cardsPlayer[0] = cardsForPlaying[cardsForPlaying.length -3];
        cardsPlayer[1] = cardsForPlaying[cardsForPlaying.length -4];
        int[][] cardsRemaining = new int[cardsForPlaying.length - 4][];
        System.arraycopy(cardsForPlaying, 0, cardsRemaining, 0, cardsForPlaying.length-4);
        displayCardsAndScore(cardsPlayer, "Dealer" ,false, true);
        return splitCards(cardsPlayer, cardsDealer, cardsRemaining);
    }

    /***
     *
     * @param cardsPlayer
     * @param cardsRemaining
     * @return [cardsPlayer1, cardsPlayer2, cardsRemaining] or [cardsPlayer, cardsRemaining]
     */
    public static int[][][] splitCards(int[][] cardsPlayer, int[][] cardsDealer, int[][] cardsRemaining){
        int[][][] collections={cardsPlayer, cardsDealer, cardsRemaining};
        if(BlackJack.split(cardsPlayer)){
            System.out.println("do you wanna Split");
            System.out.println("press ok/no :   if you want/don't want");
            Scanner scanner=new Scanner(System.in);
            String responsePlayer = scanner.nextLine().toLowerCase();
            while( responsePlayer != "ok" || responsePlayer != "no"){
                System.out.println("press ok/no :   if you want/don't want");
                responsePlayer = scanner.nextLine();
            }
            if(responsePlayer == "ok"){
                int[][] cardsPlayer1 = new int[2][];
                int[][] cardsPlayer2 = new int[2][];
                cardsPlayer1[0] = cardsPlayer[0];
                cardsPlayer2[0] = cardsPlayer[1];
                cardsPlayer1[1] = cardsPlayer[cardsPlayer.length - 1];
                cardsPlayer2[1] = cardsPlayer[cardsPlayer.length - 2];
                displayCardsAndScore(cardsPlayer1, "Player one" ,true, false);
                displayCardsAndScore(cardsPlayer2, "Player two" ,true, false);
                int[][] cardsRemain = new int[cardsRemaining.length - 2][];
                System.arraycopy(cardsRemaining , 0, cardsRemain, 0, cardsRemaining.length - 2);
                int[][][] collectionCards={cardsPlayer1, cardsPlayer2, cardsDealer, cardsRemain};
                return collectionCards;
            }
            displayCardsAndScore(cardsPlayer, "Player" ,true, false);
            return collections;
        }
        displayCardsAndScore(cardsPlayer, "Player" ,true, false);
        return collections;
    }

    public static void displayCardsAndScore(int[][] cardsPlayer, String namePlayer,boolean isPlayer,boolean hide){
        int score=BlackJack.score(cardsPlayer);
        System.out.println(namePlayer + ": "+ (isPlayer ? score : cardsPlayer[0][0] + "+?" ));
        for(int i = 0; i < cardsPlayer.length; i++){
                Cards.Cards(cardsPlayer[i][0], cardsPlayer[i][1], (hide && i == 1) ? true: false );
        }


    }
    public static void blackJackGame(){
        int[][][] cardsSplit = CardsSplit();
        int[][] cardsForPlaying = cardsSplit[0];
        int[][][] collections = initialGame(cardsForPlaying);
        if(collections.length == 4){
            int[][] cardsPlayer1 = collections[0];
            int[][] cardsRemaining = collections[3];
            hitPlayer(cardsPlayer1, cardsRemaining, "Player One", true);
            int[][] cardsPlayer2 = collections[1];
            hitPlayer(cardsPlayer2, cardsRemaining, "Player Two", true);
            int[][] cardsDealer = collections[2];
            hitPlayer(cardsDealer, cardsRemaining, "dealer", false);
        }else {
            int[][] cardsPlayer1 = collections[0];
            int[][] cardsRemaining = collections[2];
            hitPlayer(cardsPlayer1, cardsRemaining, "Player", true);
            int[][] cardsDealer = collections[1];
            hitPlayer(cardsDealer, cardsRemaining, "dealer", false);
        }
    }


    public static int[][][] hitPlayer(int [][]cardsPlayer, int[][] cardsRemaining,String namePlayer,boolean isPlayer){
        Scanner scanner=new Scanner(System.in);
        int scorePlayer=BlackJack.score(cardsPlayer);
        System.out.println("h: Hit     s: stand");
        String responsePlayer=scanner.nextLine().toLowerCase();
        int[][][] collections;
        while (responsePlayer == "h" && scorePlayer < 21){
            collections = BlackJack.hit(cardsRemaining, cardsPlayer);
            cardsPlayer = collections[0];
            displayCardsAndScore(cardsPlayer, namePlayer, isPlayer, false);
            cardsRemaining = collections[1];
            scorePlayer=BlackJack.score(cardsPlayer);
            if(scorePlayer < 21){
                System.out.println("h: Hit     s: stand");
                responsePlayer=scanner.nextLine().toLowerCase();
            }
        }
        int [][][]collectionInfo = {{{scorePlayer}},cardsPlayer,cardsRemaining};
        return collectionInfo;
    }


}