import java.util.ArrayList;

public class ticketStorage {
    private ticket ticket;
    private ArrayList<ticket> ticketlist=new ArrayList<>();

    public ticket getTicket() {
        return ticket;
    }

    public void setTicket(ticket ticket) {
        this.ticket = ticket;
    }

    public ArrayList<ticket> getTicketlist() {
        return ticketlist;
    }

    public void setTicketlist(ArrayList<ticket> ticketlist) {
        this.ticketlist = ticketlist;
    }

    public void addTicket(ticket ticket) {
        ticketlist.add(ticket);
    }
    public void removeTicket(ticket ticket) {
        ticketlist.remove(ticket);
    }
}
