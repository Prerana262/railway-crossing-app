package com.railway.app.dao;

import com.railway.app.model.RegistrationForm;
import com.railway.config.DatabaseProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationFormDAO {

  private static final String INSERT_QUERY = "INSERT INTO users (username, email, password, role) VALUES (?,?, ?, ?)";
  private static final String SELECT_QUERY = "SELECT username, email, password, role FROM users WHERE email = ?";

  public boolean saveRegistrationForm(RegistrationForm form) {
    try (Connection connection = DatabaseProvider.getConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

      statement.setString(1, form.getName());
      statement.setString(2, form.getEmail());
      statement.setString(3, form.getPassword());
      statement.setString(4, form.getRole());

      int rowsAffected = statement.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public RegistrationForm getRegistrationFormByEmail(String email) {
    try (Connection connection = DatabaseProvider.getConnection();
         PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {

      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        String name = resultSet.getString("username");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        RegistrationForm registrationForm = new RegistrationForm(name, email, password);
        registrationForm.setRole(role);
        return registrationForm;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
}
