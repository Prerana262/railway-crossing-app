package com.railway.app.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.railway.app.dao.RailwayCrossingDAO;
import com.railway.app.model.RailwayCrossing;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/govt")
public class GovtRailwayServlet extends HttpServlet {
    private RailwayCrossingDAO crossingDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the DAO with the appropriate connection
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("do post for creating record");


        if(action.equals("delete"))
        {
            int id = Integer.parseInt(request.getParameter("id"));

            // Call the delete method in DAO to delete the record
            crossingDAO = new RailwayCrossingDAO();

            crossingDAO.deleteRailwayCrossing(id);

            // Redirect back to the list page
            response.sendRedirect(request.getContextPath() + "/govt");


        }
        if(action.equals("update"))
        {
            System.out.println("do update for record");
            // Get form data
            Integer id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String landmark = request.getParameter("landmark");
            String trainSchedule = request.getParameter("trainSchedule");
            System.out.println(trainSchedule);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(trainSchedule, formatter);
            String platformInCharge = request.getParameter("platformInCharge");
            String status = request.getParameter("status");

            // Create a new RailwayCrossing object
            RailwayCrossing crossing = new RailwayCrossing();
            crossing.setName(name);
            crossing.setAddress(address);
            crossing.setLandmark(landmark);
            crossing.setTrainSchedule(dateTime);
            crossing.setPlatformInCharge(platformInCharge);
            crossing.setStatus(status);
            crossing.setId(id);

            // Save the object to the database using the DAO
            System.out.println("do post for creating record");

            crossingDAO = new RailwayCrossingDAO();
            boolean success = crossingDAO.update(crossing);
            if (success) {
                response.sendRedirect("success.jsp"); // Redirect to a success page
            } else {
                response.sendRedirect("error.jsp"); // Redirect to an error page
            }
        }
        if (action.equals("create")) {
            System.out.println("do post for creating record");
            // Get form data
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String landmark = request.getParameter("landmark");
            String trainSchedule = request.getParameter("trainSchedule");
            System.out.println(trainSchedule);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(trainSchedule, formatter);
            String platformInCharge = request.getParameter("platformInCharge");
            String status = request.getParameter("status");

            // Create a new RailwayCrossing object
            RailwayCrossing crossing = new RailwayCrossing();
            crossing.setName(name);
            crossing.setAddress(address);
            crossing.setLandmark(landmark);
            crossing.setTrainSchedule(dateTime);
            crossing.setPlatformInCharge(platformInCharge);
            crossing.setStatus(status);

            // Save the object to the database using the DAO
            System.out.println("do post for creating record");

            crossingDAO = new RailwayCrossingDAO();
            boolean success = crossingDAO.insertRailwayCrossing(crossing);
            if (success) {
                response.sendRedirect("success.jsp"); // Redirect to a success page
            } else {
                response.sendRedirect("error.jsp"); // Redirect to an error page
            }
        }
        // Add other cases for update, delete, and read operations
    }

    @Override
    public void destroy() {
        super.destroy();
        // Clean up resources, such as closing the database connection
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Get the list of RailwayCrossings from the DAO
        String action = request.getParameter("action");

        if("edit".equals(action)){
            crossingDAO = new RailwayCrossingDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            RailwayCrossing railwayCrossing = crossingDAO.getRailwayCrossing(id);
            request.setAttribute("crossing", railwayCrossing);
            request.getRequestDispatcher("govt/railway-edit-form.jsp").forward(request, response);

            return;
        }
        crossingDAO = new RailwayCrossingDAO();
        List<RailwayCrossing> crossings = crossingDAO.getAllRailwayCrossings();

        // Set the list as an attribute in the request
        request.setAttribute("crossings", crossings);

        // Forward the request to the JSP for rendering
        request.getRequestDispatcher("govt/railway-list.jsp").forward(request, response);
    }
}
