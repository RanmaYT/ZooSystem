package model.menuManagement;

public class VisitorMainMenuState implements MenuState {
    @Override
    public void writeMenu(){
        System.out.println("BEM VINDO, VISITANTE!");
        System.out.println("Escolha uma opção");
        System.out.println("[1] Consultar animais");
        System.out.println("[0] Voltar");
        System.out.print("|| ");
    }

    @Override
    public MenuState changeMenu(int option) {
        switch (option) {
            case 1:
                System.out.println("MENU DE CONSULTA ANIMAIS");
                return null;
            case 0:
                System.out.println("VOLTANDO PARA O MENU ANTERIOR");
                return new MainMenuState();
            default:
                System.out.println("Escolha um valor válido");
                return null;
        }
    }
}
