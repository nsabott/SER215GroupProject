/**
 * A 52-card deck. Contains methods to create a deck, shuffle a deck, and print the deck
 * as it's dealt.
 * 
 * 
 * @author Neil Sabott
 * @version 1.0
 */
import java.util.Random;

public class CardGame {
	
	//---------------------------------------------------------------------------------
	// Creates a deck of cards (an array of cards that has two values each), shuffles
	// the created deck, then prints out the deck as it's dealt also including
	// the number of cards remaining in the deck.
	//---------------------------------------------------------------------------------
	public static void main(String[] args){
		Deck newDeck = new Deck();
		
		newDeck.createDeck();
		
		newDeck.shuffleDeck();
		
		newDeck.dealDeck();
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
	
	public static class Deck{
		private Card[] deck;
		
		public Deck(){
			deck = new Card[52];
		}
		
		public void addCard(int numCard, String suit, String value){
			deck[numCard] = new Card(suit, value);
		}
		
		public void createDeck(){
			String[] suit = {"Spades", "Hearts", "Clubs", "Diamonds"};
			String[] faceValue = {"Ace", "King", "Queen", "Jack", "Ten", "Nine",
					"Eight", "Seven", "Six", "Five", "Four", "Three", "Two"};
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
		
		public void dealDeck(){
			for(int count = 0; count < deck.length; count++){
				System.out.println(deck[count].toString());
				System.out.println("Cards remaining: " + ((deck.length - 1) - count));
			}
		}
	}
}