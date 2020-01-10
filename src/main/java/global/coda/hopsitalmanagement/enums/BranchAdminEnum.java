package global.coda.hopsitalmanagement.enums;

import java.util.HashMap;

/**
 * The enum Branch admin enum.
 */
public enum BranchAdminEnum {
    /**
     * Doctor branch admin enum.
     */
    DOCTOR(1),
    /**
     * Patient branch admin enum.
     */
    PATIENT(2);
    private final int value;
    private static HashMap<Object, Object> map = new HashMap<>();

    /**
     *@param value .
     */
     BranchAdminEnum(int value) {
        this.value = value;
    }
    static {
        for (BranchAdminEnum pageType : BranchAdminEnum.values()) {
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
     * Value of branch admin enum.
     *
     * @param pageType the page type
     * @return the branch admin enum
     */
    public static BranchAdminEnum valueOf(int pageType) {
        return (BranchAdminEnum) map.get(pageType);
    }
}

