/**
 * BlackJack Lite Game
 * 
 * Play BlackJack Lite!
 * 
 * @author Neil Sabott
 * @version 1.0
 */

//---------------------------------------------------------------------------------
// Imports
//---------------------------------------------------------------------------------
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JLayeredPane;
import java.awt.Font;

public class BlackJackGamePlay extends JFrame {

	//---------------------------------------------------------------------------------
	// Create private variables
	//---------------------------------------------------------------------------------
	private DecimalFormat fmt = new DecimalFormat("###,###,###,##0.00");
	private boolean gameInPlay = false, gameOver = false;
	private int betAmount;
	private String betAmtStr;
	private ImageIcon cardImage;
	private ImageIcon pokerChip = new ImageIcon(this.getClass().getResource("pokerchip.png"));
	private ImageIcon welcomeImg = new ImageIcon(this.getClass().getResource("welcomeMsg.png"));
	private ImageIcon pressBetIcon = new ImageIcon(this.getClass().getResource("pressBetToPlay.png"));
	private Person dealer = new Person("Dealer"), player = new Person();
	private Card tempCard = new Card();
	private Hand dealerHand, playerHand;
	private Deck deckInPlay;
	private JPanel playerHandPanel = new JPanel(), dealerHandPanel = new JPanel();
	private JPanel contentPane;
	private JLabel[] dealerHandImage, playerHandImage;
	private JLabel lblBetAmt, lblBuyInAmount = new JLabel();
	private JButton btnBackToMenu = new JButton("Back to Menu"), btnHit = new JButton("Hit");
	private JButton btnStay = new JButton("Stay"), btnBet = new JButton("Bet");
	private JComboBox betChoice = new JComboBox();
	private final JLayeredPane chipPanel = new JLayeredPane();
	private final JPanel welcomeMsg = new JPanel(), pressBetToPlay = new JPanel();
	private final JLabel lblPlayersHand = new JLabel("[Player's Hand]"), lblDealersHand = new JLabel("[Dealer's Hand]");
	
	//---------------------------------------------------------------------------------
	// Launch the game
	//---------------------------------------------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackGamePlay frame = new BlackJackGamePlay("Player",0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//---------------------------------------------------------------------------------
	// Create the frame
	//---------------------------------------------------------------------------------
	public BlackJackGamePlay(String name, int buyInAmt) {
		
		//---------------------------------------------------------------------------------
		// Set the frame settings
		//---------------------------------------------------------------------------------
		setResizable(false);
		setTitle("Blackjack");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,700);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		int jFrameWidth=this.getSize().width;
		int jFrameHeight=this.getSize().height;
		int xPos = (scrSize.width-jFrameWidth)/2;
		int yPos = (scrSize.height-jFrameHeight)/2;
		this.setLocation(xPos, yPos);
		
		//---------------------------------------------------------------------------------
		// Set the Player Name and Buy-In Amount
		//---------------------------------------------------------------------------------
		player.setDollarsInHand(buyInAmt);
		player.setName(name);
		
		//---------------------------------------------------------------------------------
		// Set up the Content Pane
		//---------------------------------------------------------------------------------
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//---------------------------------------------------------------------------------
		// Set up the Back to Menu Button
		//---------------------------------------------------------------------------------
		btnBackToMenu.setBounds(854, 628, 120, 23);
		btnBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BlackJackGameMenu gameMenu = new BlackJackGameMenu();
				gameMenu.setVisible(true);
				closeWindow();
			}
		});
		contentPane.add(btnBackToMenu);
		
		//---------------------------------------------------------------------------------
		// Display the Player's name and Buy-In Amount right above the Back to Menu button
		//---------------------------------------------------------------------------------
		JLabel playerName = new JLabel(name);
		playerName.setHorizontalAlignment(SwingConstants.RIGHT);
		playerName.setBounds(843, 590, 57, 23);
		playerName.setForeground(new Color(255, 255, 255));
		contentPane.add(playerName);
		
		lblBuyInAmount.setText(String.valueOf(fmt.format(player.getDollarsInHand())));
		lblBuyInAmount.setBounds(914, 586, 57, 31);
		lblBuyInAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuyInAmount.setForeground(new Color(255, 255, 255));
		contentPane.add(lblBuyInAmount);
		
		JLabel label_1 = new JLabel("$");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBounds(905, 590, 13, 23);
		contentPane.add(label_1);
		
		//---------------------------------------------------------------------------------
		// Set up the JOptionBox for how much they would like to bet
		//---------------------------------------------------------------------------------
		betChoice.setBounds(274, 629, 57, 20);
		betChoice.setModel(new DefaultComboBoxModel(new String[] {"1", "5", "10", "25", "100", "All"}));
		contentPane.add(betChoice);
		
		//---------------------------------------------------------------------------------
		// Set up the JPanel for the Player's hand
		//---------------------------------------------------------------------------------
		playerHandPanel.setVisible(true);
		playerHandPanel.setBackground(new Color(0, 51, 0));
		playerHandPanel.setBounds(112, 418, 721, 141);
		contentPane.add(playerHandPanel);
		
		//---------------------------------------------------------------------------------
		// Set up the JLabel for the Player's hand
		//---------------------------------------------------------------------------------
		lblPlayersHand.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblPlayersHand.setForeground(new Color(255, 255, 255));
		lblPlayersHand.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayersHand.setBounds(375, 388, 204, 31);
		contentPane.add(lblPlayersHand);
		lblPlayersHand.setVisible(false);
		
		//---------------------------------------------------------------------------------
		// Set up the JPanel for the Dealer's hand
		//---------------------------------------------------------------------------------
		dealerHandPanel.setVisible(true);
		dealerHandPanel.setBackground(new Color(0, 51, 0));
		dealerHandPanel.setBounds(112, 102, 721, 141);
		contentPane.add(dealerHandPanel);
		
		//---------------------------------------------------------------------------------
		// Set up the JLabel for the Dealer's hand
		//---------------------------------------------------------------------------------
		lblDealersHand.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealersHand.setForeground(Color.WHITE);
		lblDealersHand.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDealersHand.setBounds(375, 250, 204, 31);
		contentPane.add(lblDealersHand);
		lblDealersHand.setVisible(false);
		
		//---------------------------------------------------------------------------------
		// Set up the JPanel for the In Play chip
		//---------------------------------------------------------------------------------
		chipPanel.setBounds(431, 292, 89, 85);
		contentPane.add(chipPanel);
		chipPanel.setVisible(false);
		lblBetAmt = new JLabel("PLAY");
		lblBetAmt.setFont(new Font("Tahoma", Font.BOLD, 10));
		chipPanel.setLayer(lblBetAmt, 1);
		lblBetAmt.setHorizontalAlignment(SwingConstants.CENTER);
		chipPanel.add(lblBetAmt);
		lblBetAmt.setForeground(new Color(255, 255, 255));
		lblBetAmt.setBounds(18, 32, 52, 20);
		
		// Set the poker chip image inside the panel
		JLabel betChipImg = new JLabel("",pokerChip,JLabel.CENTER);
		chipPanel.setLayer(betChipImg, 0);
		betChipImg.setBounds(7, 5, 75, 75);
		chipPanel.add(betChipImg);
		
		
		//---------------------------------------------------------------------------------
		// Set up the JLabel for the Welcome banner
		//---------------------------------------------------------------------------------
		JLabel welcomeBanner = new JLabel("",welcomeImg,JLabel.CENTER);
		welcomeBanner.setBounds(0, 0, 640, 80);
		welcomeMsg.add(welcomeBanner);
		welcomeMsg.setVisible(true);
		welcomeMsg.setBackground(new Color(0, 51, 0));
		welcomeMsg.setBounds(153, 11, 644, 85);
		welcomeMsg.setLayout(null);
		contentPane.add(welcomeMsg);
		
		//---------------------------------------------------------------------------------
		// Set up the JLabel for the "Press Bet to Play"
		//---------------------------------------------------------------------------------
		JLabel pressBetLbl = new JLabel("",pressBetIcon,JLabel.CENTER);
		pressBetLbl.setBounds(10, 0, 400, 50);
		pressBetToPlay.add(pressBetLbl);
		pressBetToPlay.setVisible(true);
		pressBetToPlay.setBackground(new Color(0, 51, 0));
		pressBetToPlay.setBounds(262, 562, 414, 60);
		pressBetToPlay.setLayout(null);
		contentPane.add(pressBetToPlay);
		
		//---------------------------------------------------------------------------------
		// Set up the JButton for the "Hit" Button
		//---------------------------------------------------------------------------------
		btnHit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// If the game is in play, allow the click
				if(gameInPlay == true){
					
					// Player hits
					hitCard("player");
					
					// Check if you got Blackjack
					if (checkBlackJack(playerHand)){
						
						// If so, double the bet
						doubleBet();
						
						// Update the player's dollars on hand
						resetDollarsDisplay();
						
						// Display Blackjack Message
						blackJackMsg();
						
						// End the Hand
						endTheHand();
					}
					
					// Check if you busted
					else if (checkBust(playerHand)){
						
						// If you did, check if the game is now over (out of money)
						checkGameOver();
						
						// If the game isn't over, end the hand
						if(!gameOver){
							
							// Display Bust Message
							bustMsg();
							
							// End the Hand
							endTheHand();
						}
						else{
							
							// If the game is over, kill the game
							killGame();
						}
					}
				}
				
				// If the game is not in play, do not let "Hit" button be used
				else{
					e.consume();
				}
			}
		});
		btnHit.setBounds(452, 628, 89, 23);
		contentPane.add(btnHit);
		
		//---------------------------------------------------------------------------------
		// Set up the JButton for the "Stay" Button
		//---------------------------------------------------------------------------------
		btnStay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// If the game is in play, allow the click
				if(gameInPlay == true){
					
					// Finish the Dealer's Hand
					finishDealerHand();
					
					// If the game isn't over, check to see who won
					if(gameInPlay){
						checkWinner();
					}
					
					// End the Hand
					endTheHand();
				}
				
				// If the game is not in play, do not let "Stay" button be used
				else{
					e.consume();
				}
			}
		});
		btnStay.setBounds(570, 628, 89, 23);
		contentPane.add(btnStay);
		
		//---------------------------------------------------------------------------------
		// Set up the JButton for the "Bet" Button
		//---------------------------------------------------------------------------------
		btnBet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// If the game is not in play, start a new round
				if(gameInPlay == false){
					
					// Record the Bet Amount for the round
					betAmtStr = (String) betChoice.getSelectedItem();
					
					// If they bet "All", set the Bet Amount to all the money they have in hand
					if(betAmtStr.equals("All")){
						betAmount = player.getDollarsInHand();
					}
					
					// For everything else, set the Bet Amount to what they chose
					else{
						betAmount = Integer.parseInt(betAmtStr);
					}
					
					// If they bet more than they have, don't allow the click and display the Invalid Bet Message
					if(betAmount > player.getDollarsInHand()){
						e.consume();
						
						// Display the Invalid Bet Message
						invalidBetMsg();
					}
					
					// If they select an appropriate bet, start the hand
					else{
						
						// Start the hand
						gameInPlay = true;
						
						
						// Take the bet from their dollars in hand
						takeBet();
						
						
						// Update the player's dollars on hand
						resetDollarsDisplay();
						
						// Start the Hand
						startTheHand();
						
						// Reset the Values of each the Player and Dealer's hand
						resetValues();
						
						// Deal the initial two cards to each the Player and Dealer
						dealInitialCard();
						
						// Check if you got Blackjack
						if (checkBlackJack(playerHand)){
							
							// If so, double the bet
							doubleBet();
							
							// Update the player's dollars on hand
							resetDollarsDisplay();
							
							// Display Blackjack Message
							blackJackMsg();
							
							// End the Hand
							endTheHand();
						}
						
						// Check if you busted
						else if (checkBust(playerHand)){
							
							// If you did, check if the game is now over (out of money)
							checkGameOver();
							
							// If the game isn't over, end the hand
							if(!gameOver){
								
								// Display Bust Message
								bustMsg();
								
								// End the Hand
								endTheHand();
							}
							else{
								
								// If the game is over, kill the game
								killGame();
							}
						}
					}
				}
				
				// If the game is already in play, do not let "Bet" button be used
				else{
					e.consume();
				}
			}
		});
		btnBet.setBounds(333, 628, 89, 23);
		contentPane.add(btnBet);
	}
	
	//---------------------------------------------------------------------------------
	// Reset the Dealer and Player's hand value
	//---------------------------------------------------------------------------------
	private void resetValues(){
		player.setValueOfHand(0);
		dealer.setValueOfHand(0);
	}
	
	
	//---------------------------------------------------------------------------------
	// Reset the Dollars on Hand Display
	//---------------------------------------------------------------------------------
	private void resetDollarsDisplay(){
		lblBuyInAmount.setText(String.valueOf(fmt.format(player.getDollarsInHand())));
		lblBuyInAmount.revalidate();
		lblBuyInAmount.repaint();
	}
	
	//---------------------------------------------------------------------------------
	// Start the Hand
	//---------------------------------------------------------------------------------
	private void startTheHand(){
		welcomeMsg.setVisible(false);
		pressBetToPlay.setVisible(false);
		chipPanel.setVisible(true);
		lblDealersHand.setVisible(true);
		lblPlayersHand.setVisible(true);
	}
	
	//---------------------------------------------------------------------------------
	// End the Hand
	//---------------------------------------------------------------------------------	
	private void endTheHand(){
		gameInPlay = false;
		welcomeMsg.setVisible(true);
		pressBetToPlay.setVisible(true);
		chipPanel.setVisible(false);
		lblDealersHand.setVisible(false);
		lblPlayersHand.setVisible(false);
		handIsOver();
	}
	
	//---------------------------------------------------------------------------------
	// Deal the initial first two cards to each the Player and Dealer
	//---------------------------------------------------------------------------------
	private void dealInitialCard(){
		
		// Create a new deck object
		deckInPlay = new Deck();
		
		// Create the cards inside the deck
		deckInPlay.createDeck();
		
		// Shuffle the deck
		deckInPlay.shuffleDeck();
		
		// Create a new Hand for each player
		dealerHand = new Hand();
		playerHand = new Hand();
		
		// Deal the first two cards
		playerHand.add(deckInPlay.dealCard());
		dealerHand.add(deckInPlay.dealCard());
		playerHand.add(deckInPlay.dealCard());
		dealerHand.add(deckInPlay.dealCard());
		
		// Capture the hand image (the cards)
		dealerHandImage = dealerHand.getDealerHandImage();
		playerHandImage = playerHand.getHandImage();
		
		// Add the card images to the dealer and player's hand panel
		for(int count = 0; count < playerHand.getCardsInHand(); count++){
			dealerHandPanel.add(dealerHandImage[count]);
			playerHandPanel.add(playerHandImage[count]);
			
		}
		
		// Repaint the hand panels
		dealerHandPanel.setVisible(true);
		dealerHandPanel.repaint();
		playerHandPanel.setVisible(true);
		playerHandPanel.repaint();
	}
	
	//---------------------------------------------------------------------------------
	// Check to see if the hand is a blackjack
	//---------------------------------------------------------------------------------
	private boolean checkBlackJack(Hand hand){
		return hand.isBlackJack();
	}
	
	//---------------------------------------------------------------------------------
	// Check to see if the hand is busted
	//---------------------------------------------------------------------------------
	private boolean checkBust(Hand hand){
		return hand.isBust();
	}
	
	//---------------------------------------------------------------------------------
	// Take the bet once they have hit Bet
	//---------------------------------------------------------------------------------
	private void takeBet(){
		player.setDollarsInHand(player.getDollarsInHand() - betAmount);
	}
	
	//---------------------------------------------------------------------------------
	// If player won, double the bet and return it to them
	//---------------------------------------------------------------------------------
	private void doubleBet(){
		int tempBet = 0;
		tempBet = player.getDollarsInHand();
		tempBet = tempBet + (2 * betAmount);
		player.setDollarsInHand(tempBet);
	}
	
	//---------------------------------------------------------------------------------
	// Return the bet if the game is a tie
	//---------------------------------------------------------------------------------
	private void returnBet(){
		int tempBet = 0;
		tempBet = player.getDollarsInHand();
		tempBet = tempBet + betAmount;
		player.setDollarsInHand(tempBet);
	}
	
	//---------------------------------------------------------------------------------
	// "Hit" a card
	//---------------------------------------------------------------------------------
	private void hitCard(String choice){
		
		// If Player is hitting
		if(choice.equals("player")){
			
			// Add a card to the Player's hand
			playerHand.add(deckInPlay.dealCard());
		
			// Repaint the player's panel
			playerHandPanel.removeAll();
			playerHandPanel.repaint();
			playerHandImage = playerHand.getHandImage();
			for(int count = 0; count < playerHand.getCardsInHand(); count++){
				playerHandPanel.add(playerHandImage[count]);
			}
			playerHandPanel.setVisible(true);
			playerHandPanel.revalidate();
			playerHandPanel.repaint();
		}
		
		// If Dealer is hitting
		else if(choice.equals("dealer")){
			
			// Add a card to the Dealer's hand
			dealerHand.add(deckInPlay.dealCard());
			
			// Repaint the dealer's hand
			dealerHandPanel.removeAll();
			dealerHandPanel.repaint();
			dealerHandImage = dealerHand.getHandImage();
			for(int count = 0; count < dealerHand.getCardsInHand(); count++){
				dealerHandPanel.add(dealerHandImage[count]);
			}
			dealerHandPanel.setVisible(true);
			dealerHandPanel.revalidate();
			dealerHandPanel.repaint();
		}
	}
	
	//---------------------------------------------------------------------------------
	// If Stay is chosen, finish the Dealer's hand (Hit if under 16)
	//---------------------------------------------------------------------------------
	private void finishDealerHand(){
		
		// Repaint the Dealer's hand showing the originally face down card
		dealerHandPanel.removeAll();
		dealerHandPanel.repaint();
		dealerHandImage = dealerHand.getHandImage();
		for(int count = 0; count < dealerHand.getCardsInHand(); count++){
			dealerHandPanel.add(dealerHandImage[count]);
		}
		dealerHandPanel.setVisible(true);
		dealerHandPanel.revalidate();
		dealerHandPanel.repaint();
		
		// Hit while under 16
		while(dealerHand.getHandValue() < 16){
			hitCard("dealer");
		}
		
		// Check to see if Dealer got Blackjack
		if (checkBlackJack(dealerHand)){
			
			// If so, check to see if the game is over (out of money)
			checkGameOver();
			
			// If the game isn't over
			if(!gameOver){
				
				// Display Dealer got Blackjack Message
				dealerBlackJackMsg();
				
				// End the Hand
				endTheHand();
			}
			
			// If the game is over, kill the game
			else{
				killGame();
			}
		}
		
		// Check to see if the Dealer busted
		else if (checkBust(dealerHand)){
			
			// If so, the player has won, so double the bet and return it to them
			doubleBet();
			
			// Update the player's dollars on hand
			resetDollarsDisplay();
			
			// Display Dealer Busted Message
			dealerBustMsg();
			
			// End the Hand
			endTheHand();
		}
	}
	
	//---------------------------------------------------------------------------------
	// If the hand has ended with no blackjacks or busts, check to see who has won
	//---------------------------------------------------------------------------------
	private void checkWinner(){
		
		// If the player's value is greater than the dealer's
		if(playerHand.getHandValue() > dealerHand.getHandValue()){
			
			// If so, the player has won, so double the bet and return it to them
			doubleBet();
			
			// Update the player's dollars on hand
			resetDollarsDisplay();
			
			// Display Player Has Won Message
			playerWinsMsg();
			
			// End the Hand
			endTheHand();
		}
		
		// If the player's value is less than the dealer's
		else if(playerHand.getHandValue() < dealerHand.getHandValue()){
			
			// Check to see if the game is over (out of money)
			checkGameOver();
			
			// If the game isn't over, end the round
			if(!gameOver){
				
				// Display Dealer Wins Message
				dealerWinsMsg();
				
				// End the Hand
				endTheHand();
			}
			
			// If the game is over, kill the game
			else{
				killGame();
			}
		}
		
		// If the game is a tie
		else{
			
			// Return the player's bet
			returnBet();
			
			// Update the player's dollars on hand
			resetDollarsDisplay();
			
			// Display Tie Game Message
			tieGameMsg();
			
			// End the Hand
			endTheHand();
		}
	}
	
	//---------------------------------------------------------------------------------
	// Clear and repaint the panels and hands after the round is over
	//---------------------------------------------------------------------------------
	private void handIsOver(){
		if(!gameOver){
			dealerHandPanel.removeAll();
			dealerHandPanel.repaint();
			playerHandPanel.removeAll();
			playerHandPanel.repaint();
			
			dealerHand.clearHand();
			playerHand.clearHand();
			gameInPlay = false;
		}
	}
	
	//---------------------------------------------------------------------------------
	// Check to see if the game is over (out of money)
	//---------------------------------------------------------------------------------
	private void checkGameOver(){
		if(player.getDollarsInHand() <= 0){
			gameOver = true;
		}
	}
	
	//---------------------------------------------------------------------------------
	// Kill the Game
	//---------------------------------------------------------------------------------
	private void killGame(){
		endGameMsg();
		BlackJackGameMenu gameMenu = new BlackJackGameMenu();
		gameMenu.setVisible(true);
		closeWindow();
	}
	
	//---------------------------------------------------------------------------------
	// All the Display Messages
	//---------------------------------------------------------------------------------
	private void blackJackMsg(){
		JOptionPane.showMessageDialog(null,"Congratulations! You've hit a blackjack! Your bet has been doubled! Please \"Bet\" to play again.","Blackjack!",JOptionPane.OK_OPTION);
	}
	
	private void bustMsg(){
		JOptionPane.showMessageDialog(null,"Sorry! You've busted, your bet is lost! Please \"Bet\" to play again.","Bust!",JOptionPane.OK_OPTION);
	}
	
	private void invalidBetMsg(){
		JOptionPane.showMessageDialog(null,"You don't have that amount to bet!","Invalid Bet",JOptionPane.OK_OPTION);
	}
	
	private void dealerBustMsg(){
		JOptionPane.showMessageDialog(null,"Congratulations! The Dealer has busted! Your bet has been doubled! Please \"Bet\" to play again.","Dealer Busted",JOptionPane.OK_OPTION);
	}
	
	private void dealerBlackJackMsg(){
		JOptionPane.showMessageDialog(null,"Sorry! The Dealer hit a blackjack, your bet is lost! Please \"Bet\" to play again.","Dealer Blackjack",JOptionPane.OK_OPTION);
	}
	
	private void playerWinsMsg(){
		JOptionPane.showMessageDialog(null,"Congratulations! You have beat the dealer! Your bet has been doubled! Please \"Bet\" to play again.","You Win!",JOptionPane.OK_OPTION);
	}
	
	private void dealerWinsMsg(){
		JOptionPane.showMessageDialog(null,"Sorry! You have lost to the dealer, your bet is lost! Please \"Bet\" to play again.","You Lose!",JOptionPane.OK_OPTION);
	}
	
	private void tieGameMsg(){
		JOptionPane.showMessageDialog(null,"The game is a tie, your bet has been returned! Please \"Bet\" to play again.","Tie Game!",JOptionPane.OK_OPTION);
	}
	
	private void endGameMsg(){
		JOptionPane.showMessageDialog(null,"The game is over, you have ran out of money! Better luck next time!","Game Over",JOptionPane.OK_OPTION);
	}
	
	//---------------------------------------------------------------------------------
	// Close the Window
	//---------------------------------------------------------------------------------
	private void closeWindow(){
		WindowEvent closeWndw = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWndw);
	}
}
