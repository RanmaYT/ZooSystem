package View;

import DTOs.AnimalDTO;
import DTOs.RelatoDTO;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View {
    public void mostrarMenuPrincipal(){
        System.out.println(TextColor.WHITE_BOLD + "================== BEM VINDO, ESCOLHA COMO ENTRAR: ==================");
        System.out.println(TextColor.GREEN_BOLD + "                     [1] Visitante");
        System.out.println("                     [2] Administrador (requer senha)");
        System.out.println("                     [0] <- Sair");
        System.out.print(TextColor.WHITE_BOLD + "                      ");
    }

    public void mostrarMenuVisitante(){
        System.out.println(TextColor.WHITE_BOLD + "================= BEM VINDO, VISITANTE!! =================");
        System.out.println(TextColor.GREEN_BOLD + "                    Escolha uma opção:                      ");
        System.out.println("                  [1] Consultar animais");
        System.out.println("                  [0] <- Voltar");
    }

    public void mostrarMenuAdministrador(){
        System.out.println(TextColor.WHITE_BOLD + "================= BEM VINDO, ADMIN!! =================");
        System.out.println(TextColor.GREEN_BOLD +  "                  Escolha uma opção:                ");
        System.out.println("                 [1] Consultar animal              ");
        System.out.println("                 [2] Cadastrar animal              ");
        System.out.println("                 [3] Atualizar animal              ");
        System.out.println("                 [4] Excluir animal                ");
        System.out.println("                 [5] Verificar relatos                ");
        System.out.println("                 [0] <- Voltar                     " + TextColor.ANSI_RESET);
    }

    public void mostrarMenuExclusaoAnimal(){
        System.out.println("=================== MENU EXCLUSÃO DE ANIMAIS =================== ");
        System.out.println(TextColor.RED_BOLD + "Obs: essa ação é irreversível!" + TextColor.ANSI_RESET);
    }

    public void mostrarItensCadastrados(Map<Integer, String> itensCadastrados){
        System.out.println("Escolha um dos índices: ");
        int contador = 1;

        for(Map.Entry<Integer, String> entry : itensCadastrados.entrySet()) {
            System.out.printf("[%d] - %s\n", contador, entry.getValue());
            contador++;
        }

        System.out.println("[0] - Voltar");
    }

    public void mostrarAnimalInfo(AnimalDTO animalDTO) {
        System.out.println("=========================");
        System.out.print(animalDTO);
        System.out.println("=========================");
    }

    public void mostrarRelatoInfo(RelatoDTO relatoDTO) {
        System.out.println("=========================");
        System.out.print(relatoDTO);
        System.out.println("=========================");
    }

    public void mostrarMensagem(String mensagem){
        System.out.println(mensagem);
    }

    public void mostrarMensagemDeSucesso(String mensagem){
        System.out.println(TextColor.BLUE_BOLD + mensagem + TextColor.ANSI_RESET);
    }

    public void mostrarErro(String erro){
        System.out.println(TextColor.RED_BOLD + erro + TextColor.ANSI_RESET);
    }
}
