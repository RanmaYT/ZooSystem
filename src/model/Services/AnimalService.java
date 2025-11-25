package Model.Services;

import DTOs.AnimalDTO;
import Databases.AnimalDatabase;
import Mappers.AnimalMapper;
import Model.Animal;

import java.util.Map;

public class AnimalService {
    private AnimalDatabase animalDatabase;

    public AnimalService(AnimalDatabase animalDatabase) {
        this.animalDatabase = animalDatabase;
    }

    public AnimalDTO getAnimalInfo(int idConsulta) {
        // Pegar o animal no banco
        Animal animal = animalDatabase.getEntidadeDoBanco(idConsulta);

        // Retornar o dto desse animal
        return AnimalMapper.converterParaDTO(animal);
    }

    public Map<Integer, String> pegarNomeAnimaisCadastrados(){
        return animalDatabase.getNomeItensCadastrados();
    }

    public void cadastrarAnimal(String nomePopular, String nomeCientifico, String habitat, String localNoZoo) {
        // Cria o objeto
        Animal animal = new Animal(nomePopular, nomeCientifico, habitat, localNoZoo);

        // Valida se o objeto já existe

        // Salva no BD
        animalDatabase.cadastrarNovaEntidade(animal);
    }

    public void deletarAnimal(int idAnimal) {
        animalDatabase.deletarDoBanco(idAnimal);
    }
}
