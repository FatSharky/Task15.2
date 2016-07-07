package by.training.task15.service;

import java.util.ArrayList;

import by.training.task15.dao.OutputFileDAO;
import by.training.task15.dao.exception.DAOException;
import by.training.task15.service.exeption.ServiceException;

public class Calculator {
	public double getCalculationResult() throws ServiceException {
		CalculationResult calculationResult = new CalculationResult();

		ArrayList<Thread> actionCalculatorThreads = new ArrayList<>(ServiceConstant.NUM_OF_THREADS);
		for (int i = 1; i <= ServiceConstant.NUM_OF_THREADS; i++) {
			Thread actionCalculatorThread = new Thread(new FileCalcThread(calculationResult, i));
			actionCalculatorThreads.add(actionCalculatorThread);
		}

		for (Thread thread : actionCalculatorThreads) {
			thread.start();
		}

		for (Thread thread : actionCalculatorThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new ServiceException("Exception while getting calculation result", e);
			}
		}

		try {
			OutputFileDAO outputFileDAO = new OutputFileDAO();
			outputFileDAO.writeToFile(calculationResult.getCalculationResult());
			return calculationResult.getCalculationResult();
		} catch (DAOException e) {
			throw new ServiceException("Exception while getting calculation result", e);
		}
	}

	public static class CalculationResult {
		private double calculationResult = 0.0;

		public synchronized double getCalculationResult() {
			return calculationResult;
		}

		public synchronized void setCalculationResult(double calculationResult) {
			this.calculationResult = calculationResult;
		}

		public synchronized void addToCalculationResult(double addingNumber) {
			calculationResult = calculationResult + addingNumber;
		}
	}

}
