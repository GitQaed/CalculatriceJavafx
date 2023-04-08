
package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import metier.App;
import models.Calculs;
import models.History;
import utils.CalculatriceMethods;

import java.util.ArrayList;

import exceptions.ExceptionHandler;

import javafx.event.ActionEvent;

import javafx.scene.control.Accordion;

public class AppController {

	@FXML
	Label output;

	@FXML
	Label memShort;

	@FXML
	Accordion historyPanel;

	boolean menuExpanded = false;
	boolean darkMode = true;

	private String operator;
	private String numberTemp0;
	private String numberTemp1;
	private String resultString;
	private ArrayList<String> inputList = new ArrayList<>();
	private float[] numberList = { 0, 0 };

	@FXML
	Label historyLabel;

	@FXML
	public void handleClickNumber(ActionEvent event) {
		String input = ((Button) event.getSource()).getText();
		inputList.add(input);
		numberTemp0 = "";
		for (String number : inputList) {
			numberTemp0 += number;
		}
		output.setText(numberTemp0);
		if (numberTemp0.equals(".")) {
			numberTemp0 = "0";
			inputList.clear();
		}
		numberTemp1 = numberTemp0;
	}

	@FXML
	public void handleClickOperator(ActionEvent event) {
		if (numberTemp0 == null) {
			output.setText("0");
		} else if (operator == null) {
			operator = ((Button) event.getSource()).getText();
			numberList[0] = Float.parseFloat(numberTemp0);
			numberList[1] = Float.parseFloat(numberTemp1);
			memShort.setText(numberTemp0 + " " + operator);
			inputList.clear();
			numberTemp0 = "0";
		} else {
			operator = null;
		}
	}

	@FXML
	public void handleClickNegative(ActionEvent event) {
		if (inputList.isEmpty()) {
			output.setText("0");
		} else {
			float numberTemp4 = Float.parseFloat(output.getText());
			numberTemp4 *= -1;
			System.out.println(numberTemp4);
			inputList.add(0, String.valueOf(numberTemp4));
			numberTemp0 = String.valueOf(numberTemp4);
			output.setText(String.valueOf(numberTemp0));
		}

	}

	@FXML
	public void handleClickRemove(ActionEvent event) {
		String input = ((Button) event.getSource()).getText();
		if (input.equals("CE")) {
			inputList.clear();
			output.setText("0");
		} else if (input.equals("C")) {
			reset();
		} else if (input.equals("<")) {
			if (inputList.size() < 2) {
				reset();
			} else {
				inputList.remove(inputList.size() - 1);
				String numberTemp2 = "";
				for (String number : inputList) {
					numberTemp2 += number;
				}
				System.out.println(numberTemp2);
				numberTemp0 = numberTemp2;
				output.setText(numberTemp0);
			}

		}

	}

	private void reset() {
		inputList.clear();
		output.setText("0");
		memShort.setText("0");
		resultString = "";
		numberList[0] = 0f;
		numberList[1] = 0f;
		numberTemp1 = "0";
		numberTemp0 = "0";
		operator = null;
	}

	@FXML
	public void handleClickResult(ActionEvent event) {

		float numberA = numberList[0];
		float numberB = 0;
		try {
			numberB = Float.parseFloat(numberTemp1);
		} catch (Exception e) {
			output.setText("0");
			memShort.setText("0");
			inputList.clear();
		}
		System.out.println(
				"*** debug *** : numberA : " + numberA + ", numberB : " + numberB + ", operator : " + operator);
		if (numberA == 0) {
			output.setText(String.valueOf(numberB));
			memShort.setText(String.valueOf(numberB));
			System.out.println(String.valueOf(numberB));
			inputList.clear();
		} else if (operator == null) {
			reset();
		} else if (operator.equals("/") && numberB == 0f) {
			output.setText(ExceptionHandler.handleError("ZeroDivision-exception"));
			reset();
		} else if (numberTemp0.equals(".") && numberTemp1.equals(".")) {
			reset();
		} else {
			float result = CalculatriceMethods.operation(numberA, operator, numberB);
			resultString = String.valueOf(numberA + " " + operator + " " + numberB + " = " + result);
			CalculatriceMethods.saveHistory(numberA, operator, numberB, result);
			inputList.clear();
			operator = null;
			memShort.setText(resultString);
		}

	}

	@FXML
	public void handleClickSpecial(ActionEvent event) {
		String input = ((Button) event.getSource()).getText();
		float numberB = 0;
		try {
			numberB = Float.parseFloat(numberTemp1);
		} catch (Exception e) {
			reset();
		}
		if (input.equals("1/x") && numberB == 0) {
			output.setText(ExceptionHandler.handleError("ZeroDivision-exception"));
			reset();
		} else {
			float inputFloat = 0f;
			try {
				inputFloat = Float.parseFloat(numberTemp0);
			} catch (Exception e) {
				reset();
			}
			switch (input) {
			case "1/x" -> inputFloat = CalculatriceMethods.surX(inputFloat);
			case "x²" -> inputFloat = CalculatriceMethods.power(inputFloat);
			case "√x" -> inputFloat = CalculatriceMethods.square(inputFloat);
			}
			numberList[0] = inputFloat;
			output.setText(String.valueOf(inputFloat));
			inputList.clear();
			System.out.println(inputFloat);
		}
	}

	@FXML
	public void displayHistory(ActionEvent event) {
		historyLabel.setText(numberTemp0);
		// CalculatriceMethods.displayHistory(History.getHistoryCalculs(),
		// historyLabel);

		if (menuExpanded) {
			historyPanel.setExpandedPane(null);
			menuExpanded = false;
		} else {

			historyPanel.setExpandedPane(historyPanel.getPanes().get(0));

			menuExpanded = true;
		}

	}

	@FXML
	public void colorMode(ActionEvent event) {
		if (darkMode) {
			Scene scene = output.getScene();
			scene.getStylesheets().clear();
			scene.getStylesheets().add(getClass().getResource("/ressources/css/appLight.css").toString());
			darkMode = false;
		} else {
			Scene scene = output.getScene();
			scene.getStylesheets().clear();
			scene.getStylesheets().add(getClass().getResource("/ressources/css/app.css").toString());
			darkMode = true;
		}
	}
}
