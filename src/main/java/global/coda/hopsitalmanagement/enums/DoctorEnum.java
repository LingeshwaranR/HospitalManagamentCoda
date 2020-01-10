package global.coda.hopsitalmanagement.enums;

import java.util.HashMap;

/**
 * The enum Doctor enum.
 */
public enum DoctorEnum {
    /**
     * Update doctor enum.
     */
    UPDATE(1),
    /**
     * Read doctor enum.
     */
    READ(2),
    /**
     * See all patient doctor enum.
     */
    SEE_ALL_PATIENT(3),
    /**
     * See assigned patient doctor enum.
     */
    SEE_ASSIGNED_PATIENT(4),
    /**
     * Logout doctor enum.
     */
    LOGOUT(5);
    private final int value;
    private static HashMap<Object, Object> map = new HashMap<>();

    /**
     *@param value .
     */
     DoctorEnum(int value) {
        this.value = value;
    }

    static {
        for (DoctorEnum pageType : DoctorEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Value of doctor enum.
     *
     * @param pageType the page type
     * @return the doctor enum
     */
    public static DoctorEnum valueOf(int pageType) {
        return (DoctorEnum) map.get(pageType);
    }
}

