import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Deck extends Card{

	private Card[] deck;
	
	public Deck(){
		deck = new Card[52];
	}
	
	public void addCard(int numCard, String suit, String faceName, int faceValue, BufferedImage cardImg){
		deck[numCard] = new Card(suit, faceName, faceValue, cardImg);
	}
	
	public void createDeck(){
		String[] suit = {"Spades", "Hearts", "Clubs", "Diamonds"};
		String[] faceName = {"Ace", "King", "Queen", "Jack", "10", "9",
				"8", "7", "6", "5", "4", "3", "2"};
		
		int cardNumber = 0;
		
		for(int count = 0; count < suit.length; count++){
			for(int counter = 0; counter < faceName.length; counter++){
				int tempFaceValue = 0;
				BufferedImage tempCardImage = null;
				
				tempFaceValue = getFaceValue(faceName[counter]);				
				tempCardImage = getCardImage(cardNumber);
				
				addCard(cardNumber, suit[count], faceName[counter], tempFaceValue, tempCardImage);
				cardNumber++;
			}
		}
	}
	
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
			tempFaceValue = 9;
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
	
	public BufferedImage getCardImage(int cardNumber){
		BufferedImage tempCardImg = null;
		
		for(int count = 0; count < 52; count++){
			try {
				tempCardImg = ImageIO.read(new File(cardNumber+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return tempCardImg;
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
		
		tempDeck = Arrays.copyOf(deck, deck.length - 2);
		//System.arraycopy(deck, 1, tempDeck, 0, deck.length - 1);
		deck = tempDeck;
		
		return tempCard;
	}
}