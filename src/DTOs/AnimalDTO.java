package DTOs;

import Model.Animal;

public class AnimalDTO {
    private String nomePopular;
    private String nomeCientifico;
    private String habitat;
    private String localNoZoo;

    public AnimalDTO(Animal animal) {
        this.nomePopular = animal.getNomePopular();
        this.nomeCientifico = animal.getNomeCientifico();
        this.habitat = animal.getHabitat();
        this.localNoZoo = animal.getLocalNoZoo();
    }

    @Override
    public String toString(){
        return String.format("Nome popular: %s\n" +
                "Nome científico: %s\n" +
                "Habitat: %s\n" +
                "Localização no Zoológico: %s\n",
                nomePopular, nomeCientifico, habitat, localNoZoo);
    }
}
