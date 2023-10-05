public class CardTester
{
    public static void main(String[] args) {
        Card card1 = new Card("joker",11);
        Card card2 = new Card("king",12);
        card1.setFaceUp(true);
        System.out.println(card1.toString());
        System.out.println(card1.equals(card2));
    }
}