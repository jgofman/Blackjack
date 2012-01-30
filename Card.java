//Jacob Gofman
//Project
//May 12th, 2011


import javax.swing.ImageIcon;

public class Card
{
	private String suit;
	private char rankSymbol;
	private String rank;
	private int rankValue;
	private String name;
	private ImageIcon imgFace;
	private ImageIcon imgBack;
	private String imgFilename;
	
	//create instance of a card
	public Card(String suit, String rank, int rankValue, ImageIcon cardFace)
	{
		this.suit = suit;
		this.rank = rank;
		this.rankSymbol = rank.charAt(0);
		this.rankValue = rankValue;
		this.imgFace = cardFace;
		ImageIcon cardBack = new ImageIcon("CardImages/back.gif");
		this.imgBack = cardBack;
		name = rank + " of " + suit;
		imgFilename = "CardImages/" + rankValue + suit.toLowerCase().charAt(0) + ".gif";
	}

	//get full name of card as a string
	public String getName()
	{
		return name;
	}
	
	//get card suit
	public String getSuit()
	{
		return suit;
	}
	
	//get card symbol
	public char getSymbol()
	{
		return rankSymbol;
	}
	
	//get card rank
	public int getRank()
	{
		return rankValue;
	}
	
	//get rank as a string
	public String getRankName()
	{
		return rank;
	}
	
	//get card face image
	public ImageIcon getFace()
	{
		return imgFace;
	}
	
	//get card back image
	public ImageIcon getBack()
	{
		return imgBack;
	}
	
	//get filename for image locations
	public String getFilename()
	{
		return imgFilename;
	}
	
	//set card rank
	public int setRank(int val)
	{
		return this.rankValue = val;
	}
	
}