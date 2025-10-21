package model.menuManagement;

public interface MenuState {
    public void writeMenu();
    public MenuState changeMenu(int option);
}
