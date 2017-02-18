import java.awt.image.BufferedImage;

public class Card {
	private String suit, faceName;
	private int faceValue;
	private BufferedImage cardImg;
	
	public Card(){
		suit = "";
		faceName = "";
		faceValue = 0;
		cardImg = null;		
	}
	
	public Card(String suit, String faceName, int faceValue, BufferedImage cardImg){
		this.suit = suit;
		this.faceName = faceName;
		this.faceValue = faceValue;
		this.cardImg = cardImg;
	}
	
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

	public BufferedImage getCardImg() {
		return cardImg;
	}

	public void setCardImg(BufferedImage cardImg) {
		this.cardImg = cardImg;
	}
	
	public String toString(){
		String string = "";
		string = string.concat(faceName + " of " + suit);
		return string;
	}
}
