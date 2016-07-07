package by.training.task15.service;

import by.training.task15.dao.InputFileDAO;
import by.training.task15.dao.exception.DAOException;

public class FileCalcThread implements Runnable {
	private Calculator.CalculationResult calculationResult;
	private int fileNumber;

	public FileCalcThread(Calculator.CalculationResult calculationResult, int fileNumber) {
		this.calculationResult = calculationResult;
		this.fileNumber = fileNumber;
	}

	@Override
	public void run() {
		try {
			InputFileDAO inputFileDAO = new InputFileDAO(fileNumber);
			inputFileDAO.readFromFile();

			int actionId = inputFileDAO.getActionId();
			double numberA = inputFileDAO.getNumberA();
			double numberB = inputFileDAO.getNumberB();
			double actionCalculationResult = 0.0;

			switch (actionId) {
			case 1:
				actionCalculationResult = actionCalculationResult + (numberA + numberB);
				break;
			case 2:
				actionCalculationResult = actionCalculationResult + (numberA * numberB);
				break;
			case 3:
				actionCalculationResult = actionCalculationResult + (Math.pow(numberA, 2.0) + Math.pow(numberB, 2.0));
				break;
			}

			calculationResult.addToCalculationResult(actionCalculationResult);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
