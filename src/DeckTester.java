import java.util.*;
public class DeckTester
{
    public static void main(String[] args) {
        Deck deck = new Deck();
        Card card1 = new Card("J",11);
        Card card2 = new Card("K",12);
        Card card3 = new Card("A",2);
        System.out.println(card1.toStringTo());
        
        card1.setFaceUp(true);
        deck.add(card1);
        System.out.println(card1.toStringTo());
        deck.add(card2);
        deck.add(card3);
        System.out.println(deck.toString());
        System.out.println(deck.stateOfDeck(deck));
        //System.out.println(deck);
        // Deck d = new Deck(deck.stateOfDeck(deck));
        // System.out.println(d);
    }
}