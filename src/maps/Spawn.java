package maps;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import main.GameData.GameState;

public class Spawn extends BasicGameState {

	Animation charDefault, charUp, charDown, charLeft, charRight, stillUp, stillDown, stillLeft, stillRight;
	Image stateMap;
	public Shape lake;
	public Shape cave;
	boolean quit = false;
	int[] duration = { 130, 130, 130, 130 };
	float charPositionX = 0;
	float charPositionY = 0;
	float shiftX = charPositionX + 320;
	float shiftY = charPositionY + 160;
	float alpha = 0;
	public String a;

	public Spawn(int state) {
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		String gender = Game.data.getGender().toString();
		Image[] stand = { new Image("res/Characters/" + gender + "/charFront.png"), new Image("res/Characters/" + gender + "/charFront.png"), new Image("res/Characters/" + gender + "/charFront.png"), new Image("res/Characters/" + gender + "/charFront.png") };
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
		stateMap = new Image("res/Maps/Spawn.png");
		float[] Lpoints = new float[] { 322, -174, 237, -174, 204, -208, 174, -171, 96, -172, 80, -144, 6, -138, 16, -41, 21, 5, -11, 28, -11, 43, -3, 69, 21, 78, 32, 100, 50, 107, 50, 122, 52, 179, 66, 198, 311, 198, 329, 180, 329, 106, 298, 77, 302, 44, 327, 10, 327, -88, 300, -112, 327, -142 };
		lake = new Polygon(Lpoints);
		
		float[] Cpoints = new float[] { -56, 198, -56, 34, -78, 34, -94, 0, -98, -20, -127, -17, -124, 7, -148, 24, -193, 31, -197, 44, -205, 44, -215, 44, -231, 31, -301, 31, -301, 205 };
		cave = new Polygon(Cpoints);
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
				
				if (charPositionY > 162) {
					charPositionY -= delta * .1f;
					}
				// Lake
				if (lake.contains(charPositionX, charPositionY)) {
					charPositionY -= delta * .1f;
					}

			// Cave
			if (cave.contains(charPositionX, charPositionY)) {
				charPositionY -= delta * .1f;
			}

			// Enter Cave
			if (charPositionY >= 43 && (charPositionX < -197 && charPositionX > -215)) {
				Game.data.setState(GameState.Cave);
				sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
				try {
					Thread.sleep(420);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				charDefault = stillDown;
				}
			} else {
				if (charDefault == charUp) {
					charDefault = stillUp;
				}
			}

		if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S) && !gc.isPaused()) {
			charDefault = charDown;
			charPositionY -= delta * .1f;

			if (charPositionY < -271) {
				charPositionY += delta * .1f;
			}

			// Lake
			if (lake.contains(charPositionX, charPositionY)) {
				charPositionY += delta * .1f;
			}

			// Cave
			if (cave.contains(charPositionX, charPositionY)) {
				charPositionY += delta * .1f;
			}

		} else {
			if (charDefault == charDown) {
				charDefault = stillDown;
			}
		}

		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A) && !gc.isPaused()) {
			charDefault = charLeft;
			charPositionX += delta * .1f;

			if (charPositionX > 323) {
				charPositionX -= delta * .1f;
			}

			// Lake
			if (lake.contains(charPositionX, charPositionY)) {
				charPositionX -= delta * .1f;
			}

			// Cave
			if (cave.contains(charPositionX, charPositionY)) {
				charPositionX -= delta * .1f;
			}

		} else {
			if (charDefault == charLeft) {
				charDefault = stillLeft;
			}
		}

		if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D) && !gc.isPaused()) {
			charDefault = charRight;
			
			charPositionX -= delta * .1f;

			if (charPositionX < -292) {
				charPositionX += delta * .1f;
			}

			// Lake
			if (lake.contains(charPositionX, charPositionY)) {
				charPositionX += delta * .1f;
			}

			// Cave
			if (cave.contains(charPositionX, charPositionY)) {
				charPositionX += delta * .1f;
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
		return 2;
	}

}
