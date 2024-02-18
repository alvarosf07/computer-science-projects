package monopoly;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ButtonPanel extends JPanel implements ActionListener 
{
    // Attributes
    private int faceValue= 0;
    private int faceValue2= 0;
    private JLabel rollLabel;
    private JButton rollButton;
    Player player;
    int counter=0;

    // Constructors

    public ButtonPanel(String name, String color,int position, int money) 
    {	
        this.player= new Player(name,color,position, money);
        this.counter=0;
        rollButton = new JButton(name);
        rollLabel = new JLabel("Player: "+name);
        Font font1 = new Font("Monospaced", Font.PLAIN, 24);
        //button.setBackground(Color.yellow); 
        rollLabel.setFont(font1);
        rollButton.setFont(font1);
        this.add(rollButton);
        this.add(rollLabel);
        rollButton.addActionListener(this);
    }

    // Other functions

    public void actionPerformed(ActionEvent e) 
    {
    	Dice dice= new Dice();
    	if (this.counter!=0)
    	{
        faceValue = dice.roll_dice();
        faceValue2 = dice.roll_dice();
        player.position=player.position+(faceValue+faceValue2);
        if (player.position>24) {
        	player.position=player.position-24;
        }
        rollLabel.setText("<html> Player: "+player.name+"<br/> Dice 1: "+faceValue +"<br/> Dice 2: "+faceValue2+"<br> Money: "+player.money+"<br> Position: "+ player.position);
        //rollLabel.setText("<html> Player: "+player.name+"<br/> Dice 1: "+faceValue +"<br/> Dice 2: "+faceValue2+"<br> Money: "+player.money+"<br> Position: "+ player.position+"<html>");
         
    	}
    	
    	if (this.counter==0)
    	{
        faceValue = dice.roll_dice();
        faceValue2 = dice.roll_dice();
        rollLabel.setText("<html> Player: "+player.name+"<br/> Dice 1: "+faceValue +"<br/> Dice 2: "+faceValue2+"<br> Money: "+player.money+"<br> Position: "+ player.position);
        //rollLabel.setText("<html> Player: "+player.name+"<br/> Dice 1: "+faceValue +"<br/> Dice 2: "+faceValue2+"<br> Money: "+player.money+"<br> Position: "+ player.position+"<html>");
        this.counter=1;
    	}
    }
    
    public int getValue1() 
    {
    	return faceValue;
    }
    public int getValue2() 
    {
    	return faceValue2;
    }
    
}
