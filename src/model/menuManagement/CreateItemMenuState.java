package model.menuManagement;

import controller.Input;
import model.ItemData;

public class CreateItemMenuState implements IMenuState{
    private IMenuState lastMenu;
    private ItemData itemData;
    private Integer userOption;
    private Integer nextMenuValue;

    public CreateItemMenuState(ItemData itemData, IMenuState lastMenu){
        this.itemData = itemData;
        this.lastMenu = lastMenu;
    }

    @Override
    public void writeMenu() {
        String menuTitle = String.format("==================== MENU DE CRIAÇÃO DE %s ====================", itemData.getItemName().toUpperCase());
        System.out.println(menuTitle);
        System.out.println(String.format("           [1] Adicionar %s                                    ", itemData.getItemName()));
        System.out.println("           [0] <- Voltar ao menu anterior                            ");
        System.out.print("           ---> ");
    }

    @Override
    public void doMenuOperations(Input input) {
        // Coletar o input do usuário
        userOption = input.getIntegerInput();

        // Repete o processo de criação enquanto o usuário digitar 1
        while(userOption == 1) {
            itemData.createItem(input);

            // Pergunta se o usuário quer continuar criando itens
            System.out.println(String.format("Deseja continuar adicionando %s: ", itemData.getItemName()));
            System.out.println("[1] Sim");
            System.out.println("[0] <- Voltar ao menu anterior");
            System.out.print("---> ");

            userOption = input.getIntegerInput();
        }

        nextMenuValue = userOption;
    }

    @Override
    public IMenuState changeMenu() {
        return switch(nextMenuValue) {
            case 0 -> lastMenu;
            default -> {
                System.out.println("Valor inválido, o menu será reiniciado");

                // Reseta as variáveis
                userOption = null;
                nextMenuValue = null;

                yield null;
            }
        };
    }
}
