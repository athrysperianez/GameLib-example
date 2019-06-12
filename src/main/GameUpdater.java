package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import gameLib.main.Game;
import gameLib.main.Turn;
import gameLib.main.Unit;
import gameLib.main.Updater;
import turns.PlayerTurn;

/*
 *Creado por Elias Periañez
 *12 jun. 2019
 *Como parte del proyecto GameLib
 *Este archivo esta bajo la licencia de Creative Commons Reconocimiento 4.0 Internacional (Más informacion https://creativecommons.org/licenses/by/4.0/)
________________________________________________________________________________________________________________________________________________________
 *Created by Elias Periañez
 *27 jun. 2019
 *As part of the project GameLib
 *This file is under the Creative Commons Attribution 4.0 International (More info here https://creativecommons.org/licenses/by/4.0/)
 */

public class GameUpdater implements Updater {

	List<Unit> localUpdateList = new LinkedList<>();

	public void addUnitToUpdate(Unit unit) {
		localUpdateList.add(unit);
	}

	@Override
	public void update(Turn turn, Game game) {
		if (turn instanceof PlayerTurn) {
			List<Unit> tmpList = new LinkedList<Unit>(Arrays.asList(turn.getTurnUnits()));
			for (Unit current : tmpList) {
				if (current.findUnit(game.getTable()) == null) {
					tmpList.remove(current);
				}
			}

			for (Unit current : localUpdateList) {
				tmpList.add(current);
				this.localUpdateList.remove(current);
			}

			turn.setTurnUnits((Unit[]) tmpList.toArray(new Unit[tmpList.size()]));
		}
	}

}
