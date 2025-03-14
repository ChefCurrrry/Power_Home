package iut.dam.power_home;

import java.util.ArrayList;
import java.util.List;

public class Appliance {

    public int id;
    public String name;
    public String reference;
    public int wattage;
    public List<Booking> bookings;


    public Appliance() {
        bookings = new ArrayList<>();
    }

    public Appliance(int id, String name, String reference, int wattage) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.wattage = wattage;
        bookings = new ArrayList<>();
    }

    public int getRef(String reference){
        if(reference.equals("ic_aspirateur")){
            return R.drawable.ic_aspirateur;
        }

        else if(reference.equals("ic_climatiseur")){
            return R.drawable.ic_climatiseur;
        }

        else if(reference.equals("ic_machine_a_laver")){
            return R.drawable.ic_machine_a_laver;
        }
        else
            return R.drawable.ic_fer_a_repasser;
    }

}
