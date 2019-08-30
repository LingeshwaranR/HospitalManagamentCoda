package global.coda.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.opencsv.CSVWriter;

import global.coda.hopsitalmanagement.svc.PatientServices;
import global.coda.patientdetails.model.Patient;
import global.coda.exception.InvalidException;

public class HospitalMain {
	private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
			Locale.getDefault());
	private static final String CSV_FILE_PATH
    = "C:\\Users\\Vc\\Desktop\\Test\\Patient.csv";

	public static void main(String[] args) throws InvalidException, IOException {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(HospitalMain.class);
		//File Path Added
        File file = new File(CSV_FILE_PATH);
        if (!file.exists()) {

            // Create a new file if not exists.
            file.createNewFile();

            CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH,true));
	        List<String[]> data = new ArrayList<String[]>();
	        data.add(new String[]{"ID","NAME","AGE","LOCATION"});
	        
	        writer.writeAll(data);
	        writer.close();

            

        }
       

		BasicConfigurator.configure();
		logger.info("HospitalMain Logger Initiated");
		Scanner sc = new Scanner(System.in);
		// Bean Object Initiated
		Patient patient = null;

		// Map Declared Here

		Map<Integer, Object> map = new HashMap<Integer, Object>();

		PatientServices co = new PatientServices();
		try {
			while (true) {
				int id = 0;
				logger.info("1.Create 2.Update 3.Read 4.Delete 5.ReadParticular");
				int choice = sc.nextInt();
				if (choice > 5 || choice <= 0) {

					throw new InvalidException(LOCAL_MESSAGES_BUNDLE.getString("HMS1000A"));
				}

				switch (choice) {
				case 1:
				{
				    
					
					logger.info("Enter The Patient Key(Must Be Unique)");
					id = sc.nextInt();
					for (Map.Entry m : map.entrySet()) {
						if (m.getKey().equals(id)) {
							throw new InvalidException(LOCAL_MESSAGES_BUNDLE.getString("HMS1000B"));
						}
					}
		      
					logger.info("Enter Name of patient ");

					String name = sc.next();
					List<String> address = new ArrayList<>();

					logger.info("Enter Age of patient ");
					int age = sc.nextInt();
					logger.info("Enter Location 1 ");
					String location = sc.next();
					address.add(location);
					logger.info("Enter Location 2 ");
					location = sc.next();
					address.add(location);
					logger.info("Enter Location 3 ");
					location = sc.next();
					address.add(location);
					patient = co.create(id,age, name, address,CSV_FILE_PATH);
					map.put(id, patient);

					break;
				}
				case 2: // Update Details
				{

					co.update(map,CSV_FILE_PATH);
					//co.updateCSV(CSV_FILE_PATH, "Laxman", 2, 1);

					break;
				}
				case 3:// Initial Read
				{

					co.read(map,CSV_FILE_PATH);
					// ReadPatients.read(a);
					break;
				}
				case 4: // Delete Details
				{
					co.delete(map,CSV_FILE_PATH);
					break;
				}
				case 5: // Delete Details
				{
					logger.info("Enter ID To Read");
					id = sc.nextInt();
					co.readParticular(map, id);
					break;
				}
				default:
					System.out.println("Invalid Input");
					System.exit(0);

				}

			}

		} catch (InputMismatchException e) {
			throw new InvalidException("Input must be a number");

		}
	}

}
