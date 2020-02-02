import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
        import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DealerTest {

    Deck deck;
    Dealer dealer;
    Player player1;
    Player player2;
    Player player3;
    ArrayList<Player> players;
    Card ace;
    Card five;

    @Before
    public void before(){
        deck = new Deck();
        dealer = new Dealer();
        players= new ArrayList<>();
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        player3 = new Player("Player3");
        players.add(player1);
        players.add(player2);
        players.add(player3);
        ace = new Card(Rank.ACE, Suit.DIAMONDS);
        five = new Card(Rank.FIVE, Suit.CLUBS);
    }

    @Test
    public void canPopulateDeck(){
        assertEquals(52, deck.numCardsInDeck());
    }

    @Test
    public void canDealToPlayers(){
        dealer.dealCards(players);
        int numCardsOnTable = 0;
        for(Player player : players){
            numCardsOnTable += player.getNumCards();
        }
        numCardsOnTable += dealer.getNumCards();

        assertEquals(8, numCardsOnTable);
    }

    @Test
    public void canResetDeck(){
        dealer.dealCard();
        dealer.dealCard();
        dealer.dealCard();
        dealer.dealCard();
        dealer.dealCard();
        dealer.dealCard();
        assertEquals(46, dealer.getNumCardsInDeck());

        dealer.initializeDeck();
        assertEquals(52, dealer.getNumCardsInDeck());
    }


    @Test
    public void startingWithDoubleAces(){

        player1.takeCard(ace);
        player1.takeCard(ace);

        player1.twist(five);
        player1.twist(five);
        player1.twist(five);
        player1.twist(five);



//        dealer.calculateNumAcesInHand();
//        dealer.calculateHandTotal();
//
//        if(dealer.numAces == 2) {
//            dealer.numAcesUsed += 1;
//            dealer.numAces -= 1;
//            dealer.calculateHandTotal();
//        }
//
//        dealer.playTurn();
//        dealer.calculateHandTotal();
//        System.out.println(dealer.getHandTotal());
    }



}