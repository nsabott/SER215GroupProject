
public class Person {
	private String name;
	private int dollarsInHand, valueOfHand;
	
	public Person(){
		name = "";
		dollarsInHand = 0;
		valueOfHand = 0;
	}
	
	public Person(String name){
		this.name = name;
	}
	
	public Person(String name, int dollarsInHand){
		this.name = name;
		this.dollarsInHand = dollarsInHand;
	}
	
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
