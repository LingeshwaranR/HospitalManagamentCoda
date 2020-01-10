package org.gradle.servlet;

import global.coda.hopsitalmanagement.enums.ImplEnum;
import global.coda.hopsitalmanagement.exception.InvalidException;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import global.coda.hopsitalmanagement.svc.PatientServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Edit servlet.
 */
@WebServlet(name = "EditServlet", urlPatterns = {"EditServlet"}, loadOnStartup = 1)
public class EditServlet extends HttpServlet {

    private static String CSV_FILE_PATH;
    /**
     * The Flow.
     */
    private int flow = 2;
    /**
     * The Flows.
     */
   private ImplEnum flows = ImplEnum.valueOf(flow);
    /**
     * The Patient services.
     */
   private PatientServices patientServices = new PatientServices(CSV_FILE_PATH, flows);
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
        HttpSession session = request.getSession(true);
        if (session.getAttribute("userId") != null) {
            Patient patient = new Patient();
            patient.setUserId((Integer) session.getAttribute("userId"));
            try {
                patient = patientServices.readParticularPatient(patient);
            } catch (InvalidException e) {
                e.printStackTrace();
            }

            request.setAttribute("patient", patient);
            request.getRequestDispatcher("EditPatientDetails.jsp").forward(request, response);
            response.sendRedirect("EditPatientDetails.jsp");


        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
