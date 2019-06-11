import java.sql.ResultSet;
import java.sql.Statement;

public class While {
    private String fileName;
    private String path;
    private AdministratorStorage administratorStorage;
    private FilmStorage filmStorage;
    private TicketStorage ticketStorage;
    private Statement statement;
    private String DBTable;

    public While(String DBTable, Statement statement, String fileName, String path, AdministratorStorage administratorStorage) {
        this.fileName = fileName;
        this.path = path;
        this.administratorStorage = administratorStorage;
        this.DBTable=DBTable;
        this.statement=statement;
        try {
            this.administratorStorage=whileAdmin(DBTable, statement, fileName, path, administratorStorage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public While(String DBTable, Statement statement, String fileName, String path, FilmStorage filmStorage){
        this.fileName=fileName;
        this.path=path;
        this.filmStorage=filmStorage;
        this.DBTable=DBTable;
        this.statement=statement;
        try {
            this.filmStorage=whileFilm(DBTable, statement, fileName, path, filmStorage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   /* public While(String DBTable, Statement statement,String fileName, String path, FilmStorage filmStorage, TicketStorage ticketStorage){
        this.fileName=fileName;
        this.path=path;
        this.filmStorage=filmStorage;
        this.ticketStorage=ticketStorage;
        this.DBTable=DBTable;
        this.statement=statement;
        try {
            this.ticketStorage=whileTicket(DBTable,statement,  fileName, path, filmStorage,ticketStorage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/


    public AdministratorStorage whileAdmin(String DBTable, Statement statement, String fileName, String path, AdministratorStorage administratorStorage) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from " + DBTable + ";");
            while (resultSet.next()) {
                Administrator administrator = new Administrator(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                administratorStorage.addAdministratorSQLDB(administrator);
            }
            administratorStorage.close(fileName, path);
        } catch (Exception e) {
            System.out.println(e);
        }
        return administratorStorage;
    }

    public FilmStorage whileFilm(String DBTable, Statement statement, String fileName, String path, FilmStorage filmStorage) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from " + DBTable + ";");
            while (resultSet.next()) {
                 Film film = new Film(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5));
                 filmStorage.addFilmDBSQL(film);
            }
        filmStorage.close(fileName, path);
        } catch (Exception e) {
            System.out.println(e);
        }
        return filmStorage;
    }

/*    public TicketStorage whileTicket(String DBTable, Statement statement, String fileName, String path, FilmStorage filmStorage,TicketStorage ticketStorage) throws Exception {
        ResultSet resultSet = statement.executeQuery("select * from " + DBTable + ";");
        while (resultSet.next()) {
            String filmID = resultSet.getString(2);
            filmStorage.searchFilm(filmID, filmStorage.getList());
            //Ticket newTicket = new Ticket(resultSet.getString(1), filmStorage.getFilm(), resultSet.getInt(3), resultSet.getInt(4));
            //ticketStorage.addTicketDBSQL(newTicket);
        }
//        ticketStorage.close(fileName, path);
        return ticketStorage;
    }*/



    public String getPath() {
        return path;
    }

    public AdministratorStorage getAdministratorStorage() {
        return administratorStorage;
    }

    public FilmStorage getFilmStorage() {
        return filmStorage;
    }

    public TicketStorage getTicketStorage() {
        return ticketStorage;
    }

}
