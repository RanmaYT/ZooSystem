package model;

import view.TextColor;

public class Report  {
    private String reportName;
    private String reportText;
    private Animal reportedAnimal;

    public Report(String reportName, String reportText, Animal reportedAnimal){
        this.reportName = reportName;
        this.reportText = reportText;
        this.reportedAnimal = reportedAnimal;
    }

    public String getReportName(){
        return reportName;
    }


    @Override
    public String toString(){
        return String.format( TextColor.BLUE_BOLD + "Nome do relatório: %s\nAnimal relatado: %s\nTexto do relatório: %s\n", reportName, reportedAnimal.getPopularName(), reportText);
    }
}
