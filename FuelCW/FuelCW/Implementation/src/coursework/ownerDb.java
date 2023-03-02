package coursework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ownerDb {
    public Owner getOwner(){
        Owner owner = null;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuelcw", "root", "sandushkedealwis_03");
            Statement stmt = conn.createStatement();
            Class.forName("com.mysql.cj.jdbc.Driver");
            FuelRepository petrolRepo = null;
            FuelRepository dieselRepo = null;

            ResultSet result = stmt.executeQuery("SELECT * FROM fuelrepository");
            while (result.next()) {
                if (result.getInt(1) == 1) {
                    petrolRepo = new FuelRepository(result.getInt(1), result.getInt(2));
                } else if (result.getInt(1) == 2) {
                    dieselRepo = new FuelRepository(result.getInt(1), result.getInt(2));
                }
            }
            WaitingQueue waitingQ = new WaitingQueue();
            owner = new Owner(petrolRepo, dieselRepo);
            ResultSet result1 = stmt.executeQuery("SELECT * FROM petroldispenser");
            while (result1.next()) {
                String [] vehiclesAllowed = result1.getString(2).split(" ");
                int [] vehiclesAllowedInt = new int[vehiclesAllowed.length];
                for(int i=0;i< vehiclesAllowed.length;i++){
                    vehiclesAllowedInt[i] = Integer.parseInt(vehiclesAllowed[i]);
                }
                FuelQueue petrolQ = new FuelQueue(vehiclesAllowedInt, waitingQ);
                OctaneFuelDispenserManager petrolDisp = new OctaneFuelDispenserManager(petrolQ,petrolRepo, result1.getFloat(3), 1, result1.getInt(1));
                owner.addPetrolDispenser(petrolDisp);

            }
            ResultSet result2 = stmt.executeQuery("SELECT * FROM dieseldispenser");
            while (result2.next()) {
                String [] vehiclesAllowed = result2.getString(2).split(" ");
                int [] vehiclesAllowedInt = new int[vehiclesAllowed.length];
                for(int i=0;i< vehiclesAllowed.length;i++){
                    vehiclesAllowedInt[i] = Integer.parseInt(vehiclesAllowed[i]);
                }
                FuelQueue dieselQ = new FuelQueue(vehiclesAllowedInt, waitingQ);
                DieselFuelDispenseManager dispenseManager = new DieselFuelDispenseManager(dieselQ,dieselRepo, result2.getFloat(3), 2, result2.getInt(1));
                owner.addDieselDispenser(dispenseManager);

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        servedInfo();
        return owner;
    }
    private void servedInfo(){
        ArrayList<DateTime> dateTimes = new ArrayList<>();
        ArrayList<Integer> customerID = new ArrayList<>();
        ArrayList<Integer> dispenserId = new ArrayList<>();
        ArrayList<Data> dieselData = new ArrayList<>();
        ArrayList<Data> petrolData = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuelcw", "root", "sandushkedealwis_03");
            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery("SELECT * FROM serverinfo");
            while (result.next()) {
                DateTime dateTime = new DateTime(result.getInt(2), result.getInt(3), result.getInt(4));
                dateTimes.add(dateTime);
                customerID.add(result.getInt(1));
                dispenserId.add(result.getInt(5));
            }
            for (int i = 0; i < customerID.size(); i++) {
                ResultSet result1 = stmt.executeQuery("SELECT * FROM customer WHERE customerID = " + customerID.get(i));
                result1.next();
                Customer customer = new Customer(result1.getInt(2), result1.getInt(3), result1.getInt(4), result1.getInt(5), true);
                Data data = new Data(customer, dateTimes.get(i), dispenserId.get(i));
                if (customer.getFuelType() == 1) {
                    petrolData.add(data);
                } else if (customer.getFuelType() == 2) {
                    dieselData.add(data);
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        OctaneFuelDispenserManager.addDataArray(petrolData);
        DieselFuelDispenseManager.addDataArray(dieselData);
    }

}
