package model.menuManagement;

import controller.Input;

public class MainMenuState implements IMenuState {
    private Integer userOption;

    @Override
    public void writeMenu(){
        System.out.println("ESCOLHA COMO VOCÊ QUER ENTRAR:");
        System.out.println("[1] Visitante");
        System.out.println("[2] Administrador (requer senha)");
        System.out.println("[0] Sair");
        System.out.print("|| ");
    }

    @Override
    public void getNeededData(Input input){
        userOption = input.getIntegerInput();
    }

    @Override
    public IMenuState changeMenu() {
        // Valida o input
        return switch (userOption) {
            case 1 ->
                // Visitante Main Menu
                new VisitorMainMenuState();
            case 2 ->
                // Admin Main Menu
                new AdminMainMenuState();
            case 0 -> {
                System.out.println("Saindo...");
                System.exit(0);
                yield null;
            }
            default -> {
                // Valores inválidos
                System.out.println("Digite um valor válido");
                yield null;
            }
        };
    }
}
