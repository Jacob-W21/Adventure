package main;

import java.util.Random;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import main.GameData.GameState;


public class Menu extends BasicGameState {
	
	public static String mouse = "No input yet";
	public Random song = new Random();
	public int number;
	public Music[] theme = new Music[3];
	Image playNow;
	Image exitGame;
	Image resume;
	
public void rSong() {
		
		for(int counter = 1; counter <=1; counter++) {
			number = 1 + song.nextInt(3);
			
			if(number == 1) {
				try {
					theme[0] = new Music("res/Songs/theme1.ogg", true);
				    theme[0].loop(1, 0.4f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(number == 2) {
				try {
					theme[1] = new Music("res/Songs/theme2.ogg" , true);
				    theme[1].loop(1, 0.4f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(number == 3) {
				try {
					theme[2] = new Music("res/Songs/theme3.ogg" , true);
				    theme[2].loop(1, 0.4f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }
	}
	
	public Menu(int state) {
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
		resume = new Image("res/resume.png");
		rSong();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gM) throws SlickException {
		gM.setBackground(Color.black);
		gM.drawString(mouse, 50, 50);
		gM.drawString("Are You Ready for An Adventure?", 190, 100);
		playNow.draw(100, 150);
		exitGame.draw(370, 150);
		if(Game.data.exist == true) {
			resume.draw(225,220);	
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		mouse = "Mouse position X: " + posX + " Y: " + posY;
		//Play
		if((posX > 100 && posX < 300) && (posY > 170 && posY < 211)) {
			if(Mouse.isButtonDown(0)) {
				if(Game.data.exist) {
					return;
				} else {
				sbg.enterState(1);
				if(number == 1) { theme[0].fade(1000, 0f, true); }
				if(number == 2) { theme[1].fade(1000, 0f, true); }
				if(number == 3) { theme[2].fade(1000, 0f, true); }
				}
			}
		}
		//Exit
		if((posX > 370 && posX < 570) && (posY > 170 && posY < 211)) {
			if(Mouse.isButtonDown(0)) {
				System.exit(0);
			}
		}
		//Resume
		if((posX > 230 && posX < 430) && (posY > 100 && posY < 140)) {
			if(Mouse.isButtonDown(0)) {
				Game.data.openYml();
				Game.data.readYml();
				if(Game.data.getState().ordinal() == 0) {
					Game.data.setState(GameState.Spawn);
					sbg.enterState(2);
				} else if(Game.data.getState().ordinal() == 1) {
					Game.data.setState(GameState.Cave);
					sbg.enterState(3);
				} else if(Game.data.getState().ordinal() == 2) {
					Game.data.setState(GameState.Town1);
					sbg.enterState(4);
				}
				if(number == 1) { theme[0].fade(1000, 0f, true); }
				if(number == 2) { theme[1].fade(1000, 0f, true); }
				if(number == 3) { theme[2].fade(1000, 0f, true); }	
				gc.setPaused(false);
				}
			}
	}
	
	public int getID() {
		return 0;
	}
}
