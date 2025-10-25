package model.menuManagement;

import controller.Input;
import model.AnimalData;

public class AnimalAddMenuState implements IMenuState{
    private AnimalData animalData = new AnimalData();
    private Integer userOption;
    private Integer nextMenuValue;

    @Override
    public void writeMenu() {
        System.out.println("MENU DE ADICIONAR ANIMAIS");
        System.out.println("[1] Iniciar processo de adição de informações");
        System.out.println("[0] Voltar ao menu principal");
        System.out.print("|| ");
    }

    @Override
    public void doMenuOperations(Input input) {
        // Coletar um input, caso não haja nenhum
        userOption = input.getIntegerInput();
        nextMenuValue = userOption;

        // Inicia o processo de criação caso o usuário escolha essa opção e repete enquanto o usuário quiser
        while(userOption == 1) {
            animalData.createAnimal(input);

            // Pergunta se o usuário quer continuar adicionando animais
            System.out.println("Deseja continuar adicionando animais: ");
            System.out.println("[1] Sim");
            System.out.println("[0] Voltar ao menu principal");

            userOption = input.getIntegerInput();
        }

        // Atualiza o valor do próximo menu a ser chamado
        nextMenuValue = userOption;
    }

    @Override
    public IMenuState changeMenu() {
        return switch (nextMenuValue) {
            case 0 -> new AdminMainMenuState();
            default -> {
                // Reinicia o menu e as variáveis;
                System.out.println("Valor inválido, reiniciando o menu!");

                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
