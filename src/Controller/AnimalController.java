package Controller;

import Model.Services.AnimalService;

public class AnimalController {
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void cadastrarNovoAnimal(String nome, String nomeCientifico, String habitat, String localNoZoo){
        animalService.cadastrarAnimal(nome, nomeCientifico, habitat, localNoZoo);
    }
}
