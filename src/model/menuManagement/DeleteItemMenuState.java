package model.menuManagement;

import controller.Input;
import model.ItemData;
import view.TextColor;

public class DeleteItemMenuState implements IMenuState{
    private ItemData itemData;
    private IMenuState lastMenu;
    private Integer userOption;
    private Integer nextMenuValue;

    public DeleteItemMenuState(ItemData itemData, IMenuState lastMenu) {
        this.itemData = itemData;
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu() {
        // O menu escrito vai depender se temos itens cadastrados ou não
        if(itemData.hasItem()) {
            // Escreve o menu normal
            System.out.println(String.format( TextColor.WHITE_BOLD + "=============== Escolha um %s para excluir =================", itemData.getItemName()));
            System.out.println(TextColor.RED_BOLD + "                (Obs: essa ação é irreversível)                 ");

            // Lista todos os itens cadastrados
            itemData.listAllItens();

            // Opção pra voltar
            System.out.println( TextColor.GREEN_BOLD + "[0] <- Voltar");
            System.out.print(TextColor.WHITE_BOLD + "---> ");

        }
        else{
            // Fala que não tem itens cadastrados e retorna pro menu anterior
            System.out.println(String.format(TextColor.RED_BOLD + "Não há %s cadastrados, voltando ao menu anterior", itemData.getItemName()));
            nextMenuValue = 0;
        }
    }

    @Override
    public void doMenuOperations(Input input) {
        // Pula esse bloco caso não hajam itens cadastrados
        if(itemData.hasItem()) {
            userOption = input.getIntegerInput();

            // Verifica se a opção é o índice de um item
            if(userOption >= 1 && userOption <= itemData.getItemList().size()) {
                int itemIndex = userOption - 1;

                deleteItem(itemIndex);
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
                System.out.println(TextColor.RED_BOLD + "Valor inválido, reiniciando o menu!");

                // Resetar os campos
                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }

    public void deleteItem(int itemIndex){
        itemData.deleteItem(itemIndex);

        // Perguntar se ele quer continuar deletando animais, caso haja algum
        if(itemData.hasItem()) {
            System.out.println( TextColor.WHITE_BOLD + "================= Continuar deletando? ================ ");
            System.out.println( TextColor.GREEN_BOLD + "                  [1] Sim                               ");
            System.out.println("                  [0] <- Voltar ao menu principal          ");
            System.out.print( TextColor.WHITE_BOLD + "                  ---> ");

            Input input = new Input();
            userOption = input.getIntegerInput();

            nextMenuValue = userOption;
        }
        else {
            System.out.println( TextColor.BLUE_BOLD + "Como o último item foi deletado, voltando ao menu principal");
            nextMenuValue = 0;
        }
    }
}
