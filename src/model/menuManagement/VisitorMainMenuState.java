package model.menuManagement;

import controller.Input;

public class VisitorMainMenuState implements IMenuState {
    private Integer userOption;
    private Integer nextMenuValue;

    @Override
    public void writeMenu(){
        System.out.println("================= BEM VINDO, VISITANTE!! =================");
        System.out.println("                    Escolha uma opção:                      ");
        System.out.println("                  [1] Consultar animais");
        System.out.println("                  [0] Voltar");
        System.out.print("                 ---> ");
    }

    @Override
    public void doMenuOperations(Input input){
        userOption = input.getIntegerInput();
        nextMenuValue = userOption;
    }

    @Override
    public IMenuState changeMenu() {
        return switch (nextMenuValue) {
            case 1 -> new AnimalQueryMenuState(this);
            case 0 -> new MainMenuState();
            default -> {
                System.out.println("Escolha um valor válido, reiniciando o menu!");

                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
