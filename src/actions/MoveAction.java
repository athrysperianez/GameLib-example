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

import java.util.HashMap;
import java.util.Scanner;

import gameLib.main.Action;
import gameLib.main.Section;
import javafx.util.Pair;
import units.PlayerUnit;

public class MoveAction implements Action {

	private static final long serialVersionUID = 729437748657204163L;
	private static final int DISTANCE = 1;

	private PlayerUnit owner;

	public MoveAction(PlayerUnit owner) {
		this.owner = owner;
	}

	@Override
	public Pair<String, String> getActionInfo() {
		return new Pair<String, String>(
				"Move your " + owner.getClass().getSimpleName() + " number " + owner.getUnitNumber(), null);
	}

	@Override
	public Section[][] onCall(Section[][] table) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose direction to move " + DISTANCE + " section:");
		Pair<Integer, Integer> ubication = owner.findUnit(table);
		int i = 1;
		HashMap<Integer, Pair<Integer, Integer>> tmpHs = new HashMap<Integer, Pair<Integer, Integer>>();

		Pair<Integer, Integer> tmp = new Pair<Integer, Integer>(ubication.getKey(), ubication.getValue() - 1);
		if (checkExist(tmp, table)) {
			System.out.println(i + ".Up");
			tmpHs.put(i, tmp);
			i++;
		}

		tmp = new Pair<Integer, Integer>(ubication.getKey() + 1, ubication.getValue());
		if (checkExist(tmp, table)) {
			System.out.println(i + ".Right");
			tmpHs.put(i, tmp);
			i++;
		}

		tmp = new Pair<Integer, Integer>(ubication.getKey(), ubication.getValue() + 1);
		if (checkExist(tmp, table)) {
			System.out.println(i + ".Down");
			tmpHs.put(i, tmp);
			i++;
		}

		tmp = (new Pair<Integer, Integer>(ubication.getKey() - 1, ubication.getValue()));
		if (checkExist(tmp, table)) {
			System.out.println(i + ".Left");
			tmpHs.put(i, tmp);
			i++;
		}

		boolean check = false;
		int choose = -1;
		do {
			try {
				choose = sc.nextInt();
				if (choose > 0 && choose < 5) {
					check = true;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("No number or out of bounds number, try again");
			}
		} while (!check);
		tmp = tmpHs.get(choose);

		if (table[tmp.getValue()][tmp.getKey()].getUnitOnIt() == null) {
			table[ubication.getValue()][ubication.getKey()].overrideUnitOnIt(null);
			table[tmp.getValue()][tmp.getKey()].setUnitOnIt(owner);
		} else {
			System.out.println("Couldn´t move, that section is already in use");
		}
		return table;
	}

	private boolean checkExist(Pair<Integer, Integer> ubication, Section[][] table) {
		boolean result = true;
		try {
			Section test = table[ubication.getKey()][ubication.getValue()];
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

}
