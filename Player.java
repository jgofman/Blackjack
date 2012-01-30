//Jacob Gofman
//Project
//May 12th, 2011


public class Player {
	
	private static int totalPoints = 0;
	private Hand hand;
	
	public Player()
	{
		totalPoints = 0;
		setHand();
	}
	
	//deal card
	public Card dealCard()
	{
		Card card = Deck.dealCard();
		this.hand.addCard(card);
		return card;
	}
	
	//reset hand
	public void setHand()
	{
		hand = new Hand();
	}
	
	//get hand
	public Hand getHand()
	{
		return hand;
	}	

	//get and calculate points
	public int getPoints()
	{
		totalPoints = this.hand.calcPoints();
		return totalPoints;
	}
	
	//reset scores
	public void resetScore()
	{
		totalPoints = 0;
	}

}