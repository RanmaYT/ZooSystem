package controller;

import view.TextColor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private Scanner sc = new Scanner(System.in);

    public String getAlphaInput(){
        // Obs: essa função só coleta inputs formados apenas por letras e espaços
        boolean hasOnlyLetters = false;
        String input = "";

        // Pegar o input
        input = sc.nextLine();

        // Valida se é composto apenas por letras
        hasOnlyLetters = input.matches("[a-zA-Zá-ú ]+");
        if(!hasOnlyLetters) {
            System.out.println(TextColor.RED_BOLD + "Esse campo aceita apenas letras");
            return "";
        }
        
        return input;
    }

    public String getStringInput(){
        String input = "";

        input = sc.nextLine();

        return input;
    }

    public Integer getIntegerInput(){
        while(true) {
            try {
                int input;
                // Pegar o input
                input = sc.nextInt();

                // Limpar o input
                sc.nextLine();

                return input;
            } catch(InputMismatchException e) {
                // Limpa o input em caso de erro, e printa uma mensagem
                sc.nextLine();
                System.out.println(TextColor.RED_BOLD + "Esse campo só aceita valores inteiros");

                // Retorna um valor inválido para os contextos
                return -1;
            }
        }
    }
}
