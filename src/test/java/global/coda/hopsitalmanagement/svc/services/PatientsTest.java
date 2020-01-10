/*
Lingeshwaran R
 */
package global.coda.hopsitalmanagement.svc.services;


import java.util.*;

import global.coda.hopsitalmanagement.applicationconstants.Constants;
import org.testng.annotations.Test;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * The type Patients test.
 */
public class PatientsTest {
    private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
            Locale.getDefault());
    /**
     * The Id.
     */
    private int id = 100;
    /**
     * The Name.
     */
    private String name = "Lingesh";

    /**
     * The Age.
     */
    private int age = 19;
    /**
     * The Area.
     */
    private String area = "a";
    /**
     * The City.
     */
    private String city = "b";
    /**
     * The State.
     */
    private String state = "c";
    /**
     * The Csv.
     */
    final String csv = LOCAL_MESSAGES_BUNDLE.getString(Constants.TESTFILE);

    /**
     * Test createpatients.
     */
    @Test
    public void testCreatepatients() {
        Logger logger = Logger.getLogger(PatientsTest.class);
        BasicConfigurator.configure();


    }

    /**
     * Test readpatients.
     */
    @Test
    public void testReadpatients() {


    }

    /**
     * Test updatepatients.
     */
    @Test
    public void testUpdatepatients() {


    }
}
