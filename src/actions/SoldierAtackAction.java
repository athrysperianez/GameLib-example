package actions;

/*
 *Creado por Elias Periañez
 *30 may. 2019
 *Como parte del proyecto GameLib
 *Este archivo esta bajo la licencia de Creative Commons Reconocimiento 4.0 Internacional (Más informacion https://creativecommons.org/licenses/by/4.0/)
________________________________________________________________________________________________________________________________________________________
 *Created by Elias Periañez
 *30 may. 2019
 *As part of the project GameLib
 *This file is under the Creative Commons Attribution 4.0 International (More info here https://creativecommons.org/licenses/by/4.0/)
 */

import gameLib.main.Action;
import gameLib.main.Section;
import javafx.util.Pair;
import units.PlayerUnit;

public class SoldierAtackAction implements Action {

	private static final long serialVersionUID = -4189400193648434059L;

	private PlayerUnit owner;

	public SoldierAtackAction(PlayerUnit owner) {
		this.owner = owner;
	}

	@Override
	public Pair<String, String> getActionInfo() {
		return new Pair<String, String>("Atack with your Soldier number " + owner.getUnitNumber(),
				"Your Soldier will atack the 4 adjacents sections");
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		Pair<Integer, Integer> ubication = this.owner.findUnit(table);
		this.doAction(ubication.getKey() - 1, ubication.getValue(), table);
		this.doAction(ubication.getKey() + 1, ubication.getValue(), table);
		this.doAction(ubication.getKey(), ubication.getValue() - 1, table);
		this.doAction(ubication.getKey(), ubication.getValue() + 1, table);
		return table;
	}

	private void doAction(int first, int second, Section[][] table) {
		try {
			table[first][second].overrideUnitOnIt(null);
		} catch (IndexOutOfBoundsException e) {
			// Do nothing
		}
	}
}
