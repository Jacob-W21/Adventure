package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameData {
	public static Scanner reader;
	public static String line;
	File Data = new File("Data.yml");
	boolean exist = false;
	
	public enum PlayerGender {
		Undefined,
		Boy,
		Girl;
	}
	
	public enum GameState {
		Spawn,
		Cave,
		Town1;
	}
	
	PlayerGender player = PlayerGender.Boy;
	GameState state = GameState.Spawn;
	
	public GameData() {
		if(!Data.exists()) {
			createYml();
		} else {
			openYml();
			readYml();
			exist = true;
		}
	}
	
	public void createYml() {
			System.out.println("The Character.yml does not exists.");
			try {
				Data.createNewFile();
				System.out.println("The Character.yml has been created.");
				return;
			} catch (IOException e) {
				System.out.println("The Character.yml was unable to be created, Please try again.");
				e.printStackTrace();
			}
		}
	
	public void writeYml() {
		try {
			PrintWriter file = new PrintWriter(new FileWriter(Data, true));
			file.println(getState().ordinal());
			file.println(getGender().toString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openYml() {
		try {
			reader = new Scanner(new File("Data.yml"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readYml() {
		while(reader.hasNextLine()) {
			line = reader.nextLine();
			System.out.println("" + line);
			if(line.equalsIgnoreCase("boy")) {
				setGender(PlayerGender.Boy);
			} else if(line.equalsIgnoreCase("girl")) {
				setGender(PlayerGender.Girl);
			}
			if(line.equals("0")) {
				setState(GameState.Spawn);
			} else if(line.equals("1")) {
				setState(GameState.Cave);
			} else if(line.equals("2")) {
				setState(GameState.Town1);
			}
		}
	}
	
	public PlayerGender getGender() {
		return player;
	}
	
	public void setGender(PlayerGender playerGender) {
		this.player = playerGender;
	}
	
	public GameState getState() {
		return state;
	}
	
	public void setState(GameState gameState) {
		this.state = gameState;
	}
}
