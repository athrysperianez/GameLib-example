package units;

import actions.MoveAction;
import actions.SoldierAtackAction;
import gameLib.main.Action;
import gameLib.main.Section;
import javafx.util.Pair;

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

public class BasicSoldier extends PlayerUnit {

	private static final long serialVersionUID = -7994541106678361520L;
	private static int unitCount = 0;

	private int unitNumber;

	public BasicSoldier() {
		BasicSoldier.unitCount++;
		this.unitNumber = unitCount;
	}

	@Override
	public int getUnitNumber() {
		return this.unitNumber;
	}

	@Override
	public Action[] getActionsPerUnit() {
		return new Action[] { new MoveAction(this), new SoldierAtackAction(this) };
	}

	@Override
	public Action[] getActionsPerUnitType() {
		return new Action[] {};
	}

	@Override
	public char getSummary() {
		return 'O';
	}

	@Override
	public Pair<Integer, Integer> findUnit(Section[][] table) {
		Pair<Integer, Integer> result = null;
		for (int x = 0; x < table.length - 1; x++) {
			for (int y = 0; y < table[x].length - 1; y++) {
				try {
					if (table[x][y].getUnitOnIt().equals(this)) {
						result = new Pair<Integer, Integer>(y, x);
					}
				} catch (Exception e) {
					// Do nothing
				}
			}
		}
		return result;
	}

}
