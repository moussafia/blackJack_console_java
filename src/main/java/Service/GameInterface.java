package Service;

import module.Text;

import java.util.Scanner;

public class GameInterface {
    public static void gameInterface(){
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
        String responsePlayer;
        responsePlayer=scanner.nextLine();
        while(!responsePlayer.toLowerCase().equals("ok")){
            System.out.println(Text.GREEN+"if you wanna play please press ok"+Text.RESET);
            responsePlayer=scanner.nextLine();
        }
        System.out.println("");
        GameBlackJack.blackJackGame();
    }
}
