package tickets.model.dao;

import tickets.util.RuntimeGenericException;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * ѕробрасываетс€ в случае возникновени€ исключительных ситуаций при работе на
 * уровне доступа к базе данных.
 */
public class DAOException extends RuntimeGenericException {
  public DAOException() {
  }

  public DAOException(String message) {
    super(message);
  }

  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }

  public DAOException(Throwable cause) {
    super(cause);
  }
}
