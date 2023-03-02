package coursework;

import java.sql.*;
import java.util.ArrayList;

public class OctaneFuelDispenserManager implements FuelDispenseManager {
    private static ArrayList<Data> dataArray = new ArrayList<>();
    private static int totalNumberOfVehiclesServed ;
    private static float totalProfit = 0;
    private FuelQueue fuelQueue;
    private FuelRepository repository;
    private static float pricePerLiter;
    private int fuelType;
    private int dispenserId;

    public OctaneFuelDispenserManager(FuelQueue fuelQueue, FuelRepository repository, float pricePerLiter, int fuelType, int dispenserId) {
        this.fuelQueue = fuelQueue;
        this.repository = repository;
        OctaneFuelDispenserManager.pricePerLiter = pricePerLiter;
        this.fuelType = fuelType;
        this.dispenserId= dispenserId;
    }
    @Override
    public void serveCustomer(DateTime date)  throws SQLException, ClassNotFoundException{
        servedStatsDb stats = new servedStatsDb();
        if (fuelQueue.customers.size()>0) {
            Data data = new Data(fuelQueue.getFirstCustomer(), date, this.dispenserId);
            addData(data);
            incrementVehiclesServed();
            incrementProfit(fuelQueue.getFirstCustomer());
            fuelQueue.removeCustomer(0);
            int customerId  = OctaneFuelDispenserManager.getDataArray().size()+DieselFuelDispenseManager.getDataArray().size();
            stats.addServedInfo(customerId, data.getCustomer(), data.getDateTime(), data.getDispenserId());
        } else {
            System.out.println("No Customers in the Queue");
        }
    }

    public static void addData(Data data){
        dataArray.add(data);
    }

    public static ArrayList<Data> getDataArray() {
        return dataArray;
    }

    public static Customer getHighestAmountOfFuel(DateTime date) throws SQLException, ClassNotFoundException{
        Customer highestAmountOfFuel = new Customer(0, 0, 1, 0, false);
        for (Data data : dataArray) {
            if (data.getDateTime() == date) {
                if (data.getCustomer().getFuelNeeded() > highestAmountOfFuel.getFuelNeeded()) {
                    highestAmountOfFuel = data.getCustomer();
                }
            }
        }
        return highestAmountOfFuel;
    }

    public static float totalFuelDispensed(int vehicleType) throws SQLException, ClassNotFoundException{
        float totalFuelDispensed = 0;
        for (Data data : dataArray) {
            if (data.getCustomer().getVehicleType()==vehicleType) {
                totalFuelDispensed += data.getCustomer().getFuelNeeded();
            }
        }
        return totalFuelDispensed;
    }

    public static float totalProfit(DateTime date) throws SQLException, ClassNotFoundException{
        float profit = 0;
        for (Data data : dataArray) {
            if (data.getDateTime() == date) {
                profit += data.getCustomer().getFuelNeeded() * pricePerLiter;
            }
        }
        return profit;
    }

    public void totalDieselVehicles(){

    }
    public static void addDataArray(ArrayList <Data> dataArrayList){
        dataArray=dataArrayList;
    }

    public static void incrementVehiclesServed(){
        totalNumberOfVehiclesServed++;
    }

    public static int getVehiclesServed(){
        return totalNumberOfVehiclesServed;
    }

    public static void incrementProfit(Customer customer){
        totalProfit += customer.getFuelNeeded()*pricePerLiter;
    }

    public static float getProfitPerDispenser(){
        return totalProfit;
    }

    public float checkFuelLeft(){
        return repository.checkFuelLeft();
    }

    public static float getPricePerLiter() {
        return pricePerLiter;
    }

    public int getFuelType() {
        return fuelType;
    }

    public FuelQueue getFuelQueue() {
        return fuelQueue;
    }


    public void totalOctaneVehiclesServed(){

    }
    public void totalOctaneFuelServed(){

    }
    public void octanePayment()throws ClassNotFoundException, SQLException{
        float totalPayment = 0;
        for(int i=0; i<dataArray.size();i++){
            totalPayment+= dataArray.get(i).getCustomer().getFuelNeeded()*pricePerLiter;
        }
    }

    public void totalVehicles()throws ClassNotFoundException, SQLException{
        int totalVehicles = 0;
        for(int i=0; i<dataArray.size();i++){
            totalVehicles+= dataArray.get(i).getCustomer().getRegistrationNumber()*totalNumberOfVehiclesServed;
        }
    }
}
