package global.coda.hopsitalmanagement.enums;

import java.util.*;

/**
 * The enum Patient enum.
 */
public enum PatientEnum {
    /**
     * Update patient enum.
     */
    UPDATE(1),
    /**
     * Read patient enum.
     */
    READ(2),
    /**
     * See all doctor patient enum.
     */
    SEE_ALL_DOCTOR(3),
    /**
     * See assigned doctor patient enum.
     */
    SEE_ASSIGNED_DOCTOR(4),
    /**
     * Logout patient enum.
     */
    LOGOUT(5);
    private final int value;
    private static HashMap<Object, Object> map = new HashMap<>();

    /**
     *@param value .
     */
     PatientEnum(int value) {
        this.value = value;
    }

    static {
        for (PatientEnum pageType : PatientEnum.values()) {
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
     * Value of patient enum.
     *
     * @param pageType the page type
     * @return the patient enum
     */
    public static PatientEnum valueOf(int pageType) {
        return (PatientEnum) map.get(pageType);
    }
}




