package tickets.model.dao;

import java.sql.Connection;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * ќтдел€ет функционал AbstractDAO от конкреной
 * способа получени€ соединени€ с базой данных.
 * @author SergeyLo
 * @date 07.08.2002
 * @time 12:35:08
 */
public interface ConnectionSource {
    /**
     * ¬озвращает соединение с базой данных. ¬с€кое полученное таким
     * образом соединение в ќЅя«ј“≈Ћ№Ќќћ пор€дке должно быть осовобождено
     * вызовом метода void close(Connection conn) throws DAOException.
     * @return соединение с базой данных
     * @throws DAOException в случае если подсоединитьс€ в базе данных не
     * представл€ет€€ возможным.
     */
    Connection getConnection() throws DAOException;
    /**
     * ќсовобождает ресурсы отведеннные под соединение.
     * @param conn соединение с базой данных полученоое с помощью метода
     * Connection getConnection() throws DAOException.
     * @throws DAOException в случае если возникают проблемы с освобождением
     * ресурсов отведенных под соединение.
     */
    void close(Connection conn) throws DAOException;
}

