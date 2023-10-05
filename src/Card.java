/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Getter method to access this <code>Card</code>'s value
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns whether or not this <code>Card</code>'s is facing up or not 
     * 
     * @return whether or not this <code>Card</code>'s is facing up or not
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }
    
    public void remove(){
    }

    /**
     * This Method sets <code>Card</code>'s face up
     */
    public void setFaceUp(boolean state) {
        this.isFaceUp = state;
        
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @return whether or not this <code>Card</code>'s is equal to other.
     */
    public boolean equals(Card other) {
        return this.getValue() == other.getValue();
    }

    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol or and X,
     * depending on whether the card is face up or down.
     */
    @Override
    public String toString() {
        if(!this.isFaceUp()){
            return "X";
        }else{
            return this.getSymbol();
        }
    }
    
    /**
     * Returns the difference in value between this <code>Card</code>'s and the given card
     * 
     * @return the difference in value between this <code>Card</code>'s and the given card
     */
    public int compareTo(Card card){
        return this.getValue() - card.getValue();
    }
}