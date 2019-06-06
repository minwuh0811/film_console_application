import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

public class DataBaseConnection {
    private Connection connection;
    private Statement statement;
    private String DBMySQLUrl;
    private String DBUserName;
    private String DBPassword;

    public DataBaseConnection(String DBMySQLUrl, String DBUserName, String DBPassword) {
        this.DBMySQLUrl = DBMySQLUrl;
        this.DBUserName = DBUserName;
        this.DBPassword = DBPassword;
        try {this.statement=statement();} catch (Exception e) {JOptionPane.showMessageDialog(null,e);}
    }

    private Statement statement() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
        statement = connection.createStatement();
        return statement;
    }

    public Statement getStatement() {
        return statement;
    }
}
