package com.railway.app.servlet;

import com.railway.app.model.RegistrationForm;
import com.railway.app.dao.RegistrationFormDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private RegistrationFormDAO registrationFormDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        registrationFormDAO = new RegistrationFormDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the registration form page
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from request parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create a RegistrationForm object
        RegistrationForm form = new RegistrationForm(name, email, password);
        form.setRole("PUBLIC");
        // Save the registration form
        boolean success = registrationFormDAO.saveRegistrationForm(form);

        if (success) {
            // Registration successful, redirect to a success page
            response.sendRedirect("success.jsp");
        } else {
            // Registration failed, redirect to an error page
            response.sendRedirect("error.jsp");
        }
    }
}
