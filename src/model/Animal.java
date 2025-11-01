package model;

import controller.Input;
import view.TextColor;

import java.util.LinkedHashMap;
import java.util.Map;


public class Animal implements IArchivable {
    private String popularName;
    private String cientificName;
    private String habitat;
    private String locationInZoo;

    public Animal(String popularName, String cientificName, String habitat, String locationInZoo) {
        this.popularName = popularName;
        this.cientificName = cientificName;
        this.habitat = habitat;
        this.locationInZoo = locationInZoo;
    }

    public boolean update(Input input){
        boolean atLeastOneUpdate = false;

        // Map dos campos e seu índice
        Map<Integer, String> fields = new LinkedHashMap<>();

        fields.put(1, TextColor.BLACK_BOLD + "Nome popular");
        fields.put(2, TextColor.BLACK_BOLD + "Nome científico");
        fields.put(3, TextColor.BLACK_BOLD + "Habitat");
        fields.put(4, TextColor.BLACK_BOLD + "Localização no zoológico");

        // Pede pro usuário escolher os campos que ele quer atualizar
        for(Map.Entry<Integer, String> entry : fields.entrySet()) {
            // Escreve todos os campos disponíveis
            System.out.printf(TextColor.BLUE_BOLD + "[%d] %s%n", entry.getKey(), entry.getValue());
        }

        System.out.println(TextColor.BLUE_BOLD + "Digite os valores que você quer alterar separados por uma vírgula");
        System.out.print(TextColor.BLACK_BOLD + "---> ");
        String[] fieldsToChange = input.getStringInput().split(",");

        // Atualiza os campos pedidos
        for(String fieldIndex : fieldsToChange) {
            fieldIndex = fieldIndex.trim();
            try {
                int fieldInteger = Integer.parseInt(fieldIndex);
                String currentField = fields.get(fieldInteger);

                if(currentField == null) {
                    System.out.println(TextColor.RED_BOLD + "Opção inválida: " + fieldIndex);
                    continue;
                }

                // Pede o novo valor para do campo para o usuário
                String newValue = "";

                while(newValue.isBlank()) {
                    System.out.print(TextColor.BLACK_BOLD + "Novo valor para " + currentField.toLowerCase() + ": ");
                    newValue = input.getAlphaInput();
                }

                atLeastOneUpdate = true;

                // Atualiza o valor do campo atual
                switch (currentField) {
                    case "Nome popular": {
                        popularName = newValue;
                        break;
                    }
                    case "Nome científico": {
                        cientificName = newValue;
                        break;
                    }
                    case "Habitat": {
                        habitat = newValue;
                        break;
                    }
                    case "Localização no zoológico": {
                        locationInZoo = newValue;
                        break;
                    }


                }

            } catch(NumberFormatException e) {
                System.out.println(TextColor.RED_BOLD + "Entrada inválida para atualização: " + fieldIndex);
            }
        }

        return atLeastOneUpdate;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getLocationInZoo() {
        return locationInZoo;
    }

    public void setLocationInZoo(String locationInZoo) {
        this.locationInZoo = locationInZoo;
    }

    public String getPopularName(){
        return popularName;
    }

    public void setPopularName(String popularName){
        this.popularName = popularName;
    }

    public String getCientificName(){
        return cientificName;
    }

    public void setCientificName(String cientificName){
        this.cientificName = cientificName;
    }

    @Override
    public void loadItem(){

    }

    @Override
    public String returnArchivableText() {
        return String.format(TextColor.BLACK_BOLD + "'popularName': %s, 'cientificName': %s, 'habitat': %s, 'locationInZoo': %s", popularName, cientificName, habitat, locationInZoo);
    }

    @Override
    public String toString(){
        return String.format(TextColor.BLACK_BOLD + "Nome popular: %s\nNome científico: %s\nHabitat: %s\nLocalização do zoológico: %s", popularName, cientificName, habitat, locationInZoo);
    }
}
