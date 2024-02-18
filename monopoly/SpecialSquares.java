package monopoly;

public class SpecialSquares extends Square
{
	// Attributes of Every Property
	private int square_number;
	String name;
	
	// Constructor
	public SpecialSquares (int square_number, String squareName)
	{
		super (square_number);
		this.square_number = square_number;
		this.name = squareName;
	}
	
	// No Setters
	
	// Methods
	// 1) Print information of the property in the screen
	public PanelExample get_info(PanelExample panel, int index)
	{
		System.out.println( "\n--------------------------------------");
		System.out.println( "\n       ("+this.square_number+")   "+this.name);
		System.out.println( "\n--------------------------------------");
		if (this.name == "Go Square")
		{
			System.out.println( "\n You are in the start square \n");
		}
		else if (this.name == "Free Parking")
		{
			System.out.println( "\n Enjoy your Free Parking! \n");
			System.out.println( "\n You receive "+panel.fines+" euros \n");
			panel.array_buttons[index].player.money=panel.array_buttons[index].player.money+panel.fines;
			panel.fines=0;
		}
		else if (this.name == "Jail")
		{
			if (panel.array_buttons[index].player.inJail==0) 
			{
				System.out.println( "\n You are just visiting the Jail. You can throw the dice on the next turn \n");
			}
			if (panel.array_buttons[index].player.inJail==1) 
			{
				int counter=0;
				System.out.println( "\n You are in Jail. Throw the dices \n");
				if(panel.array_buttons[index].getValue1()==panel.array_buttons[index].getValue2()) 
				{
					System.out.println( "\n You are free, you can leave Jail \n");
					panel.array_buttons[index].player.position=10+panel.array_buttons[index].getValue1()+panel.array_buttons[index].getValue2();	
				}
			
				else 
				{
					counter=counter+1;
					if (counter<3) 
					{
						System.out.println( "\n You are not free, you can not leave Jail \n");
					}
					
					if (counter==3) 
					{
						System.out.println( "\n You are free, you can leave Jail \n");
						panel.array_buttons[index].player.position=10+panel.array_buttons[index].getValue1()+panel.array_buttons[index].getValue2();
						
					}
				}
				
			}
		}
		else if (this.name == "Go To Jail")
		{
			System.out.println( "\n Go to Jail Directly, WITHOUT passing through the Go Square and WITHOUT receiving the 20.000 $. \n You must stay in JAIL for three turns, unless you have the card to get out of jail, or you get a double in throw.");
			panel.array_buttons[index].player.position=10;
			panel.array_buttons[index].player.inJail=1;
		}
		else if (this.name == "City Tax")
		{
			System.out.println( "\n You must pay 40.000 $ \n");
			panel.array_buttons[index].player.money=panel.array_buttons[index].player.money-40000;
			panel.fines=panel.fines+40000;
		}
		else if (this.name == "Luxury Tax")
		{
			System.out.println( "\n You MUST pay 20.000 $ \n");
			panel.array_buttons[index].player.money=panel.array_buttons[index].player.money-20000;
			panel.fines=panel.fines+20000;
		}
		
		return panel;
	}
	
}