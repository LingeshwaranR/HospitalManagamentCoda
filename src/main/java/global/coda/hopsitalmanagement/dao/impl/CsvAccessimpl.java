package global.coda.hopsitalmanagement.dao.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import global.coda.hopsitalmanagement.exception.FileNotPresentException;
import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import global.coda.hopsitalmanagement.applicationconstants.Constants;
import global.coda.hopsitalmanagement.dao.CsvAccess;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;

/**
 * The type Csv accessimpl.
 */
public class CsvAccessimpl implements CsvAccess {

    private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
            Locale.getDefault());
    /**
     * The Logger.
     */
    private Logger logger = Logger.getLogger(CsvAccessimpl.class);

    @Override
    public Map<Integer, Object> initialRead(String CSV_FILE_PATH) throws FileNotPresentException {
        File file = new File(CSV_FILE_PATH);
        Map<Integer, Object> map = new HashMap<Integer, Object>();

        try {

            if (!file.exists()) {

                // Create a new file if not exists.
                file.createNewFile();
            }

            //Initiate File


            FileReader filereader = new FileReader(CSV_FILE_PATH);
            // create csvReader object passing
            // filereader as parameter
            CSVReader csvReader = new CSVReader(filereader);
            List<String[]> fileElements = csvReader.readAll();
            // Map Declared Here
            if ((file.exists()) && !(file.length() == 0)) {
                for (int i = 1; i < fileElements.size(); i++) {
                    Patient arr = new Patient();
                    List<String> address = new ArrayList<>();

                    arr.setId(Integer.parseInt(fileElements.get(i)[0]));
                    arr.setName(fileElements.get(i)[1]);
                    arr.setAge(Integer.parseInt(fileElements.get(i)[2]));
                    arr.setArea(fileElements.get(i)[3]);
                    arr.setCity(fileElements.get(i)[4]);
                    arr.setState(fileElements.get(i)[5]);

                    map.put(Integer.parseInt(fileElements.get(i)[0]), arr);
                }
                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.CA3));

            }
        } catch (Exception e) {
            throw new FileNotPresentException(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX4));
        }
        return map;

    }

    @Override
    public List<Integer> initiate(String CSV_FILE_PATH) throws FileNotFoundException {
        // TODO Auto-generated method stub

        List<Integer> idList = new ArrayList<Integer>();
        File file = new File(CSV_FILE_PATH);

        try {
            // Read existing file

            // Initial Read Of file
            if (!(file.length() == 0)) {
                FileReader filereader = new FileReader(CSV_FILE_PATH);

                CSVReader csvReader = new CSVReader(filereader);
                List<String[]> csvBody = csvReader.readAll();
                int col = 0;
                int row = 1;

                for (row = 1; row < csvBody.size(); row++) {
                    idList.add(Integer.parseInt(csvBody.get(row)[col]));
                }

                csvReader.close();

                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.CA1));
                // get CSV row column and replace with by using row and column


            }
        } catch (Exception e) {
            throw new FileNotFoundException(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX4));
        }
        return idList;


    }

    @Override
    public void create(Patient a, String csv) throws FileNotFoundException {
        // TODO Auto-generated method stub
        try {
            File file = new File(csv);
            if (file.length() == 0) {
                defaultWrites(csv);
            }
            FileWriter fileWriter = new FileWriter(csv, true);
            fileWriter.append(a.toString() + "\n");
            fileWriter.flush();
            fileWriter.close();
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.CA2));
        } catch (Exception e) {
            throw new FileNotFoundException(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX4));
        }
    }

    @Override
    public void read(String file) throws FileNotFoundException {
        // TODO Auto-generated method stub
        try {

            // Create an object of filereader class
            // with CSV file as a parameter.
            File fileCsv = new File(file);
            if (!(fileCsv.length() == 0)) {
                FileReader filereader = new FileReader(file);

                // create csvReader object passing
                // filereader as parameter
                CSVReader csvReader = new CSVReader(filereader);
                String[] nextRecord;


                // we are going to read data line by line
                while ((nextRecord = csvReader.readNext()) != null) {
                    for (String cell : nextRecord) {
                        System.out.print(cell + "\t");
                    }
                    System.out.println();
                }
            } else {
                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX5));
            }
        } catch (Exception e) {
            throw new FileNotFoundException(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX4));
        }

    }

    public void defaultWrites(String csv) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] {"ID", "NAME", "AGE", "AREA", "CITY", "STATE"});

        writer.writeAll(data);
        writer.close();
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.CA4));
    }

}
