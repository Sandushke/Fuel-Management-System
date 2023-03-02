package coursework;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public void run(){
        for(int i =0; i<=1000;i++){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e){
                throw new RuntimeException(e);
            }
            Connection connection = null;
            try{
                connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuelcw", "root", "sandushkedealwis_03");
            }
            catch(SQLException E){
                throw new RuntimeException(E);
            }
            Statement stmt= null;
            try {
                stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ResultSet result = null;
            try {
                result = stmt.executeQuery("SELECT * FROM customer");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                result.absolute(2);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                result.deleteRow();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ownerDb owdb = new ownerDb();
        Owner owner = owdb.getOwner();
        System.out.println(owner.getPetrolDispensers().size());
//        Owner owner = initialize();
//        Runnable object1 = (Runnable) new Main();
//        Thread THREAD1 = new Thread(object1);
//        THREAD1.start();
        //call the getOwner method
        //add exception handling
        while(true){
            Scanner scanner = new Scanner(System.in);
            String  userInput="";
            while (true) {
                try {
                    System.out.println("1. Add a vehicle to the queue\n" +
                            "2. Serve a vehicle\n" +
                            "3. See the statistics\n" +
                            "4. Add new dispenser\n" +
                            "choice: ");

                    userInput = scanner.next();
                    if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3") || userInput.equals("4")) {
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input, please re-enter ");
                }
            }

            switch (userInput) {
                case "1":
                    //code to add a vehicle
                    //add exception handling

                    Scanner scanner6 = new Scanner(System.in);
                    String s="";
                    while (true) {
                        try {
                            System.out.println("1. Add new Vehicle\n" +
                                    "2. Add vehicle from waiting queue");
                            s = scanner6.next();
                            if (userInput.equals("1") || userInput.equals("2")) {
                                break;
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input, please re-enter ");
                        }
                    }
                    //remove code redundancy
                    if (s.equals("1")) {
                        int registrationNumber=0;
                        while (true) {
                            try {
                                Scanner scanner1 = new Scanner(System.in);
                                System.out.println("What is the registration Number?");
                                registrationNumber = scanner1.nextInt();
                                if(registrationNumber<1000000 && registrationNumber>99999){
                                    break;
                                }else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input pls enter a six digit number");
                            }
                        }
                        int vehicleType =0;
                        while (true) {
                            try {
                                Scanner scanner2 = new Scanner(System.in);
                                System.out.println("What is the vehicle type?\n" +
                                        "1. Car\n" +
                                        "2. Van\n" +
                                        "3. Three Wheel\n" +
                                        "4. Motor Bike\n" +
                                        "5. Public Transport\n" +
                                        "6. Other");
                                vehicleType = scanner2.nextInt();
                                if (vehicleType>0 && vehicleType<7) {
                                    break;
                                } else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                System.out.println("invalid input please re-enter ");
                            }
                        }
                        int fuelType=0;
                        while (true) {
                            try {
                                Scanner scanner3 = new Scanner(System.in);
                                System.out.println("What is the fuel type?\n" +
                                        "1. Petrol\n" +
                                        "2. Diesel");
                                fuelType = scanner3.nextInt();
                                if (fuelType > 0 && fuelType < 3) {
                                    break;
                                } else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input pls re-enter");
                            }
                        }
                        float fuelNeeded = 0;
                        while (true) {
                            try {
                                Scanner scanner4 = new Scanner(System.in);
                                System.out.println("How much fuel is needed?");
                                fuelNeeded = scanner4.nextFloat();
                                if (fuelNeeded > 0 && fuelNeeded < 120) {
                                    break;
                                } else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input pls re-enter");
                            }
                        }

                        Customer customer = new Customer(registrationNumber, vehicleType, fuelType, fuelNeeded, false);
                        addCustomerToQueue(owner, customer);


                    } else if (s.equals("2")) {
                        Customer customer = owner.getPetrolDispensers().get(0).getFuelQueue().getWaitingQueue().getFirstCustomer();
                        addCustomerToQueue(owner, customer);
                    }
                    break;
                case "2":
                    String choiceType ="";
                    Scanner choice = new Scanner(System.in);
                    while (true) {
                        try {
                            choice = new Scanner(System.in);
                            System.out.println("Which fuel type to serve?\n" +
                                    "1. Petrol\n" +
                                    "2. Diesel");
                            choiceType = choice.next();
                            if (choiceType.equals("1") || choiceType.equals("2")){
                                break;
                            }
                            else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input pls re-enter");
                        }
                    }
                    Date dateTime = new Date();
                    LocalDate ld = dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    DateTime dt = new DateTime(ld.getDayOfMonth(),ld.getMonthValue(),ld.getYear());
                    if (choiceType.equals("1")){

                        int choiceNumber=0;
                        while (true) {
                            try {
                                choice = new Scanner(System.in);
                                System.out.println("pick a dispenser");
                                for (int i = 0; i < owner.getPetrolDispensers().size(); i++) {
                                    int count = i + 1;
                                    System.out.println(count + ". Petrol dispensers " + count);
                                }
                                choiceNumber = choice.nextInt();
                                if (choiceNumber > 0 && choiceNumber < owner.getPetrolDispensers().size() + 1) {
                                    break;
                                } else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input pls re-enter");
                            }
                        }

                        OctaneFuelDispenserManager octD = owner.getPetrolDispensers().get((choiceNumber)-1);
                        octD.serveCustomer(dt);

                    } else if (choiceType.equals("2")) {
                        int choiceNumber=0;
                        while (true) {
                            try {
                                choice = new Scanner(System.in);
                                System.out.println("pick a dispenser");
                                for (int i = 0; i < owner.getDieselDispenser().size(); i++) {
                                    int count = i + 1;
                                    System.out.println(count + ". Diesel dispensers " + count);
                                }
                                choiceNumber = choice.nextInt();
                                if (choiceNumber > 0 && choiceNumber < owner.getDieselDispenser().size() + 1) {
                                    break;
                                } else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input pls re-enter");
                            }
                        }
                        DieselFuelDispenseManager dieselD = owner.getDieselDispenser().get((choiceNumber)-1);
                        dieselD.serveCustomer(dt);


                    }
                    //code to serve a vehicle
                    //add exception handling
                    break;
                case "3":
                    //code to see stats
                    //add exception handling
                    String selectedStats="";
                    Scanner scanner7 = new Scanner(System.in);
                    while (true) {
                        try {
                            scanner7 = new Scanner(System.in);
                            System.out.println("1. Total fuel dispensed per vehicle type per fuel type\n" +
                                    "2. The vehicle that received the largest amount of fuel for the day and the type of fuel received\n" +
                                    "3. Total number of vehicles served by each dispenser along with the amount of fuel and the total income per dispenser\n" +
                                    "4. Total income of the gas station  per day per fuel type\n" +
                                    "5. Remaining stock");
                            selectedStats = scanner7.next();
                            if (selectedStats.equals("1") || selectedStats.equals("2") || selectedStats.equals("3") || selectedStats.equals("4") || selectedStats.equals("5")) {
                                break;
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input pls re-enter");
                        }
                    }
                    if (selectedStats.equals("1")) {
                        // 6 petrol 6 diesel
                        float[] TotalFuelDispensed = new float[12];
                        for (int i=0; i<12;i++){
                            TotalFuelDispensed[i]=0;
                        }
                        for (int i =0;i<OctaneFuelDispenserManager.getDataArray().size();i++){
                            if (OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getVehicleType()== 1){
                                TotalFuelDispensed[0]+=OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getVehicleType()== 2){
                                TotalFuelDispensed[1]+=OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getVehicleType()== 3){
                                TotalFuelDispensed[2]+=OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getVehicleType()== 4){
                                TotalFuelDispensed[3]+=OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getVehicleType()== 5){
                                TotalFuelDispensed[4]+=OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getVehicleType()== 6){
                                TotalFuelDispensed[5]+=OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                        }
                        for (int i =0;i<DieselFuelDispenseManager.getDataArray().size();i++){
                            if (DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getVehicleType()== 1){
                                TotalFuelDispensed[6]+=DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getVehicleType()== 2){
                                TotalFuelDispensed[7]+=DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getVehicleType()== 3){
                                TotalFuelDispensed[8]+=DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getVehicleType()== 4){
                                TotalFuelDispensed[9]+=DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getVehicleType()== 5){
                                TotalFuelDispensed[10]+=DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                            if (DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getVehicleType()== 6){
                                TotalFuelDispensed[11]+=DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                            }
                        }
                        System.out.println("Petrol");
                        System.out.println("Car: "+ TotalFuelDispensed[0]);
                        System.out.println("Van: "+ TotalFuelDispensed[1]);
                        System.out.println("Three wheel: "+ TotalFuelDispensed[2]);
                        System.out.println("Motor Bike: "+ TotalFuelDispensed[3]);
                        System.out.println("Public Transport: "+ TotalFuelDispensed[4]);
                        System.out.println("other: "+ TotalFuelDispensed[5]);
                        System.out.println("");
                        System.out.println("Diesel");
                        System.out.println("Car: "+ TotalFuelDispensed[6]);
                        System.out.println("Van: "+ TotalFuelDispensed[7]);
                        System.out.println("Three wheel: "+ TotalFuelDispensed[8]);
                        System.out.println("Motor Bike: "+ TotalFuelDispensed[9]);
                        System.out.println("Public Transport: "+ TotalFuelDispensed[10]);
                        System.out.println("other: "+ TotalFuelDispensed[11]);
                        System.out.println("");

                    }
                    else if (selectedStats.equals("2")){
                        Date dateTime1 = new Date();
                        LocalDate ld1 = dateTime1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        DateTime dt1 = new DateTime(ld1.getDayOfMonth(),ld1.getMonthValue(),ld1.getYear());
                        float LargestFuelAmount = 0;
                        String IsHighestFuelType= null;
                        for (int i =0;i<OctaneFuelDispenserManager.getDataArray().size();i++){
                            if(OctaneFuelDispenserManager.getDataArray().get(i).getDateTime().getDate()==dt1.getDate() && OctaneFuelDispenserManager.getDataArray().get(i).getDateTime().getMonth()==dt1.getMonth() && OctaneFuelDispenserManager.getDataArray().get(i).getDateTime().getYear()==dt1.getYear()) {
                                if (OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded() > LargestFuelAmount) {
                                    LargestFuelAmount = OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                                    IsHighestFuelType = "petrol";

                                }
                            }
                        }
                        for (int i =0;i<DieselFuelDispenseManager.getDataArray().size();i++){
                            if(DieselFuelDispenseManager.getDataArray().get(i).getDateTime().getDate()==dt1.getDate() && DieselFuelDispenseManager.getDataArray().get(i).getDateTime().getMonth()==dt1.getMonth() && DieselFuelDispenseManager.getDataArray().get(i).getDateTime().getYear()==dt1.getYear()) {
                                if (DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded() > LargestFuelAmount) {
                                    LargestFuelAmount = DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                                    IsHighestFuelType = "diesel";
                                }
                            }
                        }
                        System.out.println(IsHighestFuelType +" : "+ LargestFuelAmount);
                    }
                    else if (selectedStats.equals("3")){
                        ArrayList<float[]> petrolDispenseIncome = new ArrayList<>();
                        ArrayList<float[]> dieselDispenseIncome = new ArrayList<>();
                        for (int i = 0;i<owner.getPetrolDispensers().size();i++){
                            // to store the number of vehicles served and total fuel amount served by dispenser
                            petrolDispenseIncome.add(new float[]{0,0});
                        }
                        for (int i = 0;i<owner.getDieselDispenser().size();i++){
                            dieselDispenseIncome.add(new float[]{0,0});
                        }
                        for (int i = 0; i<OctaneFuelDispenserManager.getDataArray().size();i++){
                            // increment number of vehicles served
                            petrolDispenseIncome.get(i)[0]++;
                            // adding the required fuel needed
                            petrolDispenseIncome.get(i)[1]+=OctaneFuelDispenserManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                        }
                        for (int i = 0; i<DieselFuelDispenseManager.getDataArray().size();i++){
                            dieselDispenseIncome.get(i)[0]++;
                            dieselDispenseIncome.get(i)[1]+=DieselFuelDispenseManager.getDataArray().get(i).getCustomer().getFuelNeeded();
                        }
                        for (int i =0;i<owner.getPetrolDispensers().size();i++){
                            int count = i+1;
                            System.out.println("Petrol dispenser: "+count);
                            System.out.println("Total number of vehicles served: "+petrolDispenseIncome.get(i)[0]);
                            System.out.println("Total served: "+petrolDispenseIncome.get(i)[1]);
                            System.out.println("Total income: "+petrolDispenseIncome.get(i)[1]*OctaneFuelDispenserManager.getPricePerLiter());
                            System.out.println("");
                        }
                        for (int i =0;i<owner.getDieselDispenser().size();i++){
                            int count = i+1;
                            System.out.println("Diesel dispenser: "+count);
                            System.out.println("Total number of vehicles served: "+dieselDispenseIncome.get(i)[0]);
                            System.out.println("Total served: "+dieselDispenseIncome.get(i)[1]);
                            System.out.println("Total income: "+dieselDispenseIncome.get(i)[1]*DieselFuelDispenseManager.getPricePerLiter());
                            System.out.println("");
                        }

                    }
                    else if (selectedStats.equals("4")){
                        float petrolIncome = 0;
                        float dieselIncome = 0;
                        ArrayList<Data> petrolDataArray = OctaneFuelDispenserManager.getDataArray();
                        ArrayList<Data> dieselDataArray = DieselFuelDispenseManager.getDataArray();
                        for (int i=0; i<petrolDataArray.size(); i++){
                            petrolIncome += petrolDataArray.get(i).getCustomer().getFuelNeeded() * OctaneFuelDispenserManager.getPricePerLiter();
                        }
                        for (int i=0; i<dieselDataArray.size(); i++){
                            dieselIncome += petrolDataArray.get(i).getCustomer().getFuelNeeded() * OctaneFuelDispenserManager.getPricePerLiter();
                        }
                        System.out.println("Total Income from Petrol Dispensers : " + petrolIncome);
                        System.out.println("Total Income from Diesel Dispensers : " + dieselIncome);
                    }
                    else if (selectedStats.equals("5")){
                        float petrolStock = owner.getPetrolRepository().checkFuelLeft();
                        float dieselStock = owner.getDieselRepository().checkFuelLeft();
                        System.out.println("Petrol Stock Left : " + petrolStock);
                        System.out.println("Diesel Stock Left : " + dieselStock);
                    }


                    break;
                case "4":
                    //add exception handling
                    petrolDb petrolDb=new petrolDb();
                    dieselDb dieselDb=new dieselDb();
                    ownerDb ownerDb=new ownerDb();
                    Scanner scannerCase4 = new Scanner(System.in);
                    String dispenser = "";
                    while (true) {
                        try {
                            scannerCase4 = new Scanner(System.in);
                            System.out.println("1. Add a 92 Octane Dispenser\n" +
                                    "2. Add a Diesel Dispenser");
                            dispenser = scannerCase4.next();
                            if (dispenser.equals("1")||dispenser.equals("2")){
                                break;
                            }else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input pls re-enter");
                        }
                    }
                    String vehiclesAllowed="";
                    Scanner scannerVehiclesAllowed = new Scanner(System.in);
                    while(true) {
                        try {
                            scannerVehiclesAllowed = new Scanner(System.in);
                            System.out.println("What vehicles are allowed?\n"+
                                    "1. Car\n"+
                                    "2. Van\n"+
                                    "3. Three Wheel\n"+
                                    "4. Motor Bike\n"+
                                    "5. Public Transport\n"+
                                    "6. Other"
                            );
                            vehiclesAllowed = scannerVehiclesAllowed.nextLine();
                            if (vehiclesAllowed.equals("1") || vehiclesAllowed.equals("2") || vehiclesAllowed.equals("3") || vehiclesAllowed.equals("4") || vehiclesAllowed.equals("5") || vehiclesAllowed.equals("6")) {
                                break;
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input pls re-enter");
                        }
                    }
                    if (dispenser.equals("1")) {
                        petrolDb.addPetrolDispenser( vehiclesAllowed, 450,owner.getPetrolDispensers().size()+1);
                    } else if (dispenser.equals("2")) {
                        dieselDb.addDieselDispenser(vehiclesAllowed, 430,owner.getDieselDispenser().size()+1);
                    }
                    owner = ownerDb.getOwner();
                    break;
                default:
                    //exception
            }

        }
    }


    private static Owner initialize(){
        FuelRepository petrolRepository = new FuelRepository(1, 25000);
        FuelRepository dieselRepository = new FuelRepository(2, 25000);
        Owner owner = new Owner(petrolRepository, dieselRepository);
        WaitingQueue waitingQueue = new WaitingQueue();

        FuelQueue petrolQueue1 = new FuelQueue(new int[]{1, 2}, waitingQueue);
        FuelQueue petrolQueue2 = new FuelQueue(new int[]{1, 2, 3, 5, 6}, waitingQueue);
        FuelQueue petrolQueue3 = new FuelQueue(new int[]{3}, waitingQueue);
        FuelQueue petrolQueue4 = new FuelQueue(new int[]{4}, waitingQueue);

        FuelQueue dieselQueue1 = new FuelQueue(new int[]{5}, waitingQueue);
        FuelQueue dieselQueue2 = new FuelQueue(new int[]{6}, waitingQueue);
        FuelQueue dieselQueue3 = new FuelQueue(new int[]{6}, waitingQueue);

        OctaneFuelDispenserManager petrolDispenser1 = new OctaneFuelDispenserManager(petrolQueue1, petrolRepository, 450, 1, 1);
        OctaneFuelDispenserManager petrolDispenser2 = new OctaneFuelDispenserManager(petrolQueue2, petrolRepository, 450, 1,2);
        OctaneFuelDispenserManager petrolDispenser3 = new OctaneFuelDispenserManager(petrolQueue3, petrolRepository, 450, 1,3);
        OctaneFuelDispenserManager petrolDispenser4 = new OctaneFuelDispenserManager(petrolQueue4, petrolRepository, 450, 1, 4);

        DieselFuelDispenseManager dieselDispenser1 = new DieselFuelDispenseManager(dieselQueue1, dieselRepository, 430, 2, 1);
        DieselFuelDispenseManager dieselDispenser2 = new DieselFuelDispenseManager(dieselQueue2, dieselRepository, 430, 2, 2);
        DieselFuelDispenseManager dieselDispenser3 = new DieselFuelDispenseManager(dieselQueue3, dieselRepository, 430, 2, 3);

        owner.addPetrolDispenser(petrolDispenser1);
        owner.addPetrolDispenser(petrolDispenser2);
        owner.addPetrolDispenser(petrolDispenser3);
        owner.addPetrolDispenser(petrolDispenser4);

        owner.addDieselDispenser(dieselDispenser1);
        owner.addDieselDispenser(dieselDispenser2);
        owner.addDieselDispenser(dieselDispenser3);

        return owner;
    }

    private static void addCustomerToQueue(Owner owner, Customer customer) throws SQLException, ClassNotFoundException {
        if (customer.getFuelType() == 1) {
            int count = 1;
            ArrayList<OctaneFuelDispenserManager> allFuelDispensers = owner.getPetrolDispensers();
            ArrayList<OctaneFuelDispenserManager> availableFuelDispensers = new ArrayList<>();
            Scanner scanner5 = new Scanner(System.in);
            for (OctaneFuelDispenserManager dispenser : allFuelDispensers) {
                int[] vehiclesAllowed = dispenser.getFuelQueue().getVehiclesAllowed();
                for (int j = 0; j < vehiclesAllowed.length; j++) {
                    if (vehiclesAllowed[j] == customer.getVehicleType()) {
                        if (dispenser.getFuelQueue().getFreeSpace() > 0) {
                            System.out.println(count + ". 92 Octane Dispenser " + j + "(" + dispenser.getFuelQueue().getFreeSpace() + " free spaces)");
                            assert false;
                            availableFuelDispensers.add(dispenser);
                            count++;
                        }
                    }
                }
            }
            if (count > 1) {
                String selectedDispenserString = scanner5.next();
                assert false;
                OctaneFuelDispenserManager selectedDispener = availableFuelDispensers.get(Integer.parseInt(selectedDispenserString) -1);
                selectedDispener.getFuelQueue().addCustomer(customer);
                customer.getTicket();
            } else {
                allFuelDispensers.get(0).getFuelQueue().getWaitingQueue().addCustomer(customer);
            }
        } else if (customer.getFuelType() == 2) {
            int count = 1;
            ArrayList<DieselFuelDispenseManager> allFuelDispensers = owner.getDieselDispenser();
            ArrayList<DieselFuelDispenseManager> availableFuelDispensers = null;
            Scanner scanner5 = new Scanner(System.in);
            for (DieselFuelDispenseManager dispenser : allFuelDispensers) {
                int[] vehiclesAllowed = dispenser.getFuelQueue().getVehiclesAllowed();
                for (int j = 0; j < vehiclesAllowed.length; j++) {
                    if (vehiclesAllowed[j] == customer.getVehicleType()) {
                        if (dispenser.getFuelQueue().getFreeSpace() > 0) {
                            System.out.println(count + ". Diesel Dispenser " + j + "(" + dispenser.getFuelQueue().getFreeSpace() + " free spaces)");
                            assert false;
                            availableFuelDispensers.add(dispenser);
                            count++;
                        }
                    }
                }
            }
            if (count > 1) {
                String selectedDispenserString = scanner5.next();
                assert false;
                DieselFuelDispenseManager selectedDispener = availableFuelDispensers.get(Integer.parseInt(selectedDispenserString));
                selectedDispener.getFuelQueue().addCustomer(customer);
                customer.getTicket();
            } else {
                allFuelDispensers.get(0).getFuelQueue().getWaitingQueue().addCustomer(customer);
            }
        }
    }


}
