import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class Deck
{
    ArrayList<Card> deck = new ArrayList<Card>();

    public Deck(){
        this.deck = new ArrayList<Card>();
    }

    public Deck(String str){
        int num = 0;
        int value = 0;
        String symbol = "";
        boolean bool = false;
        for(int i = 0; i < str.length(); i++){
            if(num == 0){
                symbol = str.substring(i, i+1);
            }else if(num == 1){
                if(Integer.valueOf(str.charAt(i)) != 0){
                    value = Integer.valueOf(str.substring(i, i+2));
                    i++;
                }else{
                    value = Integer.valueOf(str.charAt(i+1));
                    i++;
                }
            }else if(num == 2){
                if(str.charAt(i) == 'f'){
                    bool = false;
                }else{
                    bool = true;
                }
                num = -1;
                Card c = new Card(symbol,value);
                c.setFaceUp(bool);
                deck.add(c);
            }
            num++;
        }
    }

    public Card get(int index){
        return deck.get(index);
    }

    public String stateOfDeck(Deck deck){
        String d = "";
        for(int i = 0; i < deck.getSize(); i++){
            if(deck.get(i).getValue() >= 10){
                d += deck.get(i).getSymbol() + Integer.valueOf(deck.get(i).getValue()) + (String.valueOf(deck.get(i).isFaceUp())).charAt(0);
            }else{
                d += deck.get(i).getSymbol() + "0" + Integer.valueOf(deck.get(i).getValue()) + (String.valueOf(deck.get(i).isFaceUp())).charAt(0);
            }
        }
        return d;
    }

    public void add(Card card){
        //card.setFaceUp(false);
        deck.add(card);
    }

    int getSize(){  
        return deck.size();
    }

    boolean isEmpty(){
        if(deck.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    public void shuffle(){
        int size = deck.size();
        ArrayList<Card> list = new ArrayList<Card>();
        for(int i = 0; i < deck.size();i++){
            list.add(deck.get(i));
        }
        deck.clear();
        Random rand = new Random();
        for(int i = 0; i < size; i++){
            int r = rand.nextInt(list.size());
            deck.add(list.get(r));
            list.remove(r);
        }
    }

    public void makeDeck(String[] symbols, int[] values, int howManyDecks, boolean isFaceUp){
        this.shuffle();
        for(int i = 0; i < symbols.length; i++){
            if(!isFaceUp){
                this.add(new Card("X", values[i]));
            }else{
                this.add(new Card(symbols[i], values[i]));
            }
        }
    }

    // Card getCard(int index){ //returns the card but dosent remove it
    // }
    // Card draw(int n){ //removes the top card from the deck and returns it 
    // }
    public void remove(int index){
        deck.remove(index);
    }

    public Card pickRandomCard(){
        return deck.get((int)Math.floor(Math.random()*(deck.size())));
    }

    @Override
    public String toString(){
        String list = "";
        for(int i = 0; i < deck.size(); i++){
            if(i < deck.size() - 1){
                list += deck.get(i).toString() + ",";
            }else{
                list += deck.get(i).toString();
            }
        }
        return "[" + list + "]";
    }
}