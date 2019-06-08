import java.io.*;
import java.util.ArrayList;

public class TicketStorage extends Storage {
    private Ticket ticket;
    private ArrayList<Ticket> list=new ArrayList<>();

    public TicketStorage(){}

    public TicketStorage(String file_name, String path, String string) {
        super(file_name,path,string);
        LoadTicketFile(file_name,path);
    }

    public Ticket getTicket() {
        return ticket;
    }

    public ArrayList<String> getIDlist() {
            for (Ticket ticket: list) {
                IDlist.add(ticket.getTicketID());
            }
        return IDlist;
    }

    public ArrayList<Ticket> getList() {
        return list;
    }

    private void saveToTicketFile(String file_name, String path) {
        String file1=file_name+".bin";
        String path_bin = path + java.io.File.separator + file1;
        java.io.File file_Customer = new java.io.File(path_bin);
        String file2=file_name+".txt";
        String path_txt = path + java.io.File.separator + file2;
        java.io.File file = new java.io.File(path_txt);
        int length=list.size();
        String text="";
        for (int i=0; i<length; i++) {
            text += "" + list.get(i).getTicketID() + ' '
                    + list.get(i).getFilm().getFilmID() + ' '
                    + list.get(i).getPrice() + ' '
                    + list.get(i).TotalofTicket + '\n';
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

    private void LoadTicketFile(String file_name, String path) {
        String file = file_name + ".bin";
        path = path + java.io.File.separator + file;
        java.io.File file_Customer = new java.io.File(path);
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(file_Customer)))) {
            list = (ArrayList<Ticket>) in.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {

        }
        getIDlist();
    }

    public void searchTicket(String filmID, ArrayList<Ticket> ticketlist) {
        for (Ticket ticket : ticketlist) {
            if (ticket.getFilm().getFilmID() .equals(filmID)) {
                this.ticket = ticket;
            }
        }
    }

    public void close(String file_name,String path){
        saveToTicketFile(file_name,path);
//        saveToOrderSearchFile();
    }


    public void addTicketDBSQL(Ticket ticket) {
        list.add(ticket);
        IDlist.add(ticket.getTicketID());
    }

    public void removeTicket(Ticket ticket) {
        list.remove(ticket);
    }

    public int returnID() {
        int length = IDlist.size();
        int Result = -1;
        for (int n = 1; n <= length; n++) {
            String loop = string + n;
            if (!IDlist.get(n-1).equals(loop)) {
                Result = n;
                break;
            }
        }
        return Result;
    }

}

