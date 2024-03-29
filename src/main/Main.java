package main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import actions.CreateSniperAction;
import actions.CreateSoldierAction;
import gameLib.main.Action;
import gameLib.main.Game;
import gameLib.main.GameType;
import gameLib.main.Menu;
import gameLib.main.Menu.MenuLayer;
import gameLib.main.MenuLayerType;
import gameLib.main.Section;
import gameLib.main.Turn;
import gameLib.main.Unit;
import javafx.util.Pair;
import sections.BasicSection;
import turns.EnemyTurn;
import turns.PlayerTurn;
import units.BasicEnemy;
import units.BasicSoldier;

/*
 *Creado por Elias Peria�ez
 *27 may. 2019
 *Como parte del proyecto GameLib
 *Este archivo esta bajo la licencia de Creative Commons Reconocimiento 4.0 Internacional (M�s informacion https://creativecommons.org/licenses/by/4.0/)
________________________________________________________________________________________________________________________________________________________
 *Created by Elias Peria�ez
 *27 may. 2019
 *As part of the project GameLib
 *This file is under the Creative Commons Attribution 4.0 International (More info here https://creativecommons.org/licenses/by/4.0/)
 */

public class Main {

	public static void main(String[] args) {

		Properties properties = new Properties();

		try {

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream("config/strings.dat");
			properties.load(stream);

		} catch (Exception e) {

			e.printStackTrace();

		}

		HashMap<String, String> stringData = new HashMap<String, String>();

		for (String name : properties.stringPropertyNames()) {
			stringData.put(name, properties.getProperty(name));
		}

		Section[][] table = new Section[10][10];
		int enemyCount = 0;
		Random rand = new Random();
		int enemyMax = rand.nextInt(100);
		enemyMax++;
		// TODO Debug
		/**
		 * enemyMax = Integer.MAX_VALUE; System.out.println(enemyMax);
		 **/
		boolean lastWasEnemy = false;
		ArrayList<Unit> tmpEnemy = new ArrayList<Unit>();
		for (int x = table.length - 1; x > -1; x--) {
			for (int y = table.length - 1; y > -1; y--) {
				table[x][y] = new BasicSection();
				if (!lastWasEnemy && rand.nextBoolean() && enemyCount <= enemyMax && validEnemyPoint(x, y)) {
					table[x][y].setUnitOnIt(new BasicEnemy());
					tmpEnemy.add(table[x][y].getUnitOnIt());
					enemyCount++;
					lastWasEnemy = true;
				} else {
					lastWasEnemy = false;
				}
				// // TODO Debug
				// System.out.println("Casilla: " + x + "-" + y);
				// Unit unidad = table[x][y].getUnitOnIt();
				// if (unidad != null) {
				// System.out.println("Unidad: " + unidad.getSummary() + " referencia: " +
				// unidad);
				// }
			}
		}
		table[1][4].overrideUnitOnIt(new BasicSoldier());

		Menu playerMenu = new Menu();
		MenuLayer menuLayerMainChose = Menu.layerFactory(MenuLayerType.CHOICE,
				new Action[] { new CreateSniperAction(), new CreateSoldierAction() }, null);
		MenuLayer menuLayerMainPrint = Menu.layerFactory(MenuLayerType.PROMPT, null, stringData);
		playerMenu.setLayers(new MenuLayer[] { menuLayerMainChose, menuLayerMainPrint });
		Menu startMenu = new Menu();

		Game game = new Game("GameLib Game", table,
				new Pair<Turn, Turn>(
						new PlayerTurn(new Action[] { new CreateSoldierAction(), new CreateSniperAction() },
								new Unit[] { table[1][4].getUnitOnIt() }, playerMenu),
						new EnemyTurn(new Action[] {}, tmpEnemy.toArray(new Unit[tmpEnemy.size()]), new Menu())),
				GameType.SINGLEPLAYER);
		game.setDebug(false);
		// TODO
		game.startGame(new endChecker(), startMenu, true, new GameUpdater());
	}

	private static boolean validEnemyPoint(int x, int y) {
		return x != 0 && (x != 2 || y != 4) && (x != 1 || y != 3) && (x != 1 || y != 5) && (x != 1 || y != 4);
	}

}
