package model.menuManagement;

import controller.Input;

public interface IMenuState {
    public void writeMenu();
    public void getNeededData(Input input);
    public IMenuState changeMenu();
}
