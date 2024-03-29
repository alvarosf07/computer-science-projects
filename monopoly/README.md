# Monopoly Game Simulator (JAVA)

<br/>
<img src="2_monopoly.jpg" width="500">
<br/>

## Description of the Program
The program aims to simulate the popular board game "Monopoly". The main interface of the program is the command line, which displays all the information relative to the game, and allows us to navigate through the menus and introduce information. In addition, an extra Java Panel has been added to the interface of the command line, which will allow the players to throw the dices each turn, and will show the information relative to the players at all times (name, dices, money and position in the board), in order to make it simple for the users to quickly know the situation of the game at a glance.

At the initialization of the program, the user is asked to introduce the number of players (which is limited from 2 to 6), and the names and colors of each player. Obviously, two players are not allowed to have the same name or color. After the initialization of the program, each player shall throw the dices in order to define the order of the game. At this point, the new panel window will open, and will show from now on all the information of the players at all times. Is very important to clarify here that each player has his own button to roll the dices, which must be pushed only in the corresponding turn of that player, and only once.

After rolling the dices for each player and pressing INTRO, the orders of the turns are defined, and the game is ready to start! The turns of the players are always structured in the same way. The corresponding player rolls the dices, and the program automatically displays the number of the square where the player has moved, and the specific information about the square, depending on the class. If it is a Title Deed (property, station or utility/company), information about the owner, the purchase price, and the rent is displayed. In case the square is a card or a special square, the information is also displayed on screen.

After the corresponding information has been displayed, the main options menu for each turn is shown. The menu offers all the available possibilities for the players. In case they are in a title deed square, the BUY option is first displayed. If they want to see their properties first before buying, they can access the manage properties option, which automatically displays all the title deeds that the player has in possession. After selecting any of them, a new menu emerges, this time showing additional options on building or selling houses and hotels, or going back to the main options menu. Moreover, it is possible to access to further actions, encompassed under the "trade property" option. This will allow the player to sell the selected property to any other player, or to buy other properties belonging to any other player.

After exploring all the options of the menu, the player can decide whether or not to buy the property where he has landed. In case he doesn’t want to, he can select: "end turn". In this case, the auction for the property automatically starts, and the player who has rejected the purchase can still participate. All the players can submit their bids, and the player with the higher bid at the end of the round is the one who will purchase the property. If all the players introduce the number 0, the property will not be purchased by anyone.

<br/>


## Exceptions, Limitations and Additional Information
The game is at all times limited. All the menus displayed are programmed to select one of the alternatives given, and if any other number is introduced, a warning of "Bad Input" will be printed, and the main options menu will appear again. The only option that automatically stops the program is pressing the stop simulation button on the commandline, or alternatively the introduction of a string command when a number is required, or vice versa.

Moreover, additional information is shown in screen in case that any special event of the game takes place. For instance, when one player acquires all the properties corresponding to the same colour, a message is displayed in the command line, indicating that the player can now start building houses and hotels. Other example is the case when one player has no money left and must sell properties in order to end the turn in positive. The corresponding warning message will appear on the screen twice, and in case that the player still remains in negative, it will be eliminated. However, it is hard to end up in bankruptcy, since players are not allowed to buy properties or houses if they don’t have enough money in their account, and they can at all times trade their properties to get some extra cash and end the turn in positive!

