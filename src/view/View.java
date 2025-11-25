package View;

import org.w3c.dom.Text;

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
        System.out.println("                 [0] <- Voltar                     ");
    }

    public void mostrarMensagem(String mensagem){
        System.out.println(mensagem);
    }

    public void mostrarMensagemDeSucesso(String mensagem){
        System.out.println(TextColor.BLUE_BOLD + mensagem);
    }

    public void mostrarErro(String erro){
        System.out.println(TextColor.RED_BOLD + erro);
    }
}
