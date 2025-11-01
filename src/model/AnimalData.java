package model;

import java.util.ArrayList;
import controller.Input;

public class AnimalData extends ItemDataManager<Animal>{
    private static ArrayList<Animal> registedAnimals = new ArrayList<>();
    private static final String ITEM_NAME = "animal";
    private static final String FILE_NAME = "animaisCadastrados.txt";

    @Override
    public ArrayList<Animal> getItemList() {
        return registedAnimals;
    }

    @Override
    public boolean createItem(Input input){
        // Definir as variáveis que serão criadas
        String popularName = "";
        String cientificName = "";
        String habitat = "";
        String locationInZoo = "";

        // Coletar informações sobre o animais TODO: Podia fazer a mesma coisa de usar um map aqui igual no update
        while(popularName.isEmpty()) {
            System.out.print("Nome popular: ");
            popularName = input.getAlphaInput();
            popularName = popularName.trim();
        }

        while(cientificName.isEmpty()) {
            System.out.print("Nome científico: ");
            cientificName = input.getAlphaInput();
            cientificName = cientificName.trim();
        }

        while(habitat.isEmpty()) {
            System.out.print("Habitat: ");
            habitat = input.getAlphaInput();
            habitat = habitat.trim();
        }

        while(locationInZoo.isEmpty()) {
            System.out.print("Localização no zoológico: ");
            locationInZoo = input.getAlphaInput();
            locationInZoo = locationInZoo.trim();
        }

        // Criar o objeto do novo animal
        Animal animal = new Animal(popularName, cientificName, habitat, locationInZoo);

        if(registedAnimals.contains(animal)) {
            System.out.println("Não é possível cadastrar o mesmo animal duas vezes");
            return false;
        }

        // Registra o animal
        registerItem(animal);

        System.out.println("Animal cadastrado com sucesso\n");
        return true;
    }

    @Override
    public void updateItem(int itemIndex, Input input){
        // Pegar o animal que vai ser atualizado
        Animal animal = getItemFromList(itemIndex);
        System.out.println("Atualize os campos do animal:");

        // Delega o processo de atualização pro próprio objeto
        boolean success = animal.update(input);

        if(success) { System.out.println("Atualização foi um sucesso!"); }
        else { System.out.println("A atualização falhou, nenhum campo foi atualizado!"); }
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
        return ITEM_NAME;
    }

    @Override
    public String getFileName(){
        return FILE_NAME;
    }
}
