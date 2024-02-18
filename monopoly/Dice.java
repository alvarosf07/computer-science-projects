package monopoly;

public class Dice 
{
	private int number_of_values=6;
	private int roll_value;
	
	public int roll_dice () 
	{
        //double r; // definition of variable r of type double
        int value = (int) (number_of_values*Math.random()+1); // creation of a random number r
		this.roll_value = value;
		//print_value ();
		return this.roll_value;
	}
	
	public void print_value () 
	{   
		System.out.println(roll_value);
	}


}