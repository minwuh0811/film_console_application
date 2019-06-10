import java.sql.Statement;


public class AlterSQLData {
    private DataBaseConnection dataBaseConnection;
    private TablesSQL tablesSQL;
    private Film film;

    public AlterSQLData(DataBaseConnection dataBaseConnection, TablesSQL tablesSQL, Film film) {
        this.dataBaseConnection=dataBaseConnection;
        this.tablesSQL=tablesSQL;
        this.film=film;
    }

    public void alterSQLDataFilm(Film film ) {
        try {
            Statement statement=dataBaseConnection.getStatement();
            String string = String.format("UPDATE " + tablesSQL.getFilmDBTable() +
                    " SET totalTicket = \'" + film.getTotalofTicket() +"\' where filmID= \'" + film.getFilmID() +"\';");
            statement.execute(string);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}

