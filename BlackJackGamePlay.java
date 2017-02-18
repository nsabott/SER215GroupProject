import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
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
	private Person dealer = new Person("Dealer");
	private Person player = new Person();
	private Card tempCard = new Card();
	private int betAmount;

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
		
		JButton btnBackToMenu = new JButton("Back to Menu");
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
		
		JComboBox betChoice = new JComboBox();
		betChoice.setBounds(274, 629, 57, 20);
		betChoice.setModel(new DefaultComboBoxModel(new String[] {"1", "5", "10", "25", "100", "All"}));
		contentPane.add(betChoice);
		
		JPanel playerHand = new JPanel();
		JLabel aceOfSpades = new JLabel(new ImageIcon(this.getClass().getResource("0.png")));
		JLabel faceDownCard = new JLabel(new ImageIcon(this.getClass().getResource("back.png")));
		playerHand.add(aceOfSpades);
		playerHand.add(faceDownCard);
		playerHand.setVisible(true);
		playerHand.setBackground(new Color(0, 51, 0));
		playerHand.setBounds(190, 472, 614, 141);
		contentPane.add(playerHand);
		
		JPanel dealerHand = new JPanel();
		dealerHand.setBackground(new Color(0, 51, 0));
		dealerHand.setBounds(190, 113, 614, 141);
		contentPane.add(dealerHand);
		
		JPanel panel = new JPanel();
		ImageIcon pokerChip = new ImageIcon(this.getClass().getResource("pokerchip.png"));
		JLabel label = new JLabel("",pokerChip,JLabel.CENTER);
		panel.add(label, BorderLayout.CENTER);
		panel.setVisible(false);
		panel.setBackground(new Color(0, 51, 0));
		panel.setBounds(452, 376, 89, 85);
		contentPane.add(panel);
		
		JButton btnHit = new JButton("Hit");
		btnHit.setBounds(452, 628, 89, 23);
		contentPane.add(btnHit);
		
		JButton btnStay = new JButton("Stay");
		btnStay.setBounds(570, 628, 89, 23);
		contentPane.add(btnStay);
		
		JButton btnBet = new JButton("Bet");
		btnBet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(gameInPlay == false){
					gameInPlay = true;
					betAmount = betChoice.getSelectedIndex();
					resetValues();
					
					Deck deckInPlay = new Deck();
					deckInPlay.shuffleDeck();
					
					deckInPlay = dealACard(deckInPlay, 1);
					playerHand.add(new JLabel(new ImageIcon(tempCard.getCardImg())));
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
	
	private Deck dealACard(Deck deck, int playerSelection){
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
	}

	private void closeWindow(){
		WindowEvent closeWndw = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWndw);
	}
}
