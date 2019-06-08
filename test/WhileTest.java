import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WhileTest {
    String DBURL="jdbc:mysql://192.168.99.100:3306/filmTest";
    String DBUser="root";
    String DBPassword="password";
    static String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

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

}