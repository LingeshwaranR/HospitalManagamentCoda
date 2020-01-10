
package global.coda.hopsitalmanagement.enums;

import java.util.HashMap;

/**
 * The enum Admin privilages.
 */
public enum AdminPrivilages {
    /**
     * Create admin privilages.
     */
    CREATE(1),
    /**
     * Update admin privilages.
     */
    UPDATE(2),
    /**
     * Readparticular admin privilages.
     */
    READPARTICULAR(3),
    /**
     * Readall admin privilages.
     */
    READALL(4),
    /**
     * Delete admin privilages.
     */
    DELETE(5),
    /**
     * Logout admin privilages.
     */
    LOGOUT(6);
    private final int value;
    private static HashMap<Object, Object> map = new HashMap<>();

    /**
     *@param value .
     */
     AdminPrivilages(int value) {
        this.value = value;
    }

    static {
        for (AdminPrivilages pageType : AdminPrivilages.values()) {
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
     * Value of admin privilages.
     *
     * @param pageType the page type
     * @return the admin privilages
     */
    public static AdminPrivilages valueOf(int pageType) {
        return (AdminPrivilages) map.get(pageType);
    }
}
