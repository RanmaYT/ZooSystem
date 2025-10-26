package model;

import controller.Input;

import java.util.ArrayList;

public class ReportData implements ItemData<Report>{
    private static ArrayList<Report> registedReports = new ArrayList<>();
    private static final String itemName = "relatório";

    @Override
    public void loadItens() {

    }

    @Override
    public void listAllItens() {

    }

    @Override
    public void createItem(Input input) {

    }

    @Override
    public void registerItem(Report item) {

    }

    @Override
    public void deleteItem(int itemIndex) {

    }

    @Override
    public void displayItemInfo(int itemIndex) {

    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Override
    public ArrayList<Report> getItensList() {
        return null;
    }

    @Override
    public Report getItemFromList(int itemIndex) {
        return null;
    }

    @Override
    public String getItemName() {
        return itemName;
    }
}
