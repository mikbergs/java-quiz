/*
*	Player.java
*	Author:	Mikael Bergström
*	Version: 0.1 2016-08-23
*/
public class Player {
	private String name;
	private int points;
	
	public Player(String name){
		this.name = name;
	}
	
	//Returns the player total points.
	public int getPoints(){ return points;}
	//Return the player name.
	public String getName(){return name;}
	
	public void addPoint(){
		points++;
	}
}


