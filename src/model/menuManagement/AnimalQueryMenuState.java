package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalQueryMenuState implements IMenuState{
    private Integer nextMenuValue;
    private Integer userOption;
    private Integer animalIndex;
    private IMenuState lastMenu;
    private AnimalData animalData = new AnimalData();

    public AnimalQueryMenuState(IMenuState lastMenu){
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu(){
        // Verificar se há animais cadastrados
        if(animalData.hasAnimal()) {
            System.out.println("ESCOLHA UM ANIMAL:");

            // Listar todos os animais e seus índices
            animalData.listAllAnimals();

            // Opção para voltar
            System.out.println("[0] - Voltar");
            System.out.print("|| ");
        }
        else {
            System.out.println("Não há animais cadastrados, voltando ao menu anterior");
            nextMenuValue = 0;
        }
    }

    @Override
    public void doMenuOperations(Input input){
        // Pular esse pedido caso não hajam animais cadastrados
        if(animalData.hasAnimal()) {
            userOption = input.getIntegerInput();

            // Mostra a informação do animal se o usuário tiver escolhido um input válido
            if(userOption > 0 && userOption <= animalData.getRegistedAnimals().size()) {
                animalIndex = userOption - 1;

                // Mostra as informações do animal daquele índice e retorna se a operação foi um sucesso
                animalData.displayAnimalInfo(animalIndex);

                // Escreve um menu perguntando se o usuário quer voltar ou relatar um erro.
                System.out.println("Escolha uma opção: ");
                System.out.println("[1] Continuar buscando animais");
                System.out.println("[2] Relatar erro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print("|| ");
                userOption = input.getIntegerInput();

                nextMenuValue = userOption;
            }
            else { nextMenuValue = 0; }
        }
    }

    @Override
    public IMenuState changeMenu(){
        return switch (nextMenuValue) {
            case 0 -> lastMenu;
            case 1 -> new AnimalQueryMenuState(lastMenu);
            case 2 -> new ReportMenuState(animalData.getAnimalFromList(animalIndex), this);
            default -> {
                System.out.println("Valor inválido, reiniciando o menu!");

                userOption = null;
                animalIndex = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
