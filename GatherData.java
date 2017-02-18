import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GatherData extends JFrame {

	private JPanel contentPane;
	private JTextField buyInAmount;
	private JLabel label;
	private JLabel label_1;
	private int buyInAmt = 0;
	private String name;
	private JTextField playerName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GatherData frame = new GatherData();
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
	public GatherData() {
		setTitle("Blackjack");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,330);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		int jFrameWidth=this.getSize().width;
		int jFrameHeight=this.getSize().height;
		int xPos = (scrSize.width-jFrameWidth)/2;
		int yPos = (scrSize.height-jFrameHeight)/2;
		this.setLocation(xPos, yPos);
		
		//setBounds(100, 100, 400, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourName = new JLabel("Your first name?");
		lblYourName.setForeground(new Color(255, 255, 255));
		lblYourName.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblYourName.setBounds(127, 48, 136, 24);
		contentPane.add(lblYourName);
		
		playerName = new JTextField();
		playerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyStroke = e.getKeyChar();
				if(!(Character.isAlphabetic(keyStroke)||(keyStroke==KeyEvent.VK_BACK_SPACE))||(keyStroke==KeyEvent.VK_DELETE)){
					e.consume();
				}
			}
		});
		playerName.setBounds(106, 83, 178, 20);
		contentPane.add(playerName);
		playerName.setColumns(10);
		
		JLabel lblBuyin = new JLabel("How much will you Buy In for?");
		lblBuyin.setForeground(new Color(255, 255, 255));
		lblBuyin.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblBuyin.setBounds(71, 114, 239, 24);
		contentPane.add(lblBuyin);
		
		buyInAmount = new JTextField();
		buyInAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyStroke = e.getKeyChar();
				if(!(Character.isDigit(keyStroke)||(keyStroke==KeyEvent.VK_BACK_SPACE))||(keyStroke==KeyEvent.VK_DELETE)){
					e.consume();
					
				}
			}
		});
		buyInAmount.setHorizontalAlignment(SwingConstants.CENTER);
		buyInAmount.setBounds(152, 149, 86, 20);
		contentPane.add(buyInAmount);
		buyInAmount.setColumns(10);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buyInAmt = Integer.parseInt(buyInAmount.getText());
				if(playerName.getText().equals("")|| buyInAmt <= 0 || buyInAmt > 10000){
					e.consume();
					if(playerName.getText().equals("")){
						JOptionPane.showMessageDialog(null,"You must enter your name to play!","Nameless",JOptionPane.ERROR_MESSAGE);
					}
					if(buyInAmt <= 0){
						JOptionPane.showMessageDialog(null,"You must have money to play!","Insufficient funds",JOptionPane.ERROR_MESSAGE);
					}
					if(buyInAmt > 10000){
						JOptionPane.showMessageDialog(null,"The table max is $10,000.00!","Table Stakes",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					name = playerName.getText();
					if(name.length() > 10){
						String shortName = name.substring(0, 10);
						name = shortName;
					}
					BlackJackGamePlay playGame = new BlackJackGamePlay(name,buyInAmt);
					playGame.setVisible(true);
					closeWindow();
				}
			}
		});
		btnPlay.setBounds(139, 199, 107, 34);
		contentPane.add(btnPlay);
		
		label = new JLabel("$");
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(138, 144, 12, 31);
		contentPane.add(label);
		
		label_1 = new JLabel(".00");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBounds(240, 147, 40, 24);
		contentPane.add(label_1);
		
		JButton btnBackToMenu = new JButton("Back to Menu");
		btnBackToMenu.setBounds(264, 268, 120, 23);
		btnBackToMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BlackJackGameMenu gameMenu = new BlackJackGameMenu();
				gameMenu.setVisible(true);
				closeWindow();
			}
		});
		contentPane.add(btnBackToMenu);
	}
	
	private void closeWindow(){
		WindowEvent closeWndw = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWndw);
	}
}
