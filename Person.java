import java.text.DecimalFormat;

public class Person {
	private String name;
	private int dollars;
	private DecimalFormat fmt = new DecimalFormat("###,###,###,##0.00");
	
	public Person(){
		dollars = 0;
		name = "";
	}
	
	public Person(String name, int dollars){
		this.name = name;
		this.dollars = dollars;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDollars(int dollars){
		this.dollars = dollars;
	}
	
	public int getDollars(){
		return dollars;
	}
	
	public String dollarsOnHand(){
		return "$" + fmt.format(dollars);
	}
}