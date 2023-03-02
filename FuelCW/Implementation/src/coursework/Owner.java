package coursework;

import java.util.ArrayList;

public class Owner {
    private ArrayList<OctaneFuelDispenserManager> petrolDispensers = new ArrayList<>();
    private ArrayList<DieselFuelDispenseManager> dieselDispenser = new ArrayList<>();
    private FuelRepository petrolRepository;
    private FuelRepository dieselRepository;

    public Owner(FuelRepository petrolRepository, FuelRepository dieselRepository){
        this.petrolRepository = petrolRepository;
        this.dieselRepository = dieselRepository;
    }

    public void addPetrolDispenser(OctaneFuelDispenserManager dispenser){
        petrolDispensers.add(dispenser);
    }

    public void addDieselDispenser(DieselFuelDispenseManager dispense){
        dieselDispenser.add(dispense);
    }

    public ArrayList<OctaneFuelDispenserManager> getPetrolDispensers() {
        return petrolDispensers;
    }

    public ArrayList<DieselFuelDispenseManager> getDieselDispenser() {
        return dieselDispenser;
    }

    public FuelRepository getPetrolRepository() {
        return petrolRepository;
    }

    public FuelRepository getDieselRepository() {
        return dieselRepository;
    }

    public WaitingQueue getWaitingQueue(){
        return petrolDispensers.get(0).getFuelQueue().getWaitingQueue();
    }
}
