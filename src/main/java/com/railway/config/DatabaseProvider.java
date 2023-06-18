package com.railway.config;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DatabaseProvider {

  private static final String JDBC_URL="jdbc:mysql://localhost:3306/railway";
  private static final String USERNAME="root";
  private static final String PASSWORD="@Uvzain262000";

  public static DataSource getDataSource() {

    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(JDBC_URL);
    config.setUsername(USERNAME);
    config.setDriverClassName(Driver.class.getName());
    config.setPassword(PASSWORD);
    config.setMaximumPoolSize(100);

    return new HikariDataSource(config);
  }

  public static Connection getConnection() {
    try {
      Class.forName(Driver.class.getName());
      Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
      return connection;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static void close(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void commit(Connection connection) {
    if (connection != null) {
      try {
        connection.commit();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void autoCloseConnections()
  {
    DataSource dataSource = getDataSource();

    // Create a scheduled executor service to periodically close idle connections
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleAtFixedRate(() -> {
      try (Connection connection = dataSource.getConnection()) {
        // Perform a simple query to keep the connection active
        connection.createStatement().execute("SELECT 1");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }, 1, 30, TimeUnit.SECONDS); // Adjust the interval as needed

    // Your application logic here

    // Shutdown the executor service when the application is shutting down
    executorService.shutdown();
  }
}
