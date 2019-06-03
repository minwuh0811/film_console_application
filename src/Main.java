import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Main {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static Administrator administrator;
    static String DBUserName = "root";
    static String DBPassword = "password";
    static String DBMySQLUrl="jdbc:mysql://192.168.99.100:3306/firstdb";
    static String administratorDBTable = "administrator";
    static String filmDBTable = "film";
    static String ticketDBTable = "ticket";
    static JTable table;// 声明表格

    public static void main(String[] args) {
        String string= JOptionPane.showInputDialog("Administrator Press 1\nUser Press 2\nExit Press 3");
        int choice=Integer.parseInt(string);
        switch (choice) {
            case(1) :
                String administrator=JOptionPane.showInputDialog("AdministratorName");
                String password=JOptionPane.showInputDialog("Password");

        }


        String num=JOptionPane.showInputDialog("Do you want to book ticket?\n Press 1 to book ticket\n Press 2 to see list of Tickets\n Press 3 Exit");
        int choice = Integer.parseInt(num);
        switch (choice)  {
            case(1):
               String FilmID=JOptionPane.showInputDialog("Enter FilmID");


        }


    }

    public static void localData(String administratorDBTable){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from " + administratorDBTable + ";");
            while (resultSet.next()) {
                Administrator administrator = new Administrator(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }

            resultSet=statement.executeQuery("select * from " + filmDBTable + ";");
            while (resultSet.next()) {
               film film = new film(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }

        } catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
}

    }



}
