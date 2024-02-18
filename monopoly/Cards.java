package monopoly;

public class Cards extends Square
{
	// Attributes of Every Property
	private int square_number;
	String name;
	
	// Constructor
	public Cards (int square_number, String cardName)
	{
		super (square_number);
		this.square_number = square_number;
		this.name = cardName;
	}

	// Getters
	// Do one for each variable, divide in arrays, or make attributes public?
	
	// No Setters
	
	// Methods
	// 1) Print information of the property in the screen
	public PanelExample get_info(PanelExample panel,int index)
	{
		System.out.println( "\n--------------------------------------");
		System.out.println( "\n       ("+this.square_number+")   "+this.name);
		System.out.println( "\n--------------------------------------");
		if (this.name == "Chance Card")
		{
			System.out.println( "\n Pick up a Chance Card \n");
			
			if(panel.array_buttons[index].getValue1()>3) {

				System.out.println( "\n-----------------------------------------");
				System.out.println( "\n Go to the Jail withour receiving 20.000");
				System.out.println( "\n-----------------------------------------");
				panel.array_buttons[index].player.position=10;
			}
			
			if(panel.array_buttons[index].getValue1()<=3) {

				System.out.println( "\n---------------------------------------------------------------------------");
				System.out.println( "\n   Go to the Vermont Avenue and receive 20.000 if you pass the start box");
				System.out.println( "\n---------------------------------------------------------------------------");
				
				if(panel.array_buttons[index].player.position>8) {
					panel.array_buttons[index].player.money=panel.array_buttons[index].player.money+20000;
				}
				
				panel.array_buttons[index].player.position=8;
			}
			
			
		}
		else if (this.name == "Community Chest")
		{
			System.out.println( "\n Pick up a Community Chest \n");
			
			if(panel.array_buttons[index].getValue1()>3) {

				System.out.println( "\n-----------------------------------------");
				System.out.println( "\n             You receive 20.000 euros");
				System.out.println( "\n-----------------------------------------");
				panel.array_buttons[index].player.money=panel.array_buttons[index].player.money+20000;
			}
			
			if(panel.array_buttons[index].getValue1()<=3) {

				System.out.println( "\n-----------------------------------------");
				System.out.println( "\n             You pay 20.000 euros");
				System.out.println( "\n-----------------------------------------");
				panel.array_buttons[index].player.money=panel.array_buttons[index].player.money-20000;
				panel.fines=panel.fines+20000;
			}
			
		}
		return panel;
	}
	
	// 2) Calculate Rent calculateRent (int numUtilities, int dicesAmount)
	
}