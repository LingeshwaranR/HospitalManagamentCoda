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
 * The type Update servlet.
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"UpdateServlet"}, loadOnStartup = 1)
public class UpdateServlet extends HttpServlet {
    /**
     * The Patient.
     */
    private Patient patient;
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
     * do post is used to get.
     * @param request is the request.
     * @param response is the response.
     * @throws ServletException handles servlet exception.
     * @throws IOException handles IO exception.
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

            patient.setUsername(request.getParameter("username"));
            patient.setEmail(request.getParameter("email"));
            patient.setPassword(request.getParameter("password"));
            patient.setAge(Integer.parseInt(request.getParameter("age")));
            patient.setArea(request.getParameter("area"));
            patient.setCity(request.getParameter("city"));
            patient.setState(request.getParameter("state"));

            try {

                patient = patientServices.updatePatient(CSV_FILE_PATH, patient);
//            PrintWriter out = response.getWriter();
//            out.println(patient.toString());
//                out.println("DETAILS UPDATED SUCCESSFULLY!");
//                response.setContentType("text/html; charset=UTF-8");
//                out.println("<html>"+
//                        "<body>"+
//
//
//                        "<a href=\" LogoutServlet\">Logout</a>"+
//                        "</body>"+
//                        "</html>");
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("Patient.jsp").forward(request, response);


            } catch (InvalidException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("index.jsp");
        }

    }
}
