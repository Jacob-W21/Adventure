package main;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.GameData.PlayerGender;

public class CharacterSelection extends BasicGameState {
	
	Image Boy, Girl;
	boolean boy = false;
	boolean girl = false;

	public CharacterSelection(int character) {
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Boy = new Image("res/Characters/Boy/BcharFront.png");
		Girl = new Image("res/Characters/Girl/GcharFront.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics gCS) throws SlickException {
		gCS.setBackground(new Color(153, 0 , 0));
		BoG(gCS);
		
		if(boy) {
			gCS.drawString("Are you sure?", 205, 180);
			gCS.drawString("[Y] [N]", 200, 200);
			if(gc.getInput().isKeyPressed(Input.KEY_Y)) {
				Game.data.setGender(PlayerGender.Boy);
				sbg.enterState(2);
				boy = false;
				girl = false;
				}
			} else if(gc.getInput().isKeyPressed(Input.KEY_N)) {
				boy = false;
			}
		
		if(girl) {
			gCS.drawString("Are you sure?", 365, 180);
			gCS.drawString("[Y] [N]", 360, 200);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		//For Boy
		if(input.isKeyPressed(Input.KEY_B)) {
			if(!boy && !girl) {
				boy = true;
			}
			if(girl) {
				return;
			}
		}
		//For Girl
		if(input.isKeyPressed(Input.KEY_G)) {
			if(!girl && !boy) {
				girl = true;
			} else if(girl) {
				if(input.isKeyPressed(Input.KEY_Y)) {
					Game.data.setGender(PlayerGender.Girl);
				} else if(input.isKeyPressed(Input.KEY_N)) {
					girl = false;
				}
			}
		}
	}
	
	public void BoG(Graphics gCS) {
		gCS.drawString("Are You:", 282, 20);
		
		gCS.drawString("A Boy?", 215, 60);
		gCS.drawString("(Press B)", 200, 80);
		gCS.drawImage(Boy, 225, 110);
		
		gCS.drawString("Or", 305, 60);
		
		gCS.drawString("A Girl?", 370, 60);
		gCS.drawString("(Press G)", 360, 80);
		gCS.drawImage(Girl, 390, 110);
	}
	
	public int getID() {
		return 1;
	}

}


