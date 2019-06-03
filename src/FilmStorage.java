import java.io.*;
import java.util.ArrayList;

public class  FilmStorage {
    private film film;
    private ArrayList<film> filmlist=new ArrayList<>();
    private String file_name;
    private String path;

    public FilmStorage(){

    }

    public FilmStorage(String file_name, String path) {
        this.file_name=file_name;
        this.path=path;
        LoadFilmFile(file_name,path);
    }

    private void saveToFilmFile(String file_name, String path) {
        String file1=file_name+".bin";
        String path_bin = path + java.io.File.separator + file1;
        java.io.File file_Customer = new java.io.File(path_bin);
        String file2=file_name+".txt";
        String path_txt = path + java.io.File.separator + file2;
        java.io.File file = new java.io.File(path_txt);
        int length=filmlist.size();
        String text="";
        for (int i=0; i<length; i++) {
            text += "" + filmlist.get(i).getFilmID() + ' '
                    + filmlist.get(i).getName() + ' '
                    + filmlist.get(i).getLimitofage() + '\n';
        }

        try (FileWriter filewriter = new FileWriter(file)) {
            Repository repository=new Repository_Class(filewriter);
            Main.WriteTextFile(repository,text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file_Customer)))) {
            out.writeObject(filmlist);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void LoadFilmFile(String file_name, String path) {
        String file = file_name + ".bin";
        path = path + java.io.File.separator + file;
        java.io.File file_Customer = new java.io.File(path);
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(file_Customer)))) {
            filmlist = (ArrayList<film>) in.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {

        }
        try {
            Administrator.setID(filmlist.size());
        } catch ( java.lang.IndexOutOfBoundsException e){
            Administrator.setID(0);
        }

    }


    private void close(){
        saveToFilmFile(file_name,path);
//        saveToOrderSearchFile();
    }





    public void addfilm(film film) {
        filmlist.add(film);
        saveToFilmFile(file_name,path);
        close();
    }

    public void addFilmDBSQL(film film) {
        filmlist.add(film);
    }

    public ArrayList<film> getFilmlist() {
        return filmlist;
    }
    public void searchFilm(String filmID, ArrayList<film> filmlist) {
        for (film film : filmlist) {
            if (film.getFilmID() == filmID) {
                this.film=film;
            }
        }


    }
}
