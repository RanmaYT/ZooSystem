package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalUpdateMenuState implements IMenuState {
    private AnimalData animalData = new AnimalData();
    private Integer userOption;
    private Integer nextMenuValue;
    private Integer animalIndex;

    @Override
    public void writeMenu() {
        // Verificar se há animais cadastrados
        if(animalData.hasItem()) {
            System.out.println("QUAL ANIMAL VOCÊ QUER ATUALIZAR:");

            // Listar todos os animais e seus índices
            animalData.listAllItens();

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
    public void doMenuOperations(Input input) {
        // Pula esse bloco caso não haja animais;
        if(animalData.hasItem()) {
            userOption = input.getIntegerInput();

            // Atualiza o animal se o usuário tiver escolhido um input válido
            if(userOption > 0 && userOption <= animalData.getItensList().size()) {
                animalIndex = userOption - 1;
            }
        }
    }

    @Override
    public IMenuState changeMenu() {
        return switch (nextMenuValue) {
            case 0 -> new AdminMainMenuState();
            case 1 -> null;
            default -> {
                System.out.println("Valor inválido, reiniciando o menu!");

                // Resetar os campos
                userOption = null;
                animalIndex = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
