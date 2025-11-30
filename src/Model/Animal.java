package Model;

public class Animal {
    private String nomePopular;
    private String nomeCientifico;
    private String habitat;
    private String localNoZoo;

    public Animal(String nomePopular, String nomeCientifico, String habitat, String localNoZoo) {
        this.nomePopular = nomePopular;
        this.nomeCientifico = nomeCientifico;
        this.habitat = habitat;
        this.localNoZoo = localNoZoo;
    }

    // Getters & Setters

    public String getNomePopular(){
        return nomePopular;
    }

    public void setNomePopular(String nomePopular){
        this.nomePopular = nomePopular;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getLocalNoZoo() {
        return localNoZoo;
    }

    public void setLocalNoZoo(String localNoZoo) {
        this.localNoZoo = localNoZoo;
    }
}
