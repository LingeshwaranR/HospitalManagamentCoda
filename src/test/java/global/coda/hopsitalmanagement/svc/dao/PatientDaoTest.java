package global.coda.hopsitalmanagement.svc.dao;

import global.coda.hopsitalmanagement.dao.impl.PatientDbDaoImpl;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import org.testng.annotations.Test;



import java.sql.SQLException;

import static org.testng.Assert.*;

/**
 * The type Patient dao test.
 */
public class PatientDaoTest {
    private String email = "lintest@gmail.com";


    /**
     * Getpatient patient.
     *
     * @return the patient
     */
    Patient getpatient() {
        Patient patient = new Patient();
        patient.setUsername("Lintest");
        patient.setPassword("1");
        patient.setEmail(email);
        patient.setRoleId(1);
        patient.setAge(100);
        patient.setArea("Area");
        patient.setCity("City");
        patient.setState("State");

        return patient;
    }

    /**
     * Test insert.
     */
    @Test
    public void testInsert() {
        try {
            PatientDbDaoImpl patientDb = new PatientDbDaoImpl();
            Patient patient = getpatient();
            assertTrue(patientDb.insert(patient));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test rea.
     */
    @Test
    public void testRea() {

    }

}
