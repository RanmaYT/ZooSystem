package model.menuManagement;

import controller.Input;
import model.ItemData;
import view.TextColor;

public class UpdateItemMenuState implements IMenuState {
    private IMenuState lastMenu;
    private ItemData itemData;
    private Integer userOption;
    private Integer nextMenuValue;

    public UpdateItemMenuState(ItemData itemData, IMenuState lastMenu){
        this.itemData = itemData;
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu() {
        // O menu escrito vai depender se temos itens cadastrados ou não
        if(itemData.hasItem()) {
            // Escreve o menu normal
            System.out.println(String.format(TextColor.BLACK_BOLD + "=============== Escolha um %s para atualizar =================", itemData.getItemName()));

            // Lista todos os itens cadastrados
            itemData.listAllItens();

            // Opção pra voltar
            System.out.println("[0] <- Voltar");
            System.out.print( TextColor.BLACK_BOLD + "---> ");

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

            // Verifica se o input é o de um item
            if(userOption > 0 && userOption <= itemData.getItemList().size()) {
                int itemIndex = userOption - 1;

                // Atualiza as informações do usuário
                itemData.updateItem(itemIndex, input);

                // Perguntar se o usuário quer continuar buscando animais ou não
                System.out.println(TextColor.BLACK_BOLD + "Você deseja continuar atualizando itens: ");
                System.out.println(TextColor.BLUE_BOLD + "[1] Sim");
                System.out.println("[0] <- Voltar ao menu anterior");
                System.out.print(TextColor.BLACK_BOLD + "---> ");
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
                System.out.println(TextColor.RED_BOLD + "Valor inválido, reiniciando o menu!");

                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
