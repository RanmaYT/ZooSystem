package model;


public class Animal implements IArchivable {
    private String popularName;
    private String cientificName;
    private String habitat;
    private String locationInZoo;

    public Animal(String popularName, String cientificName, String habitat, String locationInZoo) {
        this.popularName = popularName;
        this.cientificName = cientificName;
        this.habitat = habitat;
        this.locationInZoo = locationInZoo;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getLocationInZoo() {
        return locationInZoo;
    }

    public void setLocationInZoo(String locationInZoo) {
        this.locationInZoo = locationInZoo;
    }

    public void setPopularName(String popularName){
        this.popularName = popularName;
    }

    public String getPopularName(){
        return popularName;
    }

    public void setCientificName(String cientificName){
        this.cientificName = cientificName;
    }

    public String getCientificName(){
        return cientificName;
    }

    @Override
    public String toString(){
        return String.format("Nome popular: %s\nNome científico: %s\nHabitat: %s\nLocalização do zoológico: %s", popularName, cientificName, habitat, locationInZoo);
    }
}
