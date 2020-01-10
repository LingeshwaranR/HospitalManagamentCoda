package global.coda.hopsitalmanagement.enums;

import java.util.HashMap;

/**
 * The enum Role enum.
 */
public enum RoleEnum {
    /**
     * Patient role enum.
     */
    Patient(1),
    /**
     * Doctor role enum.
     */
    Doctor(2),
    /**
     * Branch admin role enum.
     */
    BranchAdmin(3),
    /**
     * Gobal admin role enum.
     */
    GobalAdmin(4);
    private final int value;
    private static HashMap<Object, Object> map = new HashMap<>();

    /**
     *@param value .
     */
     RoleEnum(int value) {
        this.value = value;
    }

    static {
        for (RoleEnum pageType : RoleEnum.values()) {
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
     * Value of role enum.
     *
     * @param pageType the page type
     * @return the role enum
     */
    public static RoleEnum valueOf(int pageType) {
        return (RoleEnum) map.get(pageType);
    }
}
