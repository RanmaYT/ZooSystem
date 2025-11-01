package model.menuManagement;

import controller.Input;
import model.Animal;
import view.TextColor;

public class ReportMenuState implements IMenuState{
    private Animal reportedAnimal;
    private IMenuState lastMenu;
    private Integer userOption;
    private Integer nextMenuValue;

    public ReportMenuState(Animal reportedAnimal, IMenuState lastMenu) {
        this.reportedAnimal = reportedAnimal;
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu() {
        System.out.println( TextColor.BLUE_BOLD + "VOCÊ ESTÁ FAZENDO UM RELATO SOBRE: " + reportedAnimal.getPopularName());
        System.out.println("Obs: Digitando 0 você retornará para o menu de busca");
    }

    @Override
    public void doMenuOperations(Input input) {
        userOption = input.getIntegerInput();
        nextMenuValue = userOption;
    }

    @Override
    public IMenuState changeMenu() {
        return switch(nextMenuValue) {
            case 0 -> lastMenu;
            default -> null;
        };
    }
}
