package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalDeleteMenuState implements IMenuState{
    // TODO: QUANDO TIVER APENAS UM ANIMAL CADASTRADO E ELE FOR DELETADO FAZER UM CASO PERSONALIZADO.

    private AnimalData animalData = new AnimalData();
    private Integer userOption;
    private Integer animalIndex;

    @Override
    public void writeMenu() {
        // Verificar se há animais cadastrados
        if(animalData.hasAnimal()) {
            System.out.println("QUAL ANIMAL VOCÊ QUER EXCLUIR:");
            System.out.println("Obs: essa ação é irreversível");

            // Listar todos os animais e seus índices
            animalData.listAllAnimals();

            // Opção para voltar
            System.out.println("[0] - Voltar");
            System.out.print("|| ");
        }
        else {
            System.out.println("Não há animais cadastrados, voltando ao menu anterior");
            userOption = 0;
        }
    }

    @Override
    public void doMenuOperations(Input input) {
        if(animalData.hasAnimal()) {
            animalIndex = input.getIntegerInput();
            animalIndex--;

            boolean deleted = animalData.deleteAnimal(animalIndex);
            if(deleted) {userOption = 1;}
            else { userOption = 0;}
        }
    }

    @Override
    public IMenuState changeMenu() {
        // Essa função contém gambiarras para testes, TODO: Consertar as gambiarras
        if(userOption == 0) { return new AdminMainMenuState(); }
        else if(userOption == 1) { return null; }
        else {
            System.out.println("Valor inválido, reiniciando o menu!");
            return null;
        }
    }
}
