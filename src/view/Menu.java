package view;

import controller.Input;
import model.menuManagement.MainMenuState;
import model.menuManagement.IMenuState;

public class Menu {
    private IMenuState state;
    private Input inputUtil = new Input();

    public Menu() {
        state = new MainMenuState();
    }

    public void callMenu(){
        while(true) {
            Integer input;

            // Escrever o menu
            state.writeMenu();

            // Pedir para o menu pegar os inputs necessários para o funcionamento.
            state.getNeededData(inputUtil);

            // Muda o menu para o próximo
            IMenuState newState = state.changeMenu();
            if(newState != null) {
                setState(newState);
            }
        }
    }

    public void setState(IMenuState state) {
        this.state = state;
    }
}
