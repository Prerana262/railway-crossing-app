package com.railway.listener;

import com.railway.config.DatabaseProvider;
import com.railway.config.FlywayRunner;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppStartupListener implements ServletContextListener {
  public void contextInitialized(ServletContextEvent sce) {
//   FlywayRunner.run();
  }

  public void contextDestroyed(ServletContextEvent sce) {
  }
}
