package actions;

import gameLib.main.Action;
import gameLib.main.Section;
import javafx.util.Pair;
import units.PlayerUnit;

public class SniperAtackAction implements Action {

	private static final long serialVersionUID = 8143113913987245114L;

	PlayerUnit owner;

	public SniperAtackAction(PlayerUnit owner) {
		this.owner = owner;
	}

	@Override
	public Pair<String, String> getActionInfo() {
		return new Pair<String, String>("Atack with your Sniper number " + owner.getUnitNumber(),
				"Your sniper will attack the fourth square upwards counting from his position");
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		Pair<Integer, Integer> ubication = owner.findUnit(table);
		try {
			if (!PlayerUnit.class
					.isAssignableFrom(table[ubication.getKey() + 3][ubication.getValue()].getUnitOnIt().getClass())) {
				table[ubication.getKey() + 3][ubication.getValue()].setUnitOnIt(null);
			}
		} catch (NullPointerException e) {
			// Do nothing
		}
		return table;
	}

}
