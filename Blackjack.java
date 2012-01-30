//Jacob Gofman 
//Project
//May 12th, 2011

import javax.swing.*;	
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Blackjack extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6570698493311479705L;
	
	static Deck deck;
	static Dealer dealer;
	static Player player;
	static Game game;
	
	static Card dcard1; //dealers 1st card that gets flipped over at the end
	
	
	final static int CARDWIDTH = 120; //width of each card for the border
	final static int CARDHEIGHT = 130; //height of each card for the border
	final static Color TITLECOLOR = new Color(180, 180, 180); //color of the card place borders

	private JButton deal; //deal button
	private JButton stay; //stay button
	private JButton hit; //hit button
	private JButton quit; //quit button

	//dealer card places 1-7
	private JLabel dealerCardPlace1;
	private JLabel dealerCardPlace2;
	private JLabel dealerCardPlace3;
	private JLabel dealerCardPlace4;
	private JLabel dealerCardPlace5;
	private JLabel dealerCardPlace6;
	private JLabel dealerCardPlace7;
	private int dealerCardPlaceLevel = 100; //y coordinate of dealer card places

	//dealer card places 1-7
	private JLabel playerCardPlace1;
	private JLabel playerCardPlace2;
	private JLabel playerCardPlace3;
	private JLabel playerCardPlace4;
	private JLabel playerCardPlace5;
	private JLabel playerCardPlace6;
	private JLabel playerCardPlace7;
	private int playerCardPlaceLevel = 300; //y coordinate of player card places

	//other fields
	private JTextField dealerPoints; //dealer's points per game field
	private JTextField playerPoints; //player's points per game field
	private JTextField dealerScore; //dealer's total score field
	private JTextField playerScore; //player's total score field
	private int dealerGamesWon; //dealer's total score number
	private int playerGamesWon; //player's total score number
	
	private JTextField winner; //field where winner is declared
	
	//builds the GUI, foundation of game
	public void buildGUI()
	{
		// this is the blackjack table Container content = getContentPane();
		Container content = getContentPane();
		content.setLayout(null);
		content.setBackground(new Color(0, 50, 0));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Jacob Gofman's BlackJack");
		setSize(945,700); //window dimensions
		setResizable(false);
		setLocation(100,25); //where to start (0,0) in window

		//BUTTONS//
		
		//Deal button
		deal = new JButton("Deal");
		deal.setBounds(253, 30, 75, 25);
		deal.addActionListener(this);
		deal.setEnabled(true);
		content.add(deal);
		
		//Stay button
		stay = new JButton("Stay");
		stay.setBounds(373, 30, 75, 25);
		stay.addActionListener(this);
		stay.setEnabled(false);
		content.add(stay);

		//Hit button
		hit = new JButton("Hit");
		hit.setBounds(493, 30, 75, 25);
		hit.addActionListener(this);
		hit.setEnabled(false);
		content.add(hit);
		
		//Quit button
		quit = new JButton("Quit");
		quit.setBounds(613, 30, 75, 25);
		quit.addActionListener(this);
		quit.setEnabled(true);
		content.add(quit);

		
		
		//DEALER CARD PLACES//
		
		//Dealer Card Place 1
		dealerCardPlace1 = new JLabel(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace1.setBounds(50,dealerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		dealerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, "Card 1", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(dealerCardPlace1);
		dealerCardPlace1.setEnabled(false);

		//Dealer Card Place 2
		dealerCardPlace2 = new JLabel(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace2.setBounds(170,dealerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		dealerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, "Card 2", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(dealerCardPlace2);
		dealerCardPlace2.setEnabled(false);

		//Dealer Card Place 3
		dealerCardPlace3 = new JLabel(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace3.setBounds(290,dealerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		dealerCardPlace3.setBorder(BorderFactory.createTitledBorder(null, "Card 3", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(dealerCardPlace3);
		dealerCardPlace3.setEnabled(false);

		//Dealer Card Place 4
		dealerCardPlace4 = new JLabel(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace4.setBounds(410,dealerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		dealerCardPlace4.setBorder(BorderFactory.createTitledBorder(null, "Card 4", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(dealerCardPlace4);
		dealerCardPlace4.setEnabled(false);

		//Dealer Card Place 5
		dealerCardPlace5 = new JLabel(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace5.setBounds(530,dealerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		dealerCardPlace5.setBorder(BorderFactory.createTitledBorder(null, "Card 5", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(dealerCardPlace5);
		dealerCardPlace5.setEnabled(false);
		
		//Dealer Card Place 6
		dealerCardPlace6 = new JLabel(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace6.setBounds(650,dealerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		dealerCardPlace6.setBorder(BorderFactory.createTitledBorder(null, "Card 6", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(dealerCardPlace6);
		dealerCardPlace6.setEnabled(false);

		//Dealer Card Place 7
		dealerCardPlace7 = new JLabel(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace7.setBounds(770,dealerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		dealerCardPlace7.setBorder(BorderFactory.createTitledBorder(null, "Card 7", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(dealerCardPlace7);
		dealerCardPlace7.setEnabled(false);
		
		
		
		//PLAYER CARD PLACES//

		//Player Card Place 1
		playerCardPlace1 = new JLabel(new ImageIcon("CardImages/back.gif"));
		playerCardPlace1.setBounds(50,playerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		playerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, "Card 1", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(playerCardPlace1);
		playerCardPlace1.setEnabled(false);

		//Player Card Place 2
		playerCardPlace2 = new JLabel(new ImageIcon("CardImages/back.gif"));
		playerCardPlace2.setBounds(170,playerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		playerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, "Card 2", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(playerCardPlace2);
		playerCardPlace2.setEnabled(false);

		//Player Card Place 3
		playerCardPlace3 = new JLabel(new ImageIcon("CardImages/back.gif"));
		playerCardPlace3.setBounds(290,playerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		playerCardPlace3.setBorder(BorderFactory.createTitledBorder(null, "Card 3", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(playerCardPlace3);
		playerCardPlace3.setEnabled(false);
	
		//Player Card Place 4
		playerCardPlace4 = new JLabel(new ImageIcon("CardImages/back.gif"));
		playerCardPlace4.setBounds(410,playerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		playerCardPlace4.setBorder(BorderFactory.createTitledBorder(null, "Card 4", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(playerCardPlace4);
		playerCardPlace4.setEnabled(false);
		
		//Player Card Place 5
		playerCardPlace5 = new JLabel(new ImageIcon("CardImages/back.gif"));
		playerCardPlace5.setBounds(530,playerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		playerCardPlace5.setBorder(BorderFactory.createTitledBorder(null, "Card 5", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(playerCardPlace5);
		playerCardPlace5.setEnabled(false);
		
		//Player Card Place 6
		playerCardPlace6 = new JLabel(new ImageIcon("CardImages/back.gif"));
		playerCardPlace6.setBounds(650,playerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		playerCardPlace6.setBorder(BorderFactory.createTitledBorder(null, "Card 6", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(playerCardPlace6);
		playerCardPlace6.setEnabled(false);

		//Player Card Place 7
		playerCardPlace7 = new JLabel(new ImageIcon("CardImages/back.gif"));
		playerCardPlace7.setBounds(770,playerCardPlaceLevel,CARDWIDTH,CARDHEIGHT);
		playerCardPlace7.setBorder(BorderFactory.createTitledBorder(null, "Card 7", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		content.add(playerCardPlace7);
		playerCardPlace7.setEnabled(false);
		
		
		
		//OTHER FIELDS
		
		//Dealer's Points
		dealerPoints = new JTextField("Dealer's Points: 0");
		dealerPoints.setBounds(50, dealerCardPlaceLevel + 150, 125, 25);
		dealerPoints.addActionListener(this);
		dealerPoints.setEditable(false);
		content.add(dealerPoints);

		//Dealer's Score
		dealerScore = new JTextField("Dealer's Score: 0 games won.");
		dealerScore.setBounds(250, dealerCardPlaceLevel+150, 180, 25);
		dealerScore.addActionListener(this);
		dealerScore.setEditable(false);
		content.add(dealerScore);

		//Player's Points
		playerPoints = new JTextField("Player's Points: 0");
		playerPoints.setBounds(50, playerCardPlaceLevel+150,125,25);
		playerPoints.addActionListener(this);
		playerPoints.setEditable(false);
		content.add(playerPoints);		
		
		//Player's Score
		playerScore = new JTextField("Player's Score: 0 games won.");
		playerScore.setBounds(250, playerCardPlaceLevel+150, 180, 25);
		playerScore.addActionListener(this);
		playerScore.setEditable(false);
		content.add(playerScore);
		
		//Area For Declared Winner 
		winner = new JTextField("Winner: ");
		winner.setBounds(323,555,300,25);
		winner.addActionListener(this);
		winner.setEditable(false);
		content.add(winner);
	}

	//instantiates a new game
	public Blackjack()
	{
		this.setVisible(true);
		
		dealer = new Dealer(); //creates dealer
		player = new Player(); //creates player
		deck = new Deck(); //creates deck
		game = new Game(player, dealer, deck); //creates (instantiates) game

		buildGUI();	//builds GUI, foundation for game
	}

	//start of game
	public static void main(String[] args)
	{
		Blackjack frame = new Blackjack(); //new frame in which to play game
		frame.setVisible(true);
	}

	//resets fields on table to deal again
	public void reset()
	{
		//reset dealer and player hands
		dealer.setHand();
		player.setHand();
		
		//reset buttons to original enabled/disabled states
		deal.setEnabled(true);
		stay.setEnabled(false);
		hit.setEnabled(false);
		quit.setEnabled(true);		
		
		
		//Reset dealer card place images and titles, disable state
		dealerCardPlace1.setIcon(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, "Card 1", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace1.setEnabled(false);
		dealerCardPlace1.update(getGraphics());

		dealerCardPlace2.setIcon(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, "Card 2", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace2.setEnabled(false);
		dealerCardPlace2.update(getGraphics());

		dealerCardPlace3.setIcon(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace3.setBorder(BorderFactory.createTitledBorder(null, "Card 3", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace3.setEnabled(false);
		dealerCardPlace3.update(getGraphics());

		dealerCardPlace4.setIcon(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace4.setBorder(BorderFactory.createTitledBorder(null, "Card 4", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace4.setEnabled(false);
		dealerCardPlace4.update(getGraphics());

		dealerCardPlace5.setIcon(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace5.setBorder(BorderFactory.createTitledBorder(null, "Card 5", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace5.setEnabled(false);
		dealerCardPlace5.update(getGraphics());

		dealerCardPlace6.setIcon(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace6.setBorder(BorderFactory.createTitledBorder(null, "Card 6", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace6.setEnabled(false);
		dealerCardPlace6.update(getGraphics());

		dealerCardPlace7.setIcon(new ImageIcon("CardImages/back.gif"));
		dealerCardPlace7.setBorder(BorderFactory.createTitledBorder(null, "Card 7", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace7.setEnabled(false);
		dealerCardPlace7.update(getGraphics());

		
		
		//Reset player card place images and titles, disable state 
		playerCardPlace1.setIcon(new ImageIcon("CardImages/back.gif"));
		playerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, "Card 1", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		playerCardPlace1.setEnabled(false);
		playerCardPlace1.update(getGraphics());

		playerCardPlace2.setIcon(new ImageIcon("CardImages/back.gif"));
		playerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, "Card 2", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		playerCardPlace2.setEnabled(false);
		playerCardPlace2.update(getGraphics());

		playerCardPlace3.setIcon(new ImageIcon("CardImages/back.gif"));
		playerCardPlace3.setBorder(BorderFactory.createTitledBorder(null, "Card 3", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		playerCardPlace3.setEnabled(false);
		playerCardPlace3.update(getGraphics());

		playerCardPlace4.setIcon(new ImageIcon("CardImages/back.gif"));
		playerCardPlace4.setBorder(BorderFactory.createTitledBorder(null, "Card 4", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		playerCardPlace4.setEnabled(false);
		playerCardPlace4.update(getGraphics());

		playerCardPlace5.setIcon(new ImageIcon("CardImages/back.gif"));
		playerCardPlace5.setBorder(BorderFactory.createTitledBorder(null, "Card 5", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		playerCardPlace5.setEnabled(false);
		playerCardPlace5.update(getGraphics());

		playerCardPlace6.setIcon(new ImageIcon("CardImages/back.gif"));
		playerCardPlace6.setBorder(BorderFactory.createTitledBorder(null, "Card 6", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		playerCardPlace6.setEnabled(false);
		playerCardPlace6.update(getGraphics());
		
		playerCardPlace7.setIcon(new ImageIcon("CardImages/back.gif"));
		playerCardPlace7.setBorder(BorderFactory.createTitledBorder(null, "Card 7", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		playerCardPlace7.setEnabled(false);
		playerCardPlace7.update(getGraphics());

		//reset point and winner fields
		playerPoints.setText("Player's Points: 0");
		dealerPoints.setText("Dealer's Points: 0");
		winner.setText("Winner: ");
		
		//refresh
		repaint();
	}
	
	//assigns actions to the buttons when pressed
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == deal)
		{
			reset(); //reset table
			
			//set cards to variables
			dcard1 = dealer.dealCard();
			Card dcard2 = dealer.dealCard();
			Card pcard1 = player.dealCard();
			Card pcard2 = player.dealCard();
			
			//write cards to their places for dealer
			dealerCardPlace1.setEnabled(true);
			dealerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, "???", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
			dealerCardPlace1.setIcon(dcard1.getBack());
			dealerCardPlace1.update(getGraphics());
			
			dealerCardPlace2.setEnabled(true);
			dealerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, dcard2.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
			dealerCardPlace2.setIcon(dcard2.getFace());
			dealerCardPlace2.update(getGraphics());
			
			//write cards to their places for player
			playerCardPlace1.setEnabled(true);
			playerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, pcard1.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
			playerCardPlace1.setIcon(pcard1.getFace());
			playerCardPlace1.update(getGraphics());
			
			playerCardPlace2.setEnabled(true);
			playerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, pcard2.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
			playerCardPlace2.setIcon(pcard2.getFace());
			playerCardPlace2.update(getGraphics());
			
			//update player's points
			playerPoints.setText("Player's Points: " + player.getPoints());
			playerPoints.updateUI();
			
			//update dealer's points
			dealerPoints.setText("Dealer's Points: >" + dcard2.getRank());
			dealerPoints.updateUI();
			
			//enable hit and stay buttons to play game
			deal.setEnabled(false);
			hit.setEnabled(true);
			stay.setEnabled(true);
			
			//refresh
			repaint();
		}
		
		if(e.getSource() == hit)
		{
			playerRules(); //call rules for player when "hit" is pressed
		}
		
		if(e.getSource() == stay)
		{
			dealerAI(); //call dealer's brain when "stay" is pressed
		}
		
		if(e.getSource() == quit)
		{
			//Option to play again
			JOptionPane pane = new JOptionPane("Would you like to play again?");
			Object[] options = new String[] { "Yes", "No" };
			pane.setOptions(options);
			JDialog dialog = pane.createDialog(new JFrame(), "Dialog");
			dialog.show();
			Object val = pane.getValue(); 
			int result = 0;
			for (int i = 0; i < options.length; i++)
			{
				if (options[i].equals(val)) result = i;
			}
			if (result == JOptionPane.YES_OPTION)
			{
				//if yes, hide and reset
				dialog.hide();
				reset();
			}
			else
			{
				//if no, exit
				System.exit(0);
			}
		}
	}	
	
	//rules for the player when he/she hits
	private void playerRules()
	{
		if (playerCardPlace1.isEnabled()) //if card 1 exists, check...
		{
			if (playerCardPlace2.isEnabled()) //...if card 2 exists, check...
			{
				if (playerCardPlace3.isEnabled()) //...if card 3 exists, check...
				{
					if (playerCardPlace4.isEnabled()) //...if card 4 exists, check...
					{
						if (playerCardPlace5.isEnabled()) //...if card 5 exists, check...
						{
							if (playerCardPlace6.isEnabled()) //...if card 6 exists, check...
							{
								if (playerCardPlace7.isEnabled()) //...if card 7 exists, no more moves possible
								{
									winner.setText("Invalid move.");
								}
								else //set card to place 7
								{
									Card pcard7 = player.dealCard();
									playerCardPlace7.setEnabled(true);
									playerCardPlace7.setBorder(BorderFactory.createTitledBorder(null, pcard7.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
									playerCardPlace7.setIcon(pcard7.getFace());
									playerCardPlace7.update(getGraphics());
								}
							}
							else //set card to place 6
							{
								Card pcard6 = player.dealCard();
								playerCardPlace6.setEnabled(true);
								playerCardPlace6.setBorder(BorderFactory.createTitledBorder(null, pcard6.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
								playerCardPlace6.setIcon(pcard6.getFace());
								playerCardPlace6.update(getGraphics());
							}
						}
						else //set card to place 5
						{
							Card pcard5 = player.dealCard();
							playerCardPlace5.setEnabled(true);
							playerCardPlace5.setBorder(BorderFactory.createTitledBorder(null, pcard5.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
							playerCardPlace5.setIcon(pcard5.getFace());
							playerCardPlace5.update(getGraphics());
						}
					}
					else //set card to place 4
					{
						Card pcard4 = player.dealCard();
						playerCardPlace4.setEnabled(true);
						playerCardPlace4.setBorder(BorderFactory.createTitledBorder(null, pcard4.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
						playerCardPlace4.setIcon(pcard4.getFace());
						playerCardPlace4.update(getGraphics());
					}
				}
				else //set card to place 3
				{
					Card pcard3 = player.dealCard();
					playerCardPlace3.setEnabled(true);
					playerCardPlace3.setBorder(BorderFactory.createTitledBorder(null, pcard3.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
					playerCardPlace3.setIcon(pcard3.getFace());
					playerCardPlace3.update(getGraphics());
				}
			}
			else //set card to place 2
			{
				Card pcard2 = player.dealCard();
				playerCardPlace2.setEnabled(true);
				playerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, pcard2.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
				playerCardPlace2.setIcon(pcard2.getFace());
				playerCardPlace2.update(getGraphics());
			}
		}
		else //set card to place 1
		{
			Card pcard1 = player.dealCard();
			playerCardPlace1.setEnabled(true);
			playerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, pcard1.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
			playerCardPlace1.setIcon(pcard1.getFace());
			playerCardPlace1.update(getGraphics());
		}

		//update player's points
		int points = player.getPoints();
		playerPoints.setText("Player's Points: " + points);
		playerPoints.updateUI();

		if (points > 21) checkWinner(); //if points over 21 player busts
		else if (points == 21) dealerAI(); //if points equal 21 give dealer a chance to match
		
		//refresh
		repaint();
	}

	//brain of dealer
	public void dealerAI()
	{
		int points = dealer.getPoints();
		
		while (points < 18)
		{
			if (dealerCardPlace1.isEnabled()) //if card 1 exists, check...
			{
				if (dealerCardPlace2.isEnabled())//...if card 2 exists, check...
				{
					if (dealerCardPlace3.isEnabled())//...if card 3 exists, check...
					{
						if (dealerCardPlace4.isEnabled())//...if card 4 exists, check...
						{
							if (dealerCardPlace5.isEnabled())//...if card 5 exists, check...
							{
								if (dealerCardPlace6.isEnabled())//...if card 6 exists, check...
								{
									if (dealerCardPlace7.isEnabled())//...if card 7 exists, invalid move
									{
										winner = new JTextField("Invalid move.");
									}
									else
									{
										//set card to place 7
										Card dcard7 = dealer.dealCard();
										dealerCardPlace7.setEnabled(true);
										dealerCardPlace7.setBorder(BorderFactory.createTitledBorder(null, dcard7.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
										dealerCardPlace7.setIcon(dcard7.getFace());
										dealerCardPlace7.update(getGraphics());
									}
								}
								else
								{
									//set card to place 6
									Card dcard6 = dealer.dealCard();
									dealerCardPlace6.setEnabled(true);
									dealerCardPlace6.setBorder(BorderFactory.createTitledBorder(null, dcard6.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
									dealerCardPlace6.setIcon(dcard6.getFace());
									dealerCardPlace6.update(getGraphics());
								}
							}
							else
							{
								//set card to place 5
								Card dcard5 = dealer.dealCard();
								dealerCardPlace5.setEnabled(true);
								dealerCardPlace5.setBorder(BorderFactory.createTitledBorder(null, dcard5.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
								dealerCardPlace5.setIcon(dcard5.getFace());
								dealerCardPlace5.update(getGraphics());
							}
						}
						else
						{
							//set card to place 4
							Card dcard4 = dealer.dealCard();
							dealerCardPlace4.setEnabled(true);
							dealerCardPlace4.setBorder(BorderFactory.createTitledBorder(null, dcard4.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
							dealerCardPlace4.setIcon(dcard4.getFace());
							dealerCardPlace4.update(getGraphics());
						}
					}
					else
					{
						//set card to place 3
						Card dcard3 = dealer.dealCard();
						dealerCardPlace3.setEnabled(true);
						dealerCardPlace3.setBorder(BorderFactory.createTitledBorder(null, dcard3.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
						dealerCardPlace3.setIcon(dcard3.getFace());
						dealerCardPlace3.update(getGraphics());
					}
				}
				else
				{
					//set card to place 2
					Card dcard2 = dealer.dealCard();
					dealerCardPlace2.setEnabled(true);
					dealerCardPlace2.setBorder(BorderFactory.createTitledBorder(null, dcard2.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
					dealerCardPlace2.setIcon(dcard2.getFace());
					dealerCardPlace2.update(getGraphics());
				}
			}
			else
			{
				//set card to place 1
				dcard1 = dealer.dealCard();
				dealerCardPlace1.setEnabled(true);
				dealerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, dcard1.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
				dealerCardPlace1.setIcon(dcard1.getBack());
				dealerCardPlace1.update(getGraphics());
			}

			points = dealer.getPoints(); //get points to evaluate continuation of loop
		}
		
		//flip dealer's 1st card
		dealerCardPlace1.setBorder(BorderFactory.createTitledBorder(null, dcard1.getName(), TitledBorder.CENTER, TitledBorder.ABOVE_TOP, getFont(), TITLECOLOR));
		dealerCardPlace1.setIcon(dcard1.getFace());
		dealerCardPlace1.update(getGraphics());
		
		//update dealer's points
		points = dealer.getPoints();
		dealerPoints.setText("Dealer's Points: " + points);
		
		checkWinner(); 
		repaint();
	}
	
	//checks for winner
	public void checkWinner()
	{
		if (player.getPoints() > 21)
		{
			winner.setText("Sorry, you busted!");
			dealerGamesWon++;
		}
		else if (player.getPoints() == 21 && dealer.getPoints() == 21)
		{
			winner.setText("It's a tie! You both have 21!");
			playerGamesWon++;
			dealerGamesWon++;
		}
		else if (player.getPoints() < 21 && dealer.getPoints() > 21)
		{
			winner.setText("Congratulations, you won! The Dealer busted.");
			playerGamesWon++;
		}
		else if (player.getPoints() == 21 && dealer.getPoints() < 21)
		{
			winner.setText("Congratulations, you won! You got 21!");
			playerGamesWon++;
		}
		else if (player.getPoints() < 21 && dealer.getPoints() < 21)
		{
			if (player.getPoints() > dealer.getPoints())
			{
				winner.setText("Congratulations, you won!");
				playerGamesWon++;
			}
			else if (player.getPoints() < dealer.getPoints())
			{
				winner.setText("Sorry, the Dealer wins this one.");
				dealerGamesWon++;
			}
			else
			{
				winner.setText("It's a tie! You have the same value.");
				playerGamesWon++;
				dealerGamesWon++;
			}
		}
		
		//set ending to different values depending on number of games
		String dealerEnding = " games won.";
		if (dealerGamesWon == 1) dealerEnding = " game won.";
		else dealerEnding = " games won.";
		
		String playerEnding = " games won.";
		if (playerGamesWon == 1) playerEnding = " game won.";
		else playerEnding = " games won.";
		
		
		//update dealer's score
		dealerScore.setText("Dealer's Score: " + dealerGamesWon + dealerEnding);
		dealerScore.updateUI();
		
		
		//update player's score
		playerScore.setText("Player's Score: " + playerGamesWon + playerEnding);
		playerScore.updateUI();
		
		//redisable buttons for playing a game (
		//only allow quitting and dealing
		hit.setEnabled(false);
		stay.setEnabled(false);
		deal.setEnabled(true);
		quit.setEnabled(true);
		
		repaint();
	}
	
}