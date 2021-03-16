package maps;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import main.Game;

public class Town1 extends BasicGameState {
	
	private TiledMap stateMap;
	private Animation Player, down, left, right, up, Sdown, Sleft, Sright, Sup;
	
	private float charPositionX = 270;
	private float charPositionY = -621;
	private float shiftX = 320;
	private float shiftY = 160;
	float alpha = 0;
	//private int mapX, mapY;
	Rectangle player;
	Shape [] tree;


	public Town1(int town1) {
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		String gender = Game.data.getGender().toString();
		Image Down = new Image("res/Characters/" + gender + "/Down/DSheet.png");
		Image Left = new Image("res/Characters/" + gender + "/Left/LSheet.png");
		Image Right = new Image("res/Characters/" + gender + "/Right/RSheet.png");
		Image Up = new Image("res/Characters/" + gender + "/Up/UpSheet.png");
		
		//Default
		Player = getAnimation(Up, 1, 1, 32, 48, 1, 140);
				
		//Moving
		down = getAnimation(Down, 4, 1, 32, 48, 4, 140);
		left = getAnimation(Left, 4, 1, 32, 48, 4, 140);
		right = getAnimation(Right, 4, 1, 32, 48, 4, 140);
		up = getAnimation(Up, 4, 1, 32, 48, 4, 140);
				
		//Still
		Sdown = getAnimation(Down, 1, 1, 32, 48, 1, 140);
		Sleft = getAnimation(Left, 1, 1, 32, 48, 1, 140);
		Sright = getAnimation(Right, 1, 1, 32, 48, 1, 140);
		Sup = getAnimation(Up, 1, 1, 32, 48, 1, 140);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		stateMap = new TiledMap("res/Maps/Town1.tmx");
		
		tree = new Rectangle[2];
		tree[0] = new Rectangle(-33, -562, 60, 30);
		tree[1] = new Rectangle(-190, -689, 60, 30);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		player = new Rectangle(shiftX, shiftY, 32, 48);
	    int background = stateMap.getLayerIndex("background");
	    int cave = stateMap.getLayerIndex("cave");
	    int water = stateMap.getLayerIndex("water");
	    int bridge = stateMap.getLayerIndex("bridge");
	    int collision = stateMap.getLayerIndex("collision");
	    int misc = stateMap.getLayerIndex("misc");
	    int tree = stateMap.getLayerIndex("tree");
	    stateMap.render((int)charPositionX, (int)charPositionY, background);
	    stateMap.render((int)charPositionX, (int)charPositionY, cave);
	    stateMap.render((int)charPositionX, (int)charPositionY, water);
	    stateMap.render((int)charPositionX, (int)charPositionY, bridge);
	    stateMap.render((int)charPositionX, (int)charPositionY, collision);
	    stateMap.render((int)charPositionX, (int)charPositionY, misc);
		Player.draw(shiftX, shiftY);
		stateMap.render((int)charPositionX, (int)charPositionY, tree);
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
		
		
		//map.render((int)x - 32, (int)y - 32, mapX, mapY, mapX + 31, mapY + 25);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_W)) {
			Player = up;
			up.update(delta);
			charPositionY += delta * .1f;
			
			//Tree Base
			for(int i = 0; i < 2; i++) {
				if(tree[i].contains(charPositionX, charPositionY)) {
					charPositionY -= delta * .1f;
				}
			}
			
		} else {
			if(Player == up) {
				Player = Sup;
			}
		}
		
		if (input.isKeyDown(Input.KEY_A)) {
			Player = left;
			left.update(delta);
			charPositionX += delta * .1f;
			
			//Tree Base
			for(int i = 0; i < 2; i++) {
				if(tree[i].contains(charPositionX, charPositionY)) {
					charPositionX -= delta * .1f;
				}
			}
			
		} else {
			if(Player == left) {
				Player = Sleft;
			}
		}
		
		if (input.isKeyDown(Input.KEY_S)) {
			Player = down;
			down.update(delta);
			charPositionY -= delta * .1f;
			
			//Tree Base
			for(int i = 0; i < 2; i++) {
				if(tree[i].contains(charPositionX, charPositionY)) {
					charPositionY += delta * .1f;
				}
			}
			
		} else {
			if(Player == down) {
				Player = Sdown;
			}
		}
		
		if (input.isKeyDown(Input.KEY_D)) {
			Player = right;
			right.update(delta);
			charPositionX -= delta * .1f;
			
			//Tree Base
			for(int i = 0; i < 2; i++) {
				if(tree[i].contains(charPositionX, charPositionY)) {
					charPositionX += delta * .1f;
				}
			}
			
		} else {
			if(Player == right) {
				Player = Sright;
			}
		}
		
		//if(x < 0) {
			//mapX ++;
			//x = 32;
		//}
		
		//if(x > 32) {
			//mapX --;
			//x = 0;
		//}
		
		//if(y < 0) {
			//mapY ++;
			//y = 32;
		//}
		
		//if(y > 32) {
			//mapY --;
			//y = 0;
		//}
		
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
	
	public Animation getAnimation(Image Bsheet, int spritesX, int spritesY, int spriteWidth , int spriteHeight , int frames, int duration) {
		Animation a = new Animation(false);
		
		for(int y = 0; y < spritesY; y++) {
			for(int x = 0; x < spritesX; x++) {
				a.addFrame(Bsheet.getSubImage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight), duration);
			}
		}
		
		return a;
	}
	
	public int getID() {
		return 4;
	}

}
