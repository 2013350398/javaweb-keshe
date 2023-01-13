package main.DAO.Impl;

import main.DAO.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 用不到

public class DAOSqlServer implements DAO {

    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=keshe;user=sa;password=sa;Trust Server Certificate=True;encrypt=false";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("无法找到驱动类");
        }
        catch(SQLException sqlE) {
            System.out.println("sqlerror");
            sqlE.printStackTrace();
        }

        return conn;
    }
}