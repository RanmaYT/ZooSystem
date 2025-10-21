package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner sc = new Scanner(System.in);

    public static String getAlphaInput(){
        // Obs: essa função só coleta inputs alfabéticos, sem virgulas e outras coisas
        boolean hasOnlyLetters = false;
        String input = "";

        while(!hasOnlyLetters) {
            // Pegar o input
            input = sc.nextLine();

            // Valida se é composto apenas por letras
            hasOnlyLetters = input.matches("[a-zA-Zá-ú ]+");
            if(!hasOnlyLetters) {
                System.out.println("Esse campo aceita apenas letras");
            }
        }
        
        return input;
    }

    public static Integer getIntegerInput(){
        while(true) {
            try {
                int input;
                // Pegar o input
                input = sc.nextInt();

                return input;
            } catch(InputMismatchException e) {
                // Limpa o input em caso de erro, e printa uma mensagem
                sc.next();
                System.out.println("Esse campo só aceita valores inteiros");
                return null;
            }
        }
    }

    /*
    public static int getIntegerInput(int minInclusive, int maxInclusive) {
        boolean validInput;
        int input;

        // O loop acontece uma 1° vez, e se repete enquanto o número não estiver entre o limite
        do {
            // Chama a função de pegar um input inteiro
            input = getIntegerInput();

            // Valida se o input está nos limites
            validInput = input >= minInclusive && input <= maxInclusive;
            if(!validInput) { System.out.println("Coloque um valor dentro do limite"); }
        }
        while(!validInput);

        return input;
    } */
}
