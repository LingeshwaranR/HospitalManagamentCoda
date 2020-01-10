package global.coda.hopsitalmanagement.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import global.coda.hopsitalmanagement.exception.FileNotPresentException;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;


/**
 * The interface Csv access.
 */
public interface CsvAccess {
	/**
	 * Initial read map.
	 *
	 * @param CSV_FILE_PATH the csv file path
	 * @return the map
	 * @throws FileNotFoundException   the file not found exception
	 * @throws FileNotPresentException the file not present exception
	 */
	Map<Integer, Object> initialRead(String CSV_FILE_PATH) throws FileNotFoundException, FileNotPresentException;

	/**
	 * Initiate list.
	 *
	 * @param CSV_FILE_PATH the csv file path
	 * @return the list
	 * @throws FileNotFoundException the file not found exception
	 */
	List<Integer> initiate(String CSV_FILE_PATH) throws FileNotFoundException;

	/**
	 * Create.
	 *
	 * @param a   the a
	 * @param csv the csv
	 * @throws FileNotFoundException the file not found exception
	 */
	void create(Patient a, String csv) throws FileNotFoundException;

	/**
	 * Read.
	 *
	 * @param file the file
	 * @throws FileNotFoundException the file not found exception
	 */
	void read(String file) throws FileNotFoundException;

	/**
	 * Default writes.
	 *
	 * @param csv the csv
	 * @throws IOException the io exception
	 */
	void defaultWrites(String csv) throws IOException;
}

