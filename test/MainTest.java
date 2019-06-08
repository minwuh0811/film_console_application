import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
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
        Film.setID(whileFilm.getFilmStorage().getList().size());
        Film film=new Film("Frozen", 6);
        InsertSQLData insertSQLData=new InsertSQLData(dataBaseConnection,tableSQL,film);
        LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
        assertEquals(2,localDataSQL.getWhileLooplists().get(1).getFilmStorage().getList().size());
        String REMOVE="delete from film where filmName='Frozen';";
        dataBaseConnection.getStatement().execute(REMOVE);
    }

    @Test
    void insertSQLDataTicket() throws Exception {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminInsertTest", "filmInsertTest", "ticketInsertTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path,new FilmStorage());
        While whileTicket=new While(tableSQL.getTicketDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path, whileFilm.getFilmStorage(), new TicketStorage());
        Ticket.setID(whileTicket.getTicketStorage().getList().size());
        Film.setID(whileFilm.getFilmStorage().getList().size());
        Film film=new Film("Frozen", 6);
        InsertSQLData insertSQLDataFilm=new InsertSQLData(dataBaseConnection,tableSQL,film);
        Ticket ticket=new Ticket(film,103,20);
        InsertSQLData insertSQLDataTicket=new InsertSQLData(dataBaseConnection,tableSQL,ticket);
        LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
        assertEquals(2,localDataSQL.getWhileLooplists().get(2).getTicketStorage().getList().size());
        String REMOVE1="delete from film where filmName='Frozen';";
        dataBaseConnection.getStatement().execute(REMOVE1);
        String REMOVE2="delete from ticket where price=103;";
        dataBaseConnection.getStatement().execute(REMOVE2);
    }


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
    void loginSuccessful() throws IOException{
        try (InputStream inputStream = MainTest.class.getResourceAsStream("/LoginSuccess")) {
            Scanner scanner = new Scanner(inputStream);
            Main main = new Main(scanner);
            String loginName=main.getString();
            String password=main.getString();
            DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
            TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
            FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
            LoadDataSQLSaveToBinAndTxtFile localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
            assertTrue(main.login(localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList(),loginName,password));
        }

    }



}
