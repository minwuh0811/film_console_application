public class FileName {
    private String fileNameAdmin;
    private String fileNameFilm;
    private String fileNameTicket;

    public FileName(String fileNameAdmin,String fileNameFilm,String fileNameTicket ) {
        this.fileNameAdmin=fileNameAdmin;
        this.fileNameFilm=fileNameFilm;
        this.fileNameTicket=fileNameTicket;
    }

    public String getFileNameAdmin() {
        return fileNameAdmin;
    }


    public String getFileNameFilm() {
        return fileNameFilm;
    }


    public String getFileNameTicket() {
        return fileNameTicket;
    }

}
