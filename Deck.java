import java.util.Random;

public class Deck {

	private Card[] deck;
	
	public Deck(){
		deck = new Card[52];
	}
	
	public static class Card{
		public String suit, value;
		
		public Card(){
			suit = "";
			value = "";
		}
		
		public Card(String suit, String value){
			this.suit = suit;
			this.value = value;
		}
		
		public String toString(){
			String string = "";
			string = string.concat(value + " of " + suit);
			return string;
		}
	}
	
	public void addCard(int numCard, String suit, String value){
		deck[numCard] = new Card(suit, value);
	}
	
	public void createDeck(){
		String[] suit = {"Spades", "Hearts", "Clubs", "Diamonds"};
		String[] faceValue = {"Ace", "King", "Queen", "Jack", "10", "9",
				"8", "7", "6", "5", "4", "3", "2"};
		int cardNumber = 0;
		
		for(int count = 0; count < suit.length; count++){
			for(int counter = 0; counter < faceValue.length; counter++){
				addCard(cardNumber, suit[count], faceValue[counter]);
				cardNumber++;
			}
		}
	}
	
	public void shuffleDeck(){
		int index;
		Card temp = new Card();
		Random rand = new Random();
		
		for(int i = (deck.length - 1); i > 0; i--){
			index = rand.nextInt(i + 1);
			temp = deck[index];
			deck[index] = deck[i];
			deck[i] = temp;
		}
	}
	
	public Card dealCard(){
		Card[] tempDeck = null;
		Card tempCard;
		
		tempCard = deck[deck.length - 1];
		System.arraycopy(deck, 0, tempDeck, 0, deck.length - 1);
		deck = tempDeck;
		
		return tempCard;
	}
}