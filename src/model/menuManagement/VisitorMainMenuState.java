package model.menuManagement;

import controller.Input;
import model.AnimalData;
import model.ReportData;

public class VisitorMainMenuState implements IMenuState {
    private Integer userOption;
    private Integer nextMenuValue;

    @Override
    public void writeMenu(){
        System.out.println("================= BEM VINDO, VISITANTE!! =================");
        System.out.println("                    Escolha uma opção:                      ");
        System.out.println("                  [1] Consultar animais");
        System.out.println("                  [2] Criar relato de erro");
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
            case 0 -> new MainMenuState();
            case 1 -> new QueryItemMenuState(new AnimalData(), this);
            case 2 -> new CreateItemMenuState(new ReportData(), this);
            default -> {
                System.out.println("Escolha um valor válido, reiniciando o menu!");

                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
