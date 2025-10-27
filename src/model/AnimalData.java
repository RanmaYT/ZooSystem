package model;

import java.util.ArrayList;
import controller.Input;

public class AnimalData implements ItemData<Animal>{
    private static ArrayList<Animal> registedAnimals = new ArrayList<>();
    private static final String itemName = "animal";

    @Override
    public ArrayList<Animal> getItensList() {
        return registedAnimals;
    }

    @Override
    public void createItem(Input input){
        // Definir as variáveis que serão criadas
        String popularName = "";
        String cientificName = "";
        String habitat = "";
        String locationInZoo = "";

        // Coletar informações sobre o animais
        while(popularName.isBlank()) {
            System.out.print("Nome popular: ");
            popularName = input.getAlphaInput();
        }

        while(cientificName.isBlank()) {
            System.out.print("Nome científico: ");
            cientificName = input.getAlphaInput();
        }

        while(habitat.isBlank()) {
            System.out.print("Habitat: ");
            habitat = input.getAlphaInput();
        }

        while(locationInZoo.isBlank()) {
            System.out.print("Localização no zoológico: ");
            locationInZoo = input.getAlphaInput();
        }

        // Criar o objeto do novo animal
        Animal animal = new Animal(popularName, cientificName, habitat, locationInZoo);

        // Registra o animal
        registerItem(animal);

        System.out.println("Animal cadastrado com sucesso\n");
    }

    @Override
    public void loadItens(){
        // TODO: Carregar os animais registrados quando iniciar a aplicação
    }

    @Override
    public void registerItem(Animal animal){
        // Registra o animal na lista
        registedAnimals.add(animal);

        // Registra o animal no banco de dados
    }

    @Override
    public void updateItem(int itemIndex, Input input){
        // Pegar o animal que vai ser atualizado
        Animal animal = getItemFromList(itemIndex);
        System.out.println("Atualize os campos do animal:");
        System.out.println("Obs: campos que não serão atualizados coloque um 0");

        // Delega o processo de atualização pro próprio objeto
        animal.update(input);

        System.out.println("Atualização foi um sucesso!");
    }

    @Override
    public void deleteItem(int index) {
        Animal animal = getItemFromList(index);

        deleteAnimal(animal);
    }

    @Override
    public void displayItemInfo(int animalIndex) {
        // Pegar o animal naquele índice
        Animal animal = getItemFromList(animalIndex);

        if(animal == null) {
            System.out.println("Falha ao mostrar informações!");
        }

        System.out.println("Mostrando informações sobre: " + animal.getPopularName());
        System.out.println(animal);
        System.out.println("============================");
    }

    @Override
    public void listAllItens(){
        for(int i = 0; i < registedAnimals.size(); i++) {
            // Forma uma string contendo o índice do animal+1 e o nome popular dele
            String animalString = String.format("[%d] - %s", i+1, registedAnimals.get(i).getPopularName());
            System.out.println(animalString);
        }
    }

    @Override
    public String getItemName(){
        return itemName;
    }

    @Override
    public Animal getItemFromList(int index) {
        try { return registedAnimals.get(index); }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Índice invalido");
            return null;
        }
    }

    @Override
    public boolean hasItem(){
        return !registedAnimals.isEmpty();
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
}
