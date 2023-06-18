package com.railway.app.servlet;

import com.railway.app.dao.RegistrationFormDAO;
import com.railway.app.model.RegistrationForm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.rmi.RemoteException;

@WebServlet("/authenticate")
public class AuthenticateServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve form data from request parameters
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Validate user credentials (you may use your own validation logic here)
    RegistrationForm registrationForm = validateUserCredentials(email, password);



    if (registrationForm != null) {
      // Create a session and store user information
      HttpSession session = request.getSession();
      session.setAttribute("email", email);

      // Redirect to the home page or any other authorized page
      if (registrationForm.getRole().equals("GOVT")) {
        response.sendRedirect("govt");
      } else {
        response.sendRedirect("public");
      }
    } else {
      // Redirect back to the login page with an error message
      response.sendRedirect("login?error=1");
    }
  }

  private RegistrationForm validateUserCredentials(String email, String password) {
    RegistrationFormDAO registrationFormDAO = new RegistrationFormDAO();
    RegistrationForm registrationForm = registrationFormDAO.getRegistrationFormByEmail(email);
    if (registrationForm == null) {
      throw new RuntimeException("user account not found with email: " + email);
    }
    boolean flag = email.equals(registrationForm.getEmail()) && password.equals(registrationForm.getPassword());
    if (!flag) {
      throw new RuntimeException("login failed");

    }
    return registrationForm;
  }
}
