package model.menuManagement;

import controller.Input;
import model.Animal;

public class ReportMenuState implements IMenuState{
    Animal reportedAnimal;
    IMenuState lastMenu;
    Integer userOption;

    public ReportMenuState(Animal reportedAnimal, IMenuState lastMenu) {
        this.reportedAnimal = reportedAnimal;
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu() {
        System.out.println("VOCÊ ESTÁ FAZENDO UM RELATO SOBRE: " + reportedAnimal.getPopularName());
        System.out.println("Obs: Digitando 0 você retornará para o menu de busca");
    }

    @Override
    public void doMenuOperations(Input input) {
        userOption = input.getIntegerInput();
    }

    @Override
    public IMenuState changeMenu() {
        return switch(userOption) {
            case 0 -> lastMenu;
            default -> null;
        };
    }
}
