/**
 * Deck Class
 * 
 * @author Neil Sabott
 * @version 1.0
 */

//---------------------------------------------------------------------------------
// Imports
//---------------------------------------------------------------------------------
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;

public class Deck extends Card{
	
	//---------------------------------------------------------------------------------
	// Create private variables
	//---------------------------------------------------------------------------------
	private Card[] deck;
	
	//---------------------------------------------------------------------------------
	// Constructor (52 cards in a deck)
	//---------------------------------------------------------------------------------
	public Deck(){
		deck = new Card[52];
	}	
	
	//---------------------------------------------------------------------------------
	// Add a Card method
	//---------------------------------------------------------------------------------
	public void addCard(int numCard, String suit, String faceName, int faceValue, ImageIcon cardImg){
		deck[numCard] = new Card(suit, faceName, faceValue, cardImg);
	}
	
	//---------------------------------------------------------------------------------
	// Create the deck of cards with the appropriate variables for each
	//---------------------------------------------------------------------------------
	public void createDeck(){
		String[] suit = {"Spades", "Hearts", "Clubs", "Diamonds"};
		String[] faceName = {"Ace", "King", "Queen", "Jack", "10", "9",
				"8", "7", "6", "5", "4", "3", "2"};
		
		int cardNumber = 0;
		
		for(int count = 0; count < suit.length; count++){
			for(int counter = 0; counter < faceName.length; counter++){
				int tempFaceValue;
				ImageIcon tempCardImage;
				
				tempFaceValue = getFaceValue(faceName[counter]);				
				tempCardImage = getCardImage(cardNumber);
				
				addCard(cardNumber, suit[count], faceName[counter], tempFaceValue, tempCardImage);
				cardNumber++;
			}
		}
	}
	
	//---------------------------------------------------------------------------------
	// Returns the face value of the card in question
	//---------------------------------------------------------------------------------
	public int getFaceValue(String faceName){
		int tempFaceValue = 0;
		
		switch (faceName){
		case "Ace":
			tempFaceValue = 11;
			break;
		case "King":
			tempFaceValue = 10;
			break;
		case "Queen":
			tempFaceValue = 10;
			break;
		case "Jack":
			tempFaceValue = 10;
			break;
		case "10":
			tempFaceValue = 10;
			break;
		case "9":
			tempFaceValue = 9;
			break;
		case "8":
			tempFaceValue = 8;
			break;
		case "7":
			tempFaceValue = 7;
			break;
		case "6":
			tempFaceValue = 6;
			break;
		case "5":
			tempFaceValue = 5;
			break;
		case "4":
			tempFaceValue = 4;
			break;
		case "3":
			tempFaceValue = 3;
			break;
		case "2":
			tempFaceValue = 2;
			break;
		default:
			tempFaceValue = 0;
			break;
		}
				
		return tempFaceValue;
	}
	
	//---------------------------------------------------------------------------------
	// Returns the image of the card in question
	//---------------------------------------------------------------------------------
	public ImageIcon getCardImage(int cardNumber){
		String fileName = Integer.toString(cardNumber) + ".png";
		return new ImageIcon(this.getClass().getResource(fileName));
	}
	
	//---------------------------------------------------------------------------------
	// Shuffle the deck
	//---------------------------------------------------------------------------------
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
	
	//---------------------------------------------------------------------------------
	// Deal a card method, returns a Card object
	//---------------------------------------------------------------------------------
	public Card dealCard(){
		Card[] tempDeck = null;
		Card tempCard;
		
		tempCard = deck[deck.length - 1];
		
		tempDeck = Arrays.copyOf(deck, deck.length - 2);
		deck = tempDeck;
		
		return tempCard;
	}
}