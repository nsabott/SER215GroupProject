import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Hand {
	private Card[] hand;
	private int handValue;
	private int cardInHand;
	private JLabel[] handImage;
	
	public Hand(){
		cardInHand = 0;
	}
	
	public void setHandValue(int handValue){
		this.handValue = handValue;
	}
	
	public int getHandValue(){
		handValue = 0;
		calcHandValue();
		return handValue;
	}
	
	public void add(Card card){
		if(cardInHand == 0){
			hand = new Card[1];
			hand[0] = card;
			cardInHand++;
		}
		else if(cardInHand > 0){
			Card[] tempHand = new Card[cardInHand + 1];
			tempHand = Arrays.copyOf(hand, hand.length);
			tempHand[tempHand.length - 1] = card;
			hand = tempHand;
			cardInHand++;
		}
	}
	
	private void calcHandValue(){
		if(cardInHand > 0){
			for(int count = 0; count < hand.length; count++){
				handValue = handValue + hand[count].getFaceValue();
			}
		}
	}
	
	public JLabel[] getHandImage(){
		if(cardInHand > 0){
			handImage = new JLabel[cardInHand];
			for(int count = 0; count < cardInHand; count++){
				//handImage[count].add(null, hand[count].getCardImg());
				handImage[count].add(null, new ImageIcon(this.getClass().getResource("0.png")));
			}
		}
		
		return handImage;
	}
}
