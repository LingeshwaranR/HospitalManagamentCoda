package org.gradle.servlet;

import global.coda.hopsitalmanagement.applicationconstants.Constants;
import global.coda.hopsitalmanagement.enums.ImplEnum;
import global.coda.hopsitalmanagement.exception.InvalidException;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import global.coda.hopsitalmanagement.patientdetails.model.User;
import global.coda.hopsitalmanagement.svc.AuthenticationServices;
import global.coda.hopsitalmanagement.svc.PatientServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Login servlet.
 *
 * @author Lingesh
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"LoginServlet"}, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
    private User user = new User();
    private Patient patient = new Patient();
    private  PatientServices patientServices;
    private ImplEnum flows;

    private static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle("messages",
            Locale.getDefault());
    private static final String CSV_FILE_PATH = LOCAL_MESSAGES_BUNDLE.getString(Constants.FILE);

    /**
     * do get is used to get.
     * @param request is the request.
     * @param response is the response.
     * @throws ServletException handles servlet exception.
     * @throws IOException handles IO exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print("Hello, World!");


    }

    /**
     * used to handle post.
     * @param request is the reqest.
     * @param response is the response.
     * @throws ServletException handles servlet exception.
     * @throws IOException handles io exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();


        //Login Service
        AuthenticationServices authentication =  new AuthenticationServices();
        user = authentication.login(email, password);

        if (user.getPassword() != null) {
            out.println(LOCAL_MESSAGES_BUNDLE.getString(Constants.Driver10));
           int flow = 2;
            flows = ImplEnum.valueOf(flow);
            patient.setUserId(user.getUserId());
            patient.setRoleId(user.getRoleId());

            patientServices = new PatientServices(CSV_FILE_PATH, flows);


            try {
                patient = patientServices.readParticularPatient(patient);


            } catch (InvalidException e) {
                e.printStackTrace();
            }
            HttpSession session = request.getSession();
            response.setHeader("Cache-Control", "no-store,must-validate");
            session.setAttribute("userId", patient.getUserId());
            request.setAttribute("patient", patient);
            request.getRequestDispatcher("Patient.jsp").forward(request, response);
            if (patient != null) {
                response.sendRedirect("Patient.jsp");
            } else {
                response.sendRedirect("index.html");
            }
        } else {
            out.println("Email Or Pass word Wrong");
        }

    }
}
