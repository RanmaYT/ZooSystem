package Model.Services;

import Model.Animal;

public class AnimalService {
    public void cadastrarAnimal(String nomePopular, String nomeCientifico, String habitat, String localNoZoo) {
        // Cria o objeto
        Animal animal = new Animal(nomePopular, nomeCientifico, habitat, localNoZoo);

        System.out.println(animal.getNomePopular() + " foi cadastrado com sucesso!");
        // Valida se o objeto já existe

        // Salva no BD
    }
}
