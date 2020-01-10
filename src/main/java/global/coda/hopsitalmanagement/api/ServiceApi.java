package global.coda.hopsitalmanagement.api;

import global.coda.hopsitalmanagement.enums.ImplEnum;
import global.coda.hopsitalmanagement.exception.InvalidException;
import global.coda.hopsitalmanagement.exception.NotAuthorizedException;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import global.coda.hopsitalmanagement.patientdetails.model.User;
import global.coda.hopsitalmanagement.svc.AuthenticationServices;
import global.coda.hopsitalmanagement.svc.PatientServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * The type Hello service jersey.
 */
@Path("/api")
public class ServiceApi {
    /**
     * Hello patient.
     *
     * @param id the id
     * @return the patient
     */
    @GET
    @Path("/print/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient hello(@PathParam("id") int id) {
        int flow = 2;
        ImplEnum flows = ImplEnum.valueOf(flow);
        Patient patient = new Patient();
        patient.setUserId(id);
        try {

            PatientServices patientServices = new PatientServices("asaad", flows);
            patient = patientServices.readParticularPatient(patient);
        } catch (InvalidException e) {
            e.printStackTrace();
        }
        return patient;

    }

    /**
     * Login object.
     *
     * @param email    the email
     * @param password the password
     * @return object
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Object login(@FormParam("email") String email,
                        @FormParam("password") String password) {
        //Login Service

        int flow = 2;
        ImplEnum flows = ImplEnum.valueOf(flow);
        Patient patient = new Patient();

        AuthenticationServices authentication = new AuthenticationServices();
        User user = authentication.login(email, password);

        System.out.println(user);

        if (user.getPassword() != null) {
            patient.setUserId(user.getUserId());


            PatientServices patientServices = new PatientServices("asaad", flows);
            try {
                patient = patientServices.readParticularPatient(patient);
            } catch (InvalidException e) {
                e.printStackTrace();
            }
        } else {
            // An unauthorized user tries to enter

            throw new NotAuthorizedException();
        }
        return patient;


    }
}
