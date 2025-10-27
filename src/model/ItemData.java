package model;

import controller.Input;
import java.util.ArrayList;

public interface ItemData<T> {
    public void loadItens();
    public void listAllItens();
    public void registerItem(T item);
    public void updateItem(int itemIndex, Input input);
    public void deleteItem(int itemIndex);
    public void displayItemInfo(int itemIndex);
    public boolean createItem(Input input);
    public boolean hasItem();
    public ArrayList<T> getItensList();
    public T getItemFromList(int itemIndex) throws IndexOutOfBoundsException;
    public String getItemName();
}
