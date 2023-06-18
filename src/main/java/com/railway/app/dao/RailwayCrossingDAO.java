package com.railway.app.dao;

import com.railway.app.model.RailwayCrossing;
import com.railway.config.DatabaseProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RailwayCrossingDAO {

   private Connection connection;
   
   public RailwayCrossingDAO() {
      connection = DatabaseProvider.getConnection();
   }

   public boolean insertRailwayCrossing(RailwayCrossing crossing) {
      String query = "INSERT INTO railway_crossings (name, address, landmark, train_schedule, platform_in_charge, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
      
      try (PreparedStatement statement = connection.prepareStatement(query)) {
         statement.setString(1, crossing.getName());
         statement.setString(2, crossing.getAddress());
         statement.setString(3, crossing.getLandmark());
         statement.setTimestamp(4, Timestamp.valueOf(crossing.getTrainSchedule()));
         statement.setString(5, crossing.getPlatformInCharge());
         statement.setString(6, crossing.getStatus());
         
         int rowsAffected = statement.executeUpdate();
         return rowsAffected > 0;
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DatabaseProvider.close(connection);
      }
      
      return false;
   }


   public boolean update(RailwayCrossing crossing) {
      PreparedStatement statement = null;
      boolean success = false;

      try {
         String sql = "UPDATE railway_crossings SET name = ?, address = ?, landmark = ?, train_schedule = ?, platform_in_charge = ?, status = ? WHERE id = ?";
         statement = connection.prepareStatement(sql);
         statement.setString(1, crossing.getName());
         statement.setString(2, crossing.getAddress());
         statement.setString(3, crossing.getLandmark());
         statement.setTimestamp(4, Timestamp.valueOf(crossing.getTrainSchedule()));
         statement.setString(5, crossing.getPlatformInCharge());
         statement.setString(6, crossing.getStatus());
         statement.setInt(7, crossing.getId());

         // Execute the update query
         int rowsAffected = statement.executeUpdate();
         success = (rowsAffected > 0);
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         // Close the statement and connection
         DatabaseProvider.close(connection);
      }

      return success;
   }


   public List<RailwayCrossing> getAllRailwayCrossings() {
      List<RailwayCrossing> crossings = new ArrayList<>();
      String query = "SELECT * FROM railway_crossings";

      try (PreparedStatement statement = connection.prepareStatement(query);
           ResultSet resultSet = statement.executeQuery()) {

         while (resultSet.next()) {
            RailwayCrossing crossing = new RailwayCrossing();
            crossing.setId(resultSet.getInt("id"));
            crossing.setName(resultSet.getString("name"));
            crossing.setAddress(resultSet.getString("address"));
            crossing.setLandmark(resultSet.getString("landmark"));
            crossing.setTrainSchedule(resultSet.getTimestamp("train_schedule").toLocalDateTime());
            crossing.setPlatformInCharge(resultSet.getString("platform_in_charge"));
            crossing.setStatus(resultSet.getString("status"));

            crossings.add(crossing);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      finally {
         DatabaseProvider.close(connection);
      }

      return crossings;
   }


   public RailwayCrossing getRailwayCrossing(int id) {
      List<RailwayCrossing> crossings = new ArrayList<>();
      String query = "SELECT * FROM railway_crossings where id=?";

      try  {

         PreparedStatement statement = connection.prepareStatement(query);
         statement.setInt(1, id);

         ResultSet resultSet = statement.executeQuery();
         while (resultSet.next()) {
            RailwayCrossing crossing = new RailwayCrossing();
            crossing.setId(resultSet.getInt("id"));
            crossing.setName(resultSet.getString("name"));
            crossing.setAddress(resultSet.getString("address"));
            crossing.setLandmark(resultSet.getString("landmark"));
            crossing.setTrainSchedule(resultSet.getTimestamp("train_schedule").toLocalDateTime());
            crossing.setPlatformInCharge(resultSet.getString("platform_in_charge"));
            crossing.setStatus(resultSet.getString("status"));

            crossings.add(crossing);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      finally {
         DatabaseProvider.close(connection);
      }

      if(crossings.size() == 0)
      {
         throw new RuntimeException("no crossing found with id: "+id);
      }
      return crossings.get(0);
   }


   public void deleteRailwayCrossing(int id) {
      PreparedStatement statement = null;

      try {
         RailwayCrossingMappingDAO dao = new RailwayCrossingMappingDAO();
         dao.deleteMappingByCrossingId(id);
         String query = "DELETE FROM railway_crossings WHERE id = ?";
         statement = connection.prepareStatement(query);
         statement.setInt(1, id);
         statement.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DatabaseProvider.close(connection);
      }
   }


   public List<RailwayCrossing> searchByName(String name) {
      ResultSet resultSet = null;
      List<RailwayCrossing> crossings = new ArrayList<>();

      String sql = "SELECT * FROM railway_crossings WHERE name LIKE ?";

      try(PreparedStatement statement = connection.prepareStatement(sql)) {
         // Get a database connection

             // Prepare the SQL statement
         statement.setString(1, "%" + name + "%");

         // Execute the query
         resultSet = statement.executeQuery();

         // Iterate over the result set and create RailwayCrossing objects
         while (resultSet.next()) {
            RailwayCrossing crossing = new RailwayCrossing();
            crossing.setId(resultSet.getInt("id"));
            crossing.setName(resultSet.getString("name"));
            crossing.setAddress(resultSet.getString("address"));
            crossing.setLandmark(resultSet.getString("landmark"));
            crossing.setTrainSchedule(resultSet.getTimestamp("train_schedule").toLocalDateTime());
            crossing.setPlatformInCharge(resultSet.getString("platform_in_charge"));
            crossing.setStatus(resultSet.getString("status"));
            crossings.add(crossing);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         // Close the result set, statement, and connection
         DatabaseProvider.close(connection);
      }

      return crossings;
   }
}
