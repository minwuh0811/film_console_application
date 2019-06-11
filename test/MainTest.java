import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    String DBURL="jdbc:mysql://192.168.99.100:3306/filmTest";
    String DBUser="root";
    String DBPassword="password";
    static String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void localDataTreeTablesloaded(){
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
        assertEquals(3,localDataSQL.getWhileLooplists().size());
    }

    //automatic find the uniqe ID.

    @Test
    void insertSQLDataAdmin() throws Exception {
            DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
            TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
            FileName fileName=new FileName("adminInsertTest", "filmInsertTest", "ticketInsertTest");
            While whileAdmin=new While(tableSQL.getAdministratorDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameAdmin(),path,new AdministratorStorage());
            Administrator.setID(whileAdmin.getAdministratorStorage().getList().size());
            Administrator administrator=new Administrator("Mira", "Chueng", "Miramira", "password");
            InsertSQLData insertSQLData=new InsertSQLData(dataBaseConnection,tableSQL,administrator);
            LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
            assertEquals(2,localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList().size());
            String REMOVE="delete from admin where administratorFirstName='Mira';";
            dataBaseConnection.getStatement().execute(REMOVE);
    }

    @Test
    void insertSQLDataFilm() throws Exception {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminInsertTest", "filmInsertTest", "ticketInsertTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        Film.setID(whileFilm.getFilmStorage().returnID());
        Film film=new Film("Test", 5);
        InsertSQLData insertSQLData=new InsertSQLData(dataBaseConnection,tableSQL,film);
        LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
        assertEquals(8,localDataSQL.getWhileLooplists().get(1).getFilmStorage().getList().size());
        String REMOVE="delete from film where filmName='Test';";
        dataBaseConnection.getStatement().execute(REMOVE);
    }

//    @Test
//    void insertSQLDataTicket() throws Exception {
//        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
//        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
//        FileName fileName=new FileName("adminInsertTest", "filmInsertTest", "ticketInsertTest");
//        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path,new FilmStorage());
//        While whileTicket=new While(tableSQL.getTicketDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path, whileFilm.getFilmStorage(), new TicketStorage());
//        Ticket.setID(whileTicket.getTicketStorage().returnID());
//        Film.setID(whileFilm.getFilmStorage().returnID());
//        Film film=new Film("Frozen", 6);
//        InsertSQLData insertSQLDataFilm=new InsertSQLData(dataBaseConnection,tableSQL,film);
//      //  Ticket ticket=new Ticket(film,103,20);
//        //InsertSQLData insertSQLDataTicket=new InsertSQLData(dataBaseConnection,tableSQL,ticket);
//        LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
//        assertEquals(2,localDataSQL.getWhileLooplists().get(2).getTicketStorage().getList().size());
//        String REMOVE1="delete from film where filmName='Frozen';";
//        dataBaseConnection.getStatement().execute(REMOVE1);
//        String REMOVE2="delete from ticket where price=103;";
////        dataBaseConnection.getStatement().execute(REMOVE2);
//    }


    @Test
    void getString() throws IOException {
        try (InputStream inputStream = MainTest.class.getResourceAsStream("/getString")) {
            Scanner scanner = new Scanner(inputStream);
            Main main = new Main(scanner);
            String testresult = main.getString();
            assertEquals("Min Wu", testresult);
        }
    }

    @Test
    void loginSuccessful() throws IOException {
        try (InputStream inputStream = MainTest.class.getResourceAsStream("/LoginTrue")) {
            Scanner scanner = new Scanner(inputStream);
            Main main = new Main(scanner);
            String loginName = main.getString();
            String password = main.getString();
            DataBaseConnection dataBaseConnection = new DataBaseConnection(DBURL, DBUser, DBPassword);
            TablesSQL tableSQL = new TablesSQL("admin", "film", "ticket");
            FileName fileName = new FileName("adminTest", "filmTest", "ticketTest");
            LoadDataSQLSaveToBinAndTxtFile localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
            assertTrue(main.login(localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList(), loginName, password));
        }
    }

        @Test
        void loginFalseLoginName () throws IOException {
            try (InputStream inputStream = MainTest.class.getResourceAsStream("/LoginFalseLoginName")) {
                Scanner scanner = new Scanner(inputStream);
                Main main = new Main(scanner);
                String loginName = main.getString();
                String password = main.getString();
                DataBaseConnection dataBaseConnection = new DataBaseConnection(DBURL, DBUser, DBPassword);
                TablesSQL tableSQL = new TablesSQL("admin", "film", "ticket");
                FileName fileName = new FileName("adminTest", "filmTest", "ticketTest");
                LoadDataSQLSaveToBinAndTxtFile localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
                assertFalse(main.login(localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList(), loginName, password));
            }
        }

    @Test
    void loginFalsePassword () throws IOException {
        try (InputStream inputStream = MainTest.class.getResourceAsStream("/LoginFalsePassword")) {
            Scanner scanner = new Scanner(inputStream);
            Main main = new Main(scanner);
            String loginName = main.getString();
            String password = main.getString();
            DataBaseConnection dataBaseConnection = new DataBaseConnection(DBURL, DBUser, DBPassword);
            TablesSQL tableSQL = new TablesSQL("admin", "film", "ticket");
            FileName fileName = new FileName("adminTest", "filmTest", "ticketTest");
            LoadDataSQLSaveToBinAndTxtFile localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
            assertFalse(main.login(localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList(), loginName, password));
        }
    }

    @Test
    void addAdminByGUI() throws Exception{
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("mainAddAdminTest", "filmTest", "ticketTest");
        LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
        int localDataSQLBefore=localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList().size();
        try (InputStream inputStream = MainTest.class.getResourceAsStream("/addAdminByGUI")) {
            Scanner scanner = new Scanner(inputStream);
            Main main = new Main(scanner);
            String FirstName=main.getString();
            //String FirstName = JOptionPane.showInputDialog("Enter Your First Name:");
            String LastName=main.getString();
            //String LastName = JOptionPane.showInputDialog("Enter Your Last Name:");
            String AdministratorNickName=main.getString();
            //String AdministratorNickName = JOptionPane.showInputDialog("Enter Your Nick Name:");
            //String AdminPassword = JOptionPane.showInputDialog("Enter Your Password");
            String AdminPassword=main.getString();
            main.addAdmin(FirstName,LastName,AdministratorNickName,AdminPassword,localDataSQL.getWhileLooplists().get(0).getAdministratorStorage(),fileName,path,dataBaseConnection,tableSQL);
            localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
            assertEquals(localDataSQLBefore+1,localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList().size());
            String REMOVE="delete from admin where administratorID='AD2';";
            dataBaseConnection.getStatement().execute(REMOVE);
        }
    }

    @Test
    void addFilmByGUI() throws Exception {
        DataBaseConnection dataBaseConnection = new DataBaseConnection(DBURL, DBUser, DBPassword);
        TablesSQL tableSQL = new TablesSQL("admin", "film", "ticket");
        FileName fileName = new FileName("AdminTest", "mainAddFilmTest", "mainAddTicketTest");
        LoadDataSQLSaveToBinAndTxtFile localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
        int localDataSQLBefore = localDataSQL.getWhileLooplists().get(1).getFilmStorage().getList().size();
        try (InputStream inputStream = MainTest.class.getResourceAsStream("/addFilmByGUI")) {
            Scanner scanner = new Scanner(inputStream);
            Main main = new Main(scanner);
            String FilmName=main.getString();
            System.out.println("Enter Film Name:" + FilmName);
            //String AgeLimitation = JOptionPane.showInputDialog("What is the AgeLimitation:");
            String AgeLimitation=main.getString();
            int ageLimitation = Integer.parseInt(AgeLimitation);
            System.out.println("What is the AgeLimitation:" + AgeLimitation);
            main.addFilm(FilmName,ageLimitation,localDataSQL.getWhileLooplists().get(1).getFilmStorage(),fileName,path,dataBaseConnection,tableSQL);
            localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
            assertEquals(localDataSQLBefore + 1, localDataSQL.getWhileLooplists().get(1).getFilmStorage().getList().size());
            String REMOVEFilm = "delete from film where filmName='Test';";
            dataBaseConnection.getStatement().execute(REMOVEFilm);
            String REMOVETicket = "delete from ticket where filmID='FM2';";
            dataBaseConnection.getStatement().execute(REMOVETicket);
        }
    }


    @Test
    void changeTicektNumberTo50() throws Exception{
        DataBaseConnection dataBaseConnection = new DataBaseConnection(DBURL, DBUser, DBPassword);
        TablesSQL tableSQL = new TablesSQL("admin", "film", "ticket");
        FileName fileName = new FileName("AdminTest", "FilmTest", "TicketTest");
        LoadDataSQLSaveToBinAndTxtFile localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
        try (InputStream inputStream = MainTest.class.getResourceAsStream("/changeTicketNumber")) {
            Scanner scanner = new Scanner(inputStream);
            Main main = new Main(scanner);
            String FilmID=main.getString();
            System.out.println("Enter FilmID:" + FilmID);
            int totalNumberTicket=Integer.parseInt(main.getString());
            //String AgeLimitation = JOptionPane.showInputDialog("What is the AgeLimitation:");
            System.out.println("The number of the ticket:" + totalNumberTicket);
            Film resultFilm=main.changeTicektNumber(FilmID,localDataSQL.getWhileLooplists().get(1).getFilmStorage(),totalNumberTicket,dataBaseConnection,tableSQL);
            localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
            assertEquals(50,resultFilm.getTotalofTicket());
            main.changeTicektNumber(FilmID,localDataSQL.getWhileLooplists().get(1).getFilmStorage(),20,dataBaseConnection,tableSQL);

        }
    }

   @Test
    void filmsAvailableSmallerThanSevenWithMate() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm = new While(tableSQL.getFilmDBTable(), dataBaseConnection.getStatement(), fileName.getFileNameFilm(), path,new FilmStorage());
        Main main=new Main();
        String string="FM2";
        int age=5;
        int mate=1;
        assertTrue(main.filmsAvailable(main.AgeLimitation(age,mate),string, whileFilm.getFilmStorage()));
    }


    @Test
    void filmsAvailableSmallerThanSevenWithoutMate() {
        ArrayList<String> actual=new ArrayList<>();
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        LoadDataSQLSaveToBinAndTxtFile localDataSQL = new LoadDataSQLSaveToBinAndTxtFile(tableSQL, dataBaseConnection, fileName, path);
        Main main=new Main();
        String string="FM2";
        int age=5;
        int mate=0;
        assertFalse(main.filmsAvailable(main.AgeLimitation(age,mate),string, localDataSQL.getWhileLooplists().get(1).getFilmStorage()));
    }

  @Test
    void filmsAvailableSmallerThanElevenWithMate() {
        ArrayList<String> actual=new ArrayList<>();
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        Main main=new Main();
        String string="FM1";
        int age=8;
        int mate=1;
        assertTrue(main.filmsAvailable(main.AgeLimitation(age,mate),string, whileFilm.getFilmStorage()));
    }

    @Test
    void filmsAvailableSmallerThanElevenWithoutMate() {
        ArrayList<String> actual=new ArrayList<>();
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        Main main=new Main();
        String string="FM1";
        String[] filmsList=string.split(":");
        int age=8;
        int mate=0;
        assertFalse(main.filmsAvailable(main.AgeLimitation(age,mate),string, whileFilm.getFilmStorage()));
    }

    @Test
    void filmsAvailableSmallerThanFifteenWithMate() {
        ArrayList<String> actual=new ArrayList<>();
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        Main main=new Main();
        String string="FM3";
        int age=14;
        int mate=1;
        assertTrue(main.filmsAvailable(main.AgeLimitation(age,mate),string, whileFilm.getFilmStorage()));
    }

    @Test
    void filmsAvailableSmallerThanFifteenWithoutMate() {
        ArrayList<String> actual=new ArrayList<>();
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        Main main=new Main();
        String string="FM3";
        int age=14;
        int mate=0;
        assertFalse(main.filmsAvailable(main.AgeLimitation(age,mate),string, whileFilm.getFilmStorage()));
    }


    @Test
    void ageLimitationWithMate() {
        Main main = new Main();
        ArrayList<Integer> ages=new ArrayList<>();
        ArrayList<Integer> ageLimitations=new ArrayList<>();
        ages.add(5);
        ages.add(7);
        ages.add(8);
        ages.add(10);
        ages.add(11);
        ages.add(12);
        ages.add(14);
        for(int age:ages){
            int ageLimitation=main.AgeLimitation(age,1);
            ageLimitations.add(ageLimitation);
        }
        ArrayList<Integer> Expect=new ArrayList<>();
        Expect.add(7);
        Expect.add(11);
        Expect.add(11);
        Expect.add(11);
        Expect.add(15);
        Expect.add(15);
        Expect.add(15);
        assertEquals(Expect,ageLimitations);
    }

    @Test
    void ageLimitationWithoutMate() {
        Main main = new Main();
        ArrayList<Integer> ages=new ArrayList<>();
        ArrayList<Integer> ageLimitations=new ArrayList<>();
        ages.add(5);
        ages.add(7);
        ages.add(8);
        ages.add(10);
        ages.add(11);
        ages.add(12);
        ages.add(14);
        for(int age:ages){
            int ageLimitation=main.AgeLimitation(age,0);
            ageLimitations.add(ageLimitation);
        }
        ArrayList<Integer> Expect=new ArrayList<>();
        Expect.add(0);
        Expect.add(7);
        Expect.add(7);
        Expect.add(7);
        Expect.add(11);
        Expect.add(11);
        Expect.add(11);
        assertEquals(Expect,ageLimitations);
    }
}
