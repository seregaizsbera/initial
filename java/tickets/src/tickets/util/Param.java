package tickets.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Param предоставляет методы для получения информации о настройках приложения.
 * Считывает настройки из файла .properties, также содержит настройки,
 * используемые по умолчанию.
 */
public class Param {
  static private final String DB_USER_NAME_PROPERTY = "user";
  static private final String DB_USER_PASSWORD_PROPERTY = "password";
  static private final String DB_URL_PROPERTY = "url";
  static private final String DB_DRIVER_CLASS_PROPERTY = "driver_class";
  static private final String PROPERTIES_FILE = "tickets";
  static private final String ADMIN_NAME = "admin";
  static private final String ADMIN_PASSWORD = "admin_password";
  static private final String DATA_SOURCE_JNDI_NAME = "data_source_jndi_name";
  static private final String CONNECTION_SOURCE_CLASS_NAME =
      "connection_source_class_name";
  static private final String DEBUG_MODE="debug";
  static private Param instance;

  private final Properties properties;

  static public Param getInstance() {
    if(instance == null)
      instance = new Param();
    return instance;
  }

  public Param() {
    Properties defaults = new Properties();
    defaults.setProperty(DB_USER_NAME_PROPERTY, "bsa");
    defaults.setProperty(DB_USER_PASSWORD_PROPERTY, "bsa");
    defaults.setProperty(DB_URL_PROPERTY, "jdbc:odbc:tickets");
    defaults.setProperty(DB_DRIVER_CLASS_PROPERTY,
                         "sun.jdbc.odbc.JdbcOdbcDriver");
    defaults.setProperty(ADMIN_NAME, "root");
    defaults.setProperty(ADMIN_PASSWORD, "-1");
    defaults.setProperty(DATA_SOURCE_JNDI_NAME, "jdbc/Tickets");
    defaults.setProperty(CONNECTION_SOURCE_CLASS_NAME,
                         "tickets.model.connection.PoolConnectionSource");
    defaults.setProperty(DEBUG_MODE, "false");
    properties = new Properties(defaults);
    try {
      ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_FILE);
      for(Enumeration keys = bundle.getKeys(); keys.hasMoreElements();) {
        String key = (String)keys.nextElement();
        String value = bundle.getString(key);
        if(value != null)
          properties.setProperty(key, value);
      }
    }
    catch(Exception e) {
      Util.debug(e.getMessage());
    }
  }

  public String getDbUserName() {
    return properties.getProperty(DB_USER_NAME_PROPERTY);
  }

  public String getDbUserPassword() {
    return properties.getProperty(DB_USER_PASSWORD_PROPERTY);
  }

  public String getDbUrl() {
    return properties.getProperty(DB_URL_PROPERTY);
  }

  public String getDbDriverClass() {
    return properties.getProperty(DB_DRIVER_CLASS_PROPERTY);
  }

  public String getAdminName() {
    return properties.getProperty(ADMIN_NAME);
  }

  public int getAdminPassword() {
    return Integer.parseInt(properties.getProperty(ADMIN_PASSWORD));
  }

  public String getDataSourceJndiName() {
    return properties.getProperty(DATA_SOURCE_JNDI_NAME);
  }

  public String getConnectionSourceClassName() {
    return properties.getProperty(CONNECTION_SOURCE_CLASS_NAME);
  }

  public boolean getDebugMode() {
    return Boolean.valueOf(properties.getProperty(DEBUG_MODE)).booleanValue();
  }
}
