package model;

import controller.Input;
import view.TextColor;

import java.util.ArrayList;

public class ReportData extends ItemDataManager<Report>{
    private static ArrayList<Report> registedReports = new ArrayList<>();
    private static final String ITEM_NAME = "relatório";
    private static final String FILE_NAME = "relatoriosCadastrados.txt";

    private AnimalData animalData = new AnimalData();

    @Override
    public void listAllItens() {
        for(int i = 0; i < registedReports.size(); i++) {
            System.out.printf( TextColor.GREEN_BOLD + "[%d] %s%n", i+1, getItemFromList(i).getReportName());
        }
    }

    @Override
    public boolean createItem(Input input) {
        if(!animalData.hasItem()) {
            System.out.println( TextColor.RED_BOLD + "Não há animais cadastrados, impossível de fazer um relato");
            return false;
        }

        // Definir as variáveis que serão usadas
        String reportName = "";
        String reportText = "";
        Animal reportedAnimal = null;

        // Coletar as informações sobre o relatório
        while(reportedAnimal == null) {
            // Listar os animais
            System.out.println( TextColor.WHITE_BOLD + "Escolha o animal que será reportado: ");
            animalData.listAllItens();

            // Opção de sair
            System.out.println( TextColor.GREEN_BOLD + "[0] Sair");
            System.out.print("---> ");

            // Pedir o índice do animal que será reportado
            int animalIndex = input.getIntegerInput();
            if(animalIndex == 0) { return false; }
            animalIndex--;

            try { reportedAnimal = animalData.getItemFromList(animalIndex); }
            catch(IndexOutOfBoundsException e) {
                System.out.println( TextColor.RED_BOLD + "Essa não é uma opção válida!");
            }
        }

        while(reportName.isBlank()) {
            System.out.print( TextColor.BLUE_BOLD + "Escolha um nome para o relato: ");
            reportName = input.getAlphaInput();
        }

        while(reportText.isBlank()) {
            System.out.println( TextColor.WHITE_BOLD + "Escreva o relato: ");
            reportText = input.getStringInput();
        }

        // Cria o objeto do relatório
        Report newReport = new Report(reportName, reportText, reportedAnimal);

        // Registra o relatório
        registerItem(newReport);

        System.out.println( TextColor.GREEN_BOLD + "Relatório criado com sucesso!");
        return true;
    }

    @Override
    public void displayItemInfo(int itemIndex) {
        // Pega o relatório naquele índice
        Report report = getItemFromList(itemIndex);

        if(report == null) { System.out.println( TextColor.RED_BOLD + "Falha ao mostra informações"); }

        System.out.println( TextColor.GREEN_BOLD + "Mostrando informações sobre: " + report.getReportName());
        System.out.println(report);
        System.out.println("============================");

        // TODO: Perguntar se ele quer deletar o relatório
        Input input = new Input();
        System.out.println( TextColor.WHITE_BOLD + "Você deseja deletar esse relatório?");
        System.out.println( TextColor.GREEN_BOLD + "[1] Sim");
        System.out.println("[0] Manter relatório");
        System.out.print( TextColor.WHITE_BOLD + "---> ");
        int userOption = input.getIntegerInput();

        if(userOption == 1) {
            deleteItem(itemIndex);
        }
    }

    @Override
    public ArrayList<Report> getItemList() {
        return registedReports;
    }

    @Override
    public String getItemName() {
        return ITEM_NAME;
    }

    @Override
    public String getFileName(){
        return FILE_NAME;
    }
}
