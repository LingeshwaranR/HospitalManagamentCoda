package global.coda.hopsitalmanagement.dao;

import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import global.coda.hopsitalmanagement.patientdetails.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Patient db dao.
 */
public interface PatientDbDao {
    /**
     * Insert boolean.
     *
     * @param patient the patient
     * @return the boolean
     * @throws SQLException the sql exception
     */
    Boolean insert(Patient patient) throws SQLException;

    /**
     * Read user.
     *
     * @param id      the id
     * @param patient the patient
     * @return the user
     * @throws SQLException the sql exception
     */
    User read(Integer id, Patient patient) throws SQLException;

    /**
     * Read all list.
     *
     * @return the list
     * @throws SQLException the sql exception
     */
    List<Patient> readAll() throws SQLException;

    /**
     * Update boolean.
     *
     * @param patient the patient
     * @return the boolean
     * @throws SQLException the sql exception
     */
    Boolean update(Patient patient) throws SQLException;

    /**
     * Delete boolean.
     *
     * @param id      the id
     * @param patient the patient
     * @return the boolean
     * @throws SQLException the sql exception
     */
    Boolean delete(Integer id, Patient patient) throws SQLException;

    /**
     * Read all with masked details list.
     *
     * @return the list
     * @throws SQLException the sql exception
     */
    List<Patient> readAllWithMaskedDetails() throws SQLException;

}
