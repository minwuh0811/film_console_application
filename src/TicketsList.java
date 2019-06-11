import java.io.*;
import java.util.ArrayList;

public class TicketsList extends Storage implements Serializable {
    private static int ID;
    private TicketStorage ticketStorage;
    private ArrayList<TicketStorage> ticketList;
    private String string="TK";
    private String ticketID;

    public TicketsList(TicketStorage ticketStorage){
        ID++;
        this.ticketStorage=ticketStorage;
        this.ticketID="TK" + ID;
    }

    public TicketsList(String file_name, String path, String string){
        super(file_name,path,string);
        LoadFilmFile(file_name,path);
    }

    public ArrayList<TicketStorage> addToTicketList(TicketStorage ticketStorage) {
        ticketList.add(ticketStorage);
        return ticketList;
    }

    public static int getID() {
        return ID;
    }

    public ArrayList<TicketStorage> getTicketList() {
        return ticketList;
    }

    private void saveToFilmFile(String file_name, String path) {
        String file1=file_name+".bin";
        String path_bin = path + java.io.File.separator + file1;
        java.io.File file_Customer = new java.io.File(path_bin);
        String file2=file_name+".txt";
        String path_txt = path + java.io.File.separator + file2;
        java.io.File file = new java.io.File(path_txt);
        int length=ticketStorage.getList().size();
        String text="";
        for (int i=0; i<length; i++) {
            text += "" + ticketID + ' '
                    +  ticketStorage.getList().get(i).getFilm().getFilmID()+ ' '
                    + ticketStorage.getList().get(i).getFilm().getName() + ' '
                    + ticketStorage.getList().get(i).getNumberOfTicket()+ '\n';
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
    }

    public void close(String file_name,String path){
        saveToFilmFile(file_name,path);
    }
}
