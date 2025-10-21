package view;

import controller.Input;
import model.menuManagement.MainMenuState;
import model.menuManagement.MenuState;

public class Menu {
    private MenuState state;

    public Menu() {
        state = new MainMenuState();
    }

    public void callMenu(){
        while(true) {
            Integer input;

            // Escrever o menu
            state.writeMenu();

            // Pegar o input
            input = Input.getIntegerInput();
            if(input == null) { continue; }

            // Muda o menu para o próximo
            MenuState newState = state.changeMenu(input);
            if(newState != null) {
                setState(newState);
            }
        }
    }

    public void setState(MenuState state) {
        this.state = state;
    }
}
