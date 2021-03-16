package maps;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import main.GameData.GameState;

public class Cave extends BasicGameState {
	
	Animation charDefault, charUp, charDown, charLeft, charRight, stillUp, stillDown, stillLeft, stillRight;
	Image stateMap;
	public Shape Boundries;
	boolean quit = false;
	int[] duration = {130, 130, 130, 130};
	float charPositionX = 80;
	float charPositionY = -262;
	float shiftX = 320;
	float shiftY = 160;
	float alpha = 0;
	public String a;
	boolean boy = false;
	boolean girl = false;
	Shape [] rock;

	
	public Cave(int state) {
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		String gender = Game.data.getGender().toString();
		Image[] stand = { new Image("res/Characters/" + gender + "/charBack.png"), new Image("res/Characters/" + gender + "/charBack.png"), new Image("res/Characters/" + gender + "/charBack.png"), new Image("res/Characters/" + gender + "/charBack.png") };
		Image[] walkUp = { new Image("res/Characters/" + gender + "/Up/Up1.png"), new Image("res/Characters/" + gender + "/Up/Up2.png"), new Image("res/Characters/" + gender + "/Up/Up3.png"), new Image("res/Characters/" + gender + "/Up/Up4.png") };
		Image[] walkDown = { new Image("res/Characters/" + gender + "/Down/Down1.png"), new Image("res/Characters/" + gender + "/Down/Down2.png"), new Image("res/Characters/" + gender + "/Down/Down3.png"), new Image("res/Characters/" + gender + "/Down/Down4.png") };
		Image[] walkLeft = { new Image("res/Characters/" + gender + "/Left/Left1.png"), new Image("res/Characters/" + gender + "/Left/Left2.png"), new Image("res/Characters/" + gender + "/Left/Left3.png"), new Image("res/Characters/" + gender + "/Left/Left4.png") };
		Image[] walkRight = { new Image("res/Characters/" + gender + "/Right/Right1.png"), new Image("res/Characters/" + gender + "/Right/Right2.png"), new Image("res/Characters/" + gender + "/Right/Right3.png"), new Image("res/Characters/" + gender + "/Right/Right4.png") };
		Image[] standUp = { new Image("res/Characters/" + gender + "/Up/Up1.png"), new Image("res/Characters/" + gender + "/Up/Up1.png"), new Image("res/Characters/" + gender + "/Up/Up1.png"), new Image("res/Characters/" + gender + "/Up/Up1.png") };
		Image[] standDown = { new Image("res/Characters/" + gender + "/Down/Down1.png"), new Image("res/Characters/" + gender + "/Down/Down1.png"), new Image("res/Characters/" + gender + "/Down/Down1.png"), new Image("res/Characters/" + gender + "/Down/Down1.png") };
		Image[] standLeft = { new Image("res/Characters/" + gender + "/Left/Left1.png"), new Image("res/Characters/" + gender + "/Left/Left1.png"), new Image("res/Characters/" + gender + "/Left/Left1.png"), new Image("res/Characters/" + gender + "/Left/Left1.png") };
		Image[] standRight = { new Image("res/Characters/" + gender + "/Right/Right1.png"), new Image("res/Characters/" + gender + "/Right/Right1.png"), new Image("res/Characters/" + gender + "/Right/Right1.png"), new Image("res/Characters/" + gender + "/Right/Right1.png") };

		charDefault = new Animation(stand, duration, false);
		charUp = new Animation(walkUp, duration, true);
		charDown = new Animation(walkDown, duration, true);
		charLeft = new Animation(walkLeft, duration, true);
		charRight = new Animation(walkRight, duration, true);
		stillUp = new Animation(standUp, duration, false);
		stillDown = new Animation(standDown, duration, false);
		stillLeft = new Animation(standLeft, duration, false);
		stillRight = new Animation(standRight, duration, false);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		stateMap = new Image("res/Maps/Cave.png");
		float[] Bpoints = new float[] { 279,90 , 235,90 , 235,-33 , 180,-33 , 180,90 , 11,90 , 11,0 , -105,0 , -105,90 , -165,108 , -185,108 , -210,90 , -210,0 , -249,0 , -249,-107 , -233,-130 , -220,-144 , -221,-227 , -248,-227 , -241,-253 , -172,-253 , -157,-191 , -45,-191 , -19,-224 , 12,-191 , 31,-201 , 37,-219 , 33,-240 , 51,-265 , 66,-272 , 96,-272 , 122,-244 , 129,-197 , 146,-193 , 172,-157 , 241,-157 , 248,-142 , 248,-109 , 269,-93 , 279,-90} ;
		Boundries = new Polygon(Bpoints);
		
		rock = new Circle[11];
		rock[0] = new Circle(250, 54, 19);
		rock[1] = new Circle(155, 58, 19);
		rock[2] = new Circle(122, -7, 19);
		rock[3] = new Circle(59, 22, 19);
		rock[4] = new Circle(186, -104, 19);
		rock[5] = new Circle(28, -69, 19);
		rock[6] = new Circle(-4, -136, 19);
		rock[7] = new Circle(-98, -70, 19);
		rock[8] = new Circle(-227, -41, 19);
		rock[9] = new Circle(-163, -165, 19);
		rock[10] = new Circle(-194, -234, 1);
	}	
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		stateMap.draw(charPositionX, charPositionY);
		charDefault.draw(shiftX, shiftY);
		g.drawString("Your X: " + charPositionX + "\nYour Y: " + charPositionY, 400, 20);
		
		if (gc.isPaused()) {
			Rectangle rect = new Rectangle(0, 0, 640, 360);
			g.setColor(new Color(0.2f, 0.2f, 0.2f, alpha));
			g.fill(rect);
			g.setColor(Color.white);
			g.drawString("Resume Game (R)", 240, 160);
			g.drawString("Quit Game (Q)", 250, 190);

			if (alpha < 0.5f)
				alpha += 0.01f;
		} else {
			if (alpha > 0)
				alpha -= 0.01f;
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
			if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W) && !gc.isPaused()) {
					charDefault = charUp;
				
				charPositionY += delta * .1f;

				//Boundries
				if(!Boundries.contains(charPositionX, charPositionY)) {
					charPositionY -= delta * .1f;
				}
				
				//Rocks
				for(int i = 0; i < 11; i++) {
				if(rock[i].contains(charPositionX, charPositionY)) {
					charPositionY -= delta * .1f;
				}
			}

				//Exit  Cave To Town1
				if(charPositionY >= 107 && (charPositionX < -161 && charPositionX > -185)) {
					Game.data.setState(GameState.Town1);
					sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
					try {
					    Thread.sleep(370);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					charDefault = stillUp;
				}
				
		} else {
			if (charDefault == charUp) {
				charDefault = stillUp;
			}	
		}

		if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S) && !gc.isPaused()) {
				charDefault = charDown;
			charPositionY -= delta * .1f;
			
			
			//Boundries
			if(!Boundries.contains(charPositionX, charPositionY)) {
				charPositionY += delta * .1f;
			}
			
			//Rocks
			for(int i = 0; i < 11; i++) {
			if(rock[i].contains(charPositionX, charPositionY)) {
				charPositionY += delta * .1f;
			}
		}
			
			//Exit Cave To Spawn
			if(charPositionY <= -271 && (charPositionX < 95 && charPositionX > 65)) {
				Game.data.setState(GameState.Spawn);
				sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
				try {
				    Thread.sleep(370);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				charDefault = stillUp;
			}

		} else {
			if (charDefault == charDown) {
				charDefault = stillDown;
			}
		}

		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A) && !gc.isPaused()) {
			charDefault = charLeft;
			
			charPositionX += delta * .1f;
			
			//Boundries
			if(!Boundries.contains(charPositionX, charPositionY)) {
				charPositionX -= delta * .1f;
			}
			
			//Rocks
			for(int i = 0; i < 11; i++) {
			if(rock[i].contains(charPositionX, charPositionY)) {
				charPositionX -= delta * .1f;
			}
		}

		} else {
			if (charDefault == charLeft) {
				charDefault = stillLeft;
			}
		}

		if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D) && !gc.isPaused()) {
			charDefault = charRight;
			
			charPositionX -= delta * .1f;

			//Boundries
			if(!Boundries.contains(charPositionX, charPositionY)) {
				charPositionX += delta * .1f;
			}
			
			//Rocks
			for(int i = 0; i < 11; i++) {
			if(rock[i].contains(charPositionX, charPositionY)) {
				charPositionX += delta * .1f;
			}
		}

		} else {
			if (charDefault == charRight) {
				charDefault = stillRight;
			}
		}

		// Quit
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.setPaused(!gc.isPaused());
			Game.data.writeYml();
		}

		if (gc.isPaused()) {
			if (input.isKeyPressed(Input.KEY_ESCAPE)) {
				gc.setPaused(false);
			}

			if (input.isKeyPressed(Input.KEY_R)) {
				gc.setPaused(false);
			}

			if (input.isKeyPressed(Input.KEY_Q)) {
				sbg.enterState(0);
			}
		}
	}
	
	public int getID() {
		return 3;
	}
}
