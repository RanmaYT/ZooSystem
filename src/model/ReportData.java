package model;

import controller.Input;

import java.util.ArrayList;

public class ReportData implements ItemData<Report>{
    private static ArrayList<Report> registedReports = new ArrayList<>();
    private static final String ITEM_NAME = "relatório";
    private static final String FILE_NAME = "relatoriosCadastrados.txt";

    private AnimalData animalData = new AnimalData();

    @Override
    public void loadItens() {

    }

    @Override
    public void listAllItens() {
        for(int i = 0; i < registedReports.size(); i++) {
            System.out.printf("[%d] %s%n", i+1, getItemFromList(i).getReportName());
        }
    }

    @Override
    public boolean createItem(Input input) {
        if(!animalData.hasItem()) {
            System.out.println("Não há animais cadastrados");
            return false;
        }

        // Definir as variáveis que serão usadas
        String reportName = "";
        String reportText = "";
        Animal reportedAnimal = null;

        // Coletar as informações sobre o relatório
        while(reportedAnimal == null) {
            // Listar os animais
            System.out.println("Escolha o animal que será reportado: ");
            animalData.listAllItens();

            // Opção de sair
            System.out.println("[0] Sair");
            System.out.print("---> ");

            // Pedir o índice do animal que será reportado
            int animalIndex = input.getIntegerInput();
            if(animalIndex == 0) { return false; }
            animalIndex--;

            try { reportedAnimal = animalData.getItemFromList(animalIndex); }
            catch(IndexOutOfBoundsException e) {
                System.out.println("Essa não é uma opção válida!");
            }
        }

        while(reportName.isBlank()) {
            System.out.print("Escolha um nome para o relato: ");
            reportName = input.getAlphaInput();
        }

        while(reportText.isBlank()) {
            System.out.println("Escreva o relato: ");
            reportText = input.getStringInput();
        }

        // Cria o objeto do relatório
        Report newReport = new Report(reportName, reportText, reportedAnimal);

        // Registra o relatório
        registerItem(newReport);

        System.out.println("Relatório criado com sucesso!");
        return true;
    }

    @Override
    public void registerItem(Report item) {
        registedReports.add(item);
    }

    @Override
    public void updateItem(int itemIndex, Input input){

    }

    @Override
    public void deleteItem(int itemIndex) {

    }

    @Override
    public void displayItemInfo(int itemIndex) {
        // Pega o relatório naquele índice
        Report report = getItemFromList(itemIndex);

        if(report == null) {
            System.out.println("Falha ao mostra informações");
        }

        System.out.println("Mostrando informações sobre: " + report.getReportName());
        System.out.println(report);
        System.out.println("============================");
    }

    @Override
    public boolean hasItem() {
        return !registedReports.isEmpty();
    }

    @Override
    public ArrayList<Report> getItensList() {
        return registedReports;
    }

    @Override
    public Report getItemFromList(int itemIndex) throws IndexOutOfBoundsException {
        return registedReports.get(itemIndex);
    }

    @Override
    public String getItemName() {
        return ITEM_NAME;
    }
}
