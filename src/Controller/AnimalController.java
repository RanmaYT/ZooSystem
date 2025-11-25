package Controller;

import DTOs.AnimalDTO;
import Model.Services.AnimalService;

import java.util.Map;


public class AnimalController {
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void cadastrarNovoAnimal(String nome, String nomeCientifico, String habitat, String localNoZoo){
        animalService.cadastrarAnimal(nome, nomeCientifico, habitat, localNoZoo);
    }

    public void deletarAnimal(int idDelecao){
        animalService.deletarAnimal(idDelecao);
    }

    public AnimalDTO getAnimalInfo(int idConsulta) {
        return animalService.getAnimalInfo(idConsulta);
    }

    public Map<Integer, String> pegarNomeAnimaisCadastrados(){
        return animalService.pegarNomeAnimaisCadastrados();
    }
}
