import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Board
{   
    ArrayList<Deck> stacks = new ArrayList<Deck>();
    ArrayList<Card> drawPile = new ArrayList<Card>();
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  Here are examples:
     *  
     *  # numDecks     #cards in overall Deck
     *      1          13 (all same suit)
     *      2          26 (all same suit)
     *      3          39 (all same suit)
     *      4          52 (all same suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.
     */    
    public Board(int numStacks, int numDecks) {
        for(int i = 0; i < numStacks; i++){
            stacks.add(new Deck());
        }
        Deck fullDeck = new Deck();
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for(int i = 0; i < numDecks;i++){
            for(int j = 0; j < 13; j++){
                fullDeck.add(new Card(symbols[j], values[j]));
            }
        }        
        fullDeck.shuffle();
        int count = 0;
        int counter = 0;
        int i = 0;
        while(counter < (numDecks*13)/2){
            if(count < numStacks){
                stacks.get(count).add(fullDeck.get(i));
            }else{
                count = 0;
                stacks.get(count).add(fullDeck.get(i));
            }
            i++;
            count++;
            counter++;
        }
        for(int r = (numDecks*13)/2; r < numDecks*13; r++){
            drawPile.add(fullDeck.get(r));
        }
    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.
     */
    public void makeMove(int value, int src, int dest) {
        int counter = 0;
        int count = 0;
        int num = 0;
        boolean bool = false;
        try{
            counter = 0;
            count = stacks.get(src-1).getSize() - 1;
            num = 0;
            bool = false;
            while(counter == 0 && count >= 0 && bool == false && stacks.get(src-1).get(count).isFaceUp()){
                if(count == stacks.get(src-1).getSize() - 1){
                    if(stacks.get(src-1).get(count).getValue() == value){
                        num++;
                        bool = true;
                    }else{
                        num++;
                    }
                }else{
                    if((stacks.get(src-1).get(count).getValue() - stacks.get(src-1).get(count+1).getValue()) == 1){
                        if(stacks.get(src-1).get(count).getValue() == value){
                            num++;
                            bool = true;
                        }else{
                            num++;
                        }
                    }else{
                        counter++;
                    }
                }
                count--;
            }
            if(bool == true){
                if(stacks.get(dest-1).getSize() == 0 || (stacks.get(dest-1).get(stacks.get(dest-1).getSize() - 1).getValue() - stacks.get(src-1).get(stacks.get(src-1).getSize() - num).getValue()) == 1){
                    for(int i = stacks.get(src-1).getSize() - num; i <= stacks.get(src-1).getSize() - 1; i++){
                        stacks.get(dest-1).add(stacks.get(src-1).get(i));
                    }
                    // for(int i = stacks.get(dest-1).getSize() - 1; i <= stacks.get(src-1).getSize() - num + 1; i--){
                        // if(stacks.get(dest-1).get(i).isFaceUp() == false){
                            // stacks.get(dest-1).get(i).setFaceUp(true); 
                        // }
                    // }
                    int len = stacks.get(src-1).getSize();
                    for(int i = len -1; i >= len - num; i--){
                        stacks.get(src-1).remove(i);
                    }
                    if(stacks.get(src-1).get(stacks.get(src-1).getSize() - 1).isFaceUp() == false){
                        stacks.get(src-1).get(stacks.get(src-1).getSize() - 1).setFaceUp(true);
                    }
                }
            }else{
                System.out.println("Invalid Move");
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("Invalid Move");
        }

    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        int i = 0;
        while(drawPile.size() > 0 && i < stacks.size()){
            stacks.get(i).add(drawPile.get(0));
            drawPile.remove(0);
            i++;
        }

    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 

    public boolean isEmpty() {
        int counter = 0;
        for(int i =0; i < stacks.size(); i++){
            if(stacks.get(i).getSize() == 0){
                counter = counter;
            }else{
                counter++;
            }
        }
        if(drawPile.size() == 0){
            counter = counter;
        }else{
            counter++;
        }
        if(counter == 0){
            return true;
        }else{
            return false;
        }
    }

    public void save(){
        FileWriter out;
        String fin = "";
        fin += stacks.size() + "\n"; 
        for(int i = 0; i < stacks.size(); i++){
            fin += stacks.get(i).stateOfDeck(stacks.get(i)) +"\n";
        }
        for(int i = 0; i < drawPile.size() - 1; i++){
            fin += drawPile.get(i).getSymbol() + drawPile.get(i).getValue() + " ";
        }
        fin += drawPile.get(drawPile.size() - 1).getSymbol() + drawPile.get(drawPile.size() - 1).getValue();

        try{
            out = new FileWriter("P1_Zhu_Alex_Save.txt");
            out.write(fin, 0, fin.length());
            out.close();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }

    public void load(){
        stacks.clear();
        drawPile.clear();
        try{
            File file = new File("P1_Zhu_Alex_Save.txt");
            Scanner id = new Scanner(file);
            int numStacks = Integer.valueOf(id.next());
            int i = 0;
            while(id.hasNext() && i < numStacks){
                Deck deck = new Deck(id.next());
                stacks.add(deck);
                i++;
            }   
            while(id.hasNext()){
                String temp = id.next();
                Card card = new Card(temp.substring(0, 1), Integer.valueOf(temp.substring(1)));
                drawPile.add(card);
            }
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        try{
            if(sourceStack > stacks.size()){
                System.out.println("SourceStack Out of Bounds");
            }else{
                if(stacks.get(sourceStack - 1).getSize() < 13){
                    System.out.println("Invalid Move");
                }else{
                    int counter = 13;
                    int count = 0;
                    for(int i = stacks.get(sourceStack - 1).getSize() - 1; i >= stacks.get(sourceStack - 1).getSize() - 13; i--){
                        if(stacks.get(sourceStack - 1).get(i).getValue() == counter){
                            count++;
                            counter--;
                        }
                    }
                    if(count == 13){
                        int bl = stacks.get(sourceStack - 1).getSize();
                        for(int i = bl - 1; i >= bl - 13; i--){
                            stacks.get(sourceStack - 1).remove(i);
                        }
                    }else{
                        System.out.println("Invalid Move");
                    }
                }
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("Invalid move");
        }
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        System.out.println("Stacks:");

        for(int i = 0; i < stacks.size(); i++){
            if(stacks.get(i).getSize() > 0){
                stacks.get(i).get(stacks.get(i).getSize() -1).setFaceUp(true);
            }
        }
        for(int i = 0; i < stacks.size(); i++){
            if(stacks.get(i).getSize() > 0){
                System.out.println(i+1 + ": " + stacks.get(i));
            }
        }

        System.out.println();
        System.out.println("DrawPile:");
        System.out.println(drawPile);
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  draw");
        System.out.println("  move [card] [source_stack] [destination_stack]");
        System.out.println("  clear [source_stack]");
        System.out.println("  restart");
        System.out.println("  save");
        System.out.println("  load");
        System.out.println("  quit");

    }
}