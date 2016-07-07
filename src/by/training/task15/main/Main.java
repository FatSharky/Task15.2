package by.training.task15.main;

import by.training.task15.service.Calculator;
import by.training.task15.service.ContentGenerator;
import by.training.task15.service.ServiceConstant;
import by.training.task15.service.exeption.ServiceException;

public class Main {

	public static void main(String[] args) throws ServiceException {

		ContentGenerator inputFilesContentGenerator = new ContentGenerator();
		for (int i = 1; i <= ServiceConstant.NUM_OF_THREADS; i++) {
			inputFilesContentGenerator.generateContentForFile(i);
		}

		Calculator calculator = new Calculator();
		double calculationResult = calculator.getCalculationResult();
		System.out.println("–езульат калькул€ции: " + calculationResult);

	}

}
