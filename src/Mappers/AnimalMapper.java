package Mappers;

import DTOs.AnimalDTO;
import Model.Animal;

public class AnimalMapper {
    public static AnimalDTO converterParaDTO(Animal animal){
        return new AnimalDTO(animal);
    }
}
