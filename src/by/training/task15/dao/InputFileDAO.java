package by.training.task15.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import by.training.task15.dao.exception.DAOException;

public class InputFileDAO {
	private int fileNumber;

	private int actionId;
	private double numberA;
	private double numberB;

	public InputFileDAO() {
	}

	public InputFileDAO(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	public void readFromFile() throws DAOException {
		try (FileReader fileReader = new FileReader(DAOConstant.WORKING_DIRECTORY_URI + DAOConstant.SEPARATOR
				+ DAOConstant.FILE_PREFIX + fileNumber + DAOConstant.FILE_EXTENSION)) {
			StringBuilder fileContent = new StringBuilder();

			int symbol = 0;
			while ((symbol = fileReader.read()) != -1) {
				fileContent.append((char) symbol);
			}

			String fileContentStr = fileContent.toString();
			String actionIdStr = fileContentStr.substring(0, fileContentStr.indexOf(DAOConstant.NEW_ROW_SYMBOL));
			String numberAStr = fileContentStr.substring(fileContentStr.indexOf(DAOConstant.NEW_ROW_SYMBOL) + 1,
					fileContentStr.indexOf(DAOConstant.SPACE_SYMBOL));
			String numberBStr = fileContentStr.substring(fileContentStr.indexOf(DAOConstant.SPACE_SYMBOL));

			actionId = Integer.parseInt(actionIdStr);
			numberA = Double.parseDouble(numberAStr);
			numberB = Double.parseDouble(numberBStr);
		} catch (FileNotFoundException e) {
			throw new DAOException("A file was not found", e);
		} catch (IOException e) {
			throw new DAOException("Exception while reading a file", e);
		}
	}

	public void writeToFile() throws DAOException {
		try (FileWriter fileWriter = new FileWriter(DAOConstant.WORKING_DIRECTORY_URI + DAOConstant.SEPARATOR
				+ DAOConstant.FILE_PREFIX + fileNumber + DAOConstant.FILE_EXTENSION)) {
			fileWriter.write(Integer.toString(actionId));
			fileWriter.append(DAOConstant.NEW_ROW_SYMBOL);
			fileWriter.append(Double.toString(numberA));
			fileWriter.append(DAOConstant.SPACE_SYMBOL);
			fileWriter.append(Double.toString(numberB));
		} catch (FileNotFoundException e) {
			throw new DAOException("A file was not found", e);
		} catch (IOException e) {
			throw new DAOException("Exception while writing into a file", e);
		}
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public double getNumberA() {
		return numberA;
	}

	public void setNumberA(double numberA) {
		this.numberA = numberA;
	}

	public double getNumberB() {
		return numberB;
	}

	public void setNumberB(double numberB) {
		this.numberB = numberB;
	}

}
