package actions;

import gameLib.main.Action;
import gameLib.main.Section;
import javafx.util.Pair;
import main.GameUpdater;
import units.Sniper;

public class CreateSniperAction implements Action {

	private static final long serialVersionUID = 7703500191092309389L;

	@Override
	public Pair<String, String> getActionInfo() {
		return new Pair<String, String>("Create Sniper", "Creates a Sniper unit on the lowest center section");
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		Sniper addSniper = new Sniper();
		table[0][4].setUnitOnIt(addSniper);
		GameUpdater.localUpdateList.add(addSniper);
		return table;
	}

}
