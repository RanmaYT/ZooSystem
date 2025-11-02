package model.menuManagement;

import controller.Input;
import view.TextColor;

public class MainMenuState implements IMenuState {
    private Integer userOption;
    private Integer nextMenuValue;

    @Override
    public void writeMenu(){
        System.out.println(TextColor.WHITE_BOLD + "================== BEM VINDO, ESCOLHA COMO ENTRAR: ==================");
        System.out.println( TextColor.GREEN_BOLD + "                     [1] Visitante");
        System.out.println("                     [2] Administrador (requer senha)");
        System.out.println("                     [0] <- Sair");
        System.out.print( TextColor.WHITE_BOLD + "                     ---> ");
    }

    @Override
    public void doMenuOperations(Input input){
        userOption = input.getIntegerInput();
        nextMenuValue = userOption;
    }

    @Override
    public IMenuState changeMenu() {
        // Valida o input
        return switch (nextMenuValue) {
            case 1 ->
                // Visitante Main Menu
                new VisitorMainMenuState();
            case 2 ->
                // Admin Main Menu
                new AdminMainMenuState();
            case 0 -> {
                // Fechando a aplicação
                System.out.println("Saindo...");
                System.exit(0);
                yield null;
            }
            default -> {
                // Valores inválidos
                System.out.println(TextColor.RED_BOLD + "Digite um valor válido");

                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
