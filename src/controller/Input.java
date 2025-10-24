package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private Scanner sc = new Scanner(System.in);

    public String getAlphaInput(){
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
                System.out.println("Esse campo só aceita valores inteiros");
                return -1;
            }
        }
    }
}
