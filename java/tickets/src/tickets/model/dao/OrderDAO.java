package tickets.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tickets.model.dat.Order;
import tickets.model.valueobjects.Currency;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс OrderDAO предоставляет доступ к заказу на уровне базы данных
 */
public class OrderDAO extends AbstractDAO {
  static private OrderDAO instance = null;

  protected OrderDAO() {}

  public void create(Order order) throws DAOException {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
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
      close(stmt);
      query = "select a.id_order, a.count*b.price_" + order.getClassType() +
              "_class from orders as a join flights as b on" +
              " a.id_flight=b.id_flight" +
              " where a.id_order=(select max(id_order) from orders)";
      stmt = con.prepareStatement(query);
      rs = stmt.executeQuery();
      rs.next();
      int orderId = getInt(rs, 1).intValue();
      Currency price =
          new Currency(new BigDecimal(getFloat(rs, 2).doubleValue()));;
      order.setId(orderId);
      order.setPrice(price);
    } catch (SQLException e) {
      Util.debug("getCities()", e);
      throw new DAOException(e);
    } finally {
      close(rs);
      close(stmt);
      close(con);
    }
  }

  static public OrderDAO getInstance() {
    if(instance == null) {
      instance = new OrderDAO();
    }
    return instance;
  }
}
