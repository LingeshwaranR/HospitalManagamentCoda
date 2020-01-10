package global.coda.hopsitalmanagement.svc;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import global.coda.hopsitalmanagement.dao.impl.DoctorDbDaoImpl;
import global.coda.hopsitalmanagement.enums.ImplEnum;
import global.coda.hopsitalmanagement.exception.FileNotPresentException;
import global.coda.hopsitalmanagement.patientdetails.model.Doctor;
import org.apache.log4j.Logger;

import global.coda.hopsitalmanagement.applicationconstants.Constants;
import global.coda.hopsitalmanagement.exception.InvalidException;
import global.coda.hopsitalmanagement.dao.CsvAccess;
import global.coda.hopsitalmanagement.dao.impl.CsvAccessimpl;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import global.coda.hopsitalmanagement.dao.impl.PatientDbDaoImpl;

/**
 * The type Patient services.
 */
public class PatientServices {
    private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
            Locale.getDefault());
    private static String CSV_FILE_PATH;

    private Scanner sc = new Scanner(System.in);
    private CsvAccess csvDaoObj = new CsvAccessimpl();

    private PatientDbDaoImpl patientDb;


    {
        try {
            patientDb = new PatientDbDaoImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Logger logger = Logger.getLogger(PatientServices.class);
    private Map<Integer, Object> map = new HashMap<Integer, Object>();
    /**
     * The Flow.
     */
    private  ImplEnum flow;

    /**
     * Instantiates a new Patient services.
     *
     * @param csv   the csv
     * @param flows the flows
     */
    public PatientServices(String csv, ImplEnum flows) {
        try {
            //Set Flow
            flow = flows;
            //Setting Path
            if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS12))) {
                CSV_FILE_PATH = csv;
                map = csvDaoObj.initialRead(CSV_FILE_PATH);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotPresentException e) {
            e.printStackTrace();
        }

    }


    /**
     * Create patient patient.
     *
     * @param CSV_FILE_PATH the csv file path
     * @param patient       the patient
     * @return the patient
     * @throws InvalidException the invalid exception
     * @throws IOException      the io exception
     */
    public Patient createPatient(String CSV_FILE_PATH, Patient patient)
            throws InvalidException, IOException {


        if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS12))) {

            List<Integer> idList = new ArrayList<Integer>();
            //Reads id's from the files and store
            idList = csvDaoObj.initiate(CSV_FILE_PATH);


//            if (idList != null) {
//                for (int i = 0; i < idList.size(); i++) {
//                    if (idList.get(i) == id)
//                        throw new InvalidException(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX2));
//
//                }
//            }


            //Storing into the CSV file using DAO layer...
            csvDaoObj.create(patient, CSV_FILE_PATH);

        } else if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS13))) {
            try {
                if (patientDb.insert(patient)) {
                    logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS18));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        return patient;
    }

    /**
     * Read all patient list.
     *
     * @param CSV_FILE_PATH the csv file path
     * @param patient       the patient
     * @return the list
     * @throws FileNotFoundException the file not found exception
     */
    public List<Patient> readAllPatient(String CSV_FILE_PATH, Patient patient) throws FileNotFoundException {
        List<Patient> patientList = new ArrayList<>();

        if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS12))) {

            csvDaoObj.read(CSV_FILE_PATH);
        } else if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS13))) {

            try {
                patientList = patientDb.readAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return patientList;
    }

    /**
     * Read particular patient patient.
     *
     * @param patient the patient
     * @return the patient
     * @throws InvalidException the invalid exception
     */
    public Patient readParticularPatient(Patient patient) throws InvalidException {
        // Elements can traverse in any order
        if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS12))) {

            boolean foundId = false;
            for (Map.Entry m : map.entrySet()) {
                if ((int) m.getKey() == patient.getId()) {
                    foundId = true;
                    logger.info(m.getKey() + " " + m.getValue());
                }
            }
            if (!foundId) {
                throw new InvalidException(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX6));
            }
        } else if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS13))) {
            try {
                patient = patientDb.read(patient.getUserId(), patient);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return patient;
    }

    /**
     * Update patient patient.
     *
     * @param CSV_FILE_PATH the csv file path
     * @param patient       the patient
     * @return the patient
     * @throws IOException      the io exception
     * @throws InvalidException the invalid exception
     */
//Update Using Map
    public Patient updatePatient(String CSV_FILE_PATH, Patient patient) throws IOException, InvalidException {
        Boolean foundId = false;

        if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS12))) {

            List<String> address = new ArrayList<>();


            for (Map.Entry iterator : map.entrySet()) {
                Patient arr = (Patient) iterator.getValue();


//                if ((int) iterator.getKey() == id) {
//                    foundId = true;
//                    logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS8));
//                }


            }
            checkAndPush(CSV_FILE_PATH, foundId);
        } else if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS13))) {
            try {


                if (patientDb.update(patient)) {
                    logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS19));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return patient;
    }

    /**
     * Delete patient boolean.
     *
     * @param CSV_FILE_PATH the csv file path
     * @param userId        the user id
     * @param patient       the patient
     * @return the boolean
     * @throws IOException      the io exception
     * @throws InvalidException the invalid exception
     */
    public Boolean deletePatient(String CSV_FILE_PATH, int userId, Patient patient) throws IOException, InvalidException {
        Boolean bool;
        if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS12))) {

            boolean foundId = false;
            Patient arr;
            //defaultWrites For Heading Row
            csvDaoObj.defaultWrites(CSV_FILE_PATH);


            Iterator mapIterator = map.entrySet().iterator();
            while (mapIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) mapIterator.next();
                if ((int) mapElement.getKey() == userId) {
                    foundId = true;
                    mapIterator.remove();
                    logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS11));
                }
            }

            checkAndPush(CSV_FILE_PATH, foundId);
        } else if (flow.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS13))) {
            try {


                if (patientDb.delete(userId, patient)) {
                    bool = true;
                    logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS19));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    /**
     * Read all doctors list.
     *
     * @return the list
     */
    public List<Doctor> readAllDoctors() {
        DoctorDbDaoImpl doctorDbDao = new DoctorDbDaoImpl();
        List<Doctor> doctorList = new ArrayList<>();
        try {
            doctorList = doctorDbDao.readAllWithMaskedDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorList;
    }

    /**
     * Check and push.
     *
     * @param CSV_FILE_PATH the csv file path
     * @param foundId       the found id
     * @throws IOException      the io exception
     * @throws InvalidException the invalid exception
     */
    public void checkAndPush(String CSV_FILE_PATH, boolean foundId) throws IOException, InvalidException {
        if (!foundId) {
            throw new InvalidException(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX6));
        } else {
            //DELETING EXISTING FILE
            FileWriter fileWriter = new FileWriter(CSV_FILE_PATH);
            fileWriter.close();
            FileWriter fileWriter1 = new FileWriter(CSV_FILE_PATH, true);

            for (Map.Entry i : map.entrySet()) {
                Patient arr = (Patient) i.getValue();
                csvDaoObj.create(arr, CSV_FILE_PATH);

            }
            fileWriter1.close();
        }
    }
}
