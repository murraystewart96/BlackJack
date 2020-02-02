import java.util.ArrayList;

public class Dealer {

    private Deck deck;
    private ArrayList<Card> hand;
    private boolean isBust;
    private boolean hasBlackjack;
    public int numAces;
    public int numAcesUsed;
    private int handTotal;


    public Dealer(){
        this.deck = new Deck();
        this.deck.shuffleDeck();
        this.hand = new ArrayList<>();
        this.isBust = false;
        this.hasBlackjack = false;
        this.numAces = 0;
        this.numAcesUsed = 0;
        this.handTotal = 0;
    }

    public void initializeDeck(){
        this.deck.populateDeck();
        this.deck.shuffleDeck();
    }

    public void addCard(Card newCard){
        this.hand.add(dealCard());  //take cards
        calculateHandTotal();     //calculate new hand total
        calculateNumAcesInHand(); //update in case of new ace
    }

    public Card dealCard(){
        return this.deck.removeCard();
    }

    public void calculateHandTotal(){
        int total = 0;
        for(Card card : hand){
            total += card.getValue();
        }
         this.handTotal = total -= (numAcesUsed * 10);
    }

    public void dealCards(ArrayList<Player> players){
        for(int i = 0; i < 2; i++){
            for(Player player: players){
                player.takeCard(dealCard());
            }
            this.hand.add(dealCard());
        }

        calculateNumAcesInHand();
        if(numAces == 2) {
            numAcesUsed += 1;
            numAces -= 1;
            calculateHandTotal();
        }
        if(handTotal == 21) hasBlackjack = true;
    }

    public int getHandTotal() {
        return handTotal;
    }

    public int getNumCards(){return this.hand.size();}

    public int getNumCardsInDeck(){return this.deck.numCardsInDeck();}


    public void calculateNumAcesInHand(){
        int total = 0;
        for(Card card :hand){
            if(card.getRank() == Rank.ACE) total += 1;
        }

        numAces = total -= numAcesUsed;
    }


    public void playPlayersTurn(ArrayList<Player> players){

        //Loop through each players turn
        for(Player player : players){

            //UI to start of players turn
            UI.startPlayersTurn(player);

            if(player.hasBlackjack()) UI.displayBlackjack(player.getName());

            //While current players turn
            while(!player.isBust() && !player.isStuck()) {

                //get stick or twist
                char stickOrTwist = player.stickOrTwist();
                if (stickOrTwist == 't') {
                    player.twist(dealCard()); //twist card

                }else{
                    player.stick();  //stick
                }
            }
        }
    }

    public boolean isBust() {
        return isBust;
    }

    public void playTurn(){

        if(!hasBlackjack) {

            //Keep taking cards while hand total is below 17
            while (handTotal < 17) {
                addCard(dealCard());

                //while the hand total is above 21 and there are aces to be used
                while(handTotal > 21 && numAces > 0){
                    numAces -= 1;
                    numAcesUsed += 1;
                    calculateHandTotal();     //calculate new hand total
                    System.out.println(handTotal);
                }
            }

            UI.displayDealersTurn(this);

            //Check to see if player is burst
            if (handTotal > 21) {
                isBust = true;
                UI.displayDealersBust();
            }
        }else{  //if dealer has black
            UI.displayDealersTurn(this);
            UI.displayBlackjack("Dealer");
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void clearHand(){
        this.hand.clear();
        this.hasBlackjack = false;
        this.numAces = 0;
        this.numAcesUsed = 0;
        this.isBust = false;
    }

    public Card getKnownCard() {return this.hand.get(0);}


    public void compareHands(ArrayList<Player> players) {

        //if the dealer is bust
        if (this.isBust) {
            for (Player player : players) {
                if (!player.isBust()) {
                    UI.displayWin(player.getName(), player.getHandTotal());   //display win if player is not bust
                } else {
                    UI.displayLoss(player.getName(), player.getHandTotal());  //display loss if player is bust
                }
            }
        } else if(hasBlackjack) {
            for(Player player : players) {
                if(!player.hasBlackjack()) {
                    UI.displayLoss(player.getName(), player.getHandTotal());
                }else {
                    UI.displayWin(player.getName(), player.getHandTotal());   //display win if player is not bust
                }
            }
        } else {  //If dealer isnt bust
                for (Player player : players) {
                    if(!player.isBust()) {
                        if (player.getHandTotal() > handTotal) {
                            UI.displayWin(player.getName(), player.getHandTotal());     //display win if players hand is higher
                        }else {
                            UI.displayLoss(player.getName(), player.getHandTotal());    //display loss if players hand is lower or equal
                        }
                    }else{
                        UI.displayLoss(player.getName(), player.getHandTotal());     //Display loss if player is bust
                    }
                }
            }
        }
}
