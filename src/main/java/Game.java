import java.util.ArrayList;

public class Game {

    private Dealer dealer;
    private ArrayList<Player> players;
    private static int numPlayers;
    private boolean playing;


    public Game(){
        this.dealer = new Dealer();
        this.players = new ArrayList<>();
        UI.welcome();
        this.numPlayers = UI.getNumPlayers();
        addPlayers();
        this.playing = true;
    }


    private void addPlayers(){
        for(int i = 0; i < numPlayers; i++){
            String playerName = UI.getPlayerName(i+1);
            this.players.add(new Player(playerName));
        }

    }


    private void gameLoop(){

        UI.beginGame();

        while(playing){
            dealer.dealCards(players);
            UI.displayCards(players, dealer);  //display initial cards on the table
            playTurn();  //Play through a turn
            playAgain(); //Decide whether to play again
        }
    }

    private void playTurn(){
        dealer.playPlayersTurn(players); //players play turn
        dealer.playTurn();               //dealer plays
        UI.displayResult();
        dealer.compareHands(players);    //display result at the end end of round
    }


    public void run(){
        gameLoop();
    }


    private void playAgain(){
        char choice = UI.playAgain();

        if(choice == 'y'){
            clearTable();
            dealer.initializeDeck();
        }else{
            this.playing = false;
            UI.displayGameOver();
        }
    }


    private void clearTable(){
        for(Player player: players){
            player.clearHand();
        }
        dealer.clearHand();
    }

}
