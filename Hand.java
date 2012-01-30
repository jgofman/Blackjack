//Jacob Gofman
//Project
//May 12th, 2011


import java.util.Stack;

public class Hand {

	private int numCards;
	private int numAces;
	private int totalPoints;
	private Stack<Card> cards;
	private boolean aceExists;
	
	//constructor to create instance of hand
	public Hand()
	{
		cards = new Stack<Card>();
		numCards = 0;
		numAces = 0;
		totalPoints = 0;
	}
	
	//calculate total points of all cards in hand
	public int calcPoints()
	{
		totalPoints = 0;
		numAces = 0;
		int tempPoints = 0;
		int newVal = 0;
		
		if (aceExists)
		{
			for (int i = 0; i < cards.size(); i++)
			{
				if (cards.elementAt(i).getSymbol() == 'A' || cards.elementAt(i).getSymbol() == 'a') numAces++;
				else if (cards.elementAt(i).getSymbol() != 'A' && cards.elementAt(i).getSymbol() != 'a') tempPoints += cards.elementAt(i).getRank();
			}
			
			if (tempPoints + numAces * 11 > 21) newVal = 1;
			else newVal = 11;
			
			for (int i = 0; i < cards.size(); i++)
			{
				if (cards.elementAt(i).getSymbol() == 'A') cards.elementAt(i).setRank(newVal);
				totalPoints += cards.elementAt(i).getRank();
			}
		}
		else
		{
			for (int i = 0; i < cards.size(); i++)
			{
				totalPoints += cards.elementAt(i).getRank();
			}
		}
		
		return totalPoints;
	}
	
	//return stack of cards in hand
	public Stack<Card> getCards()
	{
		return cards;
	}
	
	//get number of cards in hand
	public int getNumCards()
	{
		numCards = cards.size();
		return numCards;
	}
	
	//add card to hand and calculate points
	public void addCard(Card card)
	{
		cards.add(card);
		if (card.getSymbol() == 'A') aceExists = true;
		calcPoints();
	}
	
}