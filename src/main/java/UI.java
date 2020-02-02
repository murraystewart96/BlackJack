import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private static Scanner sc = new Scanner(System.in);

    public static void welcome(){
        System.out.println("*** Welcome to BlackJack ***\n");
    }

    public static int getNumPlayers(){
        System.out.println("How many players wish to play: ");
        int numPlayers = sc.nextInt();
        sc.nextLine();
        return numPlayers;
    }

    public static String getPlayerName(int playerNo){
        System.out.println("Please Enter the name of player " + playerNo + ": ");
        return sc.nextLine();
    }

    public static void beginGame(){
        System.out.println("Enter any key to begin the game...\n");
        sc.nextLine();
    }

    public static void startRound(){
        System.out.println("Enter any key to begin the round...\n");
        sc.nextLine();
    }

    public static void displayCards(ArrayList<Player> players, Dealer dealer){
        for(Player player: players){
            System.out.printf(player.getName() + "'s cards: ");
            displayPlayersCards(player.getHand());
        }

        displayDealersKnownCard(dealer);
    }

    public static void displayDealersKnownCard(Dealer dealer){
        System.out.println("Dealer's Known Card: " + dealer.getKnownCard().getValue() +"\n\n");
    }



    public static void displayDealersCards(ArrayList<Card> hand){
        for(Card card: hand){
            System.out.printf(card.getValue() + "  ");
        }
    }

    public static void displayDealersHand(Dealer dealer){
        System.out.printf("Dealers cards: ");
        displayDealersCards(dealer.getHand());
        System.out.println("Hand Total: " + dealer.getHandTotal() +"\n");
    }

    public static void displayPlayersCards(ArrayList<Card> hand){
        for(Card card : hand) {
            if(card.getRank() == Rank.ACE){
                System.out.printf(card.getRank() + "  ");
            }else{
                System.out.printf(card.getValue() + "  ");
            }
        }
        System.out.println("\n");
    }


    public static void playerTurn(Player player){
        startPlayersTurn(player);

    }

    public static void startPlayersTurn(Player player) {
        System.out.println("*** " + player.getName() + "'s turn! ***\n");
        System.out.println("Press any key to begin turn...");
        sc.nextLine();
        displayPlayersHand(player);
    }


    public static char getStickOrTwist(){
        System.out.println("Stick or Twist: (s/t): ");
        char descision = sc.next().charAt(0);
        sc.nextLine();
        descision = Character.toLowerCase(descision);

        while(descision != 's' && descision != 't'){
            descision = sc.next().charAt(0);
            descision = Character.toLowerCase(descision);
        }

        return descision;
    }

    public static void displayPlayerIsBust(Player player){
        System.out.println(player.getName() + " is bust with " + player.getHandTotal() + "\n\n");
    }

    public static void displayPlayersHand(Player player){
        displayPlayersCards(player.getHand());

        System.out.println("Hand Total: " + player.getHandTotal() + "\n\n");

    }

    public static void displayTwist(Player player, Card card){
        if(card.getRank() == Rank.ACE){
            System.out.println(player.getName() + " was dealt an " + card.getRank() + "\n");
        }else {
            System.out.println(player.getName() + " was dealt " + card.getValue() + "\n");
        }
    }

    public static void displayStick(Player player){
        System.out.println(" *** " + player.getName() + " has stuck at " + player.getHandTotal() + " ***\n\n");
    }

    public static void displayDealersTurn(Dealer dealer){
        System.out.println("*** Dealers Turn! ***\n");
        System.out.println("Press any key start turn...");
        sc.nextLine();
        displayDealersHand(dealer);
    }


    public static void displayResult(){
        System.out.printf("*** ROUND RESULT! ***\n");
    }

    public static void displayDealersBust(){
        System.out.println("Dealer is bust!\n");
    }


    public static void displayWin(String name, int total){
        System.out.println(name + " won with " + total + "!");
    }

    public static void displayLoss(String name, int total){
        System.out.println(name + " lost with " + total + "!");
    }

    public static void displayDraw(String name){
        System.out.println(name + " drew with the dealer!");
    }


    public static char playAgain(){
        System.out.println("\nWould you like to play gain? (y/n): ");
        char choice = sc.next().charAt(0);
        choice = Character.toLowerCase(choice);

        while(choice != 'y' && choice != 'n'){
            choice = sc.next().charAt(0);
            choice = Character.toLowerCase(choice);
        }

        return choice;
    }

    public static void displayBlackjack(String name){
        System.out.println(name + " has Blackjack!\n");
    }

    public static void displayGameOver(){
        System.out.println("\nGAME OVER!");
    }

}
