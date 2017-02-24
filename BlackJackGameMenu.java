/**
 * Either Play a game or Quit the program screen
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class BlackJackGameMenu extends JFrame {
	
	//---------------------------------------------------------------------------------
	// Create private variables
	//---------------------------------------------------------------------------------
	private JPanel contentPane;

	//---------------------------------------------------------------------------------
	// Launch the window
	//---------------------------------------------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackGameMenu frame = new BlackJackGameMenu();
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
	public BlackJackGameMenu() {
		
		//---------------------------------------------------------------------------------
		// Set the frame settings
		//---------------------------------------------------------------------------------
		setResizable(false);
		setTitle("Blackjack");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,330);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		int jFrameWidth=this.getSize().width;
		int jFrameHeight=this.getSize().height;
		int xPos = (scrSize.width-jFrameWidth)/2;
		int yPos = (scrSize.height-jFrameHeight)/2;
		this.setLocation(xPos, yPos);
		
		//---------------------------------------------------------------------------------
		// Set up the Content Pane
		//---------------------------------------------------------------------------------
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//---------------------------------------------------------------------------------
		// Set up the JLabel for the Blackjack logo
		//---------------------------------------------------------------------------------
		JLabel lblBlackJackLogo = new JLabel("");
		ImageIcon blackJackLogo = new ImageIcon(this.getClass().getResource("blackjacklogo.jpg"));
		lblBlackJackLogo.setIcon(blackJackLogo);
		lblBlackJackLogo.setBounds(32, 11, 330, 150);
		contentPane.add(lblBlackJackLogo);
		
		
		//---------------------------------------------------------------------------------
		// Set up the JButton for the "Play" Button
		//---------------------------------------------------------------------------------
		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GatherData getBuyInAmt = new GatherData();
				getBuyInAmt.setVisible(true);
				closeWindow();
			}
		});
		btnPlay.setBounds(143, 186, 107, 34);
		contentPane.add(btnPlay);
		
		//---------------------------------------------------------------------------------
		// Set up the JButton for the "Exit" Button (quit game)
		//---------------------------------------------------------------------------------
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(143, 231, 107, 34);
		contentPane.add(btnExit);
	}
	
	
	//---------------------------------------------------------------------------------
	// Close the window
	//---------------------------------------------------------------------------------
	private void closeWindow(){
		WindowEvent closeWndw = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWndw);
	}
}
