package tickets.model.dao;

import java.sql.*;
import java.util.*;
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

public class OrderListDAO extends AbstractDAO {
  static private OrderListDAO instance = null;

  protected OrderListDAO() {}

  private void populate(ResultSet rs, Order order) throws SQLException {
    order.setId(getInt(rs, "id_order").intValue());
    order.setFlightId(getInt(rs, "id_flight").intValue());
    order.setClassType(getInt(rs, "class_type").intValue());
    order.setNumberOfPlaces(getInt(rs, "number_of_places").intValue());
  }

  public Collection getOrders() {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Collection result = new ArrayList();
    try {
      con = getConnection();
      String query = "select id_order, id_flight, class as class_type, count as number_of_places from orders";
      stmt = con.prepareStatement(query);
      rs = stmt.executeQuery();
      while(rs.next()) {
        Order order = new Order();
        populate(rs, order);
        result.add(order);
      }
    } catch(SQLException e) {
      Util.debug("getOrders()", e);
      throw new DAOException(e);
    } finally {
      close(con);
    }
    return result;
  }

  static public OrderListDAO getInstance() {
    if(instance == null) {
      instance = new OrderListDAO();
    }
    return instance;
  }
}

