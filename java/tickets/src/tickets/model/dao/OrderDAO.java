package tickets.model.dao;

import java.sql.*;
import tickets.model.dat.Order;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class OrderDAO extends AbstractDAO {
  static private OrderDAO instance = null;

  protected OrderDAO() {}

  public void create(Order order) throws DAOException {
    Connection con = null;
    PreparedStatement stmt = null;
    try {
      con = getConnection();
      String query = "insert into orders" +
                     " (id_flight, class, count, credit_card)"+
                     " values(?, ?, ?, ?)";
      stmt = con.prepareStatement(query);
      setInt(stmt, 1, new Integer(order.getFlightId()));
      setInt(stmt, 2, new Integer(order.getClassType()));
      setInt(stmt, 3, new Integer(order.getNumberOfPlaces()));
      setString(stmt, 4, order.getCreditCardNumber());
      stmt.executeUpdate();
      query = "select max(id_order) as id from orders";
      stmt = con.prepareStatement(query);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      int orderId = getInt(rs, 1).intValue();
      order.setId(orderId);
    } catch (SQLException e) {
      Util.debug("getCities()", e);
      throw new DAOException(e);
    } finally {
      close(con);
    }
    return;
  }

  static public OrderDAO getInstance() {
    if(instance == null) {
      instance = new OrderDAO();
    }
    return instance;
  }
}
