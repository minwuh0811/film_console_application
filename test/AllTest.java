import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AllTest {
    String DBURL="jdbc:mysql://192.168.99.100:3306/filmTest";
    String DBUser="root";
    String DBPassword="password";
    private String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void addAdministratorSQLDB() {
        AdministratorStorage administratorStorage=new AdministratorStorage("adminAddBinAndTxt",path,"AD");
        int length=administratorStorage.getList().size();
        administratorStorage.addAdministratorSQLDB(new Administrator());
        assertEquals(2,administratorStorage.getList().size());
    }

    @Test
    void addFilmToBinAndTxt() {
        FilmStorage filmStorage=new FilmStorage("filmAddBinAndTxt", path,"FM");
        Film film=new Film();
        filmStorage.addFilmDBSQL(film);
        assertEquals(2,filmStorage.getList().size());
    }

    @Test
    void removefilm() {
        FilmStorage filmStorage=new FilmStorage("filmRemoveTest",path,"FM");
        Film film=new Film();
        filmStorage.addFilmDBSQL(film);
        filmStorage.removefilm(film);
        assertEquals(1,filmStorage.getList().size());
    }


    @Test
    void searchFilmByFilmID() {
        FilmStorage filmStorage=new FilmStorage("Film",path,"FM");
        filmStorage.searchFilm("FM3", filmStorage.getList());
        assertEquals("FM3", filmStorage.getFilm().getFilmID());
    }

    @Test
    void returnFirstMissingIDForFilm() {
        FilmStorage filmStorage=new FilmStorage("Film",path,"FM");
        int ID=filmStorage.returnID();
        assertEquals(2, ID);
    }

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
        Ticket.setID(whileTicket.getTicketStorage().returnID());
        Film.setID(whileFilm.getFilmStorage().returnID());
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

    Repository_Class mock=mock(Repository_Class.class);
    @Test
    void saveTofile() {
        String string="WriteTest.txt";
        path=path + java.io.File.separator + string;
        java.io.File file = new java.io.File(path);
        try (FileWriter filewriter = new FileWriter(file)) {
            Main.WriteTextFile(mock,"the expected text");
            verify(mock).write( "the expected text");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Test
    void searchTicketByTicketID() {
        TicketStorage ticketStorage=new TicketStorage("Ticket",path,"TK");
        ticketStorage.searchTicket("FM3", ticketStorage.getList());
        assertEquals("TK3", ticketStorage.getTicket().getTicketID());
    }


    @Test
    void close() {
    }

    @Test
    void addTicketToBinAndTxt() {
        TicketStorage ticketStorage=new TicketStorage("ticketAddBinAndTxt", path,"TK");
        Ticket ticket=new Ticket();
        System.out.println(ticketStorage.getList().size());
        ticketStorage.addTicketDBSQL(ticket);
        assertEquals(2,ticketStorage.getList().size());
    }

    @Test
    void removeTicket() {
        TicketStorage ticketStorage=new TicketStorage("ticketRemoveTest", path,"TK");
        ticketStorage.removeTicket(ticketStorage.getList().get(0));
        assertEquals(0,ticketStorage.getList().size());
    }

    @Test
    void searchTicketTK3() {
        TicketStorage ticketStorage=new TicketStorage("Ticket", path,"TK");
        ticketStorage.searchTicket("FM3", ticketStorage.getList());
        assertEquals("TK3",ticketStorage.getTicket().getTicketID());
    }

    @Test
    void returnFirstMissingIDForTicket() {
        TicketStorage ticketStorage= new TicketStorage("Ticket", path,"TK");
        int ID=ticketStorage.returnID();
        assertEquals(2, ID);
    }

    @Test
    void whileAdminFirstAdminisLoginName() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileadmin=new While(tableSQL.getAdministratorDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameAdmin(),path,new AdministratorStorage());
        assertEquals("minwuh081",whileadmin.getAdministratorStorage().getList().get(0).getAdministratorName());
    }

    @Test
    void whileAdminSizeEqualsOne() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileAdmin=new While(tableSQL.getAdministratorDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameAdmin(),path,new AdministratorStorage());
        assertEquals(1,whileAdmin.getAdministratorStorage().getList().size());
    }

    @Test
    void whileFilmFirstNameOfFilm() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        assertEquals("Star War",whileFilm.getFilmStorage().getList().get(0).getName());
    }


    @Test
    void whileFilmSizeOne() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        assertEquals(1,whileFilm.getFilmStorage().getList().size());
    }

    @Test
    void whileTicketSizeOne() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        While whileTicket=new While(tableSQL.getTicketDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path,whileFilm.getFilmStorage(),new TicketStorage());
        assertEquals(1,whileTicket.getTicketStorage().getList().size());
    }

    @Test
    void whileTicketFirstTicketID() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        While whileTicket=new While(tableSQL.getTicketDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path,whileFilm.getFilmStorage(),new TicketStorage());
        assertEquals("TK1",whileTicket.getTicketStorage().getList().get(0).getTicketID());
    }

    @Test
    void whileAdminIDList() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileAdmin=new While(tableSQL.getAdministratorDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameAdmin(),path,new AdministratorStorage());
        assertEquals("AD1",whileAdmin.getAdministratorStorage().getIDlist().get(0));
    }

    @Test
    void whileFilmIDList() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        assertEquals("FM1",whileFilm.getFilmStorage().getIDlist().get(0));
    }

    @Test
    void whileTicketIDList() {
        DataBaseConnection dataBaseConnection=new DataBaseConnection(DBURL,DBUser,DBPassword);
        TablesSQL tableSQL=new TablesSQL("admin", "film", "ticket");
        FileName fileName=new FileName("adminTest", "filmTest", "ticketTest");
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(),path,new FilmStorage());
        While whileTicket=new While(tableSQL.getTicketDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path,whileFilm.getFilmStorage(),new TicketStorage());
        assertEquals("TK1",whileTicket.getTicketStorage().getIDlist().get(0));
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
            main.addAdmin(main,FirstName,LastName,AdministratorNickName,AdminPassword,localDataSQL.getWhileLooplists().get(0).getAdministratorStorage(),fileName,path,dataBaseConnection,tableSQL);
            localDataSQL= new LoadDataSQLSaveToBinAndTxtFile(tableSQL,dataBaseConnection,fileName,path);
            assertEquals(localDataSQLBefore+1,localDataSQL.getWhileLooplists().get(0).getAdministratorStorage().getList().size());
            String REMOVE="delete from admin where administratorID='AD2';";
            dataBaseConnection.getStatement().execute(REMOVE);
        }
    }
}
