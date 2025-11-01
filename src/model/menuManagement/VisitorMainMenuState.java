package model.menuManagement;

import controller.Input;
import model.AnimalData;
import model.ReportData;
import view.TextColor;

public class VisitorMainMenuState implements IMenuState {
    private Integer userOption;
    private Integer nextMenuValue;

    @Override
    public void writeMenu(){
        System.out.println(TextColor.BLACK_BOLD + "================= BEM VINDO, VISITANTE!! =================");
        System.out.println(TextColor.GREEN_BOLD + "                    Escolha uma opção:                      ");
        System.out.println("                  [1] Consultar animais");
        System.out.println("                  [2] Criar relato de erro");
        System.out.println("                  [0] <- Voltar");
        System.out.print(TextColor.BLACK_BOLD + "                 ---> ");
    }

    @Override
    public void doMenuOperations(Input input){
        userOption = input.getIntegerInput();
        nextMenuValue = userOption;
    }

    @Override
    public IMenuState changeMenu() {
        return switch (nextMenuValue) {
            case 0 -> new MainMenuState();
            case 1 -> new QueryItemMenuState(new AnimalData(), this);
            case 2 -> new CreateItemMenuState(new ReportData(), this);
            default -> {
                System.out.println(TextColor.RED_BOLD + "Escolha um valor válido, reiniciando o menu!");

                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
