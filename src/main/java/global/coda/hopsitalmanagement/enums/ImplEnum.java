package global.coda.hopsitalmanagement.enums;

import java.util.*;

/**
 * The enum Impl enum.
 */
public enum ImplEnum {
    /**
     * Csv impl enum.
     */
    CSV(1),
    /**
     * Db impl enum.
     */
    DB(2);
    private final int value;
    private static HashMap<Object, Object> map = new HashMap<>();
    /**
     *@param value .
     */
     ImplEnum(int value) {
        this.value = value;
    }

    static {
        for (ImplEnum pageType : ImplEnum.values()) {
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
     * Value of impl enum.
     *
     * @param pageType the page type
     * @return the impl enum
     */
    public static ImplEnum valueOf(int pageType) {
        return (ImplEnum) map.get(pageType);
    }
}
