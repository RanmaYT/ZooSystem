package model;

import controller.Input;
import java.util.ArrayList;

public interface ItemData<T> {
    public void loadItens();
    public void listAllItens();
    public void createItem(Input input);
    public void registerItem(T item);
    public void deleteItem(int itemIndex);
    public void displayItemInfo(int itemIndex);
    public boolean hasItem();
    public ArrayList<T> getItensList();
    public T getItemFromList(int itemIndex);
    public String getItemName();
}
