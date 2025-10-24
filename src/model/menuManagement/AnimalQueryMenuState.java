package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalQueryMenuState implements IMenuState{
    private Integer userOption;
    private Integer animalIndex;
    private IMenuState lastMenu;
    private AnimalData animalData = new AnimalData();

    public AnimalQueryMenuState(IMenuState lastMenu){
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu(){
        // TODO: CORRIGIR BUG QUE AO VOLTAR DO MENU RELATO ELE FICA REPETINDO ESSE MENU


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
            userOption = 0;
        }
    }

    @Override
    public void doMenuOperations(Input input){
        // Pular esse pedido caso não hajam animais cadastrados
        if(animalData.hasAnimal()) {
            animalIndex = input.getIntegerInput();
            // TODO: CORRIGIR ESSE BUG AQUI OU MELHORAR A ABORDAGEM
            if(animalIndex == 0) {
                userOption = 0;
                return;
            }

            animalIndex--;

            // Mostra as informações do animal
            boolean displayedInfos = animalData.displayAnimalInfo(animalIndex);

            // Verifica se as informações são veridícas
            if(displayedInfos) {
                // Escreve um menu perguntando se o usuário quer voltar ou relatar um erro.
                System.out.println("Escolha uma opção: ");
                System.out.println("[1] Continuar buscando animais");
                System.out.println("[2] Relatar erro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print("|| ");
                userOption = input.getIntegerInput();
            } else {
                System.out.println("Como informações não foram mostradas, reiniciando o menu");
                userOption = 1;
            }
        }

    }

    @Override
    public IMenuState changeMenu(){
        if(userOption == null) { return null;}

        return switch (userOption) {
            case 0 -> lastMenu;
            case 1 -> new AnimalQueryMenuState(lastMenu);
            case 2 -> new ReportMenuState(animalData.getAnimalFromList(animalIndex), this);
            default -> {
                System.out.println("Valor inválido, voltando ao menu de busca!");
                yield null;
            }
        };
    }
}
