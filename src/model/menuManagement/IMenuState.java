package model.menuManagement;

import controller.Input;

public interface IMenuState {
    public void writeMenu();
    public void doMenuOperations(Input input);
    public IMenuState changeMenu();
}
