import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> tickets = new LinkedList<>();
    private final int maxCapacity;

    public TicketPool(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(Ticket ticket){
        while (tickets.size() >= maxCapacity) {

        }
    }
}
