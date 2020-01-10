package global.coda.hopsitalmanagement.patientdetails.model;

/**
 * The type Doctor.
 */
public class Doctor extends  User {
    /**
     * Gets ima number.
     *
     * @return the ima number
     */
    public int getImaNumber() {
        return imaNumber;
    }

    /**
     * Sets ima number.
     *
     * @param imaNumber the ima number
     */
    public void setImaNumber(int imaNumber) {
        this.imaNumber = imaNumber;
    }

    /**
     * Gets specialization.
     *
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets specialization.
     *
     * @param specialization the specialization
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * The Ima number.
     */
    int imaNumber;
    /**
     * The Specialization.
     */
    String specialization;

}
