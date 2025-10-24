package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalAddMenuState implements IMenuState{
    AnimalData animalData = new AnimalData();
    Integer userOption;

    @Override
    public void writeMenu() {
        System.out.println("MENU DE ADICIONAR ANIMAIS");
        System.out.println("[1] Iniciar processo de adição de informações");
        System.out.println("[0] Voltar ao menu principal");
        System.out.print("|| ");
    }

    @Override
    public void doMenuOperations(Input input) {
        userOption = input.getIntegerInput();

        if(userOption == 1) { animalData.createAnimal(input); }
        // Continuar sistema para permitir que o usuário continue no menu
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
