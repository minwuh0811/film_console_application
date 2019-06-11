
import javax.imageio.metadata.IIOMetadataNode;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private Scanner sc;
    private boolean control=true;

    public  boolean isControl() {
        return control;
    }

    public  void setControl(boolean control) {
        this.control = control;
    }

    public Main(Scanner sc){
        this.sc=sc;
    }

    public Main(){}

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

        while (main.isControl()) {
        System.out.println("Administrator Press 1\nUser Press 2\nExit Press 3");
        String string=main.getString();
        //String string = JOptionPane.showInputDialog("Administrator Press 1\nUser Press 2\nExit Press 3");
        int choice = Integer.parseInt(string);
            switch (choice) {
                case (1):
                    //String admin = JOptionPane.showInputDialog("Add New Administrator Press 1\nAdministrator Login Press 2\nExit Press 3");
                    System.out.println("Administrator Login Press 1\nExit Press 2");
                    String admin=main.getString();
                    int admin_choice = Integer.parseInt(admin);
                    switch (admin_choice) {
                        case (1):
                            //String loginName = JOptionPane.showInputDialog("Enter your login Name:");
                            System.out.println("Enter your login Name:");
                            String loginName=main.getString();
//                            String password = JOptionPane.showInputDialog("Enter password");
                            System.out.println("Enter password");
                            String password=main.getString();
                            if (main.login(administratorStorageBin.getList(), loginName, password)) {
                                //JOptionPane.showMessageDialog(null, "Login Successfully");
                                System.out.println("Login Successfully");
                                //String LoginChoice = JOptionPane.showInputDialog("Add a Film Press 1\nRemove a Film Press 2\nChange the Number of Ticket in the Storage Press 3\nExist 4");
                                System.out.println("Add New Administrator Press 1\nAdd a Film Press 2\nChange the Number of Ticket in the Storage Press 3\nExist 4");
                                String LoginChoice=main.getString();
                                int loginChoice = Integer.parseInt(LoginChoice);
                                switch (loginChoice) {
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
                                        main.addAdmin(FirstName,LastName,AdministratorNickName,AdminPassword,administratorStorageBin,fileName,path,dataBaseConnection,tableSQL);
                                        break;
                                    case (2):
                                        //String FilmName = JOptionPane.showInputDialog("Enter Film Name:");
                                        System.out.println("Enter Film Name:");
                                        String FilmName=main.getString();
                                        //String AgeLimitation = JOptionPane.showInputDialog("What is the AgeLimitation:");
                                        System.out.println("What is the AgeLimitation:");
                                        String AgeLimitation=main.getString();
                                        int ageLimitation = Integer.parseInt(AgeLimitation);
                                        main.addFilm(FilmName,ageLimitation,filmStorageBin,fileName,path,dataBaseConnection,tableSQL);
                                        break;
                                    case (3):
                                        //String FilmID = JOptionPane.showInputDialog("Enter the filmID which you want to change the number of tickets in the storage");
                                        System.out.println("Enter the filmID which you want to change the number of tickets in the storage");
                                        String FilmID=main.getString();
                                        filmStorageBin.searchFilm(FilmID,filmStorageBin.getList());
                                        System.out.println("Tickets "+ filmStorageBin.getFilm().getTotalofTicket() + " has left.");
                                        System.out.println("The number of the tickets?");
                                        String TotalNumberofTicket=main.getString();
                                        int totalNumberTicket=Integer.parseInt(TotalNumberofTicket);
                                        Film resultFilm=main.changeTicektNumber(FilmID,filmStorageBin,totalNumberTicket,dataBaseConnection,tableSQL);
                                        System.out.println("FilmID: " + resultFilm.getFilmID() + " FilmName: " + resultFilm.getName() + " has " + resultFilm.getTotalofTicket() + " tickets left now.");
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
                        case (2):
                            System.out.println("Exist");
                            break;
                    }
                    break;
                case (2):
                    //String User = JOptionPane.showInputDialog("Buy Ticket Press 1\nFilmList Press 2\nExit Press 3");
                    System.out.println("Buy Ticket Press 1\nExit Press 2");
                    String User=main.getString();
                    int choice_user = Integer.parseInt(User);
                    switch (choice_user) {
                        case (1):
                            System.out.println("Choose the films from the list below.");
                            Print print=new Print(filmStorageBin.getList());
                            System.out.println("Choose films Press 1\nExist press 2");
                            int chooseFilms=Integer.parseInt(main.getString());
                            switch(chooseFilms) {
                                case(1):
                                    int n=1;
                                    TicketStorage ticketStorage = new TicketStorage();
                                    while (n<=3) {
                                        ArrayList<String> FilmIDs = new ArrayList<>();
                                        System.out.println("You can choose at most 3 Films. Enter the FilmID: \nPress 2 Exist");
                                        String FilmID_1 = main.getString();
                                        //String userAge = JOptionPane.showInputDialog("What is your age?");
                                        if (!FilmID_1.equals("2")){
                                            System.out.println("What is your age?");
                                            int userAge = Integer.parseInt(main.getString());
                                            if (userAge < 15) {
                                                System.out.println("a mate with the kids? 1: Yes, 0: No");
                                                int mate = Integer.parseInt(main.getString());
                                                if (main.filmsAvailable(main.AgeLimitation(userAge, mate), FilmID_1, filmStorageBin)) {
                                                    System.out.println("How many tickets you need?");
                                                    int numberTicketBought = Integer.parseInt(main.getString());
                                                    Film film_1 = filmStorageBin.searchFilm(FilmID_1, filmStorageBin.getList());
                                                    Ticket ticket = new Ticket(film_1, numberTicketBought);
                                                    try {
                                                        AlterSQLData alterSQLData = new AlterSQLData(dataBaseConnection, tableSQL, film_1);
                                                        alterSQLData.alterSQLDataFilm(film_1);
                                                    } catch (Exception e) {
                                                        System.out.println(e);
                                                    }
                                                    ticketStorage.addTicketDBSQL(ticket);
                                                    System.out.println("Press 1 continues\nPress 2 to Exist");
                                                    int continues = Integer.parseInt(main.getString());
                                                    if (continues == 1) {
                                                        n++;
                                                    } else {
                                                        n = 4;
                                                    }
                                                } else {
                                                    System.out.println("Ops!! You can not Choose this film at your age");
                                                }
                                            }else {
                                                System.out.println("How many tickets you need?");
                                                int numberTicketBought = Integer.parseInt(main.getString());
                                                Ticket ticket = new Ticket(filmStorageBin.searchFilm(FilmID_1, filmStorageBin.getList()), numberTicketBought);
                                                ticketStorage.addTicketDBSQL(ticket);
                                                System.out.println("Press 1 continues\nPress 2 to Exist");
                                                int continues = Integer.parseInt(main.getString());
                                                if (continues == 1) {
                                                    n++;
                                                } else {
                                                    n = 4;
                                                }
                                            }
                                        } else {n=4;}
                                    }
                                    System.out.println("The Tickets you booked as followed.");
                                    System.out.println("");

                                    System.out.println("Exist");
                                    break;
                                case(2):
                                    System.out.println("Exist");
                                    break;
                            }

                        case (2):
                            System.out.println("Exist");
                            break;
                    }
                    break;
                case (3):
                    //JOptionPane.showMessageDialog(null, "Exist");
                    System.out.println("Exist");
                    main.setControl(false);
                    break;
            }

        }
    }


    public boolean login(ArrayList<Administrator> administratorslist, String login, String password) {
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

    public void addAdmin(String FirstName, String LastName, String AdministratorNickName, String AdminPassword, AdministratorStorage administratorStorage, FileName fileName, String path, DataBaseConnection dataBaseConnection, TablesSQL tableSQL  ){
        Administrator.setID(administratorStorage.getList().size());
        Administrator administratorNew = new Administrator(FirstName, LastName, AdministratorNickName, AdminPassword);
        administratorStorage.addAdministratorSQLDB(administratorNew);
        administratorStorage.close(fileName.getFileNameTicket(), path);
        try {
            InsertSQLData insertSQLData= new InsertSQLData(dataBaseConnection,tableSQL,administratorNew);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addFilm(String FilmName,int ageLimitation, FilmStorage filmStorage, FileName fileName, String path, DataBaseConnection dataBaseConnection, TablesSQL tableSQL ) {
        //String FilmName = JOptionPane.showInputDialog("Enter Film Name:");
        Film.setID(filmStorage.returnID());
        Film newFilm = new Film(FilmName, ageLimitation);
        filmStorage.addFilmDBSQL(newFilm);
        filmStorage.close(fileName.getFileNameFilm(), path);
        try {
            InsertSQLData insertSQLData=new InsertSQLData(dataBaseConnection,tableSQL,newFilm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public int ageLimitation(int Age, int mat){
//        int ageLimitation;
//        if (mat==1){
//            if (Age<7
//
//        } else if (mat==0) {
//
//        }
    public Film changeTicektNumber(String FilmID, FilmStorage filmStorage,int totalNumberTicket, DataBaseConnection dataBaseConnection, TablesSQL tablesSQL ){
        filmStorage.searchFilm(FilmID, filmStorage.getList()).setTotalofTicket(totalNumberTicket);
        //String totalTicket = JOptionPane.showInputDialog("FilmID: " + ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName() + " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left.\nThe number of the tickets?");
        try {
            AlterSQLData alterSQLData=new AlterSQLData(dataBaseConnection,tablesSQL,filmStorage.getFilm());
            alterSQLData.alterSQLDataFilm(filmStorage.getFilm());
        } catch (Exception e) {
            System.out.println(e);};
        //JOptionPane.showMessageDialog(null, "FilmID: " + ticketStorageBin.getTicket().getFilm().getFilmID() + " FilmName: " + ticketStorageBin.getTicket().getFilm().getName() + " has " + ticketStorageBin.getTicket().TotalofTicket + " tickets left now.");
        return filmStorage.getFilm();
    }

    public boolean filmsAvailable(int ageLimitation,String FilmID, FilmStorage filmStorage) {
        filmStorage.searchFilm(FilmID, filmStorage.getList());
            if (ageLimitation>=filmStorage.searchFilm(FilmID, filmStorage.getList()).getLimitofage()) {
                return true;
            } else {
                return false;
        }
    }

    public int AgeLimitation(int age, int mate) {
        int ageLimitation = 15;
        if (mate == 1) {
            if (age < 7) {
                ageLimitation = 7;
            } else if (age < 11 && age >= 7) {
                ageLimitation = 11;
            }
        } else if (mate == 0) {
            if (age < 7) {
                ageLimitation = 0;
            } else if (age < 11 && age >= 7) {
                ageLimitation = 7;
            } else if (age >= 11 && age < 15) {
                ageLimitation = 11;
            }
        }
        return ageLimitation;
    }


    public void buyTicket() {

    }
}