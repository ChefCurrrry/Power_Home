package iut.dam.power_home;

import java.util.ArrayList;
import java.util.List;

public class Habitat {
    private String proprio;
    private int etage;
    private List<Integer> equipements;

    public Habitat(String nom, List<Integer> equipement, int etage){
        this.proprio = nom;
        this.etage = etage;
        this.equipements = equipement;
    }

    public String getProprio() {
        return proprio;
    }

    public int getEtage() {
        return etage;
    }

    public List<Integer> getEquipements() {
        return equipements;
    }
}
