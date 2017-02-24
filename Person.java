/**
 * Person Class
 * 
 * @author Neil Sabott
 * @version 1.0
 */

public class Person {
	
	//---------------------------------------------------------------------------------
	// Create private variables
	//---------------------------------------------------------------------------------
	private String name;
	private int dollarsInHand, valueOfHand;
	
	//---------------------------------------------------------------------------------
	// Constructor - Blank
	//---------------------------------------------------------------------------------
	public Person(){
		name = "";
		dollarsInHand = 0;
		valueOfHand = 0;
	}
	
	//---------------------------------------------------------------------------------
	// Constructor - With Name assigned only
	//---------------------------------------------------------------------------------
	public Person(String name){
		this.name = name;
	}
	
	//---------------------------------------------------------------------------------
	// Constructor - With Name and Dollars in Hand
	//---------------------------------------------------------------------------------
	public Person(String name, int dollarsInHand){
		this.name = name;
		this.dollarsInHand = dollarsInHand;
	}
	
	//---------------------------------------------------------------------------------
	// Getters/Setters
	//---------------------------------------------------------------------------------
	public void setDollarsInHand(int dollarsInHand){
		this.dollarsInHand = dollarsInHand;
	}
	
	public int getDollarsInHand(){
		return dollarsInHand;
	}
	
	public void setValueOfHand(int valueOfHand){
		this.valueOfHand = valueOfHand;
	}
	
	public int getValueOfHand(){
		return valueOfHand;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
