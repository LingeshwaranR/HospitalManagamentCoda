package org.gradle.servlet;

import global.coda.hopsitalmanagement.enums.ImplEnum;
import global.coda.hopsitalmanagement.patientdetails.model.Patient;
import global.coda.hopsitalmanagement.svc.PatientServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Logout servlet.
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"LogoutServlet"}, loadOnStartup = 1)
public class LogoutServlet extends HttpServlet {
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
     *
     * @param request  is the request.
     * @param response is the response.
     * @throws ServletException handles servlet exception.
     * @throws IOException      handles IO exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print("Hello, World!");
        HttpSession session = request.getSession(false);

        session.removeAttribute("userId");

        session.invalidate();
        response.sendRedirect("index.jsp");
    }
}

