package model;

public class Report implements IArchivable {
    private String reportText;
    private Animal reportedAnimal;

    public Report(String reportText, Animal reportedAnimal){
        this.reportText = reportText;
        this.reportedAnimal = reportedAnimal;
    }
}
