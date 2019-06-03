import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;


public class Main {
    static film filmCurrent;
    static Connection connection;
    static Statement statement;
    static ResultSet resultSetAdmin;
    static ResultSet resultSetFilm;
    static ResultSet resultSetTicket;
    static String DBUserName = "root";
    static String DBPassword = "password";
    static String DBMySQLUrl="jdbc:mysql://192.168.99.100:3306/db_film";
    static String administratorDBTable = "admin";
    static String filmDBTable = "film";
    static String ticketDBTable = "ticket";
    static JTable table;// 声明表格


    public static void main(String[] args) {
        String path = System.getProperty("user.home")
                + java.io.File.separator + "IdeaProjects"
                + java.io.File.separator + "film_konsollapplikation"
                + java.io.File.separator + "src";
        AdministratorStorage administratorStorageBin = new AdministratorStorage("Administrator", path);
        FilmStorage filmStorageBin=new FilmStorage("Film",path);

        localData();
        String string = JOptionPane.showInputDialog("Administrator Press 1\nUser Press 2\nExit Press 3");
        int choice = Integer.parseInt(string);
        boolean control=true;
        while (control) {
            switch (choice) {
                case (1):
                    String admin = JOptionPane.showInputDialog("Add New Administrator Press 1\nAdministrator Login Press 2\nExit Press 3");
                    int admin_choice = Integer.parseInt(admin);
                    switch (admin_choice) {
                        case (1):
                            String FirstName = JOptionPane.showInputDialog("Enter Your First Name:");
                            String LastName = JOptionPane.showInputDialog("Enter Your Last Name:");
                            String AdministratorNickName = JOptionPane.showInputDialog("Enter Your Nick Name:");
                            String AdminPassword = JOptionPane.showInputDialog("Enter Your Password");
                            Administrator administratorNew = new Administrator(FirstName, LastName, AdministratorNickName, AdminPassword);
                            administratorStorageBin.addAdministrator(administratorNew);
                            insertSQLDataAdmin(administratorNew);
                            break;
                        case (2):
                            String loginName = JOptionPane.showInputDialog("Enter your login Name:");
                            String password = JOptionPane.showInputDialog("Enter password");
                            if (login(administratorStorageBin.getAdministratorlist(), loginName, password)) {
                                JOptionPane.showMessageDialog(null, "Login Successfully");
                                String LoginChoice = JOptionPane.showInputDialog("Add a Film Press 1\nRemove a Film Press 2\nChange the Number of Ticket in the Storage Press 3\nExist 4");
                                int loginChoice=Integer.parseInt(LoginChoice);
                                switch (loginChoice) {
                                    case (1):
                                        String FilmName = JOptionPane.showInputDialog("Enter Film Name:");
                                        String AgeLimitation = JOptionPane.showInputDialog("What is the AgeLimitation:");
                                        int ageLimitation = Integer.parseInt(AgeLimitation);
                                        film newFilm = new film(FilmName, ageLimitation);
                                        filmStorageBin.addfilm(newFilm);
                                        insertSQLDataFilm(newFilm);
                                        break;
                                    case(4):
                                        JOptionPane.showMessageDialog(null, "Exist");
                                        break;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "LoginName or Password not Exist");
                            }
                            break;


                        case (3):
                            JOptionPane.showMessageDialog(null, "Exist");
                            break;
                    }
                case (2):
                    String User = JOptionPane.showInputDialog("Buy Ticket Press 1\nFilmList Press 2\nExit Press 3");
                    int choice_user=Integer.parseInt(User);
                    switch (choice_user) {
                        case (1):
                            String FilmID=JOptionPane.showInputDialog("Enter FilmID");
                            String userAge=JOptionPane.showInputDialog("What is your age?");
                            int Age=Integer.parseInt(userAge);
                            String Discount=JOptionPane.showInputDialog("Do you have BonusCard? 1: Yes, 0: No");
                            String TicketNum=JOptionPane.showInputDialog("How Much Ticket Do you Need?");
                            int ticketNum=Integer.parseInt(TicketNum);
                            String Mate=JOptionPane.showInputDialog("Do you have a mate with you? 1: Yes, 0: No");

                    }

            }
        }
    }

    public static void localData() {
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
                statement = connection.createStatement();
                resultSetAdmin = statement.executeQuery("select * from " + administratorDBTable + ";");
                AdministratorStorage administratorstorageSQL=new AdministratorStorage();
                int n=0;
            while (resultSetAdmin.next()) {
                Administrator administrator = new Administrator(resultSetAdmin.getString(1), resultSetAdmin.getString(2), resultSetAdmin.getString(3), resultSetAdmin.getString(4), resultSetAdmin.getString(5));
                administratorstorageSQL.addAdministratorSQLDB(administrator);
            }
            resultSetFilm = statement.executeQuery("select * from " + filmDBTable + ";");
            FilmStorage filmstorageSQL=new FilmStorage();
            while (resultSetFilm.next()) {
                film film = new film(resultSetFilm.getString(1), resultSetFilm.getString(2), resultSetFilm.getInt(3));
                filmstorageSQL.addFilmDBSQL(film);
            }
            resultSetTicket = statement.executeQuery("select * from " + ticketDBTable + ";");
            ticketStorage ticketstorageSQL=new ticketStorage();
            while (resultSetTicket.next()) {
                String filmID = resultSetTicket.getString(2);
                filmstorageSQL.searchFilm(filmID,filmstorageSQL.getFilmlist());
                ticket ticket = new ticket(resultSetTicket.getString(1), filmCurrent, resultSetTicket.getDouble(3), resultSetTicket.getInt(4));
                ticketstorageSQL.addTicket(ticket);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void insertSQLDataAdmin(Administrator administrator){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string= String.format("INSERT INTO " + administratorDBTable +
                    " VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",administrator.getAdministratorID(), administrator.getFirstname(), administrator.getLastname(), administrator.getAdministratorName(),administrator.getPassword());
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public static void insertSQLDataFilm(film film){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string= String.format("INSERT INTO " + filmDBTable +
                    " VALUES(\"%s\",\"%s\",\"%d\");",film.getFilmID(), film.getName(), film.getLimitofage());
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public static void insertSQLDataTicket(ticket ticket){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string= String.format("INSERT INTO " + ticketDBTable +
                    " VALUES(\"%s\",\"%s\", \"%1$,.2f\",\"%d\");",ticket.getTicketID(), ticket.getFilm().getFilmID(), ticket.getPrice(),ticket.TotalofTicket);
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private static boolean login(ArrayList<Administrator> administratorslist,String login, String password){
        int length=administratorslist.size();
        for (int i=0; i<length; i++) {
            if (administratorslist.get(i).getAdministratorName().equals(login)&&administratorslist.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }



    public static void WriteTextFile(Repository repository, String text) {
        repository.write(text);
    }

    }







