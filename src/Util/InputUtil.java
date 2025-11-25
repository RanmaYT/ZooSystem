package Util;

import Exceptions.InputInvalidoException;
import View.TextColor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    private Scanner sc = new Scanner(System.in);

    public String getAlphaInput(String campoEntrada, boolean repetir){
        do {
            boolean hasOnlyLetters = false;

            // Pegar o input
            System.out.print(campoEntrada);
            String input = sc.nextLine();

            // Valida se é composto apenas por letras
            hasOnlyLetters = input.matches("[a-zA-Zá-úÁ-ÚÇç ]+");

            if(!hasOnlyLetters) {
                System.out.println(TextColor.RED_BOLD + "Esse campo aceita apenas letras" + TextColor.ANSI_RESET);
                continue;
            }

            // Retornar o input
            return input;
        } while(repetir);

        // Joga um erro
        throw new InputInvalidoException("Input alfabético inválido!");
    }

    public int getIntInput(String campoEntrada, boolean repetir){
        do {
            try {
                // Pegar o input
                System.out.print(campoEntrada);
                int input = sc.nextInt();

                // Limpar o scanner
                sc.nextLine();

                // Retornar o input
                return input;
            }
            catch (InputMismatchException e) {
                // Limpar o input, em caso de erro
                sc.nextLine();

            }
        } while(repetir);

        // Joga um erro
        throw new InputInvalidoException("Input inteiro inválido!");
    }
}
