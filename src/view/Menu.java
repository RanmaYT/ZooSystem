package view;

import controller.Input;
import model.menuManagement.MainMenuState;
import model.menuManagement.IMenuState;

public class Menu {
    private float secondsBeforeCleaning = 1;
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

            // Pega os dados necessários e realizar alguma operação com eles se necessário
            state.doMenuOperations(inputUtil);

            // Muda o menu para o próximo, caso retorne null repete o mesmo menu.
            IMenuState newState = state.changeMenu();
            if(newState != null) {
                setState(newState);
            }

            // Limpa o menu
            cleanMenu();
        }
    }

    public void setState(IMenuState state) {
        this.state = state;
    }

    public void cleanMenu(){
        try{
            Thread.sleep((long)secondsBeforeCleaning * 1000);
            for(int i = 0; i < 10; i++) { System.out.println("\n"); }
        } catch(InterruptedException e) {
            e.printStackTrace();;
        }
    }
}
