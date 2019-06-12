package units;

/*
 *Creado por Elias Periañez
 *28 may. 2019
 *Como parte del proyecto GameLib
 *Este archivo esta bajo la licencia de Creative Commons Reconocimiento 4.0 Internacional (Más informacion https://creativecommons.org/licenses/by/4.0/)
________________________________________________________________________________________________________________________________________________________
 *Created by Elias Periañez
 *28 may. 2019
 *As part of the project GameLib
 *This file is under the Creative Commons Attribution 4.0 International (More info here https://creativecommons.org/licenses/by/4.0/)
 */

import actions.EnemyAction;
import gameLib.main.Action;
import gameLib.main.Section;
import gameLib.main.Unit;
import javafx.util.Pair;

public class BasicEnemy implements Unit {

	private static final long serialVersionUID = 1808757688916214133L;

	Action atack;

	public BasicEnemy() {
		this.atack = new EnemyAction(this);
	}

	@Override
	public Action[] getActionsPerUnit() {
		return new Action[] { new EnemyAction(this) };
	}

	@Override
	public Action[] getActionsPerUnitType() {
		return new Action[] {};
	}

	@Override
	public char getSummary() {
		return 'X';
	}

	@Override
	public Pair<Integer, Integer> findUnit(Section[][] table) {
		Pair<Integer, Integer> result = null;
		for (int x = 0; x < table.length; x++) {
			for (int y = 0; y < table[x].length; y++) {
				if (table[x][y].getUnitOnIt() != null && table[x][y].getUnitOnIt().equals(this)) {
					result = new Pair<Integer, Integer>(x, y);
				}
			}
		}
		return result;
	}

}
