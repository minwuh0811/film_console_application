import java.io.*;
import java.util.ArrayList;

public class ticketStorage {
    private ticket ticket;
    private ArrayList<ticket> ticketlist=new ArrayList<>();
    private String file_name;
    private String path;

    public ticketStorage(){}

    public ticketStorage(String file_name, String path) {
        this.file_name=file_name;
        this.path=path;
        LoadTicketFile(file_name,path);
    }

    public ticket getTicket() {
        return ticket;
    }

    public ArrayList<ticket> getTicketlist() {
        return ticketlist;
    }

    private void saveToTicketFile(String file_name, String path) {
        String file1=file_name+".bin";
        String path_bin = path + java.io.File.separator + file1;
        java.io.File file_Customer = new java.io.File(path_bin);
        String file2=file_name+".txt";
        String path_txt = path + java.io.File.separator + file2;
        java.io.File file = new java.io.File(path_txt);
        int length=ticketlist.size();
        String text="";
        for (int i=0; i<length; i++) {
            text += "" + ticketlist.get(i).getTicketID() + ' '
                    + ticketlist.get(i).getFilm().getFilmID() + ' '
                    + ticketlist.get(i).getPrice() + ' '
                    + ticketlist.get(i).TotalofTicket + '\n';
        }

        try (FileWriter filewriter = new FileWriter(file)) {
            Repository repository=new Repository_Class(filewriter);
            Main.WriteTextFile(repository,text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file_Customer)))) {
            out.writeObject(ticketlist);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadTicketFile(String file_name, String path) {
        String file = file_name + ".bin";
        path = path + java.io.File.separator + file;
        java.io.File file_Customer = new java.io.File(path);
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(file_Customer)))) {
            ticketlist = (ArrayList<ticket>) in.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {

        }
        try {
            Administrator.setID(ticketlist.size());
        } catch ( java.lang.IndexOutOfBoundsException e){
            Administrator.setID(0);
        }

    }

    public void searchTicket(String filmID, ArrayList<ticket> ticketlist) {
        for (ticket ticket : ticketlist) {
            if (ticket.getFilm().getFilmID() .equals(filmID)) {
                this.ticket = ticket;
            }
        }
    }

    public void close(String file_name,String path){
        saveToTicketFile(file_name,path);
//        saveToOrderSearchFile();
    }

    public void addTicketDBSQL(ticket ticket) {
        ticketlist.add(ticket);
    }

    public void removeTicket(ticket ticket) {
        ticketlist.remove(ticket);
        saveToTicketFile(file_name,path);
        close(file_name,path);
    }
}

