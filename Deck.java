//Jacob Gofman
//Project
//May 12th, 2011


import java.util.Collections;
import java.util.Stack;


import javax.swing.ImageIcon;

public class Deck
{
	
	private static int index;
	private static Stack<Card> deck;
	final int SIZE = 104; //2 deck game
	private int cardsLeft;
	private static String[] suits = { "Diamonds", "Clubs", "Hearts", "Spades" };
	
	//create instance of deck
	public Deck()
	{
		deck = new Stack<Card>();
		index = 0;
	}
	
	//create a new deck (2 decks)
	public void createDeck()
	{
		int[] rankValue = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		String[] rankName = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace" };
		
		for (int h = 0; h < 2; h++) //for each deck..
		{
			for (int i = 0; i < 4; i++) //for each suit..
			{
				for (int j = 0; j < 13; j++) //for each card..
				{
					String imgFilename;
					if (rankName[j].equalsIgnoreCase("Ten")) imgFilename = "CardImages/" + "t" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("Jack")) imgFilename = "CardImages/" + "j" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("Queen")) imgFilename = "CardImages/" + "q" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("King")) imgFilename = "CardImages/" + "k" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("Ace")) imgFilename = "CardImages/" + "a" + suits[i].toLowerCase().charAt(0) + ".gif";
					else imgFilename = "CardImages/" + rankValue[j] + suits[i].toLowerCase().charAt(0) + ".gif";

					ImageIcon cardFace = new ImageIcon(imgFilename);

					Card card = new Card(suits[i], rankName[j], rankValue[j], cardFace);
					deck.push(card);
				}
			}
		}
	}
	
	//return suits array
	public String[] getSuits()
	{
		return suits;
	}
	
	//get amount of cards left in decks
	public int getCardsLeft()
	{
		cardsLeft = SIZE - index;
		return cardsLeft;
	}
	
	//return number for how far into the decks you are
	public int getIndex()
	{
		return index;
	}
	
	//returns the 2 decks as a stack
	public static Stack<Card> getDeck()
	{
		return deck;
	}
	
	//add card to decks
	public void appendCard(Card card)
	{
		deck.add(card);
	}
	
	//shuffle deck
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	
	//deal a card from the deck
	public static Card dealCard()
	{
		if (deck.size() >= 26) //if more than 26 cards left, use current deck
		{
			index++;
			return deck.pop();
		}
		else //if less than 26 cards left, create new deck and use that one
		{
			reCreateDeck();
			Collections.shuffle(deck);
			index = 0;
			return deck.pop();
		}
	}
	
	//recreate the deck (need static var to call from outside)
	public static void reCreateDeck()
	{
		int[] rankValue = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		String[] rankName = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace" };
		
		for (int h = 0; h < 2; h++) //for each of 2 decks
		{
			for (int i = 0; i < 4; i++) //for each card suit..
			{
				for (int j = 0; j < 13; j++) //for each card..
				{
					String imgFilename;
					if (rankName[j].equalsIgnoreCase("Ten")) imgFilename = "CardImages/" + "t" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("Jack")) imgFilename = "CardImages/" + "j" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("Queen")) imgFilename = "CardImages/" + "q" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("King")) imgFilename = "CardImages/" + "k" + suits[i].toLowerCase().charAt(0) + ".gif";
					else if (rankName[j].equalsIgnoreCase("Ace")) imgFilename = "CardImages/" + "a" + suits[i].toLowerCase().charAt(0) + ".gif";
					else imgFilename = "CardImages/" + rankValue[j] + suits[i].toLowerCase().charAt(0) + ".gif";

					ImageIcon cardFace = new ImageIcon(imgFilename); //establish image variable as card face

					Card card = new Card(suits[i], rankName[j], rankValue[j], cardFace); //create a card
					deck.push(card); //place card into deck

				}
			}
		}
	}
}