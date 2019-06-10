import java.util.ArrayList;

public class TicketStorage {
    private Ticket ticket;
    private ArrayList<Ticket> list=new ArrayList<>();

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ArrayList<Ticket> getList() {
        return list;
    }

    public void setList(ArrayList<Ticket> list) {
        this.list = list;
    }

    public void addTicketDBSQL(Ticket ticket) {
        list.add(ticket);
    }



}

