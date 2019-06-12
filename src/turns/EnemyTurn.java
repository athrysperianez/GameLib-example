package turns;

import gameLib.main.Action;
import gameLib.main.Menu;
import gameLib.main.Section;
import gameLib.main.Turn;
import gameLib.main.Unit;

public class EnemyTurn extends Turn {

	private static final long serialVersionUID = 2863423158102943838L;

	public EnemyTurn(Action[] possibleActions, Unit[] units, Menu menu) {
		super(possibleActions, units, menu);
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		for(Unit u : this.getTurnUnits()) {
			u.getActionsPerUnit()[0].onCall(table);
		}
		return table;
	}
}
