import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int[][] card = new int[1][2];
        int[][] arr = blackJack.listCards(1, 1, card);
        int[][] arrM =  new int[52][];
        for(int i=0;i<52;i++){
            arrM=blackJack.melanger_jeu_cartes(arr);
        }
        System.out.println("fefedf");
    }
    }

