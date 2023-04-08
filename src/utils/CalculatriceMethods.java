
package utils;

import java.util.ArrayList;

import exceptions.ExceptionHandler;
import javafx.scene.control.Label;

import models.Calculs;
import models.History;

public class CalculatriceMethods {

	public static void saveHistory(float numberA, String operator, float numberB, float result) {
		History.getHistoryCalculs().add(new Calculs(numberA, operator, numberB, result));
	}

	public static void displayHistory(ArrayList<Calculs> list, Label area) {
		if (list.size() == 0) {
			ExceptionHandler.handleError("EmptyHistory-exception");
		} else {
			area.setText("*** Historique ***");
			for (Calculs calcul : list) {
				area.setText(calcul.toString());
			}
		}
	}

	public static float addition(float numberA, float numberB) {
		return numberA + numberB;
	}

	public static float soustraction(float numberA, float numberB) {
		return numberA - numberB;
	}

	public static float multiplication(float numberA, float numberB) {
		return numberA * numberB;
	}

	public static float division(float numberA, float numberB) {
		return numberA / numberB;
	}

	public static float power(float number) {
		return (float) Math.pow(number, 2);
	}

	public static float square(float number) {
		return (float) Math.sqrt(number);
	}

	public static float surX(float number) {
		return 1 / number;
	}

	public static float operation(Float numberA, String operator, Float numberB) {
		float result = 0;
		switch (operator) {
		case "+" -> result = addition(numberA, numberB);
		case "-" -> result = soustraction(numberA, numberB);
		case "x" -> result = multiplication(numberA, numberB);
		case "/" -> result = division(numberA, numberB);
		}
		return result;
	}

}
