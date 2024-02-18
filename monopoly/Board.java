package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board 
{
	private int total_squares;
	static int total_houses;
	static int total_hotels;
	static List<Square> boardSquares = new ArrayList<Square>();
	private int turnSquareNumber;
	Player turnPlayer;
	Square turnSquare;
	Player[] turnPlayers;
	
	// CONSTRUCTOR FOR BOARD:
	public Board ()
	{
		// CONSTRUCTOR FOR THE BOARD
		
		//      1) DEFINITION OF THE INITIAL NUMBER OF SQUARES IN THE BOARD: 40
		this.total_squares = 40;
		Board.total_houses = 32;
		Board.total_hotels = 12;
		
		//      2) SUBCLASSES INSIDE THE CLASS {SQUARE}
		//
		//         Title Deeds:
		//           Subclass 1 —> Real State Properties (22 in total)
		//           Subclass 2 —> Stations (4 in total)
		//           Subclass 3 —> Utilities (2 in total)
		//		   
		//         Cards:
		//           Subclass 4 —> Cards
		//           	       — Community Chest (3 in total)
		//                     — Chance Card (3 in total)
		//
		//         Special Squares:
		//           Subclass 5 —> Special Squares
		//                     — Go Square
		//                     — Free Parking Square
		//                     — Jail (Just Visiting)
		//                     — Go To Jail
		//                     — City Tax 
		//			           — Luxury Tax
		//
		//        2.1) DEFINITION OF ALL THE OBJECTS (22 IN TOTAL) OF THE SUBCLASS {PROPERTIES} 
		//        Information of each property: (Square Number, Name, Neighborhood, Price, Price per House, Rent, Rent(1 House), Rent(2 House), Rent(3 House), Rent(4 House), Rent Hotel, Mortgage)
		Properties property1 = new Properties(1, "Mediterranean Avenue", "Brown",12000,10000,400,2000,6000,18000,32000,50000,6000);
		Properties property2 = new Properties(3, "Baltic Avenue", "Brown", 12000,10000,800,4000,12000,36000,64000,90000,6000);
		Properties property3 = new Properties(6, "Oriental Avenue", "Blue", 20000,10000,1200,6000,18000,54000,80000,110000,10000);
		Properties property4 = new Properties(8, "Vermont Avenue", "Blue", 20000,10000,1200,6000,18000,54000,80000,110000,10000);
		Properties property5 = new Properties(9, "Connecticut Avenue", "Blue", 24000,10000,1600,8000,20000,60000,90000,120000,12000);
		Properties property6 = new Properties(11, "St.Charles Place", "Pink", 28000,20000,2000,10000,30000,90000,125000,150000,14000);
		Properties property7 = new Properties(13, "States Avenue", "Pink", 28000,20000,2000,10000,30000,90000,125000,150000,14000);
		Properties property8 = new Properties(14, "Virginia Avenue", "Pink", 32000,20000,2400,12000,36000,100000,140000,180000,16000);
		Properties property9 = new Properties(16, "St. James Place", "Orange", 36000,20000,2800,14000,40000,110000,150000,190000,18000);
		Properties property10 = new Properties(18, "Tennessee Avenue","Orange", 36000,20000,2800,14000,40000,110000,150000,190000,18000);
		Properties property11 = new Properties(19, "New York Avenue","Orange", 40000,20000,3200,16000,44000,120000,160000,200000,20000);
		Properties property12 = new Properties(21, "Kentucky Avenue", "Red", 44000,30000,3600,18000,50000,140000,175000,210000,22000);
		Properties property13 = new Properties(23, "Indiana Avenue","Red", 44000,30000,3600,18000,50000,140000,175000,210000,22000);
		Properties property14 = new Properties(24, "Illinois Avenue", "Red", 48000,30000,4000,20000,60000,150000,185000,220000,24000);
		Properties property15 = new Properties(26, "Atlantic Avenue", "Yellow", 52000,30000,4400,22000,66000,160000,195000,230000,26000);
		Properties property16 = new Properties(27, "Ventnor Avenue", "Yellow", 52000,30000,4400,22000,66000,160000,195000,230000,26000);
		Properties property17 = new Properties(29, "Marven Gardens","Yellow", 56000,30000,4800,24000,72000,170000,205000,240000,28000);
		Properties property18 = new Properties(31, "Pacific Avenue", "Green", 60000,40000,5200,26000,78000,180000,220000,255000,30000);
		Properties property19 = new Properties(32, "North Carolina Avenue", "Green", 60000,40000,5200,26000,78000,180000,220000,255000,30000);
		Properties property20 = new Properties(34, "Pennsylvania Avenue", "Green", 64000,40000,5600,30000,90000,200000,240000,280000,32000);
		Properties property21 = new Properties(37, "Park Place", "Navy", 70000,40000,7000,35000,100000,220000,260000,300000,35000);
		Properties property22 = new Properties(39, "Boardwalk", "Navy", 80000,40000,10000,40000,120000,280000,340000,400000,40000);
		
		//        2.2) DEFINITION OF ALL THE OBJECTS (4 IN TOTAL) OF THE SUBCLASS: {STATIONS} 
		Stations station1 = new Stations (5, "Reading Railroad");
		Stations station2 = new Stations (15, "Pennsylvania Railroad");
		Stations station3 = new Stations (25, "B&O Railroad");
		Stations station4 = new Stations (35, "King's Cross Station");
		
		//        2.3) DEFINITION OF ALL THE OBJECTS (2 IN TOTAL) OF THE SUBCLASS: {UTILITIES} 
		Utilities utility1 = new Utilities (12, "Electry Company");
		Utilities utility2 = new Utilities (28, "Water Works");
		
		//        2.4) DEFINITION OF THE TWO OBJECTS (CHANCE AND COMMUNITY CHEST) OF THE SUBCLASS: {CARDS} 
		Cards chance1 = new Cards (7,"Chance Card");
		Cards chance2 = new Cards (22,"Chance Card");
		Cards chance3 = new Cards (36,"Chance Card");
		Cards communityChest1 = new Cards (2,"Community Chest");
		Cards communityChest2 = new Cards (17,"Community Chest");
		Cards communityChest3 = new Cards (33,"Community Chest");
		
		
		//        2.5) DEFINITION OF SPECIAL SQUARES (6 TYPES)
		SpecialSquares goSquare = new SpecialSquares (0, "Go Square");
		SpecialSquares freeParking = new SpecialSquares (20, "Free Parking");
		SpecialSquares jailVisit = new SpecialSquares (10, "Jail (Just Visiting)");
		SpecialSquares goToJail = new SpecialSquares (30, "Go To Jail");
		SpecialSquares cityTax = new SpecialSquares (4, "City Tax");
		SpecialSquares luxuryTax = new SpecialSquares (38, "Luxury Task");
		
		//	 3) CONSTRUCTION OF THE BOARD: ARRAYLIST OF OBJECTS
		boardSquares.add(goSquare);
		boardSquares.add(property1);
		boardSquares.add(communityChest1);
		boardSquares.add(property2);
		boardSquares.add(cityTax);
		boardSquares.add(station1);
		boardSquares.add(property3);
		boardSquares.add(chance1);
		boardSquares.add(property4);
		boardSquares.add(property5);
		boardSquares.add(jailVisit);
		boardSquares.add(property6);
		boardSquares.add(utility1);
		boardSquares.add(property7);
		boardSquares.add(property8);
		boardSquares.add(station2);
		boardSquares.add(property9);
		boardSquares.add(communityChest2);
		boardSquares.add(property10);
		boardSquares.add(property11);
		boardSquares.add(freeParking);
		boardSquares.add(property12);
		boardSquares.add(chance2);
		boardSquares.add(property13);
		boardSquares.add(property14);
		boardSquares.add(station3);
		boardSquares.add(property15);
		boardSquares.add(property16);
		boardSquares.add(utility2);
		boardSquares.add(property17);
		boardSquares.add(goToJail);
		boardSquares.add(property18);
		boardSquares.add(property19);
		boardSquares.add(communityChest3);
		boardSquares.add(property20);
		boardSquares.add(station4);
		boardSquares.add(chance3);
		boardSquares.add(property21);
		boardSquares.add(luxuryTax);
		boardSquares.add(property22);

	}
	
	// GETTERS
	public Square get_BoardSquare(int square_number)
	{ 
		Square boardObject = boardSquares.get(square_number);
		return boardObject;
	}
	
	// METHOD 1: Run The Turn of a Player
	public PanelExample runTurn(PanelExample panel, int index, Player[] players) 
	{
		//Player Bank
        Player Bank= new Player("Bank","NoColour",0,0);
        
		//this.square_number = index;
		if(panel.array_buttons[index].player.position>39) 
		{
			panel.array_buttons[index].player.position=panel.array_buttons[index].player.position-(this.total_squares);
		}
		
		//get_info (panel.array_buttons[index].player.position);
		
		this.turnSquareNumber = panel.array_buttons[index].player.position;
		this.turnPlayer = panel.array_buttons[index].player;
		this.turnSquare = boardSquares.get(this.turnSquareNumber);
		this.turnPlayers = players;
		
        if (this.turnSquare instanceof Properties) 
		{
            ((Properties) this.turnSquare).get_info();
            
            if ( ((Properties) this.turnSquare).ownerName=="Unowned" || ((Properties) this.turnSquare).ownerName==turnPlayer.name)
            {
            	menuTitleDeeds(1,panel,index);
            }
            else 
            {
            	((Properties) this.turnSquare).payRent(index, panel);
            	menuTitleDeeds(1,panel,index);
            }
            	
            if (panel.array_buttons[index].player.money<0) 
            {
                menuTitleDeeds(1,panel,index);
            }	
            	
		}  	
            	
//            	if (panel.array_buttons[index].player.money<0) 
//            	{
//                    System.out.println( "\n                                ------------------------------------------------------------------------------------------");
//                    System.out.println( "\n                                  CAUTION YOU NEED TO SELL UNTIL YOUR MONEY QUANTITY IS POSITIVE OR YOU WILL BE ELIMINATED ");
//                    System.out.println( "\n                                ------------------------------------------------------------------------------------------\n");
//
//                    menuTitleDeeds(1,panel);
//                }
//                if (panel.array_buttons[index].player.money<0) 
//                {
//                        System.out.println( "\n                                ------------------------------------------------------------------------------------------");
//                        System.out.println( "\n                                                                 YOU HAVE BEEN ELIMINATED ");
//                        System.out.println( "\n                                ------------------------------------------------------------------------------------------\n");
//
//                        panel.array_buttons[index].player.eliminatePlayer(Bank);
//                }
//				menuTitleDeeds(1,panel);
//				}
		
		if (this.turnSquare instanceof Stations) 
		{
            ((Stations) this.turnSquare).get_info();
            
            if ( ((Stations) this.turnSquare).ownerName=="Unowned" || ((Stations) this.turnSquare).ownerName==turnPlayer.name)
            {
            	menuTitleDeeds(2,panel,index);
            }
            else 
            {
            	((Stations) this.turnSquare).payRent(index, panel);
            	menuTitleDeeds(2,panel,index);
            }
            
            if (panel.array_buttons[index].player.money<0) 
            {
                menuTitleDeeds(2,panel,index);
            }
        }
		//if (boardSquares.get(square_number) instanceof Utilities) 
		if (this.turnSquare instanceof Utilities) 
		{
            //((Utilities) boardSquares.get(square_number)).get_info();
			((Utilities) this.turnSquare).get_info();
			
			if ( ((Utilities) this.turnSquare).ownerName=="Unowned" || ((Utilities) this.turnSquare).ownerName==turnPlayer.name)
            {
				menuTitleDeeds(3,panel,index);
            }
            else 
            {
            	((Utilities) this.turnSquare).payRent(index, panel);
            	menuTitleDeeds(3,panel,index);
            }
			
			 if (panel.array_buttons[index].player.money<0) 
	         {
	            menuTitleDeeds(1,panel,index);
	         }
        }
		if (this.turnSquare instanceof Cards) 
		{
            ((Cards) this.turnSquare).get_info(panel,index);
            menuSpecial(panel, index);
			if (panel.array_buttons[index].player.money<0) 
	        {
				menuSpecial(panel,index);
	        }
        }
		
		if (this.turnSquare instanceof SpecialSquares) 
		{
            ((SpecialSquares) this.turnSquare).get_info(panel,index);
            menuSpecial(panel, index);
            
            if (panel.array_buttons[index].player.money<0) 
	        {
            	menuSpecial(panel,index);
	        }
        }
        
		if (panel.array_buttons[index].player.money<0) 
        {
                System.out.println( "\n                                ------------------------------------------------------------------------------------------");
                System.out.println( "\n                                                                 YOU HAVE BEEN ELIMINATED ");
                System.out.println( "\n                                ------------------------------------------------------------------------------------------\n");

                panel.array_buttons[index].player.eliminatePlayer(Bank);
        }
		
		return panel;
	}

	public void menuTitleDeeds (int titleDeed, PanelExample panel, int index)
	{
		if (panel.array_buttons[index].player.money<0) 
        {
            System.out.println( "\n                                ------------------------------------------------------------------------------------------");
            System.out.println( "\n                                  CAUTION YOU NEED TO SELL UNTIL YOUR MONEY QUANTITY IS POSITIVE OR YOU WILL BE ELIMINATED ");
            System.out.println( "\n                                ------------------------------------------------------------------------------------------\n");

        }
		
		System.out.println( "\n                                                  --------------------------------------");
		System.out.println( "\n                                                               OPTIONS MENU   ");
		System.out.println( "\n                                                  --------------------------------------");
		System.out.println( "\n                                                           1) BUY");
		System.out.println( "\n                                                           2) MANAGE PROPERTIES");
		System.out.println( "\n                                                           3) END TURN");
		System.out.println( "\n                                                  --------------------------------------");
		System.out.println( "\nPRESS 1, 2 or 3");
		
		Scanner scan = new Scanner(System.in);
		int menuOption = scan.nextInt();
	    int menuOut = 0;
	    
	    do
	    {
	    	switch (menuOption)
	    	{
	    		case 1: // BUY TITLE DEED
	    			
	    			if (titleDeed==1)
	    			{
	    				// If property unowned —> buy
	    				if ( ((Properties) this.turnSquare).ownerName=="Unowned" )
	    				{
	    					((Properties) this.turnSquare).buyProperty(this.turnPlayer);
	    				}
	    				// If property owned —> offer possibility for trade
	    				else
	    				{
	    					System.out.println( "\n  This property cannot be purchased because "+((Properties) this.turnSquare).ownerName+" is the owner!\n");
	    				}
	    			}
	    			if (titleDeed==2)
	    			{
	    				// If property unowned —> buy
	    				if ( ((Stations) this.turnSquare).ownerName=="Unowned" )
	    				{
	    					((Stations) this.turnSquare).buyStation(this.turnPlayer);
	    				}
	    				// If property owned —> offer possibility for trade
	    				else
	    				{
	    					System.out.println( "\n  This property cannot be purchased because "+((Stations) this.turnSquare).ownerName+" is the owner!\n");
	    				}
	    			}
	    			if (titleDeed==3)
	    			{
	    				// If property unowned —> buy
	    				if (((Utilities) this.turnSquare).ownerName=="Unowned")
	    				{
	    					((Utilities) this.turnSquare).buyUtility(this.turnPlayer);
	    				}
	    				// If property owned —> offer possibility for trade
	    				else
	    				{
	    					System.out.println( "\n  This property cannot be purchased because "+((Utilities) this.turnSquare).ownerName+" is the owner!\n");
	    				}
	    			}
	    			break;
	    	
	    		case 2: // MANAGE TITLE DEEDS
	    			System.out.println( "\n          --------------------------------");
	    			System.out.println("\n                List of Title Deeds");
	    			System.out.println( "\n          --------------------------------");
	 
	    			for (int i=0; i<this.turnPlayer.listTitleDeeds.size(); i++)
	    			{
	    				if (this.turnPlayer.listTitleDeeds.get(i)instanceof Properties)
	        			{
	    					System.out.println( "\n              "+(i+1)+") "+((Properties)this.turnPlayer.listTitleDeeds.get(i)).name+"  ("+((Properties)this.turnPlayer.listTitleDeeds.get(i)).neighbhood+")");
	        			}
	    				else if (this.turnPlayer.listTitleDeeds.get(i) instanceof Stations)
	        			{
	        				System.out.println( "\n              "+(i+1)+") "+((Stations)this.turnPlayer.listTitleDeeds.get(i)).name);
	        			}
	    				else if (this.turnPlayer.listTitleDeeds.get(i) instanceof Utilities)
	        			{
	        				System.out.println( "\n              "+(i+1)+") "+((Utilities)this.turnPlayer.listTitleDeeds.get(i)).name);
	        			}
	    			}
	    			
	    			System.out.println( "\n\n          ------------------------------------------");
	    			System.out.println("\n              Select a property (Introduce Number)");
	    			System.out.println( "\n          ------------------------------------------");
	    			int property = scan.nextInt()-1;
	    			if (0 <= property & property < this.turnPlayer.listTitleDeeds.size())
	    			{
	        			if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
	        			{
	        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).get_info();
	        			}
	        			if (this.turnPlayer.listTitleDeeds.get(property) instanceof Stations)
	        			{
	        				((Stations)this.turnPlayer.listTitleDeeds.get(property)).get_info();
	        			}
	        			if (this.turnPlayer.listTitleDeeds.get(property) instanceof Utilities)
	        			{
	        				((Utilities)this.turnPlayer.listTitleDeeds.get(property)).get_info();
	        			}
	        			
	        			System.out.println("\n\n           -----------------------------------");
		    			System.out.println("\n              Select an action to perform ");
		    			System.out.println("\n           -----------------------------------");
		    			System.out.println("\n                 1) Build Houses \n");
		    			System.out.println("\n                 2) Build Hotel \n");
		    			System.out.println("\n                 3) Sell Houses \n");
		    			System.out.println("\n                 4) Sell Hotels \n");
		    			System.out.println("\n                 5) Trade With Other Players  \n");
		    			System.out.println("\n                 6) Go Back To Menu \n");
		    			int action = scan.nextInt();
		
		    			if (action==1)
		    			{
		    				// Buy House
		    				if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).buyHouse(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only build Houses in properties! \n");
		    				}
		    				
		    			}
		    			else if (action==2)
		    			{
		    				// Build Hotel
		    				if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).buyHotel(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only build Hotels in properties! \n");
		    				}
		    			}
		    			else if (action==3)
		    			{
		    				// Sell House
		    				if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).sellHouse(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only sell Houses in properties! \n");
		    				}
		    			}
		    		    else if (action==4)
		    			{
		    				// Sell Hotel
		    		    	if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).sellHotel(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only sell Hotels in properties! \n");
		    				}
		    			}
		    		    else if (action==5)
		    			{
		    				//
		    		    	if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).tradeProperty(panel);
		        			}
		    		    	else if (this.turnPlayer.listTitleDeeds.get(property)instanceof Stations)
		        			{
		        				((Stations)this.turnPlayer.listTitleDeeds.get(property)).tradeStation(panel);
		        			}
		    		    	else if (this.turnPlayer.listTitleDeeds.get(property)instanceof Utilities)
		        			{
		        				((Utilities)this.turnPlayer.listTitleDeeds.get(property)).tradeUtility(panel);
		        			}
		    		    	
		    			}
		    		    else if (action==6)
		    			{
		    		    	System.out.println("\n\n");
		    			}
		    		    else
		    		    {
		    		    	System.out.println("\n Command inconnu \n");
		    		    }
		    			break;
	    			}
	    			else
	    			{
	    				System.out.println( "\n Bad Input");
	    				menuTitleDeeds (titleDeed,panel,index);
	    			}
	    			
	    		case 3: // END TURN
	    			if (this.turnSquare instanceof Properties)
	    			{
	    				if ( ((Properties) this.turnSquare).ownerName=="Unowned" )
	    				{
	    					((Properties) this.turnSquare).auctionProperty(panel);
	    				}
	    				
	    			}
	    			else if (this.turnSquare instanceof Stations)
	    			{
	    				if ( ((Stations) this.turnSquare).ownerName=="Unowned" )
	    				{
	    					((Stations) this.turnSquare).auctionStation(panel);
	    				}
	    			}
	    			else if (this.turnSquare instanceof Utilities)
	    			{
	    				if ( ((Utilities) this.turnSquare).ownerName=="Unowned" )
	    				{
	    					((Utilities) this.turnSquare).auctionUtility(panel);
	    				}
	    			}
	    			menuOut=1;
	    			break;
	    	}
	    	if (menuOut == 0)
	    		menuTitleDeeds(titleDeed,panel,index);
	    	else
	    		break;
	    }
	    while (menuOut!=0);
	}
	
	public void menuSpecial (PanelExample panel, int index)
	{
		if (panel.array_buttons[index].player.money<0) 
        {
            System.out.println( "\n                           --------------------------------------------------------------------------------------------");
            System.out.println( "\n                              CAUTION YOU NEED TO SELL UNTIL YOUR MONEY QUANTITY IS POSITIVE OR YOU WILL BE ELIMINATED ");
            System.out.println( "\n                           --------------------------------------------------------------------------------------------\n");

        }
		
		System.out.println( "\n                                                  --------------------------------------");
		System.out.println( "\n                                                               OPTIONS MENU   ");
		System.out.println( "\n                                                  --------------------------------------");
		System.out.println( "\n                                                          1) MANAGE PROPERTIES");
		System.out.println( "\n                                                          2) END TURN");
		System.out.println( "\n                                                  --------------------------------------");
		System.out.println( "\nPRESS 1 or 2");
	
		Scanner scan = new Scanner(System.in);
		int menuOption = scan.nextInt();
	    int menuOut = 0;
	    
	    do
	    {
	    	switch (menuOption)
	    	{
	    		case 1: // MANAGE TITLE DEEDS
	    			System.out.println( "\n          --------------------------------");
	    			System.out.println("\n                List of Title Deeds");
	    			System.out.println( "\n          --------------------------------");
	 
	    			for (int i=0; i<this.turnPlayer.listTitleDeeds.size(); i++)
	    			{
	    				if (this.turnPlayer.listTitleDeeds.get(i) instanceof Properties)
	        			{
	    					System.out.println( "\n              "+(i+1)+") "+((Properties)this.turnPlayer.listTitleDeeds.get(i)).name+"  ("+((Properties)this.turnPlayer.listTitleDeeds.get(i)).neighbhood+")");
	        			}
	    				else if (this.turnPlayer.listTitleDeeds.get(i) instanceof Stations)
	        			{
	        				System.out.println( "\n              "+(i+1)+") "+((Stations)this.turnPlayer.listTitleDeeds.get(i)).name);
	        			}
	    				else if (this.turnPlayer.listTitleDeeds.get(i) instanceof Utilities)
	        			{
	        				System.out.println( "\n              "+(i+1)+") "+((Utilities)this.turnPlayer.listTitleDeeds.get(i)).name);
	        			}
	    			}
	    			
	    			System.out.println( "\n\n          ------------------------------------------");
	    			System.out.println("\n              Select a property (Introduce Number)");
	    			System.out.println( "\n          ------------------------------------------");
	    			int property = scan.nextInt()-1;
	    			if (0 <= property & property < this.turnPlayer.listTitleDeeds.size())
	    			{
	        			if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
	        			{
	        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).get_info();
	        			}
	        			if (this.turnPlayer.listTitleDeeds.get(property) instanceof Stations)
	        			{
	        				((Stations)this.turnPlayer.listTitleDeeds.get(property)).get_info();
	        			}
	        			if (this.turnPlayer.listTitleDeeds.get(property) instanceof Utilities)
	        			{
	        				((Utilities)this.turnPlayer.listTitleDeeds.get(property)).get_info();
	        			}
	        			
	        			System.out.println("\n\n           -----------------------------------");
		    			System.out.println("\n              Select an action to perform ");
		    			System.out.println("\n           -----------------------------------");
		    			System.out.println("\n                 1) Build Houses \n");
		    			System.out.println("\n                 2) Build Hotel \n");
		    			System.out.println("\n                 3) Sell Houses \n");
		    			System.out.println("\n                 4) Sell Hotels \n");
		    			System.out.println("\n                 5) Trade With Other Players  \n");
		    			System.out.println("\n                 6) Go Back To Menu \n");
		    			int action = scan.nextInt();
		
		    			if (action==1)
		    			{
		    				// Buy House
		    				if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).buyHouse(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only build Houses in properties! \n");
		    				}
		    				
		    			}
		    			else if (action==2)
		    			{
		    				// Build Hotel
		    				if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).buyHotel(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only build Hotels in properties! \n");
		    				}
		    			}
		    			else if (action==3)
		    			{
		    				// Sell House
		    				if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).sellHouse(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only sell Houses in properties! \n");
		    				}
		    			}
		    		    else if (action==4)
		    			{
		    				// Sell Hotel
		    		    	if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).sellHotel(this.turnPlayer);
		        			}
		    				else
		    				{
		    					System.out.println("\n You can only sell Hotels in properties! \n");
		    				}
		    			}
		    		    else if (action==5)
		    			{
		    				//
		    		    	if (this.turnPlayer.listTitleDeeds.get(property)instanceof Properties)
		        			{
		        				((Properties)this.turnPlayer.listTitleDeeds.get(property)).tradeProperty(panel);
		        			}
		    		    	else if (this.turnPlayer.listTitleDeeds.get(property)instanceof Stations)
		        			{
		        				((Stations)this.turnPlayer.listTitleDeeds.get(property)).tradeStation(panel);
		        			}
		    		    	else if (this.turnPlayer.listTitleDeeds.get(property)instanceof Utilities)
		        			{
		        				((Utilities)this.turnPlayer.listTitleDeeds.get(property)).tradeUtility(panel);
		        			}
		    		    	
		    			}
		    		    else if (action==6)
		    			{
		    		    	System.out.println("\n\n");
		    			}
		    		    else
		    		    {
		    		    	System.out.println("\n Command inconnu \n");
		    		    }
		    			break;
	    			}
	    			else
	    			{
	    				System.out.println( "\n Bad Input");
	    				menuSpecial(panel, index);
	    			}
	    			
	    		case 2: // END TURN
	    			menuOut=1;
	    			break;
	    	}
	    	if (menuOut == 0)
	    		menuSpecial(panel, index);
	    	else
	    		break;
	    }
	    while (menuOut!=0);	
	}
	
	public int[] check3Doubles(PanelExample panel, int index, int[] counterDoubles) 
	{
        if (panel.array_buttons[index].getValue1()==panel.array_buttons[index].getValue2())
         {
             counterDoubles[index]=counterDoubles[index]+1;
         }
        return counterDoubles;
    }
	
}
