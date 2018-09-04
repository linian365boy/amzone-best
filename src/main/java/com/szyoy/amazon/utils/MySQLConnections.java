package com.szyoy.amazon.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author niange
 * @ClassName: MySQLConnections
 * @desp:
 * @date: 2018/8/21 下午11:26
 * @since JDK 1.7
 */
public class MySQLConnections {

    private String driver = "";
    private String dbURL = "";
    private String user = "";
    private String password = "";
    private static MySQLConnections connection = null;

    private MySQLConnections() throws Exception {
        driver = "com.mysql.jdbc.Driver";
        dbURL = "jdbc:mysql://127.0.0.1:3306/best_sale";
        user = "root";
        password = "root";
    }

    public static Connection getConnection() {
        Connection conn = null;
        if (connection == null) {
            try {
                connection = new MySQLConnections();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            Class.forName(connection.driver);
            conn = DriverManager.getConnection(connection.dbURL,
                    connection.user, connection.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void batchInsert(){

    }
}
