package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalQueryMenuState implements IMenuState{
    private Integer userOption;
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
            // 1. Pega o nome popular de cada animal cadastrado
            for(int i = 0; i < animalData.getAnimaisCadastrado().size(); i++) {
                // Forma uma string contendo o índice do animal+1 e o nome popular dele
                String animalString = String.format("[%d] - %s", i+1, animalData.getAnimaisCadastrado().get(i).getPopularName());
                System.out.println(animalString);
            }

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
    public void getNeededData(Input input){
        // Pular esse pedido caso não hajam animais cadastrados
        if(animalData.hasAnimal()) {userOption = input.getIntegerInput();}

        // Essa parte aqui eu vou abstrair
        boolean displayInfo = userOption != null && (userOption >= 1 && userOption <= animalData.getAnimaisCadastrado().size());
        if(displayInfo) {
            // Mostra as informações do animal
            animalData.displayAnimalInfo(userOption - 1);

            // Pergunta se o usuário quer continuar pegando animais
            System.out.println("Escolha uma opção: ");
            System.out.println("[1] Voltar ao menu anterior");
            System.out.println("[2] Relatar erro");
            System.out.print("|| ");
            userOption = input.getIntegerInput();

        }
    }

    @Override
    public IMenuState changeMenu(){
        return switch (userOption) {
            case 0 -> lastMenu;
            default -> null;
        };
    }

}
