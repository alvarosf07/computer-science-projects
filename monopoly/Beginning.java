package monopoly;

import java.util.Arrays;
import java.util.Scanner;

public class Beginning 
{
	
    // Attributes
    private int[] FirstPlayer;
    public String[] array_players_name;
    public String[] array_players_colour;

    // Constructors
    public Beginning()
    {
    	
    }
    
    // Getters
    public String[] name_players ()
    {
    	return this.array_players_name;
    }
    
    public String[] colour_players ()
    {
    	return this.array_players_colour;
    }
    
    //Other methods
    public void initialization ()
    {
    	// F1.1) Welcome_message
		System.out.println(
		         "\n\n                                       Y8b Y8b Y888P 888'Y88 888       e88'Y88   e88 88e       e   e     888'Y88             \n" +
		         "                                        Y8b Y8b Y8P  888 ,'Y 888      d888  'Y  d888 888b     d8b d8b    888 ,'Y             \n" +
		         "                                         Y8b Y8b Y   888C8   888     C8888     C8888 8888D   e Y8b Y8b   888C8               \n" +
		         "                                          Y8b Y8b    888 \",d 888  ,d  Y888  ,d  Y888 888P   d8b Y8b Y8b  888 \",d            \n" +
		         "                                           Y8P Y     888,d88 888,d88   \"88,d88   \"88 88\"   d888b Y8b Y8b 888,d88             \n" +
		         "                                                                                                                             \n" +
		         "                                                                   88P'888'Y88   e88 88e                                     \n" +
		         "                                                                   P'  888  'Y  d888 888b                                    \n" +
		         "                                                                       888     C8888 8888D                                   \n" +
		         "                                                                       888      Y888 888P                                    \n" +
		         "                                                                       888       \"88 88\"                                   \n" +
		         "                                                                                                                             \n" +
		         "                                        e   e       e88 88e   Y88b Y88    e88 88e   888 88e    e88 88e   888     Y88b Y8P    \n" + 
		         "                                       d8b d8b     d888 888b   Y88b Y8   d888 888b  888 888D  d888 888b  888      Y88b Y     \n" + 
		         "                                      e Y8b Y8b   C8888 8888D b Y88b Y  C8888 8888D 888 88\"  C8888 8888  888       Y88b      \n" + 
		         "                                     d8b Y8b Y8b   Y888 888P  8b Y88b    Y888 888P  888       Y888 888P  888  ,d    888      \n" + 
		         "                                    d888b Y8b Y8b   \"88 88\"   88b Y88b   \"88 88\"    888        \"88  88\"  888,d88    888\n" +
				 "\n                      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  \n");
		
		// F1.3) Players
		Scanner scan = new Scanner(System.in);
		System.out.println( "\n                                   ---------------------------------------------------------------------------------------");
		System.out.println( "\n                                                            INTRODUCE TOTAL NUMBER OF PLAYERS");
		System.out.println( "\n                                   ---------------------------------------------------------------------------------------");
		int total_players = scan.nextInt();
		int counterPlayers=0;
		
		if (total_players<2 || total_players>6) 
        {
			do
			{
				System.out.println("Number of players invalid, try again between 2 and 6");
				total_players = scan.nextInt();
				
				if (total_players>=2 && total_players<=6) 
	            {
	            	counterPlayers=1;
	            }
			}
			while (counterPlayers==0);
        }
		
		this.array_players_name = new String[total_players];
		this.array_players_colour = new String[total_players];
		System.out.println( "\n\n                                   ---------------------------------------------------------------------------------------");
		System.out.println( "\n                                                              INTRODUCE PLAYER'S INFORMATION ");
		System.out.println( "\n                                   ---------------------------------------------------------------------------------------\n");
		
		int availableName = 0;
		int availableColour = 0;
		String uselessPlayer_names = scan.nextLine();
		
		for (int i=0; i<total_players; i++)
		{
			do
			{
				System.out.println( "\n--------------------------------");
				System.out.println( "\n           PLAYER  "+(i+1));
				System.out.println( "\n--------------------------------");
				System.out.println( "Introduce Name of the Player "+(i+1));
				String player_name = scan.nextLine();
				
				if (Arrays.asList(this.array_players_name).contains(player_name))
				{
					System.out.println("\n Name already in use. Choose a different one. \n");
					availableName=0;
				}
				else
				{
					availableName=1;
					this.array_players_name [i] = player_name;
				}
			}
			while (availableName==0);
			
			do
			{
				System.out.println( "\n\nIntroduce Colour of the Player "+(i+1));
				String player_colour = scan.nextLine();
				
				if (Arrays.asList(this.array_players_colour).contains(player_colour))
				{
					System.out.println("\n Colour already in use. Choose a different one. \n");
					availableColour=0;
				}
				else
				{
					availableColour=1;
					this.array_players_colour [i] = player_colour;
				}
			}
			while (availableColour==0);
			//Player player = new Player();
		}
		while (availableName==0 || availableColour==0 );
		System.out.println( "\n--------------------------------");
		System.out.println( "\n         PLAYER'S  SUMMARY   ");
		System.out.println( "\n--------------------------------");
		System.out.println( "\n Players: \n");
		System.out.println(Arrays.toString(this.array_players_name));
		System.out.println( "\n\n Colours: \n");
		System.out.println(Arrays.toString(this.array_players_colour));
    	//return this.array_players_name;
    }
    
  
    public Player[] setOrder(PanelExample panel,Player[] array_players) 
    {
            int Value_Players[]= new int[array_players.length];

    		System.out.println( "\n                                   ---------------------------------------------------------------------------------------");
    		System.out.println( "\n                                                                         TURN ORDER ");
    		System.out.println( "\n                                   ---------------------------------------------------------------------------------------\n");
    		
    		System.out.println("ROLL THE DICE ONCE FOR EACH PLAYER, AND PRESS ENTER: ");
    		Scanner scan = new Scanner(System.in);
    		String useless = scan.nextLine();
            
            for (int i=0; i<array_players.length; i++)
                {
                 Value_Players[i]=panel.array_buttons[i].getValue1()+panel.array_buttons[i].getValue2();
                }
            int maximum=0;
            for (int i=0; i<array_players.length; i++)
            {
                if (Value_Players[i]>maximum)
                {
                    maximum=Value_Players[i];
                }
            }
            int firstPlayer=0;
            for (int i=0;i<array_players.length;i++) {
                if (Value_Players[i]==maximum)
                {
                    firstPlayer=i;
                }
            }
            Player array_players_order[]= new Player[array_players.length];

            for (int i=0;i<array_players.length;i++) {
                if(i<=firstPlayer) {
                    array_players_order[i]=array_players[firstPlayer-i];
                }
                if(i>firstPlayer) {
                    array_players_order[i]=array_players[array_players.length-(i-(firstPlayer))];
                }
            }
    		System.out.println( "\n--------------------------------");
    		System.out.println( "\n         STARTING ORDER   ");
    		System.out.println( "\n--------------------------------");
    		for (int i=0; i<array_players.length; i++)
    		{
    			System.out.println((i+1)+": "+array_players_order[i].name);
    		}
            return array_players_order;
         }
    
    public ButtonPanel[] setOrderPannels(PanelExample panel,Player[] array_players) {

             int Value_Players[]= new int[array_players.length];

             for (int i=0; i<array_players.length; i++)
                 {
                  Value_Players[i]=panel.array_buttons[i].getValue1()+panel.array_buttons[i].getValue2();
                 }
             int maximum=0;
             for (int i=0; i<array_players.length; i++)
             {
                 if (Value_Players[i]>maximum)
                 {
                     maximum=Value_Players[i];
                 }
             }
             int firstPlayer=0;
             for (int i=0;i<array_players.length;i++) {
                 if (Value_Players[i]==maximum)
                 {
                     firstPlayer=i;
                 }
             }
             ButtonPanel[] array_buttons_order = new ButtonPanel[array_players.length];

             for (int i=0;i<array_players.length;i++) {
                 if(i<=firstPlayer) {
                     array_buttons_order[i]=panel.array_buttons[firstPlayer-i];
                 }
                 if(i>firstPlayer) {
                     array_buttons_order[i]=panel.array_buttons[array_players.length-(i-(firstPlayer))];
                 }
             }
             return array_buttons_order;
          }

    public PanelExample ActualizePanel(PanelExample panel, ButtonPanel[] array_buttons_order) {
          for (int i=0;i<array_buttons_order.length;i++) 
          {
              panel.array_buttons[i]=array_buttons_order[i];
          }
          return panel;
      }
    
    public PanelExample ActualizePanel2(PanelExample panel, int index) 
    {
          if(panel.array_buttons[0].getValue1()!=0)
          panel.array_buttons[0].counter=1;
          return panel;
    }

}

