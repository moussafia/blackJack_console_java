package Service;

import module.Cards;
import module.Text;

import java.util.Scanner;

public class Game {

    public static void blackJackGame(){
        Scanner scanner=new Scanner(System.in);
        System.out.println( Text.RED+"Welcome to Blackjack!"+Text.RESET);
        System.out.println("");
        System.out.println("1)-The goal is to get as close to 21 as possible without going over.");
        System.out.println("2)-Card values: 2-10 are face value, faces are 10, and Aces can be 1 or 11.");
        System.out.println("3)-The dealer will draw cards until they have 17 or more.");
        System.out.println("4)-You can keep asking for cards until you decide to stand or go over 21.");
        System.out.println("5)-If you have a higher total than the dealer without going over 21, you win.");
        System.out.println("6)-If the dealer goes over 21, you win. If you go over 21, you lose.");
        System.out.println("7)-If you and the dealer have the same total, it's a tie.");
        System.out.println("");
        System.out.println(Text.GREEN+"if you wanna play please press ok"+Text.RESET);
        String responsePlayer=new String();
        responsePlayer=scanner.nextLine();
        while(!responsePlayer.toLowerCase().equals("ok")){
            System.out.println(Text.GREEN+"if you wanna play please press ok"+Text.RESET);
            responsePlayer=scanner.nextLine();
        }
        System.out.println("");
        int[][] CreatedCards = blackJack.createCards();
        int[][] arrayInitial =  new int[0][2];
        int[][]    cardsMixed = blackJack.melanger_jeu_cartes(CreatedCards,arrayInitial);
        int indicePioche = (int) Math.floor(Math.random()*(28-20)+20);
        int[][][] cardsSplitAndRemaining = blackJack.piocher_n_cartes(cardsMixed, indicePioche);
        int [][]cardsForPlaying = cardsSplitAndRemaining[0];
        int[][] dealer = new int[21][];
        int[][] player = new int[21][];
        int lengthCardsForPlaying = cardsForPlaying.length-1;
        dealer[0] = cardsForPlaying[lengthCardsForPlaying];
        dealer[1] = cardsForPlaying[lengthCardsForPlaying-1];
        player[0] = cardsForPlaying[lengthCardsForPlaying-2];
        player[1] = cardsForPlaying[lengthCardsForPlaying-3];
        int sumTotalPlayer = player[0][0] + player[1][0];
        int sumTotalDealer = dealer[0][0] + dealer[1][0];
        System.out.println("dealer : " + dealer[0][0] + "+? score");
        Cards.Cards(dealer[0][0],dealer[0][1],false);
        Cards.Cards(dealer[1][0],dealer[1][1],true);
        System.out.println("YOU : " + sumTotalPlayer + " score");
        Cards.Cards(player[0][0],player[0][1],false);
        Cards.Cards(player[1][0],player[1][1],false);
        System.out.println("s : for stand   |     h: for hit");
        String standOrHeat=scanner.nextLine();
        while (!standOrHeat.toLowerCase().equals("h") && !standOrHeat.toLowerCase().equals("s")){
            System.out.println("s : for stand   |     h: for hit");
            standOrHeat=scanner.nextLine();
        }

    }
}
