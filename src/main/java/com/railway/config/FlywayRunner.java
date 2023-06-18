package com.railway.config;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.sql.SQLException;

public class FlywayRunner {

  public static void run()
  {
    DataSource dataSource = DatabaseProvider.getDataSource();
    Flyway flyway = Flyway.configure()
        .dataSource(dataSource)
        .locations("classpath:db/migration")
        .load();

    // Migrate the database
    flyway.clean(); // remove everyhting from db
    flyway.migrate(); // apply the script

    try {
      DatabaseProvider.close(dataSource.getConnection());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
