package turns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import gameLib.main.Action;
import gameLib.main.Menu;
import gameLib.main.Section;
import gameLib.main.Turn;
import gameLib.main.Unit;
import javafx.util.Pair;

public class PlayerTurn extends Turn {

	private static final long serialVersionUID = 6904518500434787161L;

	public PlayerTurn(Action[] possibleActions, Unit[] units, Menu menu) {
		super(possibleActions, units, menu);
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		HashMap<Integer, Action> selector = new HashMap<Integer, Action>();
		int i = 1;
		for (Action current : this.getPossibleActions()) {
			Pair<String, String> actionInfo = current.getActionInfo();
			if (actionInfo.getValue() == null) {
				System.out.println(i + "." + actionInfo.getKey() + "\n");
			} else {
				System.out.println(i + "." + actionInfo.getKey() + "\n    " + actionInfo.getValue() + "\n");
			}
			selector.put(i, current);
			i++;
		}

		for (Unit currentUnit : this.getTurnUnits()) {
			for (Action current : currentUnit.getActionsPerUnit()) {
				Pair<String, String> actionInfo = current.getActionInfo();
				if (actionInfo.getValue() == null) {
					System.out.println(i + "." + actionInfo.getKey() + "\n");
				} else {
					System.out.println(i + "." + actionInfo.getKey() + "\n" + actionInfo.getValue() + "\n");
				}
				selector.put(i, current);
				i++;
			}

			ArrayList<Class> tmpClass = new ArrayList<Class>();
			for (Action current : currentUnit.getActionsPerUnitType()) {
				if (tmpClass.contains(current.getClass())) {
					Pair<String, String> actionInfo = current.getActionInfo();
					if (actionInfo.getValue() == null) {
						System.out.println(i + "." + actionInfo.getKey() + "\n");
					} else {
						System.out.println(i + "." + actionInfo.getKey() + "\n" + actionInfo.getValue() + "\n");
					}
					selector.put(i, current);
					i++;
					tmpClass.add(current.getClass());
				}
			}
		}

		Scanner sc = new Scanner(System.in);
		Action selected = null;
		boolean check = true;
		do {
			try {
				selected = selector.get(sc.nextInt());
				if (selected == null) {
					throw new Exception();
				} else {
					check = true;
				}

			} catch (Exception e) {
				check = false;
			}
		} while (!check);
		selected.onCall(table);
		return table;
	}
}
