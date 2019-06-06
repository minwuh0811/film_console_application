import java.util.ArrayList;

public class LoadDataSQLSaveToBinAndTxtFile {
    private While whileLoop;
    private DataBaseConnection dataBaseConnection;
    private TablesSQL tablesSQL;
    private FileName fileName;
    private String path;
    private ArrayList<While> whileLooplists=new ArrayList<>();

    public LoadDataSQLSaveToBinAndTxtFile( TablesSQL tablesSQL, DataBaseConnection dataBaseConnection,FileName fileName, String path) {
        this.dataBaseConnection=dataBaseConnection;
        this.path=path;
        this.tablesSQL=tablesSQL;
        this.fileName=fileName;
        this.whileLooplists=localDataSQL(tablesSQL,dataBaseConnection,fileName,path);
    }

    public ArrayList<While> localDataSQL(TablesSQL tableSQL, DataBaseConnection dataBaseConnection, FileName fileName, String path) {
        ArrayList<While> whileslist=new ArrayList<>();
        While whileAdmin= new While(tableSQL.getAdministratorDBTable(), dataBaseConnection.getStatement(), fileName.getFileNameAdmin(), path,new AdministratorStorage());
        whileslist.add(whileAdmin);
        While whileFilm=new While(tableSQL.getFilmDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameFilm(), path, new FilmStorage() );
        whileslist.add(whileFilm);
        While whileTicket=new While(tableSQL.getTicketDBTable(),dataBaseConnection.getStatement(),fileName.getFileNameTicket(),path,whileFilm.getFilmStorage(),new TicketStorage());
        whileslist.add(whileTicket);
        return whileslist;
    }

    public ArrayList<While> getWhileLooplists() {
        return whileLooplists;
    }
}
