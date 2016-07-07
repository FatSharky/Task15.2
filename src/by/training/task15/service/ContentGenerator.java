package by.training.task15.service;

import by.training.task15.dao.InputFileDAO;
import by.training.task15.dao.exception.DAOException;
import by.training.task15.service.exeption.ServiceException;

public class ContentGenerator {
	public void generateContentForFile(int fileNumber) throws ServiceException {
		InputFileDAO inputFileDAO = new InputFileDAO(fileNumber);

		inputFileDAO.setActionId(generateActionId());
		inputFileDAO.setNumberA(generateNumber());
		inputFileDAO.setNumberB(generateNumber());

		try {
			inputFileDAO.writeToFile();
		} catch (DAOException e) {
			throw new ServiceException("Exception while generating content for an input file", e);
		}
	}

	private int generateActionId() {
		return (int) Math.ceil(Math.random() * 3);
	}

	private double generateNumber() {
		return Math.random();
	}

}
