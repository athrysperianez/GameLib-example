package main;

import gameLib.main.Game;
import gameLib.main.GameEndChecker;
import gameLib.main.Section;
import gameLib.main.Turn;
import units.BasicEnemy;

public class endChecker implements GameEndChecker {

	@Override
	public Turn checkGameEnded(Game game) {
		Turn result = game.getTurns().getKey();
		Section[][] table = game.getTable();
		for (int i = 0; i < table.length - 1; i++) {
			for (Section section : table[i]) {
				if(section.getUnitOnIt() instanceof BasicEnemy){
					result = null;
				}
			}
		}
		return result;
	}

}
