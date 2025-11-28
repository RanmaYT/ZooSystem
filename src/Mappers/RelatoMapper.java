package Mappers;

import DTOs.RelatoDTO;
import Databases.AnimalDatabase;
import Model.Animal;
import Model.Relato;

public class RelatoMapper {
    public static RelatoDTO converterRelatoParaDTO(Relato relato) {
        AnimalDatabase animalDatabase = new AnimalDatabase();
        Animal animal = animalDatabase.getEntidadeDoBanco(relato.getAnimalRelatadoId());

        return new RelatoDTO(relato.getTituloRelato(), relato.getTextoRelato(), animal.getNomePopular());
    }
}
