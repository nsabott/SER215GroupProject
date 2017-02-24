/**
 * Deck Class
 * 
 * @author Neil Sabott
 * @version 1.0
 */

//---------------------------------------------------------------------------------
// Imports
//---------------------------------------------------------------------------------
import javax.swing.ImageIcon;

public class Card {
	
	//---------------------------------------------------------------------------------
	// Create private variables
	//---------------------------------------------------------------------------------
	private String suit, faceName;
	private int faceValue;
	private ImageIcon cardImg;
	
	//---------------------------------------------------------------------------------
	// Constructor - Blank
	//---------------------------------------------------------------------------------
	public Card(){
		suit = "";
		faceName = "";
		faceValue = 0;
		cardImg = null;		
	}
	
	//---------------------------------------------------------------------------------
	// Constructor - With Values Assigned
	//---------------------------------------------------------------------------------
	public Card(String suit, String faceName, int faceValue, ImageIcon cardImg){
		this.suit = suit;
		this.faceName = faceName;
		this.faceValue = faceValue;
		this.cardImg = cardImg;
	}
	
	//---------------------------------------------------------------------------------
	// Getters/Setters
	//---------------------------------------------------------------------------------
	public String getSuit() {
		return suit;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getFaceName() {
		return faceName;
	}

	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}
	
	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
	
	public ImageIcon getCardImg() {
		return cardImg;
	}

	public void setCardImg(ImageIcon cardImg) {
		this.cardImg = cardImg;
	}
	
	//---------------------------------------------------------------------------------
	// Override the Card object's toString method
	//---------------------------------------------------------------------------------
	public String toString(){
		String string = "";
		string = string.concat(faceName + " of " + suit);
		return string;
	}
}
