package monopoly;

import java.util.ArrayList;
import java.util.List;

public class Player 
{

    //Atributes
        public String colour;
        public String name;
        public int position;
        public int money;
        public List<Square> listTitleDeeds = new ArrayList<Square>();
        public int counter;
        public int numberStations;
        public int numberUtilities;
        public int inJail;
        public int counterJail;
        public List<String> listNeighbhoods = new ArrayList<String>();
        //Square[] listTitleDeeds;
        //Properties[] listProperties;
        //Stations[] listStations;
        //Utilities[] listUtilities;

    // Constructors
        public Player (String name, String colour, int position, int money) {
            this.name=name;
            this.colour=colour;
            this.position=position;
            this.money=money;
            this.counter=0;
            this.inJail=0;
            this.counterJail=1;
        }
        
    // Method 1: Adds a new property to the ownership of the player
        public void addProperty (int square_number)
        {
        	listTitleDeeds.add(Board.boardSquares.get(square_number));
        	addNeighbhood(square_number);
        }
        
        public void addStation (int square_number)
        {
        	//listStations [square_number] = (Stations) Board.boardSquares.get(square_number);
        	//listTitleDeeds [square_number] = (Stations) Board.boardSquares.get(square_number);
        	listTitleDeeds.add(Board.boardSquares.get(square_number));
        	this.numberStations=this.numberStations+1;
        }
        
        public void addUtility (int square_number)
        {
        	//listUtilities [square_number] = (Utilities) Board.boardSquares.get(square_number);
        	//listTitleDeeds [square_number] = (Utilities) Board.boardSquares.get(square_number);
        	listTitleDeeds.add(Board.boardSquares.get(square_number));
        	this.numberUtilities=this.numberUtilities+1;
        }

        public void eliminatePlayer(Player Bank) {
            for (int i=0; i<this.listTitleDeeds.size(); i++)
            {
                if (this.listTitleDeeds.get(i) instanceof Properties)
                {
                    ((Properties)this.listTitleDeeds.get(i)).ownerName="Unknown"; 
                    ((Properties)this.listTitleDeeds.get(i)).Owner=Bank;
                }
                else if (this.listTitleDeeds.get(i) instanceof Stations)
                {
                    ((Properties)this.listTitleDeeds.get(i)).ownerName="Unknown"; 
                    ((Properties)this.listTitleDeeds.get(i)).Owner=Bank;
                }
                else if (this.listTitleDeeds.get(i) instanceof Utilities)
                {
                    ((Properties)this.listTitleDeeds.get(i)).ownerName="Unknown"; 
                    ((Properties)this.listTitleDeeds.get(i)).Owner=Bank;
                }
            }
        }

        public void addNeighbhood (int square_number)
        {
        	String neighbhoodColour = ((Properties) Board.boardSquares.get(square_number)).neighbhood;
        	int colourCounter = 0;
        	
        	for (int i=0; i<this.listTitleDeeds.size(); i++)
			{
				if (this.listTitleDeeds.get(i) instanceof Properties)
    			{
					if ( ((Properties)this.listTitleDeeds.get(i)).neighbhood == neighbhoodColour)
					{
						colourCounter = colourCounter+1;
					}
    			}
			}
        	
        	if (colourCounter==3)
        	{
        		listNeighbhoods.add(neighbhoodColour);
        		System.out.println( "\n   -----------------------------------------------------------------------------------------------------------------------------------------------------------");
        		System.out.println( "\n     CONGRATULATIONS "+this.name+"! You have acquired the complete "+neighbhoodColour+" neighborhood! You can start building houses and hotels now! :) ");
        		System.out.println( "\n   -----------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
        	}
        	if ( colourCounter==2 & (neighbhoodColour=="Brown" || neighbhoodColour=="Navy") )
        	{
        		listNeighbhoods.add(neighbhoodColour);
        		System.out.println( "\n   -----------------------------------------------------------------------------------------------------------------------------------------------------------");
        		System.out.println( "\n     CONGRATULATIONS "+this.name+"! You have acquired the complete "+neighbhoodColour+" neighborhood! You can start building houses and hotels now! :) ");
        		System.out.println( "\n   -----------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
        	}
        	
        }

}


