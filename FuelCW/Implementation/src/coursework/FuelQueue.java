package coursework;

import java.sql.SQLException;

public class FuelQueue extends Queue{
    private final int maximumCustomers = 10;
    private int noOfCustomers;
    private int[] vehiclesAllowed;
    // vehicles allowed
    //1 - car
    //2 - van
    //3- threewheel
    //4 - motorbike
    //5 - public transport
    //6 - other
    private WaitingQueue waitingQueue;

    public FuelQueue(int[] vehiclesAllowed, WaitingQueue waitingQueue){
        super();
        this.noOfCustomers = 0;
        this.vehiclesAllowed = vehiclesAllowed;
        this.waitingQueue = waitingQueue;
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, ClassNotFoundException{
        if (noOfCustomers<maximumCustomers) {
            if (checkVehicleType(customer)) {
                super.addCustomer(customer);
                noOfCustomers++;
            }
            else {
                System.out.println("Vehicle not allowed in the Queue");
            }
        } else {
            System.out.println("Queue is full");
            waitingQueue.addCustomer(customer);
            System.out.println("Added the customer to the fuel Queue");
        }
    }

    //can use to remove the served customer and remove a selected customer
    public void removeCustomer(int index) throws SQLException, ClassNotFoundException{
        customers.remove(index);
        noOfCustomers--;
    }

    public Customer getFirstCustomer(){
        return customers.get(0);
    }

    public int getFreeSpace(){
        return maximumCustomers-noOfCustomers;
    }

    private boolean checkVehicleType(Customer customer) throws SQLException, ClassNotFoundException{
        for (int s : vehiclesAllowed) {
            if (s==customer.getVehicleType()) {
                return true;
            }
        }
        return false;
    }

    public int[] getVehiclesAllowed() {
        return vehiclesAllowed;
    }

    public void giveTicket() throws SQLException, ClassNotFoundException{
        for (Customer customer : customers) {
            if (!customer.gotTicket()) {
                customer.getTicket();
            }
        }
    }

    public WaitingQueue getWaitingQueue() {
        return waitingQueue;
    }
}
