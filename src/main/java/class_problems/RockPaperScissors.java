import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("Rock-Paper-Scissors Game");
        System.out.println("Enter Rock, Paper, or Scissors.");

        System.out.println("\nRound | Player Move | Computer Move | Result");
        System.out.println("---------------------------------------------");

        for (int round = 1; round <= 5; round++) {

            System.out.print("Round " + round + " - Enter your move: ");
            String playerMove = sc.nextLine();

            playerMove = playerMove.substring(0, 1).toUpperCase()
                    + playerMove.substring(1).toLowerCase();

            String computerMove = moves[random.nextInt(3)];

            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println(round + "     | " + playerMove + "        | "
                    + computerMove + "       | " + result);
        }

        double winPercentage = (wins / 5.0) * 100;

        System.out.println("\nFinal Summary (after 5 rounds)");
        System.out.println("Wins: " + wins +
                " | Losses: " + losses +
                " | Draws: " + draws +
                " | Win % = " + winPercentage + "%");

        sc.close();
    }
}