package model;

import java.util.ArrayList;
import controller.Input;

public class AnimalData {
    private static ArrayList<Animal> registedAnimals = new ArrayList<>();

    public void loadRegistedAnimals(){
        // TODO: Carregar os animais registrados quando iniciar a aplicação
    }

    public ArrayList<Animal> getRegistedAnimals() {
        return registedAnimals;
    }

    public Animal getAnimalFromList(int index) {
        try { return registedAnimals.get(index); }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Índice invalido");
            return null;
        }
    }

    public boolean hasAnimal(){
        return !registedAnimals.isEmpty();
    }

    public void registerAnimal(Animal animal){
        // Registra o animal na lista
        registedAnimals.add(animal);

        // Registra o animal no banco de dados
    }

    public void listAllAnimals(){
        for(int i = 0; i < registedAnimals.size(); i++) {
            // Forma uma string contendo o índice do animal+1 e o nome popular dele
            String animalString = String.format("[%d] - %s", i+1, registedAnimals.get(i).getPopularName());
            System.out.println(animalString);
        }
    }

    public boolean displayAnimalInfo(int animalIndex) {
        // Pegar o animal naquele índice
        Animal animal = getAnimalFromList(animalIndex);
        if(animal == null) {
            System.out.println("Falha ao mostrar informações!");
            return false;
        }

        System.out.println("Mostrando informações sobre: " + animal.getPopularName());
        System.out.println(animal);
        return true;
    }

    public void createAnimal(Input input){
        // Definir as variáveis que serão criadas
        String popularName = "";
        String cientificName = "";
        String habitat = "";
        String locationInZoo = "";

        // Coletar informações sobre o animais
        System.out.print("Nome popular: ");
        popularName = popularName.isBlank() ? input.getAlphaInput() : popularName;

        System.out.print("Nome científico: ");
        cientificName = cientificName.isBlank() ? input.getAlphaInput() : cientificName;

        System.out.print("Habitat: ");
        habitat = habitat.isBlank() ? input.getAlphaInput() : habitat;

        System.out.print("Localização no zoológico: ");
        locationInZoo = locationInZoo.isBlank() ? input.getAlphaInput() : locationInZoo;

        // Criar o novo animal
        Animal animal = new Animal(popularName, cientificName, habitat, locationInZoo);

        // Registra o animal
        registerAnimal(animal);

        System.out.println("Animal cadastrado com sucesso");
    }

    public boolean deleteAnimal(Animal animal) {
        if(animal == null) {
            System.out.println("Falha ao deletar animal");
            return false;
        }

        // Deletar o animal da lista
        registedAnimals.remove(animal);

        // Deletar animal do banco de dados

        System.out.println(animal.getPopularName() + " foi deletado com sucesso");
        return true;
    }

    public boolean deleteAnimal(int index) {
        Animal animal = getAnimalFromList(index);

        return deleteAnimal(animal);
    }
}
