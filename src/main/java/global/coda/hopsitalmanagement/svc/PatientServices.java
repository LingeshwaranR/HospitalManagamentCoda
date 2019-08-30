package global.coda.hopsitalmanagement.svc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import global.coda.exception.InvalidException;
import global.coda.patientdetails.model.Patient;

public class PatientServices {
	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
			Locale.getDefault());

	public static Patient create(int id, int age, String name, List<String> address, String csv)
			throws InvalidException, IOException {
		Logger logger = Logger.getLogger(PatientServices.class);
		Patient arr = new Patient();

		// arr.PatientAdd(age, name, address);

		arr.setAddress(address);
		arr.setAge(age);
		arr.setName(name);
		arr.setId(id);

		FileWriter fileWriter = new FileWriter(csv, true);
		fileWriter.append(arr.toString() + "\n");
		fileWriter.flush();
		fileWriter.close();
		logger.info("Patient List Inserted Successfully");
		return arr;
	}

	public static Boolean read(Map<Integer, Object> map, String file) {
		Logger logger = Logger.getLogger(PatientServices.class);
		try {

			// Create an object of filereader class
			// with CSV file as a parameter.
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static Boolean readParticular(Map<Integer, Object> map, int id) {
		Logger logger = Logger.getLogger(PatientServices.class);
		// Elements can traverse in any order
		for (Map.Entry m : map.entrySet()) {
			if (m.getKey().equals(id))
				logger.info(m.getKey() + " " + m.getValue());
		}
		return true;
	}

//Update Using Map
	public static void update(Map<Integer, Object> map, String csv) throws IOException {
		Logger logger = Logger.getLogger(PatientServices.class);
		Scanner sc = new Scanner(System.in);

		logger.info("Update Patients");
		logger.info("Enter Unique ID Of Patient To Update");
		int uid = sc.nextInt();
		List<String> address = new ArrayList<>();
		FileWriter fileWriter = new FileWriter(csv);
//		  fileWriter.flush();

		fileWriter.close();
		CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "ID", "NAME", "AGE", "LOCATION" });

		writer.writeAll(data);
		writer.close();

		for (Map.Entry m : map.entrySet()) {
			Patient arr = (Patient) m.getValue();

			if (m.getKey().equals(uid)) {
				logger.info("Enter Name of patient ");

				String name = sc.next();
				arr.setName(name);
				logger.info("Enter Age of patient ");
				int age = sc.nextInt();
				arr.setAge(age);
				logger.info("Enter Age of Location 1 ");
				String location = sc.next();
				address.add(location);
				logger.info("Enter Age of Location 2 ");
				location = sc.next();
				address.add(location);
				logger.info("Enter Age of Location 3 ");
				location = sc.next();
				address.add(location);
				arr.setAddress(address);
//				arr=new Patient();
//				arr.PatientAdd(age, name,address);

				logger.info("Patient List Updated Successfully");
			}

			// logger.info(m.getKey()+" "+m.getValue());
		}
		FileWriter fileWriter1 = new FileWriter(csv, true);

		for (Map.Entry i : map.entrySet()) {
			Patient arr = (Patient) i.getValue();
			fileWriter1.append(arr.toString() + "\n");

		}
		fileWriter1.close();

	}

	public static void delete(Map<Integer, Object> map, String csv) throws IOException {
		Logger logger = Logger.getLogger(PatientServices.class);
		Scanner sc = new Scanner(System.in);
		List<String> address = new ArrayList<>();
		FileWriter fileWriter = new FileWriter(csv);
//		  fileWriter.flush();

		fileWriter.close();
		CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "ID", "NAME", "AGE", "LOCATION" });

		writer.writeAll(data);
		writer.close();

		logger.info("Delete Patients");
		logger.info("Enter Unique ID Of Patient To Delete");
		int uid = sc.nextInt();
		for (Map.Entry m : map.entrySet()) {
			Patient arr = (Patient) m.getValue();

			if (m.getKey().equals(uid)) {

				map.remove(uid);

				logger.info("Patient List Deleted Successfully");
			}

			// logger.info(m.getKey()+" "+m.getValue());
		}
		FileWriter fileWriter1 = new FileWriter(csv, true);

		for (Map.Entry i : map.entrySet()) {
			Patient arr = (Patient) i.getValue();
			fileWriter1.append(arr.toString() + "\n");

		}
		fileWriter1.close();

	}

}