package by.training.task15.dao;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import by.training.task15.dao.exception.DAOException;

public class OutputFileDAO {
	public void writeToFile(double result) throws DAOException {
		try (FileWriter fileWriter = new FileWriter(
				DAOConstant.WORKING_DIRECTORY_URI + DAOConstant.SEPARATOR + DAOConstant.OUTPUT_FILE_NAME)) {
			fileWriter.write(Double.toString(result));
		} catch (FileNotFoundException e) {
			throw new DAOException("A file was not found", e);
		} catch (IOException e) {
			throw new DAOException("Exception while writing into a file", e);
		}
	}

}
