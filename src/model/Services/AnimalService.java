package Model.Services;

import DTOs.AnimalDTO;
import Databases.AnimalDatabase;
import Exceptions.AnimalInvalidoException;
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

    public void atualizarAnimal(int indiceAnimal, int indiceCampo, String novoValor){
        Animal animal = animalDatabase.getEntidadeDoBanco(indiceAnimal);

        switch (indiceCampo) {
            case 1:
                animal.setNomePopular(novoValor);
                break;
            case 2:
                animal.setNomeCientifico(novoValor);
                break;
            case 3:
                animal.setHabitat(novoValor);
                break;
            case 4:
                animal.setLocalNoZoo(novoValor);
                break;
        }

        validarAnimal(animal, true);

        animalDatabase.atualizarAnimal(indiceAnimal, animal);
    }

    public void cadastrarAnimal(String nomePopular, String nomeCientifico, String habitat, String localNoZoo) {
        // Cria o objeto
        Animal animal = new Animal(nomePopular, nomeCientifico, habitat, localNoZoo);

        // Valida o objeto
        validarAnimal(animal, false);

        // Salva no BD
        animalDatabase.cadastrarNovaEntidade(animal);
    }

    public void deletarAnimal(int idAnimal) {
        animalDatabase.deletarDoBanco(idAnimal);
    }

    public void validarAnimal(Animal animal, boolean pularVerificacaoNoBanco) throws AnimalInvalidoException{
        // Valida se os campos estão com o tamanho certo
        if(!(animal.getNomePopular().length() <= 100)) {
            throw new AnimalInvalidoException("Nome popular ultrapassou o limite de caracteres!");
        }

        if(!(animal.getNomeCientifico().length() <= 100)) {
            throw new AnimalInvalidoException("Nome científico ultrapassou o limite de caracteres!");
        }

        if(!(animal.getHabitat().length() <= 100)) {
            throw new AnimalInvalidoException("Habitat ultrapassou o limite de caracteres!");
        }

        if(!(animal.getLocalNoZoo().length() <= 100)) {
            throw new AnimalInvalidoException("A localização no zoológico ultrapassou o limite de caracteres!");
        }

        // Valida se o objeto já existe no banco
        if(animalDatabase.AnimalJaCadastrado(animal) && !pularVerificacaoNoBanco) {
            throw new AnimalInvalidoException("Um animal com esse nome popular ou nome científico já foi cadastrado, impossível cadastrar o mesmo!");
        }
    }
}
