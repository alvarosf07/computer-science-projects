package monopoly;

// Import of Packages
import java.util.Scanner;
import java.util.Arrays;


public class Game 
{	
	public static PanelExample panel;
	public static ButtonPanel[] array_buttons_order; 
	public static Player[] array_players_order;
	
	

	public static void main(String[] args) 
	{
    	// 1) INTIALIZATION OF DATA
		Beginning new_game = new Beginning();
		new_game.initialization();
		String[] array_players_name = new_game.array_players_name;
		String[] array_players_colour = new_game.array_players_colour;
		
		// 2) Create Player's Class
		Player[] array_players = new Player[array_players_name.length];
		for (int i=0; i<array_players_name.length; i++)
		{
			array_players[i]= new Player(array_players_name[i],array_players_colour[i],0,150000);
		}
		
		// 3) Create the Window Panel
		PanelExample panel= new PanelExample(array_players);
		Player[] array_players_order = new Player[array_players.length];
		ButtonPanel[] array_buttons_order= new ButtonPanel[array_players.length];
		
		// 4) Turn Order
		array_players_order=new_game.setOrder(panel,array_players);
		array_buttons_order=new_game.setOrderPannels(panel,array_players);
		panel=new_game.ActualizePanel(panel, array_buttons_order);
		
		
		// 5) START OF THE GAME
		runGame(panel, array_players_order);
		
	}
	
	
	
	public static void runGame (PanelExample panel, Player[] array_players_order)
	{
		System.out.println( "\n  ---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println( "\n\n                                                                    START OF THE GAME ");
		System.out.println( "\n\n  ---------------------------------------------------------------------------------------------------------------------------------------------------------------");

		Board Board1 = new Board();
		int counter;
		
		int endGame=0;	
		while(endGame==0)
		{
			int[] counterDoubles = new int [panel.array_buttons.length];
			for(int i=0;i<counterDoubles.length;i++) 
			{
				counterDoubles[i]=0;
			}
			
			for (int i=0; i<array_players_order.length; i++) 
			{
				
				if(panel.array_buttons[i].player.money>0 && endGame==0) 
				{
					
		    		System.out.println( "\n\n                                   ---------------------------------------------------------------------------------------");
					System.out.println( "\n                                                                     "+array_players_order[i].name + " TURN");
			    	System.out.println( "\n                                   ---------------------------------------------------------------------------------------\n");		
		    		
			    	
			    	if (panel.array_buttons[i].player.inJail!=1)
		    		{
			    		counter=panel.array_buttons[i].player.position;
			    		while (counter==panel.array_buttons[i].player.position)
			    		{
			    			array_players_order[i].position=array_players_order[i].position+(panel.array_buttons[i].getValue1()+panel.array_buttons[i].getValue2());
			        	}
		    		}	
			    	else 
			    	{
			    		System.out.println( "\n You are in Jail. Throw the dices \n");
			    		counter=panel.array_buttons[i].player.position;
			    		while (counter==panel.array_buttons[i].player.position)
			    		{
			    			array_players_order[i].position=array_players_order[i].position+(panel.array_buttons[i].getValue1()+panel.array_buttons[i].getValue2());
			        	}	
						
			    		if(panel.array_buttons[i].getValue1()==panel.array_buttons[i].getValue2()) 
						{
							System.out.println( "\n You got doubles!! You are free, you can leave Jail \n");
							panel.array_buttons[i].player.counterJail=0;
						}
						else 
						{
							panel.array_buttons[i].player.counterJail=panel.array_buttons[i].player.counterJail+1;
							if (panel.array_buttons[i].player.counterJail<3) 
							{
								System.out.println( "\n You are not free, you can not leave Jail \n");
								array_players_order[i].position=10;
							}
							
							if (panel.array_buttons[i].player.counterJail==3) 
							{
								System.out.println( "\n You didn't got doubles. However, it is your third turn here. You are free, you can leave Jail \n");
								panel.array_buttons[i].player.counterJail=0;
								
							}
						}
			    	}
		    		
		    		
		    		counterDoubles=Board1.check3Doubles(panel,i,counterDoubles);
		    		
		    		if (counterDoubles[i]==3) 
		    		{
		    			System.out.println("YOU HAD 3 STRAIGHT DOUBLES!! YOU GO TO JAIL");
		    			panel.array_buttons[i].player.position=10;
		    			panel.array_buttons[i].player.inJail=1;
		    		}
		    		else 
		    		{
			    		
		    			panel=Board1.runTurn(panel,i,array_players_order);
			    		endGame=panel.checkMoney();
			    		
			    		if (endGame==1) {
			    		    int indexWinner=0;
			    		    for (int j=0;j<panel.array_buttons.length;j++)
			    		    {
			    		    	if (panel.array_buttons[j].player.money>0)
			    		    	{
			    		    		indexWinner=j;
			    		    	}
			    		    }
			    		    System.out.println( "\n\n        -----------------------------------------------------------------------------------------------------------------------------");
			    			System.out.println( "\n                                      GAME FINISHED  Congratulations "+ panel.array_buttons[indexWinner].player.name+" you have won                       ");
			    	    	System.out.println( "\n        -----------------------------------------------------------------------------------------------------------------------------\n");		
			    		    
			    		}
			    		else 
			    		{
				    		System.out.println( "\nEND OF TURN");
				    		
				    		if (panel.array_buttons[i].getValue1()==panel.array_buttons[i].getValue2() && counterDoubles[i]<3) 
				    		{
			                    i=i-1;
			                    System.out.println("\n Double Throw!! The player repeats the turn!");
			                }
			    		}
		    		}
				}	
			}	  	
		}
	}

	
}

