package global.coda.hopsitalmanagement.dao;

import global.coda.hopsitalmanagement.patientdetails.model.Doctor;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Doctor db dao.
 */
public interface DoctorDbDao {
    /**
     * Insert boolean.
     *
     * @param doctor the doctor
     * @return the boolean
     * @throws SQLException the sql exception
     */
    Boolean insert(Doctor doctor) throws SQLException;

    /**
     * Read doctor.
     *
     * @param doctor the doctor
     * @return the doctor
     * @throws SQLException the sql exception
     */
    Doctor read(Doctor doctor) throws SQLException;

    /**
     * Read all list.
     *
     * @return the list
     * @throws SQLException the sql exception
     */
     List<Doctor> readAll() throws SQLException;

    /**
     * Read all with masked details list.
     *
     * @return the list
     * @throws SQLException the sql exception
     */
     List<Doctor> readAllWithMaskedDetails() throws SQLException;

    /**
     * Update boolean.
     *
     * @param doctor the doctor
     * @return the boolean
     * @throws SQLException the sql exception
     */
     Boolean update(Doctor doctor) throws SQLException;

    /**
     * Delete boolean.
     *
     * @param doctor the doctor
     * @return the boolean
     * @throws SQLException the sql exception
     */
     Boolean delete(Doctor doctor) throws SQLException;
}
