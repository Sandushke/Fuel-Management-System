package coursework;

public class Data {
    private Customer customer;
    private DateTime dateTime;
    private int dispenserId;

    public Data(Customer customer, DateTime dateTime, int dispenserId) {
        this.customer = customer;
        this.dateTime = dateTime;
        this.dispenserId = dispenserId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDispenserId() {
        return dispenserId;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

}
