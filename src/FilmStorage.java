import java.io.*;
import java.util.ArrayList;

public class FilmStorage extends Storage implements FilmInterface {
    private Film film;
    private ArrayList<Film> list=new ArrayList<>();

    public FilmStorage(){

    }

    public FilmStorage(String file_name, String path, String string) {
        super(file_name,path,string);
        LoadFilmFile(file_name,path);
    }

    public int returnID() {
        int length = IDlist.size();
        int Result = -1;
        for (int n = 1; n <=length; n++) {
            String loop = string + n;
            if (!IDlist.get(n-1).equals(loop)) {
                Result = n;
                break;
            }
        }
        return Result;
    }


    public ArrayList<String> getIDlist() {
        for (Film film: list) {
            IDlist.add(film.getFilmID());       }
        return IDlist;
    }

    public Film getFilm() {
        return film;
    }

    private void saveToFilmFile(String file_name, String path) {
        String file1=file_name+".bin";
        String path_bin = path + java.io.File.separator + file1;
        java.io.File file_Customer = new java.io.File(path_bin);
        String file2=file_name+".txt";
        String path_txt = path + java.io.File.separator + file2;
        java.io.File file = new java.io.File(path_txt);
        int length=list.size();
        String text="";
        for (int i=0; i<length; i++) {
            text += "" + list.get(i).getFilmID() + ' '
                    + list.get(i).getName() + ' '
                    + list.get(i).getLimitofage() + '\n';
        }

        Repository_Class repository= new Repository_Class();
        repository.SaveTofile(file,text);

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file_Customer)))) {
            out.writeObject(list);
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
            list = (ArrayList<Film>) in.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {

        }
        getIDlist();
    }

    public void close(String file_name,String path){
        saveToFilmFile(file_name,path);
    }

    public void removefilm(Film film) {
        list.remove(film);
    }

    public void addFilmDBSQL(Film film) {
        list.add(film);
        IDlist.add(film.getFilmID());
    }

    public ArrayList<Film> getList() {
        return list;
    }

    public void searchFilm(String filmID, ArrayList<Film> filmlist) {
        for (Film film : filmlist) {
            if (film.getFilmID().equals(filmID)) {
                this.film=film;
            }
        }

    }

    }



