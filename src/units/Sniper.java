package units;

import actions.MoveAction;

/*
 *Creado por Elias Periañez
 *29 may. 2019
 *Como parte del proyecto GameLib
 *Este archivo esta bajo la licencia de Creative Commons Reconocimiento 4.0 Internacional (Más informacion https://creativecommons.org/licenses/by/4.0/)
________________________________________________________________________________________________________________________________________________________
 *Created by Elias Periañez
 *29 may. 2019
 *As part of the project GameLib
 *This file is under the Creative Commons Attribution 4.0 International (More info here https://creativecommons.org/licenses/by/4.0/)
 */

import actions.SniperAtackAction;
import gameLib.main.Action;
import gameLib.main.Section;
import javafx.util.Pair;

public class Sniper extends PlayerUnit {

	private static final long serialVersionUID = 2545113351434840171L;
	private static int unitCount = 0;

	private int unitNumber;

	public Sniper() {
		Sniper.unitCount++;
		this.unitNumber = unitCount;
	}

	@Override
	public int getUnitNumber() {
		return this.unitNumber;
	}

	@Override
	public Action[] getActionsPerUnit() {
		return new Action[] { new MoveAction(this), new SniperAtackAction(this) };
	}

	@Override
	public Action[] getActionsPerUnitType() {
		return new Action[] {};
	}

	@Override
	public char getSummary() {
		return '|';
	}

	@Override
	public Pair<Integer, Integer> findUnit(Section[][] table) {
		Pair<Integer, Integer> result = null;
		for (int x = 0; x < table[0].length; x++) {
			for (int y = 0; x < table[x].length; y++) {
				if (table[x][y].getUnitOnIt().equals(this)) {
					result = new Pair<Integer, Integer>(x, y);
				}
			}
		}
		return result;
	}

}
