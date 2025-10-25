package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalDeleteMenuState implements IMenuState{
    private AnimalData animalData = new AnimalData();
    private Integer userOption;
    private Integer animalIndex;
    private Integer nextMenuValue;

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
            nextMenuValue = 0;
        }
    }

    @Override
    public void doMenuOperations(Input input) {
        // Pula esse bloco caso não haja animais;
        if(animalData.hasAnimal()) {
            userOption = input.getIntegerInput();

            // Deleta o animal se o usuário tiver escolhido um input válido
            if(userOption > 0 && userOption <= animalData.getRegistedAnimals().size()) {
                animalIndex = userOption - 1;

                deleteAnimal(animalIndex);
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

    public void deleteAnimal(int animalIndex) {
        animalData.deleteAnimal(animalIndex);

        // Perguntar se ele quer continuar deletando animais, caso haja algum
        if(animalData.hasAnimal()) {
            System.out.println("Deseja continuar deletando animais: ");
            System.out.println("[1] Sim");
            System.out.println("[0] Voltar ao menu principal");

            Input input = new Input();
            userOption = input.getIntegerInput();

            nextMenuValue = userOption;
        }
        else {
            System.out.println("Como o último animal foi deletado, voltando ao menu principal");
            nextMenuValue = 0;
        }
    }
}
