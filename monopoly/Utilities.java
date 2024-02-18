package monopoly;

import java.util.Scanner;

public class Utilities extends Square
{
	// Attributes of Every Property
	private int square_number;
	String name;
	String ownerName;
	Player Owner;
	public int price;
	private int rentWith1Utility;
	private int rentWith2Utility;
	private int mortgage;
	
	// Constructor
	public Utilities (int square_number, String stationName)
	{
		super (square_number);
		this.square_number = square_number;
		this.name = stationName;
		this.ownerName = "Unowned";
		this.price = 30000;
		this.rentWith1Utility = 4;
		this.rentWith2Utility = 10;
		this.mortgage = 15000;
	}

	// Getters
	// Do one for each variable, divide in arrays, or make attributes public?
	
	// Setters
	// 1) New Property Owner
	public void set_owner (Player newOwner)
	{
		this.ownerName = newOwner.name;
		this.Owner = newOwner;
		this.Owner.addUtility(this.square_number);
	}
	
	
	// Methods
	// 1) Print information of the property in the screen
	public void get_info()
	{
		//int[] infoProperty = {this.square_number,this.propertyName,this.propertyOwner,this.numHouses,this.numHotels,this.neighbhood,this.neighbhoodOwner};
		//return  infoProperty;
		System.out.println( "\n--------------------------------------");
		System.out.println( "\n      ("+this.square_number+")   "+this.name);
		System.out.println( "\n--------------------------------------");
		System.out.println( "\n Owner: "+this.ownerName);
		System.out.println( "\n Price of the Station: "+this.price);
		System.out.println( "\n Rent with 1 Utility:  Dice Amount x"+this.rentWith1Utility);
		System.out.println( "\n Rent with 2 Utilities:  Dice Amount x"+this.rentWith2Utility);
		System.out.println( "\n Mortgage: "+this.mortgage);
	}
	
	// 2) Buy Utility
	public int buyUtility(Player player) 
    {
        // This method receives the name of a player who wants to buy the station (buyer), and:
        //        1) Assigns the station to the buyer
        //         2) Subtracts the economic price from the account of the buyer
        //        3) Returns the player money

        if(player.money>this.price) 
        {
	        set_owner (player);
	        player.money=player.money-this.price;
	        System.out.println("\n Congratulations "+player.name+"!  You have bought "+this.name+" for "+this.price+" $\n");
        }

        else 
        {
            System.out.print("You don´t have enough money");
        }

        return player.money;
    }
	
	public void payRent(int indexPayer, PanelExample panel) 
    {
        int indexOwner=0;
        for(int i=0;i<panel.array_buttons.length;i++)
        {
            if (panel.array_buttons[i].player.name==this.ownerName) 
            {
                indexOwner=i;
            }
        }

        int numUtilities=panel.array_buttons[indexOwner].player.numberUtilities;
        if (numUtilities==1) 
        {
            panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+(this.rentWith1Utility*(panel.array_buttons[indexPayer].getValue1()+panel.array_buttons[indexPayer].getValue2()));
            panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money+(this.rentWith1Utility*(panel.array_buttons[indexPayer].getValue1()+panel.array_buttons[indexPayer].getValue2()));
            System.out.println("\n This utility is Property of "+this.ownerName+"!  The rent ("+(this.rentWith1Utility*(panel.array_buttons[indexPayer].getValue1()+panel.array_buttons[indexPayer].getValue2()))+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }
        else if(numUtilities==2) 
        {
            panel.array_buttons[indexOwner].player.money=panel.array_buttons[indexOwner].player.money+(this.rentWith2Utility*(panel.array_buttons[indexPayer].getValue1()+panel.array_buttons[indexPayer].getValue2()));
            panel.array_buttons[indexPayer].player.money=panel.array_buttons[indexPayer].player.money+(this.rentWith2Utility*(panel.array_buttons[indexPayer].getValue1()+panel.array_buttons[indexPayer].getValue2()));
            System.out.println("\n This utility is Property of "+this.ownerName+"!  The rent ("+(this.rentWith2Utility*(panel.array_buttons[indexPayer].getValue1()+panel.array_buttons[indexPayer].getValue2()))+") has been paid from "+panel.array_buttons[indexPayer].player.name+" to "+panel.array_buttons[indexOwner].player.name);
        }
        else
        {
            // Do Nothing
        }
    }
	
	public PanelExample auctionUtility(PanelExample panel) 
    {
        int [] bids= new int[panel.array_buttons.length];
        Scanner scan = new Scanner(System.in);

        System.out.println( "\n      ------------------------------------------------------------------");
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
            System.out.println("\n\n Congratulations "+panel.array_buttons[indexCounter].player.name+"! You have bought "+this.name+" for "+maximumBid+" $");
          //this.ownerName=players[indexCounter].name;
            set_owner (panel.array_buttons[indexCounter].player);
            panel.array_buttons[indexCounter].player.money=panel.array_buttons[indexCounter].player.money-maximumBid;
        }
        //scan.close();
        return panel;
    }
	
	public int tradeUtility(PanelExample panel) 
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
	        
	        System.out.println( "\n              0)  Sell "+this.name+" to "+panel.array_buttons[player2].player.name+" at Market Price:  "+this.price+" $ ");
			
	        for (int i=0; i<panel.array_buttons[player2].player.listTitleDeeds.size(); i++)
			{
				if (panel.array_buttons[player2].player.listTitleDeeds.get(i) instanceof Properties)
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
	        		if (panel.array_buttons[indexTrader].player.listTitleDeeds.get(i) instanceof Utilities)
					{
						if (((Utilities)panel.array_buttons[indexTrader].player.listTitleDeeds.get(i)).name == this.name)
						{	
							panel.array_buttons[indexTrader].player.listTitleDeeds.remove(i);
							System.out.println( "\n  "+panel.array_buttons[indexTrader].player.name+" has sold —> "+this.name+" to "+(panel.array_buttons[player2].player.name)+" for  "+(this.price)+" $ ");
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
