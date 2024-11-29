import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> tickets = new LinkedList<>();
    private final int maxTicketCapacity;

    public TicketPool(int maxCapacity){
        this.maxTicketCapacity = maxCapacity;
    }

    public synchronized void addTicket(Ticket ticket){
        while (tickets.size() >= maxTicketCapacity) {
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        tickets.add(ticket);
        notifyAll();
    }

    public synchronized Ticket removeTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket ticket = tickets.poll();
        notifyAll();
        return ticket;
    }
}
