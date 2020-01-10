package global.coda.hopsitalmanagement.svc;

import global.coda.hopsitalmanagement.applicationconstants.Constants;
import global.coda.hopsitalmanagement.dao.PatientDbDao;
import global.coda.hopsitalmanagement.dao.impl.DoctorDbDaoImpl;
import global.coda.hopsitalmanagement.dao.impl.PatientDbDaoImpl;
import global.coda.hopsitalmanagement.patientdetails.model.Doctor;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Doctor services.
 */
public class DoctorServices {
    private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
            Locale.getDefault());
    /**
     * The Doctor db.
     */
    DoctorDbDaoImpl doctorDb = new DoctorDbDaoImpl();
    private Logger logger = Logger.getLogger(DoctorServices.class);


    /**
     * Create doctor doctor.
     *
     * @param doctor the doctor
     * @return the doctor
     */
    public Doctor createDoctor(Doctor doctor) {
        try {
            if (doctorDb.insert(doctor)) {
                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.DS7));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;

    }

    /**
     * Read particular doctor doctor.
     *
     * @param doctor the doctor
     * @return the doctor
     */
    public Doctor readParticularDoctor(Doctor doctor) {
        try {
            doctorDb.read(doctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return doctor;
    }

    /**
     * Update doctor doctor.
     *
     * @param doctor the doctor
     * @return the doctor
     */
    public Doctor updateDoctor(Doctor doctor) {
        try {
            if (doctorDb.update(doctor)) {
                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.DS7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    /**
     * Read all doctor list.
     *
     * @param doctor the doctor
     * @return the list
     */
    public List<Doctor> readAllDoctor(Doctor doctor) {
        List<Doctor> doctorList = new ArrayList<>();

        try {
            doctorList = doctorDb.readAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorList;
    }

    /**
     * Delete doctor doctor.
     *
     * @param doctor the doctor
     * @return the doctor
     */
    public Doctor deleteDoctor(Doctor doctor) {
        try {
            doctorDb.delete(doctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    /**
     * Read all patients list.
     *
     * @return the list
     */
    public List<Patient> readAllPatients() {
        List<Patient> patientList = new ArrayList<>();

        try {
            PatientDbDaoImpl patientDbDao = new PatientDbDaoImpl();
            patientList = patientDbDao.readAllWithMaskedDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientList;
    }
}
