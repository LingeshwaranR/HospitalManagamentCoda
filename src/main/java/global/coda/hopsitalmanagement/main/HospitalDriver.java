package global.coda.hopsitalmanagement.main;

import java.io.IOException;
import java.util.*;

import global.coda.hopsitalmanagement.enums.AdminPrivilages;
import global.coda.hopsitalmanagement.enums.BranchAdminEnum;
import global.coda.hopsitalmanagement.enums.DoctorEnum;
import global.coda.hopsitalmanagement.enums.ImplEnum;
import global.coda.hopsitalmanagement.enums.PatientEnum;
import global.coda.hopsitalmanagement.enums.RoleEnum;

import global.coda.hopsitalmanagement.exception.InvalidException;
import global.coda.hopsitalmanagement.patientdetails.model.Doctor;
import global.coda.hopsitalmanagement.patientdetails.model.User;
import global.coda.hopsitalmanagement.svc.AuthenticationServices;
import global.coda.hopsitalmanagement.svc.DoctorServices;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import global.coda.hopsitalmanagement.applicationconstants.Constants;
import global.coda.hopsitalmanagement.svc.PatientServices;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;

/**
 * The type Hospital driver.
 */
public class HospitalDriver {
    private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
            Locale.getDefault());
    private static final String CSV_FILE_PATH = LOCAL_MESSAGES_BUNDLE.getString(Constants.FILE);
    /**
     * The User.
     */
    User user;
    /**
     * The Patient.
     */
    Patient patient;
    /**
     * The Doctor.
     */
    Doctor doctor;
    /**
     * The Logger.
     */
    Logger logger = Logger.getLogger(HospitalDriver.class);
    /**
     * The Sc.
     */
    Scanner sc = new Scanner(System.in);
    /**
     * The Id.
     */
//User Details
    int id;
    /**
     * The Name.
     */
    String name;
    /**
     * The Email.
     */
    String email = null;
    /**
     * The Password.
     */
    String password = null;
    /**
     * The Role id.
     */
    int roleId = 0;
    /**
     * The Age.
     */
//Patient Details
    int age;
    /**
     * The Area.
     */
    String area;
    /**
     * The City.
     */
    String city;
    /**
     * The State.
     */
    String state;
    /**
     * The Imo number.
     */
//Doctor Details
    int imoNumber;
    /**
     * The Specialization.
     */
    String specialization;

    /**
     * The Map.
     */
    Map<Integer, Object> map = new HashMap<Integer, Object>();
    /**
     * The Flow.
     */
    int flow;
    /**
     * The Flows.
     */
    ImplEnum flows;
    /**
     * The Patient services.
     */
    PatientServices patientServices;

    /**
     * Init.
     */
    void init() {
        BasicConfigurator.configure();
        user = new User();
        patient = new Patient();
        doctor = new Doctor();


        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver1));
        Scanner sc = new Scanner(System.in);
        // Bean Object Initiated
        //Split the flow of backend Impl
        flow = 2;
        flows = ImplEnum.valueOf(flow);
        patientServices = new PatientServices(CSV_FILE_PATH, flows);
        //LOGIN CREDENTIALS

        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver7));
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver8));
        String emailLogin = sc.next();
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver9));
        String passwordLogin = sc.next();

        //Login Service
        AuthenticationServices authentication = new AuthenticationServices();
        user = authentication.login(emailLogin, passwordLogin);


        if (user.getPassword() != null) {
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver10));

            int roleId = user.getRoleId();
            RoleEnum choices = RoleEnum.valueOf(roleId);

            switch (choices) {
                case Patient: {
                    patient.setUserId(user.getUserId());
                    patient.setRoleId(user.getRoleId());
                    patientFlow(roleId);
                    break;
                }
                case Doctor: {
                    doctor.setUserId(user.getUserId());
                    doctor.setRoleId(user.getRoleId());
                    doctorFlow(roleId);
                    break;

                }
                case BranchAdmin: {
                    adminFlow(roleId);
                    break;
                }
                case GobalAdmin: {
                    globalAdminFlow();
                    break;
                }
                default:
                    System.exit(0);
            }


        } else if (user.getEmail() == null) {
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver14));

        } else {
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver11));
        }
        sc.close();
    }


    /**
     * Patient flow.
     *
     * @param roleId the role id
     */
    void patientFlow(int roleId) {
        Boolean bool = true;
        while (bool) {
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver18));
            int choice = sc.nextInt();
            PatientEnum choices = PatientEnum.valueOf(choice);
            try {
                switch (choices) {

                    case UPDATE: // Update Details
                    {
                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS6));

                        getPatientDetails(flows);
                        setPatient(patient, id, name, email, password, this.roleId, age, area, city, state);

                        patient = patientServices.updatePatient(CSV_FILE_PATH, patient);

                        break;
                    }
                    case READ: {

                        patient = patientServices.readParticularPatient(patient);
                        displayPatient(patient);

                        break;
                    }
                    case SEE_ALL_DOCTOR: {
                        List<Doctor> doctorList = patientServices.readAllDoctors();
                        displayDoctor(doctorList);
                        break;

                    }
                    case SEE_ASSIGNED_DOCTOR: {
                        break;

                    }
                    case LOGOUT: {

                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver19));
                        bool = false;
                        break;
                    }

                    default:
                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver4));
                        System.exit(0);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                logger.error(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX8));
            }

        }
        //Restart Application
        BasicConfigurator.resetConfiguration();
        init();
    }


    private void doctorFlow(int roleId) {
        Boolean bool = true;
        while (bool) {
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver13));
            int choice = sc.nextInt();
            DoctorEnum choices = DoctorEnum.valueOf(choice);
            DoctorServices doctorServices = new DoctorServices();

            try {

                switch (choices) {
                    case UPDATE: {
                        getDoctorDetails();
                        setDoctor(doctor, id, name, email, password, imoNumber, specialization, roleId);
                        doctorServices.updateDoctor(doctor);
                        break;


                    }

                    case READ: {
                        doctor = doctorServices.readParticularDoctor(doctor);
                        displayDoctor(doctor);
//                        List<Doctor> doctorList=doctorServices.readAllDoctor(doctor);
//                        displayDoctor(doctorList);
                        break;
                    }
                    case SEE_ALL_PATIENT: {
                        List<Patient> patientList = doctorServices.readAllPatients();
                        displayPatient(patientList);
                        break;

                    }
                    case SEE_ASSIGNED_PATIENT: {
                        break;

                    }
                    case LOGOUT: {

                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver19));
                        bool = false;
                        break;
                    }

                    default: {
                        System.exit(0);
                    }
                }

            } catch (NullPointerException e) {
                logger.error(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX8));
            }
        }
        //Restart Application
        BasicConfigurator.resetConfiguration();
        init();

    }


    private void adminFlow(int roleId) {
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver15));
        int choice = sc.nextInt();
        BranchAdminEnum choices = BranchAdminEnum.valueOf(choice);
        switch (choices) {
            case DOCTOR: {
                adminPrivilages(BranchAdminEnum.DOCTOR);
                break;

            }
            case PATIENT: {
                adminPrivilages(BranchAdminEnum.PATIENT);
                break;

            }
            default: {

            }
        }


    }

    private void adminPrivilages(BranchAdminEnum branchAdminEnum) {
        Boolean bool = true;
        while (bool) {
            int choice;
            BranchAdminEnum doctorEnum = BranchAdminEnum.DOCTOR;
            BranchAdminEnum patientEnum = BranchAdminEnum.PATIENT;
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver2));

            if (branchAdminEnum == doctorEnum) {
                choice = sc.nextInt();
                AdminPrivilages choices = AdminPrivilages.valueOf(choice);
                DoctorServices doctorServices = new DoctorServices();
                switch (choices) {
                    case CREATE: {
                        getDoctorDetails();
                        setDoctor(doctor, id, name, email, password, imoNumber, specialization, roleId);
                        doctorServices.createDoctor(doctor);
                        break;

                    }
                    case UPDATE: {
                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver17));
                        doctor.setUserId(sc.nextInt());
                        getDoctorDetails();
                        setDoctor(doctor, id, name, email, password, imoNumber, specialization, roleId);
                        doctorServices.updateDoctor(doctor);
                        break;

                    }
                    case READPARTICULAR: {
                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver17));
                        doctor.setUserId(sc.nextInt());
                        doctor = doctorServices.readParticularDoctor(doctor);
                        displayDoctor(doctor);

                        break;

                    }
                    case READALL: {
                        List<Doctor> doctorList = doctorServices.readAllDoctor(doctor);
                        displayDoctor(doctorList);
                        break;

                    }
                    case DELETE: {
                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver17));
                        doctor.setUserId(sc.nextInt());
                        doctorServices.deleteDoctor(doctor);

                    }
                    case LOGOUT: {

                        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver19));
                        bool = false;
                        break;
                    }
                    default:


                }

            } else if (branchAdminEnum == patientEnum) {
                choice = sc.nextInt();
                AdminPrivilages choices = AdminPrivilages.valueOf(choice);
                DoctorServices doctorServices = new DoctorServices();
                try {
                    switch (choices) {
                        case CREATE: {
                            getPatientDetails(flows);
                            setPatient(patient, id, name, email, password, this.roleId, age, area, city, state);

                            try {
                                patient = patientServices.createPatient(CSV_FILE_PATH, patient);
                            } catch (InvalidException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;

                        }
                        case UPDATE: {
                            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver16));
                            patient.setUserId(sc.nextInt());
                            getPatientDetails(flows);
                            setPatient(patient, id, name, email, password, this.roleId, age, area, city, state);


                            patient = patientServices.updatePatient(CSV_FILE_PATH, patient);


                            break;

                        }
                        case READPARTICULAR: {
                            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver16));
                            patient.setUserId(sc.nextInt());
                            patient = patientServices.readParticularPatient(patient);

                            displayPatient(patient);


                            break;
                        }
                        case READALL: {


                            List<Patient> patientList = patientServices.readAllPatient(CSV_FILE_PATH, patient);

                            displayPatient(patientList);
                            break;

                        }
                        case DELETE: {
                            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver16));
                            patient.setUserId(sc.nextInt());
                            patientServices.deletePatient(CSV_FILE_PATH, patient.getUserId(), patient);
                            break;
                        }
                        case LOGOUT: {
                            user = null;
                            doctor = null;
                            patient = null;
                            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver19));
                            bool = false;
                        }
                        default:

                    }
                } catch (IOException e) {
                    logger.error(e);
                } catch (InvalidException e) {
                    logger.error(e);
                }

            }
        }
        //RestartApp
        BasicConfigurator.resetConfiguration();
        init();


    }

    private void globalAdminFlow() {
    }


    /**
     * Get patient details.
     *
     * @param flows the flows
     */
    void getPatientDetails(ImplEnum flows) {
        try {
            if (flows.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS12))) {

                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver3));
                id = sc.nextInt();
            }

            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS1));

            name = sc.next();
            if (flows.toString().equals(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS13))) {
                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS14));
                email = sc.next();
                logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS15));
                password = sc.next();

                //Not Require, it is a foreign key!
//				logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS17));
//				userId=sc.nextInt();


            }
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS2));
            age = sc.nextInt();
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS3));
            area = sc.next();
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS4));
            city = sc.next();
            logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.PS5));
            state = sc.next();


        } catch (InputMismatchException e) {
            logger.error(LOCAL_MESSAGES_BUNDLE.getString(Constants.EX3));
        }
    }

    /**
     * Sets patient.
     *
     * @param patient  the patient
     * @param id       the id
     * @param name     the name
     * @param email    the email
     * @param password the password
     * @param roleId   the role id
     * @param age      the age
     * @param area     the area
     * @param city     the city
     * @param state    the state
     */
    public void setPatient(Patient patient, int id, String name, String email, String password, int roleId, int age, String area, String city, String state) {


        patient.setAge(age);
        patient.setName(name);
        patient.setId(id);
        patient.setArea(area);
        patient.setCity(city);
        patient.setState(state);

        map.put(id, patient);
        patient.setEmail(email);
        patient.setPassword(password);
        patient.setRoleId(roleId);
        patient.setUsername(name);


    }

    /**
     * Display patient.
     *
     * @param patient the patient
     */
    public void displayPatient(Patient patient) {

        logger.info("\nUserId: " + patient.getUserId() + "\nName: " + patient.getUsername() + "\nEmail: " + patient.getEmail() + "\nPassword: " + patient.getPassword() + "\nRole ID: " + patient.getRoleId() + "\nAge: " +
                patient.getAge() + "\nArea: " + patient.getArea() + "\nCity: " + patient.getCity() + "\nState: " + patient.getState() + "\n");
    }

    /**
     * Display patient.
     *
     * @param patientList the patient list
     */
    public void displayPatient(List<Patient> patientList) {
        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getUserId() == 0) {
                logger.info("\nName: " + patientList.get(i).getUsername() + "\nEmail: " + patientList.get(i).getEmail() + "\n Age: " + patientList.get(i).getAge());
            } else {
                logger.info("\nUserId: " + patientList.get(i).getUserId() + "\nName: " + patientList.get(i).getUsername() + "\nEmail: " + patientList.get(i).getEmail() + "\nPassword: " + patientList.get(i).getPassword() + "\nRole: " + patientList.get(i).getRoleId() + "\nAge: " +
                        patientList.get(i).getAge() + "\nArea: " + patientList.get(i).getArea() + "\nCity: " + patientList.get(i).getCity() + "\nState: " + patientList.get(i).getState() + "\n");
            }
        }
    }

    private void getDoctorDetails() {
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.DS1));
        name = sc.next();

        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.DS2));
        email = sc.next();
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.DS3));
        password = sc.next();
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.DS4));
        imoNumber = sc.nextInt();
        logger.info(LOCAL_MESSAGES_BUNDLE.getString(Constants.DS5));
        specialization = sc.next();


    }

    private void setDoctor(Doctor doctor, int id, String name, String email, String password, int imoNumber, String specialization, int roleId) {
        doctor.setUsername(name);
        doctor.setEmail(email);
        doctor.setPassword(password);
        doctor.setImaNumber(imoNumber);
        doctor.setSpecialization(specialization);
        doctor.setRoleId(roleId);
    }

    private void displayDoctor(Doctor doctor) {
        logger.info("\nUserId: " + doctor.getUserId() + "\nName: " + doctor.getUsername() + "\nEmail: " + doctor.getEmail() + "\nPassword: " + doctor.getPassword() + "\nRole Id:" + doctor.getRoleId() + "\nIMA Number: " +
                doctor.getImaNumber() + "\nSpecialization: " + doctor.getSpecialization());

    }

    private void displayDoctor(List<Doctor> doctorList) {
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getUserId() == 0) {
                logger.info("\nName: " + doctorList.get(i).getUsername() + "\nSpecialization: " + doctorList.get(i).getSpecialization());
            } else {
                logger.info("\nUserId: " + doctorList.get(i).getUserId() + "\nName: " + doctorList.get(i).getUsername() + "\nEmail: " + doctorList.get(i).getEmail() + "\nPassword: " + doctorList.get(i).getPassword() + "\nRole Id:" + doctorList.get(i).getRoleId() + "\nIMA Number: " +
                        doctorList.get(i).getImaNumber() + "\nSpecialization: " + doctorList.get(i).getSpecialization() + "\n");
            }
        }

    }

}
