import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		
		BlackJack program = new BlackJack();
		program.welcomeScreen();
		program.howManyPlayers();
		program.setUpPlayers();
		program.setUpBuyInAmount();
		program.playGame();
		
	}
	
	public static class BlackJack{
		private Scanner scan = new Scanner(System.in);
		private int players = 0;
		private boolean valid = false;
		private Person[] playerList;
		
		public BlackJack(){
			
		}
		
		public void welcomeScreen(){
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("$                       WELCOME TO BLACKJACK                       $");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n");
		}
		
		public void howManyPlayers(){
			while(players <= 0){
				try{
					System.out.print("\nHow many Players are there?: ");
					players = Integer.parseInt(scan.next());
					System.out.println();
					// If positive, continue
					if(players <= 0){
						System.out.println("   [ERROR] You must enter a positive integer.");
					}
				}
				// Catches non-numerical exceptions
				catch(NumberFormatException e){
					System.out.println("\n   [ERROR] You must enter a number.");
				}
			}
		}
		
		public void setUpPlayers(){
			Person tempPerson;
			playerList = new Person[players];
			
			for(int i = 0; i < players; i++){
				tempPerson = new Person();
				tempPerson.setName("Player " + (i + 1));
				playerList[i] = tempPerson;
			}
		}
		
		public void setUpBuyInAmount(){
			int buyIn = 0;
			
			for(int i = 0; i < players; i++){
				do{
					try{
						System.out.print("\nHow many dollars is " + playerList[i].getName() + " buying in for?: ");
						buyIn = Integer.parseInt(scan.next());
						System.out.println();
						// If positive, continue
						if(buyIn <= 0){
							System.out.println("   [ERROR] You must enter a positive integer.");
						}
						else
							valid = true;
					}
					// Catches non-numerical exceptions
					catch(NumberFormatException e){
						System.out.println("\n   [ERROR] You must enter a number.");
					}
				}while(!valid);
				valid = false;
				
				playerList[i].setDollars(buyIn);
			}
		}
		
		public void playGame(){
			int choice = 0;
			
			do{
				System.out.println("------------------------- [ STANDINGS ] -------------------------\n");
				for(int i = 0; i < players ; i++){
					System.out.println(playerList[i].getName() + ": " + playerList[i].dollarsOnHand());
				}
				System.out.println("\n-------------------------\n\n");
				
				int totalAmtFromPlayers = 0;
				for(int i = 0; i < players; i++){
					totalAmtFromPlayers = totalAmtFromPlayers + playerList[i].getDollars();
				}
				
				if(totalAmtFromPlayers > 0){
					do{
						System.out.println("1. Bet and Play");
						System.out.println("2. Stand Up (exit)");
						try{
							System.out.print("\nWhat would you like to do?: ");
							choice = Integer.parseInt(scan.next());
							System.out.println();
							// If positive, continue
							if(choice <= 0){
								System.out.println("   [ERROR] You must enter a positive integer.");
							}
							else if(choice == 1){
								roundOfPlay();
								valid = true;
							}
							else if(choice == 2){
								valid = true;
							}
						}
						// Catches non-numerical exceptions
						catch(NumberFormatException e){
							System.out.println("\n   [ERROR] You must enter a number.");
						}
					}while(!valid);
					valid = false;
				}
				else{
					System.out.println("   [GAME OVER] All Players have ran out of money.");
					choice = 2;
				}
			}while(choice != 2);
			System.out.println("\n---------------------------------------------------------------\n\n");
			System.out.println("                     [ THANKS FOR PLAYING ]                    ");
		}
		
		private void roundOfPlay(){
			int[][] bet = new int[players][1];
			
			for(int i = 0; i < players; i++){
				if(playerList[i].getDollars() > 0){
					do{
						try{
							System.out.print("\n" + playerList[i].getName() + ", how much would you like to bet?: ");
							bet[i][0] = Integer.parseInt(scan.next());
							System.out.println();
							// If positive, continue
							if(bet[i][0] <= 0){
								System.out.println("   [ERROR] You must enter a positive integer.");
							}
							else if(bet[i][0] > playerList[i].getDollars()){
								System.out.println("   [ERROR] You only have " + playerList[i].dollarsOnHand() + ".");
							}
							else if(bet[i][0] <= playerList[i].getDollars()){
								playerList[i].setDollars(playerList[i].getDollars() - bet[i][0]);
								valid = true;
							}
						}
						// Catches non-numerical exceptions
						catch(NumberFormatException e){
							System.out.println("\n   [ERROR] You must enter a number.");
						}
					}while(!valid);
					valid = false;
				}
			}
		}
		
	}
}
