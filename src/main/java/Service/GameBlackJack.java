package Service;

import module.Cards;
import module.Text;

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
        cardsDealer[0] = cardsForPlaying[cardsForPlaying.length - 1];
        cardsDealer[1] = cardsForPlaying[cardsForPlaying.length - 2];
        cardsPlayer[0] = cardsForPlaying[cardsForPlaying.length - 3];
        cardsPlayer[1] = cardsForPlaying[cardsForPlaying.length - 4];
        int[][] cardsRemaining = new int[cardsForPlaying.length - 4][];
        System.arraycopy(cardsForPlaying, 0, cardsRemaining, 0, cardsForPlaying.length-4);
        displayCardsAndScore(cardsDealer, "Dealer" ,false, true, true);
//        cardsPlayer = new int[][]{{3, 2}, {3, 4}};
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
        displayCardsAndScore(cardsPlayer, "Player" ,true, false, true);
        if(BlackJack.split(cardsPlayer)){
            System.out.println("do you wanna Split");
            System.out.println("press ok/no :   if you want/don't want");
            Scanner scanner=new Scanner(System.in);
            String responsePlayer = scanner.nextLine().toLowerCase();
            while( !responsePlayer.equals("ok") && !responsePlayer.equals("no")){
                System.out.println("press ok/no :   if you want/don't want");
                responsePlayer = scanner.nextLine();
            }
            if(responsePlayer.equals("ok")){
                int[][] cardsPlayer1 = new int[2][];
                int[][] cardsPlayer2 = new int[2][];
                cardsPlayer1[0] = cardsPlayer[0];
                cardsPlayer2[0] = cardsPlayer[1];
                cardsPlayer1[1] = cardsRemaining[cardsPlayer.length - 1];
                cardsPlayer2[1] = cardsRemaining[cardsPlayer.length - 2];
                displayCardsAndScore(cardsPlayer1, "Player one" ,true, false, false);
                displayCardsAndScore(cardsPlayer2, "Player two" ,true, false, false);
                int[][] cardsRemain = new int[cardsRemaining.length - 2][];
                System.arraycopy(cardsRemaining , 0, cardsRemain, 0, cardsRemaining.length - 2);
                int[][][] collectionCards={cardsPlayer1, cardsPlayer2, cardsDealer, cardsRemain};
                return collectionCards;
            }
            return collections;
        }
        return collections;
    }
    public static void displayCardsAndScore(int[][] cardsPlayer, String namePlayer, boolean isPlayer, boolean hide, boolean isInitialGame){
        for(int i = 0; i < cardsPlayer.length; i++){
                Cards.Cards(cardsPlayer[i][0], cardsPlayer[i][1], (hide && i == 1) ? true: false );
        }
        int score=BlackJack.score(cardsPlayer);
        int scoreDealer=0;
        if(isInitialGame){
            scoreDealer = cardsPlayer[0][0] > 10 ? 10 : cardsPlayer[0][0];
            scoreDealer = scoreDealer == 1 ? 11 : scoreDealer;
        }
        System.out.println(Text.BLUE + namePlayer + ": "+ (isPlayer ? score : scoreDealer + "+?" ) + Text.RESET);
    }

    public static int[][][] hitPlayer(int [][]cardsPlayer, int[][] cardsRemaining,String namePlayer,boolean isPlayer,int condition, boolean initialHeat){
        Scanner scanner=new Scanner(System.in);
        int scorePlayer=BlackJack.score(cardsPlayer);
        int[][][] collections;
        if(!initialHeat){
            displayCardsAndScore(cardsPlayer, namePlayer, isPlayer, false, false);
        }
        String responsePlayer = new String();
        if(scorePlayer < condition  && condition == 21){
            System.out.println("h: Hit     s: stand  for " + Text.ORANGE + namePlayer + Text.RESET);
            responsePlayer=scanner.nextLine().toLowerCase().trim();
        }
        boolean conditionHit = scorePlayer < condition;
        if(condition == 21){
            conditionHit = conditionHit && responsePlayer.equals("h");
        }
        while (conditionHit){
            collections = BlackJack.hit(cardsRemaining, cardsPlayer);
            cardsPlayer = collections[0];
            displayCardsAndScore(cardsPlayer, namePlayer, isPlayer, false, false);
            cardsRemaining = collections[1];
            scorePlayer=BlackJack.score(cardsPlayer);
            conditionHit = scorePlayer < condition;
            if(scorePlayer < condition && condition == 21){
                System.out.println("h: Hit     s: stand");
                responsePlayer=scanner.nextLine().toLowerCase().trim();
                conditionHit = conditionHit && responsePlayer.equals("h");
            }
        }
        int [][][]collectionInfo = {{{scorePlayer}},cardsPlayer,cardsRemaining};
        return collectionInfo;
    }

    public static void displayWinner(String[] namePlayers,int[][] dataPlayers){
         int[][] dataWinner = BlackJack.displayWinner(dataPlayers);
         int NumberWinners = 0;
          System.out.println("the winner are / is :");
         for(int i = 0; i < dataWinner.length; i++){
             if(dataWinner[i][1] == 1){
                 int indexNameWinner = dataWinner[i][0];
                 System.out.println(Text.GREEN + namePlayers[indexNameWinner] + " score: " + dataPlayers[indexNameWinner][1] + Text.RESET);
                 NumberWinners ++;
             }
         }
         if(NumberWinners == 0){
             System.out.println(Text.RED + "No one Win" + Text.RESET);
         }
    }

    public static int[][][] assuranceBlackJack(int [][]cardsDealer){
        displayCardsAndScore(cardsDealer, "Dealer" ,false, true, true);
        System.out.println("do you wanna do assurance press ok or no");
        Scanner scanner = new Scanner(System.in);
        String responsePlayer = scanner.nextLine().toLowerCase();
        while (!responsePlayer.equals("ok") && !responsePlayer.equals("no")){
            System.out.println("do you wanna do assurance press ok or no");
            responsePlayer = scanner.nextLine();
        }
        if(responsePlayer.equals("ok")){
            displayCardsAndScore(cardsDealer, "Dealer" ,true, false, true);
            int score=BlackJack.score(cardsDealer);
            int[][][] NewCardsDealer = BlackJack.assurance(score, cardsDealer);
                return NewCardsDealer;
        }
        return null;
    }

    public static void hitPlayerSplited(int[][] cardsPlayerOne, int[][] cardsPlayerTwo, int[][] cardsDealers, int[][] cardsRemain){
        int[][] cardsPlayer1 = cardsPlayerOne;
        int[][] cardsRemaining = cardsRemain;
        int[][][] hitPlayerOne = hitPlayer(cardsPlayer1, cardsRemaining, "Player One", true, 21, true);
        cardsRemaining = hitPlayerOne[2];
        int scorePlayerOne = hitPlayerOne[0][0][0];
        int[][] cardsPlayer2 = cardsPlayerTwo;
        int[][][] hitPlayerTwo = hitPlayer(cardsPlayer2, cardsRemaining, "Player Two", true, 21, false);
        int scorePlayerTwo = hitPlayerTwo[0][0][0];
        int[][] cardsDealer = cardsDealers;
        cardsRemaining = hitPlayerTwo[2];
        int scoreDealer = BlackJack.score(cardsDealers);
        if(scorePlayerOne != 21 && scorePlayerTwo != 21){
            int[][][] hitDealer = hitPlayer(cardsDealer, cardsRemaining, "dealer", true, 17, false);
            scoreDealer = hitDealer[0][0][0];
        }
        String[] namePlayer = {"Player One", "Player Two", "dealer"};
        int[][] DataPlayers={{0, scorePlayerOne},{1, scorePlayerTwo},{2, scoreDealer}};
        displayWinner(namePlayer, DataPlayers);
    }
    public static void hitPlayerNoneSplited(int[][] cardsPlayer, int[][] cardsDealers, int[][] cardsRemain){
        int[][] cardsPlayer1 = cardsPlayer;
        int[][] cardsRemaining = cardsRemain;
        int[][][] hitPlayer = hitPlayer(cardsPlayer1, cardsRemaining, "Player", true, 21, true);
        int scorePlayer = hitPlayer[0][0][0];
        cardsRemaining = hitPlayer[2];
        int[][] cardsDealer = cardsDealers;
        int scoreDealer = BlackJack.score(cardsDealer);
        if(scorePlayer !=21){
            int[][][] NewCardsDealer =assuranceBlackJack(cardsDealer);
            int[][][] hitDealer;
            if(NewCardsDealer == null){
                hitDealer = hitPlayer(cardsDealer, cardsRemaining, "dealer", true, 17, false);
            }else {
                hitDealer = hitPlayer(NewCardsDealer[1], cardsRemaining, "dealer", true, 17, false);
                System.out.println();
            }
            scoreDealer = hitDealer[0][0][0];
        }

        String[] namePlayers = {"YOU", "dealer"};
        int[][] dataPlayers={{0, scorePlayer},{1, scoreDealer}};
        displayWinner(namePlayers, dataPlayers);
    }

    public static void blackJackGame(){
        int[][][] cardsSplit = CardsSplit();
        int[][] cardsForPlaying = cardsSplit[0];
        int[][][] collections = initialGame(cardsForPlaying);
        if(collections.length == 4){
            hitPlayerSplited(collections[0], collections[1], collections[2], collections[3]);
        }
        else {
            hitPlayerNoneSplited(collections[0], collections[1], collections[2]);
        }
    }


}
