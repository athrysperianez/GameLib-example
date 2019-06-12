package actions;

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

import gameLib.main.Action;
import gameLib.main.Section;
import gameLib.main.Unit;
import javafx.util.Pair;

public class EnemyAction implements Action {

	private static final long serialVersionUID = -5757941247278269589L;

	private Unit owner;

	public EnemyAction(Unit owner) {
		this.owner = owner;
	}

	@Override
	public Pair<String, String> getActionInfo() {
		return new Pair<String, String>("Generic enemy action", null);
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		Pair<Integer, Integer> ubication = this.owner.findUnit(table);
		if (ubication != null) {
			this.doAction(ubication.getKey() - 1, ubication.getValue(), table);
			this.doAction(ubication.getKey() + 1, ubication.getValue(), table);
			this.doAction(ubication.getKey(), ubication.getValue() - 1, table);
			this.doAction(ubication.getKey(), ubication.getValue() + 1, table);
		}
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
