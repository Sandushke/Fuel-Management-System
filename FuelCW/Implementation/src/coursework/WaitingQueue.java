package coursework;

public class WaitingQueue extends Queue {
    public WaitingQueue() {
        super();
    }

    public void sendCustomerToFuelQueue(){
        Customer customer = customers.get(0);
        customers.remove(0);
    }

    public Customer getFirstCustomer(){
        return customers.get(0);
    }
}
