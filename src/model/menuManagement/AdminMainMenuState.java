package model.menuManagement;

import controller.Input;

public class AdminMainMenuState implements IMenuState {
    private Integer userOption;

    @Override
    public void writeMenu() {
        System.out.println("BEM VINDO, ADMIN");
        System.out.println("Escolha uma opção");
        System.out.println("[1] Consultar animal");
        System.out.println("[2] Adicionar animal");
        System.out.println("[3] Atualizar animal");
        System.out.println("[4] Excluir animal");
        System.out.println("[0] Voltar");
        System.out.print("|| ");
    }

    @Override
    public void getNeededData(Input input){
        userOption = input.getIntegerInput();
    }

    @Override
    public IMenuState changeMenu() {
        switch (userOption) {
            case 1:
                return new AnimalQueryMenuState(this);
            case 2:
                System.out.println("Adicionar animal");
                break;
            case 3:
                System.out.println("Atualizar animal");
                break;
            case 4:
                System.out.println("Excluir animal");
                break;
            case 0:
                return new MainMenuState();
            default:
                System.out.println("Essa não é uma opção válida");
        }

        return null;
    }
}
