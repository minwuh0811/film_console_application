
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private Scanner sc;

    public Main(Scanner sc){
        this.sc=sc;
    }

    public static void main(String[] args) {
        Main main=new Main(new Scanner(System.in));
        String path = System.getProperty("user.home")
                + java.io.File.separator + "IdeaProjects"
                + java.io.File.separator + "film_konsollapplikation"
                + java.io.File.separator + "src";
        FileName fileName=new FileName("Administrator","Film", "Ticket" );

        // Load Data from MySQL and updated the binary and TXT file locally
        TablesSQL tableSQL= new TablesSQL("admin","film", "ticket");
        DataBaseConnection dataBaseConnection=new DataBaseConnection("jdbc:mysql://192.168.99.100:3306/db_film", "root", "password");
        LoadDataSQLSaveToBinAndTxtFile loadDataSQLSaveToBinAndTxtFile= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
        AdministratorStorage administratorStorageSQL=loadDataSQLSaveToBinAndTxtFile.getWhileLooplists().get(0).getAdministratorStorage();
        FilmStorage filmStorageSQL=loadDataSQLSaveToBinAndTxtFile.getWhileLooplists().get(1).getFilmStorage();
        TicketStorage ticketStorageSQL=loadDataSQLSaveToBinAndTxtFile.getWhileLooplists().get(2).getTicketStorage();

        AdministratorStorage administratorStorageBin = new AdministratorStorage(fileName.getFileNameAdmin(), path, "AD");
        FilmStorage filmStorageBin = new FilmStorage(fileName.getFileNameFilm(), path, "FM");
        TicketStorage ticketStorageBin = new TicketStorage(fileName.getFileNameTicket(), path, "TK");

        System.out.println("Administrator Press 1\nUser Press 2\nExit Press 3");
        String string=main.getString();
        //String string = JOptionPane.showInputDialog("Administrator Press 1\nUser Press 2\nExit Press 3");
        int choice = Integer.parseInt(string);
        boolean control = true;
        while (control) {
            switch (choice) {
                case (1):
                    //String admin = JOptionPane.showInputDialog("Add New Administrator Press 1\nAdministrator Login Press 2\nExit Press 3");
                    System.out.println("Add New Administrator Press 1\nAdministrator Login Press 2\nExit Press 3");
                    String admin=main.getString();
                    int admin_choice = Integer.parseInt(admin);
                    switch (admin_choice) {
                        case (1):
                            System.out.println("Enter Your First Name:");
                            String FirstName=main.getString();
                            //String FirstName = JOptionPane.showInputDialog("Enter Your First Name:");
                            System.out.println("Enter Your Last Name:");
                            String LastName=main.getString();
                            //String LastName = JOptionPane.showInputDialog("Enter Your Last Name:");
                            System.out.println("Enter Your Nick Name:");
                            String AdministratorNickName=main.getString();
                            //String AdministratorNickName = JOptionPane.showInputDialog("Enter Your Nick Name:");
                            //String AdminPassword = JOptionPane.showInputDialog("Enter Your Password");
                            System.out.println("Enter Your Password");
                            String AdminPassword=main.getString();
                            Administrator.setID(administratorStorageBin.getList().size());
                            Administrator administratorNew = new Administrator(FirstName, LastName, AdministratorNickName, AdminPassword);
                            administratorStorageBin.addAdministratorSQLDB(administratorNew);
                            administratorStorageBin.close(fileName.getFileNameTicket(), path);
                            try {
                                InsertSQLData insertSQLData= new InsertSQLData(dataBaseConnection,tableSQL,administratorNew);
                            } catch (Exception e) {
                                continue;
                            }
                            break;
                        case (2):
                            //String loginName = JOptionPane.showInputDialog("Enter your login Name:");
                            System.out.println("Enter your login Name:");
                            String loginName=main.getString();
//                            String password = JOptionPane.showInputDialog("Enter password");
                            System.out.println("Enter password");
                            String password=main.getString();
                            if (login(administratorStorageBin.getList(), loginName, password)) {
                                //JOptionPane.showMessageDialog(null, "Login Successfully");
                                System.out.println("Login Successfully");
                                //String LoginChoice = JOptionPane.showInputDialog("Add a Film Press 1\nRemove a Film Press 2\nChange the Number of Ticket in the Storage Press 3\nExist 4");
                                System.out.println("Add a Film Press 1\nRemove a Film Press 2\nChange the Number of Ticket in the Storage Press 3\nExist 4");
                                String LoginChoice=main.getString();
                                int loginChoice = Integer.parseInt(LoginChoice);
                                switch (loginChoice) {
                                    case (1):
                                        //String FilmName = JOptionPane.showInputDialog("Enter Film Name:");
                                        System.out.println("Enter Film Name:");
                                        String FilmName=main.getString();
                                        //String AgeLimitation = JOptionPane.showInputDialog("What is the AgeLimitation:");
                                        System.out.println("What is the AgeLimitation:");
                                        String AgeLimitation=main.getString();
                                        int ageLimitation = Integer.parseInt(AgeLimitation);
                                        Film.setID(filmStorageBin.getList().size());
                                        Film newFilm = new Film(FilmName, ageLimitation);
                                        filmStorageBin.addFilmDBSQL(newFilm);
                                        filmStorageBin.close(fileName.getFileNameFilm(), path);
                                        try {
                                            InsertSQLData insertSQLData=new InsertSQLData(dataBaseConnection,tableSQL,newFilm);
                                        } catch (Exception e) {
                                            continue;
                                        }
                                        //String NumberOfticket = JOptionPane.showInputDialog("What is the number of tickets?");
                                        System.out.println("What is the number of tickets?");
                                        String NumberOfticket=main.getString();
                                        int numberOfTicket = Integer.parseInt(NumberOfticket);
                                        //String price = JOptionPane.showInputDialog("What is the price?");
                                        System.out.println("What is the price?");
                                        String price =main.getString();
                                        int PRICE = Integer.parseInt(price);

                                        Ticket newTicket = new Ticket(newFilm, PRICE, numberOfTicket);
                                        ticketStorageBin.addTicketDBSQL(newTicket);
                                        ticketStorageBin.close(fileName.getFileNameTicket(), path);
                                        try {
                                            InsertSQLData insertSQLData=new InsertSQLData(dataBaseConnection, tableSQL, newTicket);
                                        } catch (Exception e) {
                                            continue;
                                        }
                                        break;
                                    case (2):
                            /*            control = true;
                                        while (control) {
                                            String FilmID = JOptionPane.showInputDialog("Enter the filmID which you want to remove");
                                            filmStorageBin.searchFilm(FilmID, filmStorageBin.getList());
                                            ticketStorageBin.searchTicket(FilmID, ticketStorageBin.getList());
                                            String confirmation = JOptionPane.showInputDialog(("Is the film \"" + filmStorageBin.getFilm().getName()) + "\" you want to delete? 1: Yes, 0: No");
                                            int confirmation_choice = Integer.parseInt(confirmation);
                                            if (confirmation_choice == 1) {
                                                filmStorageBin.removefilm(filmStorageBin.getFilm());
                                                filmStorageBin.close(fileName.getFileNameFilm(), path);
                                                try {
                                                    removeSQLDataFilm(filmStorageBin.getFilm());
                                                } catch (Exception e) {
                                                    continue;
                                                }
                                                ticketStorageBin.removeTicket(ticketStorageBin.getTicket());
                                                ticketStorageBin.close(fileNameTicket, path);
                                                try {
                                                    removeSQLDataTicket(ticketStorageBin.getTicket());
                                                } catch (Exception e) {
                                                    continue;
                                                }
                                                control = false;
                                            } else if (confirmation_choice == 0) {
                                                String delete_film = JOptionPane.showInputDialog("Cancel remove film? 1: Yes 0: No");
                                                int delete_film_choice = Integer.parseInt(delete_film);
                                                if (delete_film_choice == 1) {
                                                    control = false;
                                                }
                                            }
                                        }*/
                                        break;
                                    case (3):
                                        //String FilmID = JOptionPane.showInputDialog("Enter the filmID which you want to change the number of tickets in the storage");
                                        System.out.println("Enter the filmID which you want to change the number of tickets in the storage");
                                        String FilmID=main.getString();
                                        ticketStorageBin.searchTicket(FilmID, ticketStorageBin.getList());
                                        //String totalTicket = JOptionPane.showInputDialog("FilmID: " + ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName() + " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left.\nThe number of the tickets?");
                                        System.out.println("FilmID: " + ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName() + " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left.\nThe number of the tickets?");
                                        String totalTicket=main.getString();
                                        int totalNumberTicket = Integer.parseInt(totalTicket);
                                        ticketStorageBin.getTicket().TotalofTicket = totalNumberTicket;
                                        //JOptionPane.showMessageDialog(null, "FilmID: " + ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName() + " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left now.");
                                        System.out.println("FilmID: " + ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName() + " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left now.");
                                        break;
                                    case (4):
                                        //JOptionPane.showMessageDialog(null, "Exist");
                                        System.out.println("Exist");
                                        break;

                                }
                            } else {
                                System.out.println("LoginName or Password not Exist");
                            }
                            break;
                        case (3):
                            System.out.println("Exist");
                            break;
                    }
                    break;
                case (2):
                    //String User = JOptionPane.showInputDialog("Buy Ticket Press 1\nFilmList Press 2\nExit Press 3");
                    System.out.println("Buy Ticket Press 1\nFilmList Press 2\nExit Press 3");
                    String User=main.getString();
                    int choice_user = Integer.parseInt(User);
                    switch (choice_user) {
                        case (1):
                            //String FilmID = JOptionPane.showInputDialog("Enter FilmID");
                            System.out.println("Enter FilmID");
                            String FilmID =main.getString();
                            //String userAge = JOptionPane.showInputDialog("What is your age?");
                            System.out.println("What is your age?");
                            String userAge=main.getString();
                            int Age = Integer.parseInt(userAge);
                            //String Discount = JOptionPane.showInputDialog("Do you have BonusCard? 1: Yes, 0: No");
                            System.out.println("Do you have BonusCard? 1: Yes, 0: No");
                            String Discount=main.getString();
                            //String TicketNum = JOptionPane.showInputDialog("How Much Ticket Do you Need?");
                            System.out.println("How Much Ticket Do you Need?");
                            String TicketNum=main.getString();
                            int ticketNum = Integer.parseInt(TicketNum);
                            //String Mate = JOptionPane.showInputDialog("Do you have a mate with you? 1: Yes, 0: No");
                            System.out.println("Do you have a mate with you? 1: Yes, 0: No");
                            String Mate=main.getString();
                    }

                    break;
                case (3):
                    //JOptionPane.showMessageDialog(null, "Exist");
                    System.out.println("Exist");
                    control = false;
                    break;
            }

        }
    }


//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select * from " + DBTable + ";");
//            While whilnew=whileload
//        } catch (Exception e) {
//        }
//        return whileload.getStorage();
//    }


//            resultSetFilm = statement.executeQuery("select * from " + filmDBTable + ";");
//            FilmStorage filmstorageSQL = new FilmStorage();
//            while (resultSetFilm.next()) {
//                film film = new film(resultSetFilm.getString(1), resultSetFilm.getString(2), resultSetFilm.getInt(3));
//                filmstorageSQL.addFilmDBSQL(film);
//            }
//            filmstorageSQL.close("Film", path);
//            database.add(filmstorageSQL);
//            resultSetTicket = statement.executeQuery("select * from " + ticketDBTable + ";");
//            ticketStorage ticketstorageSQL = new ticketStorage();
//            while (resultSetTicket.next()) {
//                String filmID = resultSetTicket.getString(2);
//                filmstorageSQL.searchFilm(filmID, filmstorageSQL.getList());
//                ticket newTicket = new ticket(resultSetTicket.getString(1), filmstorageSQL.getFilm(), resultSetTicket.getInt(3), resultSetTicket.getInt(4));
//                ticketstorageSQL.addTicketDBSQL(newTicket);
//            }
//            ticketstorageSQL.close("Ticket", path);
//            database.add(ticketstorageSQL);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return database;


//    public static void insertSQLDataAdmin(Administrator administrator, String administratorDBTable,String DBMySQLUrl, String DBUserName, String DBPassword ) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
//            statement = connection.createStatement();
//            String string = String.format("INSERT INTO " + administratorDBTable +
//                    " VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");", administrator.getAdministratorID(), administrator.getFirstname(), administrator.getLastname(), administrator.getAdministratorName(), administrator.getPassword());
//            statement.execute(string);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }

/*
    public static void removeSQLDataAdmin(Administrator administrator) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string = String.format("DELETE FROM " + administratorDBTable +
                    " WHERE administratorID=\'" + administrator.getAdministratorID() + "\';");
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
*/

/*    public static void insertSQLDataFilm(film film) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string = String.format("INSERT INTO " + filmDBTable +
                    " VALUES(\"%s\",\"%s\",\"%d\");", film.getFilmID(), film.getName(), film.getLimitofage());
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }*/

/*    public static void removeSQLDataFilm(film film) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string = String.format("DELETE FROM " + filmDBTable +
                    " WHERE filmID=\'" + film.getFilmID() + "\';");
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }*/

/*    public static void insertSQLDataTicket(ticket ticket) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string = String.format("INSERT INTO " + ticketDBTable +
                    " VALUES(\"%s\",\"%s\", \"%d\",\"%d\");", ticket.getTicketID(), ticket.getFilm().getFilmID(), ticket.getPrice(), ticket.TotalofTicket);
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }*/

/*    public static void removeSQLDataTicket(ticket ticket) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBMySQLUrl, DBUserName, DBPassword);
            statement = connection.createStatement();
            String string = String.format("DELETE FROM " + ticketDBTable +
                    " WHERE ticketID=\'" + ticket.getTicketID() + "\';");
            statement.execute(string);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }*/

    private static boolean login(ArrayList<Administrator> administratorslist, String login, String password) {
        int length = administratorslist.size();
        for (int i = 0; i < length; i++) {
            if (administratorslist.get(i).getAdministratorName().equals(login) && administratorslist.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void WriteTextFile(Repository_Class repository, String text) {
        repository.write(text);
    }



    public String getString() {
        return sc.nextLine();
    }

}