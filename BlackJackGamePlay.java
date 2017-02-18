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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;

public class BlackJackGamePlay extends JFrame {

	private JPanel contentPane;
	private BufferedImage cardImage;
	private DecimalFormat fmt = new DecimalFormat("###,###,###,##0.00");
	private boolean gameInPlay = false, stayActive = false;
	// Set up Players
	private Person dealer = new Person("Dealer"), player = new Person();
	private Card tempCard = new Card();
	private int betAmount;
	private JButton btnBackToMenu = new JButton("Back to Menu"), btnHit = new JButton("Hit");
	private JButton btnStay = new JButton("Stay"), btnBet = new JButton("Bet");
	private JComboBox betChoice = new JComboBox();
	private JPanel playerHandPanel = new JPanel(), dealerHandPanel = new JPanel();
	private Hand dealerHand, playerHand;
	private Deck deckInPlay;
	private JLabel[] dealerHandImage, playerHandImage;
	

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public BlackJackGamePlay(String name, int buyInAmt) {
		setTitle("Blackjack");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,700);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		int jFrameWidth=this.getSize().width;
		int jFrameHeight=this.getSize().height;
		int xPos = (scrSize.width-jFrameWidth)/2;
		int yPos = (scrSize.height-jFrameHeight)/2;
		this.setLocation(xPos, yPos);
		player.setDollarsInHand(buyInAmt);
		player.setName(name);
		
		//setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnBackToMenu.setBounds(854, 628, 120, 23);
		btnBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BlackJackGameMenu gameMenu = new BlackJackGameMenu();
				gameMenu.setVisible(true);
				closeWindow();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnBackToMenu);
		
		JLabel lblBuyInAmount = new JLabel(String.valueOf(fmt.format(buyInAmt)));
		lblBuyInAmount.setBounds(914, 586, 57, 31);
		lblBuyInAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBuyInAmount.setForeground(new Color(255, 255, 255));
		contentPane.add(lblBuyInAmount);
		
		JLabel playerName = new JLabel(name);
		playerName.setHorizontalAlignment(SwingConstants.RIGHT);
		playerName.setBounds(843, 590, 57, 23);
		playerName.setForeground(new Color(255, 255, 255));
		contentPane.add(playerName);
		
		betChoice.setBounds(274, 629, 57, 20);
		betChoice.setModel(new DefaultComboBoxModel(new String[] {"1", "5", "10", "25", "100", "All"}));
		contentPane.add(betChoice);
		
		//JLabel aceOfSpades = new JLabel(new ImageIcon(this.getClass().getResource("0.png")));
		//JLabel faceDownCard = new JLabel(new ImageIcon(this.getClass().getResource("back.png")));
		//playerHandPanel.add(aceOfSpades);
		//playerHandPanel.add(faceDownCard);
		playerHandPanel.setVisible(true);
		playerHandPanel.setBackground(new Color(0, 51, 0));
		playerHandPanel.setBounds(190, 472, 614, 141);
		contentPane.add(playerHandPanel);
		
		dealerHandPanel.setVisible(true);
		dealerHandPanel.setBackground(new Color(0, 51, 0));
		dealerHandPanel.setBounds(190, 113, 614, 141);
		contentPane.add(dealerHandPanel);
		
		JPanel chipPanel = new JPanel();
		ImageIcon pokerChip = new ImageIcon(this.getClass().getResource("pokerchip.png"));
		JLabel betChipImg = new JLabel("",pokerChip,JLabel.CENTER);
		chipPanel.add(betChipImg, BorderLayout.CENTER);
		chipPanel.setVisible(false);
		chipPanel.setBackground(new Color(0, 51, 0));
		chipPanel.setBounds(452, 376, 89, 85);
		contentPane.add(chipPanel);
		
		chipPanel.setVisible(true);
		btnHit.setBounds(452, 628, 89, 23);
		contentPane.add(btnHit);
		
		chipPanel.setVisible(false);
		btnStay.setBounds(570, 628, 89, 23);
		contentPane.add(btnStay);
		btnBet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(gameInPlay == false){
					gameInPlay = true;
					betAmount = betChoice.getSelectedIndex();
					resetValues();
					dealInitialCard();
					//deckInPlay = dealACard(deckInPlay, 1);
					
					
					//playerHand.add(new JLabel(new ImageIcon(tempCard.getCardImg())));
					sleepOneSec();
					
				}
				else{
					e.consume();
				}
			}
		});
		
		btnBet.setBounds(333, 628, 89, 23);
		contentPane.add(btnBet);
		
		JLabel label_1 = new JLabel("$");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBounds(905, 590, 13, 23);
		contentPane.add(label_1);
	}
	
	private void sleepOneSec(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	private void resetValues(){
		player.setValueOfHand(0);
		dealer.setValueOfHand(0);
	}
	
	private void dealInitialCard(){
		deckInPlay = new Deck();
		deckInPlay.createDeck();
		deckInPlay.shuffleDeck();
		//deckInPlay.printDeck();
		
		dealerHand = new Hand();
		playerHand = new Hand();
		
		playerHand.add(deckInPlay.dealCard());
		dealerHand.add(deckInPlay.dealCard());
		playerHand.add(deckInPlay.dealCard());
		dealerHand.add(deckInPlay.dealCard());
		
		dealerHandImage = dealerHand.getHandImage();
		playerHandImage = playerHand.getHandImage();
		
		for(int count = 0; count < 2; count++){
			dealerHandPanel.add(dealerHandImage[count]);
			playerHandPanel.add(playerHandImage[count]);
		}
	}
	
	/**private Deck dealACard(Deck deck, int playerSelection){
		tempCard = deck.dealCard();
		// Deal Card to Dealer
		if (playerSelection == 0){
			int tempValue = dealer.getValueOfHand();
			tempValue = tempValue + tempCard.getFaceValue();
			dealer.setValueOfHand(tempValue);
		}
		// Deal Card to Player
		else if (playerSelection == 1){
			int tempValue = player.getValueOfHand();
			tempValue = tempValue + tempCard.getFaceValue();
			player.setValueOfHand(tempValue);
		}
		return deck;
	}*/

	private void closeWindow(){
		WindowEvent closeWndw = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWndw);
	}
}
