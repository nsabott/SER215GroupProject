/**
 * Hand Class
 * 
 * @author Neil Sabott
 * @version 1.0
 */

//---------------------------------------------------------------------------------
// Imports
//---------------------------------------------------------------------------------
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Hand {
	
	//---------------------------------------------------------------------------------
	// Create private variables
	//---------------------------------------------------------------------------------
	private Card[] hand;
	private Card[] tempHand;
	private int handValue;
	private int cardInHand;
	private JLabel[] handImage;
	
	//---------------------------------------------------------------------------------
	// Constructor - Blank
	//---------------------------------------------------------------------------------
	public Hand(){
		cardInHand = 0;
	}
	
	//---------------------------------------------------------------------------------
	// Getter/Setter
	//---------------------------------------------------------------------------------
	public void setHandValue(int handValue){
		this.handValue = handValue;
	}
	
	public int getHandValue(){
		handValue = 0;
		calcHandValue();
		return handValue;
	}
	
	//---------------------------------------------------------------------------------
	// Add a Card to the Hand method
	//---------------------------------------------------------------------------------
	public void add(Card card){
		
		// If the hand is empty
		if(cardInHand == 0){
			hand = new Card[1];
			hand[0] = card;
			cardInHand++;
		}
		
		// If the hand already has at least a card in it
		else if(cardInHand > 0){
			
			// Extend the hand and add the card
			tempHand = new Card[cardInHand + 1];
			
			for(int count = 0; count < cardInHand; count++){
				tempHand[count] = hand[count];
			}
			
			// Calculate the hand's value
			calcHandValue();
			
			// If the card added in an Ace
			if(card.getFaceValue() == 11){
				
				// If that Ace will push you over 21
				if((handValue + 11) > 21){
					
					// Set that Ace's value to 1 instead of 11
					card.setFaceValue(1);
					tempHand[tempHand.length - 1] = card;
				}
				
				// If not, add the card
				else{
					tempHand[tempHand.length - 1] = card;
				}
			}
			
			// If adding that card will push you over 21, check to see if you have an Ace in your hand
			else{
				
				// If you do, set one Ace in your hand to a value of 1 instead of 11
				if((handValue + card.getFaceValue()) > 21){
					setAceToOne();
					tempHand[tempHand.length - 1] = card;
				}
				
				// If not, add the card
				else{
					tempHand[tempHand.length - 1] = card;
				}
			}
			
			// Finalize the hand after adding the card
			hand = tempHand;
			cardInHand++;
		}
	}
	
	//---------------------------------------------------------------------------------
	// Set the value of the Ace card to 1 instead of 11
	//---------------------------------------------------------------------------------
	private void setAceToOne(){
		for(int count = 0; count < hand.length; count++){
			if(hand[count].getFaceValue() == 11){
				hand[count].setFaceValue(1);
				break;
			}
		}
	}
	
	//---------------------------------------------------------------------------------
	// Calculate the value of the hand (add up each card's value in your hand)
	//---------------------------------------------------------------------------------
	private void calcHandValue(){
		if(cardInHand > 0){
			for(int count = 0; count < hand.length; count++){
				handValue = handValue + hand[count].getFaceValue();
			}
		}
	}
	
	//---------------------------------------------------------------------------------
	// Check to see if the hand is a Blackjack hand (value = 21)
	//---------------------------------------------------------------------------------
	public boolean isBlackJack(){
		getHandValue();
		return handValue == 21;
	}
	
	//---------------------------------------------------------------------------------
	// Check to see if the hand is bustead (value > 21)
	//---------------------------------------------------------------------------------
	public boolean isBust(){
		getHandValue();
		return handValue > 21;
	}
	
	//---------------------------------------------------------------------------------
	// Return the hand's image (array of all the card's images in the hand)
	//---------------------------------------------------------------------------------
	public JLabel[] getHandImage(){
		handImage = new JLabel[hand.length];
		
		for(int count = 0; count < hand.length; count++){
			handImage[count] = new JLabel(hand[count].getCardImg());
		}
		
		return handImage;
	}
	
	//---------------------------------------------------------------------------------
	// Returns the initial Dealer's hand image (the first card is face down)
	//---------------------------------------------------------------------------------
	public JLabel[] getDealerHandImage(){
		handImage = new JLabel[hand.length];
		
		handImage[0] = new JLabel(new ImageIcon(this.getClass().getResource("back.png")));
		for(int count = 1; count < hand.length; count++){
			handImage[count] = new JLabel(hand[count].getCardImg());
		}
		
		return handImage;
	}
	
	//---------------------------------------------------------------------------------
	// Return how many cards are in the hand
	//---------------------------------------------------------------------------------
	public int getCardsInHand(){
		return cardInHand;
	}
	
	//---------------------------------------------------------------------------------
	// Clears the hand (resets it to null)
	//---------------------------------------------------------------------------------
	public void clearHand(){
		hand = null;
		cardInHand = 0;
	}
}
