package service;

import model.IArchivable;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private static Database instance;

    private Database() {}

    public static Database getInstance(){
        if(instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void delete(String fileName, IArchivable itemToDelete) {

    }

    public ArrayList<IArchivable> load(String fileName){
        ArrayList<IArchivable> archivableItens = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Pegar cada linha do arquivo
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                
            }
        }
        catch(IOException e) {
            System.out.println("Erro ao ler o arquivo");
        }

        return archivableItens;
    }

    public void save(String fileName, IArchivable objectToSave){
        String textToSave = objectToSave.returnArchivableText();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            // Escreve o texto no arquivo
            writer.write(textToSave);
            writer.newLine();

            writer.close();

            System.out.println("Item salvo no arquivo");
        }
        catch (IOException e) {
            System.out.println("erro ao escrever no arquivo");
        }
    }
}
