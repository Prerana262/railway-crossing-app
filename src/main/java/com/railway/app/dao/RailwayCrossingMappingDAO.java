package com.railway.app.dao;

import com.railway.app.model.RailwayCrossingMapping;
import com.railway.config.DatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RailwayCrossingMappingDAO {

  private static final String INSERT_QUERY = "INSERT INTO railway_crossing_mapping (email, railway_crossing_id) VALUES (?, ?)";
  private static final String SELECT_QUERY = "SELECT id, railway_crossing_id FROM railway_crossing_mapping WHERE email = ?";
  private static final String DELETE_MAPPING_QUERY = "DELETE FROM railway_crossing_mapping WHERE email = ? AND railway_crossing_id = ?";
  private static final String DELETE_CROSSING_ID = "DELETE FROM railway_crossing_mapping WHERE railway_crossing_id = ?";


  // Insert a new mapping into the database
  public boolean saveMapping(RailwayCrossingMapping mapping) {
    boolean success = false;
    try (Connection connection = DatabaseProvider.getConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
      // Get a database connection

      // Prepare the SQL statement
      statement.setString(1, mapping.getEmail());
      statement.setInt(2, mapping.getRailwayCrossingId());

      // Execute the statement
      int rowsAffected = statement.executeUpdate();

      // Check if the insertion was successful
      if (rowsAffected > 0) {
        success = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Close the statement and connection
    }

    return success;
  }


  public List<RailwayCrossingMapping> getMappingsByEmail(String email) {
    ResultSet resultSet = null;
    List<RailwayCrossingMapping> mappings = new ArrayList<>();

    try (Connection connection = DatabaseProvider.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
      // Get a database connection

      // Prepare the SQL statement
      statement.setString(1, email);

      // Execute the query
      resultSet = statement.executeQuery();

      // Iterate over the result set and create RailwayCrossingMapping objects
      while (resultSet.next()) {
        RailwayCrossingMapping mapping = new RailwayCrossingMapping();
        mapping.setId(resultSet.getInt("id"));
        mapping.setEmail(email);
        mapping.setRailwayCrossingId(resultSet.getInt("railway_crossing_id"));
        mappings.add(mapping);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Close the result set, statement, and connection
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return mappings;
  }


  public boolean deleteMapping(String email, int crossingId) {
    boolean success = false;

    try (Connection connection = DatabaseProvider.getConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_MAPPING_QUERY);) {
      // Get a database connection

      // Prepare the SQL statement
      statement.setString(1, email);
      statement.setInt(2, crossingId);

      // Execute the statement
      int rowsAffected = statement.executeUpdate();

      // Check if deletion was successful
      if (rowsAffected > 0) {
        success = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Close the statement and connection
    }

    return success;
  }


  public boolean deleteMappingByCrossingId(int crossingId) {
    boolean success = false;

    try (Connection connection = DatabaseProvider.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CROSSING_ID)) {
      // Get a database connection

      // Prepare the SQL statement
      statement.setInt(1, crossingId);

      // Execute the statement
      int rowsAffected = statement.executeUpdate();

      // Check if deletion was successful
      if (rowsAffected > 0) {
        success = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Close the statement and connection
    }

    return success;
  }

}
