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
 * �������� ���������� AbstractDAO �� ���������
 * ������� ��������� ���������� � ����� ������.
 * @author SergeyLo
 * @date 07.08.2002
 * @time 12:35:08
 */
public interface ConnectionSource {
    /**
     * ���������� ���������� � ����� ������. ������ ���������� �����
     * ������� ���������� � ������������ ������� ������ ���� ������������
     * ������� ������ void close(Connection conn) throws DAOException.
     * @return ���������� � ����� ������
     * @throws DAOException � ������ ���� �������������� � ���� ������ ��
     * �������������� ���������.
     */
    Connection getConnection() throws DAOException;
    /**
     * ������������ ������� ����������� ��� ����������.
     * @param conn ���������� � ����� ������ ���������� � ������� ������
     * Connection getConnection() throws DAOException.
     * @throws DAOException � ������ ���� ��������� �������� � �������������
     * �������� ���������� ��� ����������.
     */
    void close(Connection conn) throws DAOException;
}

