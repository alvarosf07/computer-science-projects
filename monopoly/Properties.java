package monopoly;

import java.util.Scanner;


public class Properties extends Square
{
	// Attributes of Every Property
	private int square_number;
	String name;
	String ownerName;
	Player Owner;
	private int numHouses;
	private int numHotels;
	String neighbhood;
	String neighbhoodOwner;
	public  int price;
	private int pricePerHouse;
	private int rent;
	private int rentWith1House;
	private int rentWith2House;
	private int rentWith3House;
	private int rentWith4House;
	private int rentWith1Hotel;
	private int mortgage;
	
	// Constructor
	public Properties (int square_number, String propertyName, String neighbhood, int price, int pricePerHouse, int rent, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWith1Hotel, int mortgage)
	{
		super (square_number);
		this.square_number = square_number;
		this.name = propertyName;
		this.ownerName = "Unowned";
		this.numHouses = 0;
		this.numHotels = 0;
		this.neighbhood = neighbhood;
		this.neighbhoodOwner = "Unowned";
		this.price = price;
		this.pricePerHouse = pricePerHouse;
		this.rent = rent;
		this.rentWith1House = rentWith1House;
		this.rentWith2House = rentWith2House;
		this.rentWith3House = rentWith3House;
		this.rentWith4House = rentWith4House;
		this.rentWith1Hotel = rentWith1Hotel;
		this.mortgage = mortgage;
	}

	// Getters
	// Do one for each variable, divide in arrays, or make attributes public?
	
	// Setters
	// 1) New Property Owner
	public void set_owner (Player newOwner)
	{
		this.ownerName = newOwner.name;
		this.Owner = newOwner;
		this.Owner.addProperty(this.square_number);
		
	}
	// 2) New Neighborhood Owner
	public void set_NeighbhoodOwner (Player newNeighbhoodOwner)
	{
		this.neighbhoodOwner = newNeighbhoodOwner.name;
		this.Owner = newNeighbhoodOwner;
		
	}
	// 3) Number of Houses
	public void set_numHouses (int HouseVariation)
	{
		Board.total_houses=Board.total_houses-HouseVariation;
		this.numHouses = this.numHouses+HouseVariation;
	}
	// 4) Number of Hotels
	public void set_numHotels (int HotelVariation)
	{
		Board.total_hotels=Board.total_hotels-HotelVariation;
		this.numHotels = this.numHotels+HotelVariation;
	}
	
	
	// Methods
	// 1) Print information of the property in the screen
	public void get_info()
	{
		//int[] infoProperty = {this.square_number,this.propertyName,this.propertyOwner,this.numHouses,this.numHotels,this.neighbhood,this.neighbhoodOwner};
		//return  infoProperty;
		// F1.5) Summary of Turn Order
		System.out.println( "\n--------------------------------------");
		System.out.println( "\n       ("+this.square_number+")   "+this.name);
		System.out.println( "\n--------------------------------------");
		System.out.println( "\n Owner: "+this.ownerName);
		System.out.println( "\n Number of Houses: "+this.numHouses);
		System.out.println( "\n Number of Hotels: "+this.numHotels);
		System.out.println( "\n Neighborhood: "+this.neighbhood);
		System.out.println( "\n Neighborhood Owner: "+this.neighbhoodOwner);
		System.out.println( "\n Price of the Property: "+this.price);
		System.out.println( "\n Price To Build Each House: "+this.pricePerHouse);
		System.out.println( "\n Rent: "+this.rent);
		System.out.println( "\n Rent with 1 House: "+this.rentWith1House);
		System.out.println( "\n Rent with 2 Houses: "+this.rentWith2House);
		System.out.println( "\n Rent with 3 Houses: "+this.rentWith3House);
		System.out.println( "\n Rent with 4 Houses: "+this.rentWith4House);
		System.out.println( "\n Rent with Hotel: "+this.rentWith1Hotel);
		System.out.println( "\n Mortgage: "+this.mortgage);
	}
	
	// 2) Buy Properties
	public int buyProperty(Player player) 
    {
        // This method receives the name of a player who wants to buy the property (buyer), and:
        //        1) Assigns the property to the buyer
        //        2) Subtracts the economic price from the account of the buyer
        //        3) Returns the player money
        if (player==this.Owner)
        {
        	System.out.println( "\n  This property cannot be purchased because "+this.ownerName+" is the owner!\n");
        }
        else if (player.money>this.price) 
        {
	        set_owner (player);
	        player.money=player.money-this.price;
	        System.out.println("\n Congratulations "+player.name+"!  You have bought "+this.name+ " for "+this.price+"$\n");
        }
        else 
        {
            System.out.print("You don´t have enough money");
        }
        return player.money;
    }

	// 3) Pay Rent
	public PanelExample payRent(int indexPayer, PanelExample panel) {
        int indexOwner=0;
        for(int i=0;i<panel.array_buttons.length;i++)     
        {
	        if (panel.array_buttons[i].player.name==this.ownerName) 
	        {
	            indexOwner=i;
	        }
        }
       
        if(numHouses==0) 
        {
        	panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+this.rent;
        	panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money-this.rent;
        	System.out.println("\n\n The rent ("+this.rent+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }

        if(numHouses==1) 
        {
        	panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+this.rentWith1House;
        	panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money-this.rentWith1House;
        	System.out.println("\n\n The rent ("+this.rentWith1House+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }

        if(numHouses==2) 
        {
        	panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+this.rentWith2House;
        	panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money-this.rentWith2House;
        	System.out.println("\n\n The rent ("+this.rentWith2House+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }
        if(numHouses==3) 
        {
        	panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+this.rentWith3House;
        	panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money-this.rentWith3House;
        	System.out.println("\n\n The rent ("+this.rentWith3House+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }
        if(numHouses==4) 
        {
        	panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+this.rentWith4House;
        	panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money-this.rentWith4House;
        	System.out.println("\n\n The rent ("+this.rentWith4House+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }
        if(numHotels==1) 
        {
        	panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+this.rentWith1Hotel;
        	panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money-this.rentWith1Hotel;
        	System.out.println("\n\n The rent ("+this.rentWith1Hotel+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }
        
        return panel;
    }
	
	
	// 4) Auction
	public PanelExample auctionProperty(PanelExample panel) 
    {

        int [] bids= new int[panel.array_buttons.length];
        Scanner scan = new Scanner(System.in);

        System.out.println( "\n\n      ------------------------------------------------------------------");
        System.out.println("\n             The Auction for "+this.name+" begins!!");
        System.out.println( "\n      -----------------------------------------------------------------");


        for (int i=0; i<panel.array_buttons.length; i++) 
        {
            if(panel.array_buttons[i].player.money>0) 
            {
                System.out.println("\n"+panel.array_buttons[i].player.name+ ", introduce your bid:"); 

                int counter=0;
                while(counter==0) {
                    bids[i]= scan.nextInt();
                    if (bids[i]>panel.array_buttons[i].player.money) 
                    {
                        System.out.println("\n"+panel.array_buttons[i].player.name+ ", you don´t have enough money, introduce your bid again:"); 
                    }
                    else {
                        counter=1;
                    }
                }
            }
        }
        
        int maximumBid=0;
        int indexCounter=0;
        for (int i=0; i<panel.array_buttons.length; i++) 
        {
            if (bids[i]>maximumBid) 
            {
                maximumBid=bids[i];
                indexCounter=i;
            }
        }
        if (maximumBid==0) 
        {
            System.out.println("\n No one has bought the property");
        }
        if (maximumBid!=0) 
        {
            System.out.println("\n\n Congratulations "+panel.array_buttons[indexCounter].player.name+" you have bought "+this.name+" for "+maximumBid+" $");
          //this.ownerName=players[indexCounter].name;
            set_owner (panel.array_buttons[indexCounter].player);
            panel.array_buttons[indexCounter].player.money=panel.array_buttons[indexCounter].player.money-maximumBid;
        }
        //scan.close();
        return panel;
    }
	
	public int buyHouse(Player player) 
    {
        // This method receives the name of a player who wants to buy the property (buyer), and:
        //        1) Assigns the property to the buyer
        //        2) Subtracts the economic price from the account of the buyer
        //        3) Returns the player money
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println( "\n  ----> Introduce the number of houses you want to build: \n");
		int newNumHouses = scan.nextInt();
		int totalPrice = newNumHouses*this.pricePerHouse;
		int temp = this.Owner.listNeighbhoods.contains(this.neighbhood) ? 1 : 2;
		
		if (temp==1)
		{
	        if (player.money>totalPrice)
	        {
	        	if (Board.total_houses>=newNumHouses)
	        	{
	            	set_numHouses (newNumHouses);
	    	        player.money=player.money-totalPrice;
	    	        System.out.println("\n\n Congratulations "+player.name+"!  You have bought "+newNumHouses+ " Houses on "+this.name+" for "+totalPrice+"$\n");
	        	}
	        	else
	        	{
	        		System.out.print("\n\nThere is not enough Houses to build!!");
	        	}
	        }
	        else 
	        {
	            System.out.print("\n\n You don´t have enough money to build "+newNumHouses+" House");
	        }
		}
		else
		{
			System.out.println("\n\n You cannot build houses in this property because you are not the owner of the neighborhood! (Yet)\n");
		}
		//scan.close();
		return player.money;
    }
	
	public int buyHotel (Player player) 
    {
        // This method receives the name of a player who wants to buy the property (buyer), and:
        //        1) Assigns the property to the buyer
        //        2) Subtracts the economic price from the account of the buyer
        //        3) Returns the player money
		
		if (this.numHouses>=4)
		{
	        if (player.money>this.pricePerHouse)
	        {
	        	if (Board.total_hotels>0)
	        	{
	            	set_numHotels (1);
	            	set_numHouses (-4);
	    	        player.money=player.money-this.pricePerHouse;
	    	        System.out.println("\n\n Congratulations "+player.name+"!  You have build a Hotel on "+this.name+" for"+this.pricePerHouse+"$\n");
	        	}
	        	else
	        	{
	        		System.out.print("\n\nThere is not enough Hotels to build!!");
	        	}
	        }
	        else 
	        {
	            System.out.print("\n\n You don´t have enough money to build a Hotel");
	        }
		}
		else
		{
			System.out.println("\n\n You need a minimum of 4 houses to build an hotel. \n");
		}
        return player.money;
    }
	
	
	public int sellHouse(Player player) 
    {
        // This method receives the name of a player who wants to buy the property (buyer), and:
        //        1) Assigns the property to the buyer
        //        2) Subtracts the economic price from the account of the buyer
        //        3) Returns the player money
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println( "\n  ----> Introduce the number of houses you want to sell: \n");
		int numSellHouses = scan.nextInt();
		int totalPrice = numSellHouses*this.pricePerHouse;
		
    	if (this.numHouses>=numSellHouses)
    	{
        	set_numHouses (-numSellHouses);
	        player.money=player.money+totalPrice;
	        System.out.println("\n\n Congratulations "+player.name+"!  You have sold "+numSellHouses+ " Houses on "+this.name+" for "+totalPrice+"$\n");
    	}
    	else
    	{
    		System.out.print("\n\nThere is not enough Houses to sell!!");
    	}
    	//scan.close();
    	return player.money;
    }
	
	public int sellHotel(Player player) 
    {
        // This method receives the name of a player who wants to buy the property (buyer), and:
        //        1) Assigns the property to the buyer
        //        2) Subtracts the economic price from the account of the buyer
        //        3) Returns the player money
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println( "\n  ----> Introduce the number of houses you want to sell: \n");
		int numSellHouses = scan.nextInt();
		int totalPrice = numSellHouses*this.pricePerHouse;
		
    	if (this.numHotels>=1)
    	{
        	set_numHotels (-1);
	        player.money=player.money+(5*this.pricePerHouse);
	        System.out.println("\n\n Congratulations "+player.name+"!  You have sold a Hotel on "+this.name+" for "+totalPrice+"$\n");
    	}
    	else
    	{
    		System.out.print("\n\n You have no Hotels to Sell!!");
    	}
    	//scan.close();
        return player.money;
        
    }
	
	public int tradeProperty(PanelExample panel) 
    {
        // This method receives the name of a player who wants to trade the property (trader1), and:
        //        1) Asks for the player he wants to trade the property with (trader2) (it can also be sold to the bank)
        //        2) Asks the property of the other player he wants to obtain (it can also be sold for money)
        //        3) Asks for an additional sum of money 
		int indexTrader=0;
		for(int i=0;i<panel.array_buttons.length;i++)     
        {
	        if (panel.array_buttons[i].player.name==this.ownerName) 
	        {
	            indexTrader=i;
	        }
        }
		
		Scanner scan = new Scanner(System.in);
		System.out.println( "\n      ------------------------------------------------------------------");
        System.out.println( "\n             Introduce the player you want to trade with:");
        System.out.println( "\n      -----------------------------------------------------------------\n");
        for(int i=0;i<panel.array_buttons.length;i++)     
        {
        	if (panel.array_buttons[i].player.name!=this.ownerName) 
	        {
        		System.out.println( "\n     "+(i+1)+")  "+panel.array_buttons[i].player.name);
	        }
        	else
        	{
        		
        	}
        }
        System.out.println( "\n");
		int player2 = scan.nextInt()-1;
		
		if (0 <= player2 & player2 < panel.array_buttons.length)
		{
			System.out.println( "\n      ------------------------------------------------------------------");
	        System.out.println( "\n               Introduce the action you want to do:");
	        System.out.println( "\n      -----------------------------------------------------------------\n");
	        
	        System.out.println( "\n              0)  Sell "+this.name+" to "+panel.array_buttons[player2].player.name+" at Market Price:  "+(this.price+(this.numHouses*this.pricePerHouse)+(this.numHotels*5*this.pricePerHouse))+" $ ");
			
	        for (int i=0; i<panel.array_buttons[player2].player.listTitleDeeds.size(); i++)
			{
				if (panel.array_buttons[player2].player.listTitleDeeds.get(i)instanceof Properties)
				{
					System.out.println( "\n              "+(i+1)+")  BUY "+panel.array_buttons[player2].player.name+"'s Property —> "+((Properties)panel.array_buttons[player2].player.listTitleDeeds.get(i)).name+"  ("+((Properties)panel.array_buttons[player2].player.listTitleDeeds.get(i)).neighbhood+") :  "+((Properties)panel.array_buttons[player2].player.listTitleDeeds.get(i)).price+" $");
				}
				else if (panel.array_buttons[player2].player.listTitleDeeds.get(i) instanceof Stations)
				{
					System.out.println( "\n              "+(i+1)+")  BUY "+panel.array_buttons[player2].player.name+"'s Property —> "+((Stations)panel.array_buttons[player2].player.listTitleDeeds.get(i)).name+" :  "+((Stations)panel.array_buttons[player2].player.listTitleDeeds.get(i)).price+" $");
				}
				else if (panel.array_buttons[player2].player.listTitleDeeds.get(i) instanceof Utilities)
				{
					System.out.println( "\n              "+(i+1)+")  BUY "+panel.array_buttons[player2].player.name+"'s Property —>"+((Utilities)panel.array_buttons[player2].player.listTitleDeeds.get(i)).name+" :  "+((Utilities)panel.array_buttons[player2].player.listTitleDeeds.get(i)).price+" $");
				}
			}
	        System.out.println( "\n\n        Press any other number to go back to the Options Menu");
	        int action = scan.nextInt()-1;
	        
	        if (action < 0)
	        {
	        	//Sell Property
	        	for (int i=0; i<panel.array_buttons[indexTrader].player.listTitleDeeds.size(); i++)
	        	{
	        		if (panel.array_buttons[indexTrader].player.listTitleDeeds.get(i) instanceof Properties)
					{
						if (((Properties)panel.array_buttons[indexTrader].player.listTitleDeeds.get(i)).name == this.name)
						{	
							panel.array_buttons[indexTrader].player.listTitleDeeds.remove(i);
							System.out.println( "\n  "+panel.array_buttons[indexTrader].player.name+" has sold —> "+this.name+" to "+(panel.array_buttons[player2].player.name)+" for  "+(this.price+(this.numHouses*this.pricePerHouse)+(this.numHotels*5*this.pricePerHouse))+" $ ");
						}

					}
				}
	        	// Assign New Owner
	        	set_owner (panel.array_buttons[player2].player);
	        	
	        }
	        else if (0 <= action & action < panel.array_buttons[player2].player.listTitleDeeds.size())
			{
    			if (panel.array_buttons[player2].player.listTitleDeeds.get(action) instanceof Properties)
    			{
    				((Properties)panel.array_buttons[player2].player.listTitleDeeds.get(action)).buyProperty(panel.array_buttons[indexTrader].player);
    				
    			}
    			if (panel.array_buttons[player2].player.listTitleDeeds.get(action) instanceof Stations)
    			{

    				((Stations)panel.array_buttons[player2].player.listTitleDeeds.get(action)).buyStation(panel.array_buttons[indexTrader].player);
    			}
    			if (panel.array_buttons[player2].player.listTitleDeeds.get(action) instanceof Utilities)
    			{
    				((Utilities)panel.array_buttons[player2].player.listTitleDeeds.get(action)).buyUtility(panel.array_buttons[indexTrader].player);
    			}

			}
			else
			{
				System.out.println( "\n\n ");
				//menuTitleDeeds (titleDeed,panel);
			}
	        
		}
		else
		{
			System.out.println( "\n\n   The Player Introduced Doesn't Exist \n");
		}
		//scan.close();
        return panel.array_buttons[indexTrader].player.money;
    }
}


