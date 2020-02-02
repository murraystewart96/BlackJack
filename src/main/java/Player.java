import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private boolean isStuck;
    private int stuckAt;
    private boolean isBust;
    private boolean hasBlackjack;
    private int numAces;
    private int numAcesUsed;
    private int handTotal;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.isStuck = false;
        this.isBust = false;
        this.numAces = 0;
        this.hasBlackjack = false;
        this.handTotal = 0;
        this.numAcesUsed = 0;
    }

    public void takeCard(Card newCard){
        this.hand.add(newCard);
        this.calculateHandTotal();

        if(handTotal == 21){
            this.stick();
        }

        if(newCard.getRank() == Rank.ACE) numAces += 1;

        if(this.hand.size() ==2 && handTotal ==21){
            this.hasBlackjack = true;
        }else if(this.hand.size() == 2 && handTotal == 22){
            this.numAcesUsed += 1;
            this.numAces -= 1;
            this.calculateHandTotal();
        }


    }

    public boolean hasBlackjack(){return hasBlackjack;}

    public void calculateHandTotal(){
        int total = 0;
        for(Card card : hand){
            total += card.getValue();
        }
        this.handTotal = total -= (10 * numAcesUsed);
    }

    public int getHandTotal(){
        return handTotal;
    }


    public int getNumCards(){return this.hand.size();}

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int stuckAt() {return this.stuckAt;}

    public boolean isStuck() {
        return isStuck;
    }

    public char stickOrTwist(){
        return UI.getStickOrTwist();
    }


    public void twist(Card card){

        UI.displayTwist(this, card);
        takeCard(card);

        //if not stuck
        if(!isStuck) {
            //while above 21 and aces are available
            while (handTotal > 21 && numAces > 0) {
                numAces -= 1;     //Use ace to lower hand total
                numAcesUsed += 1;
                calculateHandTotal(); //Recalcuate hand total
            }

            UI.displayPlayersHand(this);

            if (handTotal > 21) {
                isBust = true;
                UI.displayPlayerIsBust(this);
            }
        }

    }


    public void stick(){
        this.isStuck = true;
        UI.displayStick(this);
    }


    public boolean isBust(){return isBust;}


    //clear hand and reset relevant data for new round
    public void clearHand(){
        this.hand.clear();
        this.hasBlackjack = false;
        this.isStuck = false;
        this.isBust = false;
        this.numAcesUsed = 0;
        this.numAces = 0;
        this.handTotal = 0;
    }
}
