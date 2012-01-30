//Jacob Gofman
//Project
//May 12th, 2011


public class Game
{
	
	private Player player;
	private Dealer dealer;
	private Deck deck;
	
	//initiate instance of game
	public Game(Player p, Dealer p2, Deck d)
	{		
		d.createDeck();
		d.shuffle();
		
		player = p;
		dealer = p2;
		deck = d;
	}
	
	//returns player
	public Player getPlayer()
	{
		return player;
	}

	//returns dealer
	public Dealer getDealer()
	{
		return dealer;
	}
	
	//retirns deck
	public Deck getDeck()
	{
		return deck;
	}
	
}
