import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class While {
    private String fileName;
    private String path;
    private AdministratorStorage administratorStorage;
    private FilmStorage filmStorage;
    private ticketStorage ticketStorage;
    private Statement statement;
    private String DBTable;

    public While(String DBTable, Statement statement, String fileName, String path, AdministratorStorage administratorStorage) {
        this.fileName = fileName;
        this.path = path;
        this.administratorStorage = administratorStorage;
        this.DBTable=DBTable;
        this.statement=statement;
        try {
            this.administratorStorage=whileAdmin();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public While(String DBTable, Statement statement, String fileName, String path, FilmStorage filmStorage){
        this.fileName=fileName;
        this.path=path;
        this.filmStorage=filmStorage;
        this.DBTable=DBTable;
        this.statement=statement;
        try {
            this.filmStorage=whileFilm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public While(String DBTable, Statement statement,String fileName, String path, FilmStorage filmStorage, ticketStorage ticketStorage){
        this.fileName=fileName;
        this.path=path;
        this.filmStorage=filmStorage;
        this.ticketStorage=ticketStorage;
        this.DBTable=DBTable;
        this.statement=statement;
        try {
            this.ticketStorage=whileTicket();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    private AdministratorStorage whileAdmin() throws Exception {
        try {
            ResultSet resultSet = statement.executeQuery("select * from " + DBTable + ";");
            while (resultSet.next()) {
                Administrator administrator = new Administrator(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                administratorStorage.addAdministratorSQLDB(administrator);
            }
            administratorStorage.close(fileName, path);
        } catch (Exception e) {}
        return administratorStorage;
    }

    public FilmStorage whileFilm() throws Exception {
        ResultSet resultSet = statement.executeQuery("select * from " + DBTable + ";");
        while (resultSet.next()) {
            film film = new film(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
            filmStorage.addFilmDBSQL(film);
        }
        filmStorage.close(fileName, path);
        return filmStorage;
    }

    public ticketStorage whileTicket() throws Exception {
        ResultSet resultSet = statement.executeQuery("select * from " + DBTable + ";");
        while (resultSet.next()) {
            String filmID = resultSet.getString(2);
            filmStorage.searchFilm(filmID, filmStorage.getList());
            ticket newTicket = new ticket(resultSet.getString(1), filmStorage.getFilm(), resultSet.getInt(3), resultSet.getInt(4));
            ticketStorage.addTicketDBSQL(newTicket);
        }
        ticketStorage.close(fileName, path);
        return ticketStorage;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public AdministratorStorage getAdministratorStorage() {
        return administratorStorage;
    }

    public FilmStorage getFilmStorage() {
        return filmStorage;
    }

    public ticketStorage getTicketStorage() {
        return ticketStorage;
    }
    
}
