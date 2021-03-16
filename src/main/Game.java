package main;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import maps.*;

public class Game extends StateBasedGame {
	
	public static final String gamename = "Adventure!";
	public static final int menu = 0;
	public static final int character = 1;
	public static final int spawn = 2;
	public static final int cave = 3;
	public static final int town1 = 4;
	public static GameData data;
	
	public Game(String gamename) {
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new CharacterSelection(character));
		this.addState(new Spawn(spawn));
		this.addState(new Cave(cave));
		this.addState(new Town1(town1));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(character).init(gc, this);
		this.getState(spawn).init(gc, this);
		this.getState(cave).init(gc, this);
		this.getState(town1).init(gc, this);
		this.enterState(menu);
	}

	public static void main(String[] args) {
		data = new GameData();
		data.createYml();
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(640, 360, false);
			appgc.start();
		}catch(SlickException e) {
			e.printStackTrace();
		}
	}

}
