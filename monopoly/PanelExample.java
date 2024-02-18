package monopoly;

import java.awt.*;
import javax.swing.*;

public class PanelExample 
{ 

    //Attributes
    public ButtonPanel[] array_buttons;
    int fines;

     public PanelExample(Player[] array_players )
     { 
        this.fines = 0;
    	JFrame f= new JFrame("Panel Example");
        JPanel panel=new JPanel(); 
        
        panel.setBounds(40,80,1200,800);
        //panel.setBackground(Color.gray);

        this.array_buttons=new ButtonPanel[array_players.length];

        for (int i=0; i<array_players.length; i++) 
        {
        	array_buttons[i] = new ButtonPanel(array_players[i].name,array_players[i].colour,array_players[i].position,array_players[i].money);
        }

        for (int i=0; i<array_players.length; i++) 
        {
            panel.add(array_buttons[i]);
        }


        //JPanel panel=new JPanel();
        //panel.setBounds(40,80,1200,800);
        //panel.setBackground(Color.gray);
        //JButton b1=new JButton("Manolo");
        //rollLabel = new JLabel("Player: ");
        //b1.setBounds(50,100,80,30);
        //b1.setBackground(Color.yellow);
        //JButton b2=new JButton("Pepe");
        //b2.setBounds(100,100,80,30);
        //b2.setBackground(Color.green);
        //panel.add(b1); panel.add(b2);
        	f.add(panel);
                f.setSize(1800,300);
                f.setLayout(null);
                f.setVisible(true);
        }
     
     public int checkMoney() 
     {
         int counter1=0;
         int counter2=0;
         for (int i=0;i<this.array_buttons.length;i++)
         {
             if (this.array_buttons[i].player.money>0) 
             {
                 counter1=counter1+1;
             }
         }
         if(counter1==1) 
         {
             counter2=counter2+1;
         }
         return counter2;
     }
        // Other attributes
}
