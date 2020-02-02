import java.util.ArrayList;
import java.util.Collections;

public class Deck {


    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        populateDeck();
    }

    public void populateDeck(){

        if(!cards.isEmpty()) cards.clear();

        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                this.cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(this.cards);
    }

    public int numCardsInDeck() {return this.cards.size();}


    public Card removeCard(){
        if(!this.cards.isEmpty()){
            return this.cards.remove(0);
        }
        return null;
    }


}
