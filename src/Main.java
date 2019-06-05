import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class Main {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSetAdmin;
    static ResultSet resultSetFilm;
    static ResultSet resultSetTicket;
    static String administratorDBTable = "admin";
    static String filmDBTable = "film";
    static String ticketDBTable = "ticket";
    static JTable table;// 声明表格
    static String DBMySQLUrl = "jdbc:mysql://192.168.99.100:3306/db_film";
    static String DBUserName = "root" ;
    static String DBPassword = "password";
    static String fileNameAdmin="Administrator";
    static String fileNameFilm="Film";
    static String fileNameTicket="Ticket";

    public static void main(String[] args) {
        String path = System.getProperty("user.home")
                + java.io.File.separator + "IdeaProjects"
                + java.io.File.separator + "film_konsollapplikation"
                + java.io.File.separator + "src";
        localData(DBMySQLUrl,DBUserName,DBPassword,path);
        AdministratorStorage administratorStorageBin = new AdministratorStorage("Administrator", path);
        FilmStorage filmStorageBin=new FilmStorage("Film",path);
        ticketStorage ticketStorageBin=new ticketStorage("Ticket",path);
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
                            Administrator.setID(administratorStorageBin.getAdministratorlist().size()+1);
                            Administrator administratorNew = new Administrator(FirstName, LastName, AdministratorNickName, AdminPassword);
                            administratorStorageBin.addAdministratorSQLDB(administratorNew);
                            administratorStorageBin.close(fileNameAdmin, path);
                            try {
                            insertSQLDataAdmin(administratorNew);} catch (Exception e) {
                                continue;
                            }
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
                                        film.setID(filmStorageBin.getFilmlist().size()+1);
                                        film newFilm = new film(FilmName, ageLimitation);
                                        filmStorageBin.addFilmDBSQL(newFilm);
                                        filmStorageBin.close(fileNameFilm,path);
                                        try {
                                        insertSQLDataFilm(newFilm);} catch (Exception e) {
                                            continue;}
                                        String NumberOfticket = JOptionPane.showInputDialog("What is the number of tickets?");
                                        int numberOfTicket=Integer.parseInt(NumberOfticket);
                                        String price=JOptionPane.showInputDialog("What is the price?");
                                        double PRICE= Double.parseDouble(price);
                                        ticket.setID(ticketStorageBin.getTicketlist().size()+1);
                                        ticket newticket=new ticket(newFilm,PRICE, numberOfTicket);
                                        System.out.println(newticket.getTicketID());
                                        ticketStorageBin.addTicketDBSQL(newticket);
                                        ticketStorageBin.close(fileNameTicket,path);
                                        try {
                                            insertSQLDataTicket(newticket);
                                        } catch (Exception e) { continue;}
                                        break;
                                    case (2):
                                        control=true;
                                        while (control) {
                                            String FilmID = JOptionPane.showInputDialog("Enter the filmID which you want to remove");
                                            filmStorageBin.searchFilm(FilmID, filmStorageBin.getFilmlist());
                                            ticketStorageBin.searchTicket(FilmID,ticketStorageBin.getTicketlist());
                                            String confirmation = JOptionPane.showInputDialog(("Is the film \"" + filmStorageBin.getFilm().getName()) + "\" you want to delete? 1: Yes, 0: No");
                                            int confirmation_choice = Integer.parseInt(confirmation);
                                            if (confirmation_choice == 1) {
                                                filmStorageBin.removefilm(filmStorageBin.getFilm());
                                                filmStorageBin.close(fileNameFilm,path);
                                                removeSQLDataFilm(filmStorageBin.getFilm());
                                                ticketStorageBin.removeTicket(ticketStorageBin.getTicket());
                                                ticketStorageBin.close(fileNameTicket,path);
                                                removeSQLDataTicket(ticketStorageBin.getTicket());
                                            } else if (confirmation_choice == 0) {
                                                String delete_film = JOptionPane.showInputDialog("Cancel remove film? 1: Yes 0: No");
                                                int delete_film_choice=Integer.parseInt(delete_film);
                                                if (delete_film_choice==1) {
                                                    control=false;
                                                }
                                            }
                                        }
                                        break;
                                    case (3):
                                        String FilmID = JOptionPane.showInputDialog("Enter the filmID which you want to change the number of tickets in the storage");
                                        ticketStorageBin.searchTicket(FilmID, ticketStorageBin.getTicketlist());
                                        String totalTicket=JOptionPane.showInputDialog( "FilmID: " +ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName()+ " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left.\nThe number of the tickets?");
                                        int totalNumberTicket=Integer.parseInt(totalTicket);
                                        ticketStorageBin.getTicket().TotalofTicket=totalNumberTicket;
                                        JOptionPane.showMessageDialog(null,"FilmID: " +ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName()+ " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left now.");
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
                case(3):
                    JOptionPane.showMessageDialog(null, "Exist");
                    control=false;
                    break;
            }
        }
    }

    public static ArrayList localData(String DBMySQLUrl,String DBUserName, String DBPassword,String path) {
        ArrayList database=new ArrayList();
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
                statement = connection.createStatement();
                resultSetAdmin = statement.executeQuery("select * from " + administratorDBTable + ";");
                AdministratorStorage administratorstorageSQL=new AdministratorStorage();
            while (resultSetAdmin.next()) {
                Administrator administrator = new Administrator(resultSetAdmin.getString(1), resultSetAdmin.getString(2), resultSetAdmin.getString(3), resultSetAdmin.getString(4), resultSetAdmin.getString(5));
                administratorstorageSQL.addAdministratorSQLDB(administrator);
            }
            administratorstorageSQL.close("Administrator", path);
            database.add(administratorstorageSQL);
            resultSetFilm = statement.executeQuery("select * from " + filmDBTable + ";");
            FilmStorage filmstorageSQL=new FilmStorage();
            while (resultSetFilm.next()) {
                film film = new film(resultSetFilm.getString(1), resultSetFilm.getString(2), resultSetFilm.getInt(3));
                filmstorageSQL.addFilmDBSQL(film);
            }
            filmstorageSQL.close("Film",path);
            database.add(filmstorageSQL);
            resultSetTicket = statement.executeQuery("select * from " + ticketDBTable + ";");
            ticketStorage ticketstorageSQL=new ticketStorage();
            while (resultSetTicket.next()) {
                String filmID = resultSetTicket.getString(2);
                filmstorageSQL.searchFilm(filmID,filmstorageSQL.getFilmlist());
                ticket newTicket = new ticket(resultSetTicket.getString(1), filmstorageSQL.getFilm(), resultSetTicket.getDouble(3), resultSetTicket.getInt(4));
                ticketstorageSQL.addTicketDBSQL(newTicket);
            }
            ticketstorageSQL.close("Ticket",path);
            database.add(ticketstorageSQL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return database;
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

    public static void removeSQLDataAdmin(Administrator administrator) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string= String.format("DELETE FROM " + administratorDBTable +
                    " WHERE administratorID=\'" + administrator.getAdministratorID() + "\';");
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

    public static void removeSQLDataFilm(film film) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string= String.format("DELETE FROM " + filmDBTable +
                    " WHERE filmID=\'" + film.getFilmID() + "\';");
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
                    " VALUES(\"%s\",\"%s\", \"%1$,.1f\",\"%d\");",ticket.getTicketID(), ticket.getFilm().getFilmID(), ticket.getPrice(),ticket.TotalofTicket);
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public static void removeSQLDataTicket(ticket ticket) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string= String.format("DELETE FROM " + ticketDBTable +
                    " WHERE ticketID=\'" + ticket.getTicketID() + "\';");
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

    public static void WriteTextFile(ticket.Repository repository, String text) {
        repository.write(text);
    }

    }







