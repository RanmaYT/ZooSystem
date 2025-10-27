package model.menuManagement;

import controller.Input;
import model.AnimalData;
import view.TextColor;

public class AdminMainMenuState implements IMenuState {
    private Integer userOption;
    private Integer nextMenuValue;

    @Override
    public void writeMenu() {
        System.out.println("================= BEM VINDO, ADMIN!! =================");
        System.out.println("                  Escolha uma opção:                ");
        System.out.println("                 [1] Consultar animal              ");
        System.out.println("                 [2] Adicionar animal              ");
        System.out.println("                 [3] Atualizar animal              ");
        System.out.println("                 [4] Excluir animal                ");
        System.out.println("                 [5] Verificar relatos                ");
        System.out.println("                 [0] <- Voltar                     ");
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
            case 1 -> new QueryItemMenuState(new AnimalData(),this);
            case 2 -> new CreateItemMenuState(new AnimalData(), this);
            case 3 -> new UpdateItemMenuState(new AnimalData(), this);
            case 4 -> new DeleteItemMenuState(new AnimalData(), this);
            case 0 -> new MainMenuState();
            default -> {
                System.out.println("Essa não é uma opção válida, reiniciando menu!");

                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
