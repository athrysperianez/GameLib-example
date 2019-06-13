package actions;

import gameLib.main.Action;
import gameLib.main.Section;
import gameLib.main.Updater;
import javafx.util.Pair;
import main.GameUpdater;
import units.BasicSoldier;

public class CreateSoldierAction implements Action {

	private static final long serialVersionUID = -2354739353798581479L;

	@Override
	public Pair<String, String> getActionInfo() {
		return new Pair<String, String>("Create a basic soldier",
				"Creates a basic soldier unit on the lowest center section");
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		BasicSoldier addSoldier = new BasicSoldier();
		table[0][4].setUnitOnIt(addSoldier);
		GameUpdater.localUpdateList.add(addSoldier);
		return table;
	}

}
