package model.menuManagement;

import controller.Input;
import model.ItemData;
import model.ReportData;

public class QueryItemMenuState implements IMenuState{
    private Integer nextMenuValue;
    private Integer userOption;
    private Integer itemIndex;
    private IMenuState lastMenu;
    private ItemData itemData;

    public QueryItemMenuState(ItemData itemData, IMenuState lastMenu){
        this.itemData = itemData;
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu() {
        // O menu escrito vai depender se temos itens cadastrados ou não
        if(itemData.hasItem()) {
            // Escreve o menu normal
            System.out.println(String.format("=============== Escolha um %s para consultar =================", itemData.getItemName()));

            // Lista todos os itens cadastrados
            itemData.listAllItens();

            // Opção pra voltar
            System.out.println("[0] <- Voltar");
            System.out.print("---> ");

        }
        else{
            // Fala que não tem itens cadastrados e retorna pro menu anterior
            System.out.println(String.format("Não há %s cadastrados, voltando ao menu anterior", itemData.getItemName()));
            nextMenuValue = 0;
        }
    }

    @Override
    public void doMenuOperations(Input input) {
        // Pula esse bloco caso não hajam itens cadastrados
        if(itemData.hasItem()) {
            userOption = input.getIntegerInput();

            // Verifica se o input é o de um item
            if(userOption > 0 && userOption <= itemData.getItensList().size()) {
                itemIndex = userOption--;

                // Mostra as informações sobre o item
                itemData.displayItemInfo(itemIndex);

                // Perguntar se o usuário quer continuar buscando animais ou não
                System.out.println("Você deseja continuar buscando itens: ");
                System.out.println("[1] Sim");
                System.out.println("[0] Voltar ao menu anterior");
                userOption = input.getIntegerInput();
            }

            nextMenuValue = userOption;
        }
    }

    @Override
    public IMenuState changeMenu() {
        return switch (nextMenuValue) {
            case 0 -> lastMenu;
            case 1 -> null;
            default -> {
                System.out.println("Valor inválido, reiniciando o menu!");

                userOption = null;
                itemIndex = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
