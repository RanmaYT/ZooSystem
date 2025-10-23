package model.menuManagement;

import controller.Input;

public class VisitorMainMenuState implements IMenuState {
    private Integer userOption;

    @Override
    public void writeMenu(){
        System.out.println("BEM VINDO, VISITANTE!");
        System.out.println("Escolha uma opção");
        System.out.println("[1] Consultar animais");
        System.out.println("[0] Voltar");
        System.out.print("|| ");
    }

    @Override
    public void getNeededData(Input input){
        userOption = input.getIntegerInput();
    }

    @Override
    public IMenuState changeMenu() {
        return switch (userOption) {
            case 1 -> new AnimalQueryMenuState(this);
            case 0 -> {
                System.out.println("VOLTANDO PARA O MENU ANTERIOR");
                yield new MainMenuState();
            }
            default -> {
                System.out.println("Escolha um valor válido");
                yield null;
            }
        };
    }
}
