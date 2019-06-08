import java.sql.Statement;

public class InsertSQLData {
    private DataBaseConnection dataBaseConnection;
    private TablesSQL tablesSQL;
    private Administrator administrator;
    private Film film;
    private Ticket ticket;

    public InsertSQLData(DataBaseConnection dataBaseConnection, TablesSQL tablesSQL, Administrator administrator) {
        this.dataBaseConnection=dataBaseConnection;
        this.tablesSQL=tablesSQL;
        this.administrator=administrator;
        insertSQLDataAdmin( administrator,tablesSQL,dataBaseConnection );
    }

    public InsertSQLData(DataBaseConnection dataBaseConnection, TablesSQL tablesSQL, Film film) {
        this.dataBaseConnection=dataBaseConnection;
        this.tablesSQL=tablesSQL;
        this.film=film;
        insertSQLDataFilm(film,tablesSQL,dataBaseConnection );
    }

    public InsertSQLData(DataBaseConnection dataBaseConnection, TablesSQL tablesSQL, Ticket ticket) {
        this.dataBaseConnection=dataBaseConnection;
        this.tablesSQL=tablesSQL;
        this.ticket=ticket;
        insertSQLDataTicket(ticket,tablesSQL,dataBaseConnection );
    }

    private static void insertSQLDataAdmin(Administrator administrator, TablesSQL tablesSQL, DataBaseConnection dataBaseConnection ) {
        try {
            Statement statement = dataBaseConnection.getStatement();
            String string = String.format("INSERT INTO " + tablesSQL.getAdministratorDBTable() +
                    " VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");", administrator.getAdministratorID(), administrator.getFirstname(), administrator.getLastname(), administrator.getAdministratorName(), administrator.getPassword());
            statement.execute(string);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

        private static void insertSQLDataFilm(Film film, TablesSQL tablesSQL, DataBaseConnection dataBaseConnection ) {
            try {
                Statement statement=dataBaseConnection.getStatement();
                String string = String.format("INSERT INTO " + tablesSQL.getFilmDBTable() +
                        " VALUES(\"%s\",\"%s\",\"%d\");", film.getFilmID(), film.getName(), film.getLimitofage());
                statement.execute(string);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private static void insertSQLDataTicket(Ticket ticket,  TablesSQL tablesSQL, DataBaseConnection dataBaseConnection) {
            try {
                Statement statement=dataBaseConnection.getStatement();
                String string = String.format("INSERT INTO " + tablesSQL.getTicketDBTable() +
                        " VALUES(\"%s\",\"%s\", \"%d\",\"%d\");", ticket.getTicketID(), ticket.getFilm().getFilmID(), ticket.getPrice(), ticket.TotalofTicket);
                statement.execute(string);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }




