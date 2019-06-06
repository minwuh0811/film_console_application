public class TablesSQL {
    private String administratorDBTable = "admin";
    private String filmDBTable = "film";
    private String ticketDBTable = "ticket";

    public TablesSQL(String administratorDBTable,String filmDBTable, String ticketDBTable ) {
        this.administratorDBTable=administratorDBTable;
        this.filmDBTable=filmDBTable;
        this.ticketDBTable=ticketDBTable;
    }

    public String getAdministratorDBTable() {
        return administratorDBTable;
    }

    public void setAdministratorDBTable(String administratorDBTable) {
        this.administratorDBTable = administratorDBTable;
    }

    public String getFilmDBTable() {
        return filmDBTable;
    }

    public void setFilmDBTable(String filmDBTable) {
        this.filmDBTable = filmDBTable;
    }

    public String getTicketDBTable() {
        return ticketDBTable;
    }

    public void setTicketDBTable(String ticketDBTable) {
        this.ticketDBTable = ticketDBTable;
    }
}
