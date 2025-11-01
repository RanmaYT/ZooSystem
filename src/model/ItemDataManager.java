package model;

import controller.Input;
import service.Database;
import view.TextColor;

import java.util.ArrayList;

public abstract class ItemDataManager<T> implements ItemData<T>{

    @Override
    public void deleteItem(int itemIndex){
        // Pega o item que vai deletado
        T item = getItemFromList(itemIndex);

        if(item == null) {
            System.out.println( TextColor.RED_BOLD + "Falha ao deletar o item: item é nulo");
            return;
        }

        // Apaga ele da lista
        getItemList().remove(item);

        // Apaga ele do banco de dados


        System.out.println( TextColor.GREEN_BOLD + "Deletado com sucesso!");
    }

    @Override
    public void registerItem(T item){
        ArrayList<T> itemList = getItemList();

        // Registrar na lista
        itemList.add(item);

        // Registrar no banco de dados
        //Database.getInstance().save(getFileName(), (IArchivable) item);
    }

    @Override
    public boolean hasItem(){
        return !getItemList().isEmpty();
    }

    @Override
    public T getItemFromList(int itemIndex) {
        try { return getItemList().get(itemIndex); }
        catch(IndexOutOfBoundsException e) {
            System.out.printf( TextColor.RED_BOLD + "Não foi possível acessar o %s, valor inválido\n", getItemName());
            return null;
        }
    }

    @Override
    public void loadItens(){

    }

    @Override
    public void updateItem(int itemIndex, Input input) {

    }

    // Métodos obrigatórios de serem implementados
    public abstract void listAllItens();
    public abstract void displayItemInfo(int itemIndex);
    public abstract boolean createItem(Input input);
    public abstract ArrayList<T> getItemList();
    public abstract String getFileName();
    public abstract String getItemName();
}
