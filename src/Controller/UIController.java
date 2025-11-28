package Controller;

import DTOs.AnimalDTO;
import DTOs.RelatoDTO;
import Util.InputUtil;
import View.View;

import java.util.HashMap;
import java.util.Map;

public class UIController {
    private AnimalController animalController;
    private RelatoController relatoController;
    private InputUtil inputUtil;
    private View view;

    public UIController(AnimalController animalController, RelatoController relatoController, InputUtil inputUtil, View view){
        this.animalController = animalController;
        this.relatoController = relatoController;
        this.inputUtil = inputUtil;
        this.view = view;
    }

    public void menuPrincipal(){
        // Loop para repetir o menu
        while (true) {
            try {

                // Pede pra view mostrar o menu
                view.mostrarMenuPrincipal();

                // Pede pro inputUtil coletar o input
                int opcao = inputUtil.getIntInput("---> ", false);

                // Passa pro próximo menu de acordo com a entrada
                switch (opcao) {
                    case 1:
                        // Menu Visitante
                        menuVisitante();
                        break;
                    case 2:
                        // Menu Administração

                        // Admin vai fazer login (sistema bem simples)
                        String senha = inputUtil.getStringInput("Digite a senha do sistema de administração: ", false);
                        if(!senha.equals("Zoo1234!")) {
                            view.mostrarErro("Senha inválida, voltando ao menu!");
                            continue;
                        }

                        // Entra no menu caso dê tudo certo com o login
                        menuAdministrador();
                        break;
                    case 0:
                        // Sair
                        return;
                    default:
                        view.mostrarErro("Valor inválido!");
                        break;
                }
            } catch (Exception e) {
                // Mostra o erro
                view.mostrarErro(e.getMessage());

                // Talvez um limpar o console depois de um tempo?
            }
        }
    }

    public void menuVisitante(){
        while(true) {
            try{
                view.mostrarMenuVisitante();

                int opcao = inputUtil.getIntInput("---> ", false);

                switch (opcao) {
                    case 1:
                        menuConsultaAnimal();
                        break;
                    case 0:
                        return;
                    default:
                        view.mostrarErro("Insira um valor válido!");
                        break;
                }
            }
            catch (Exception e) {
                view.mostrarErro(e.getMessage());
            }
        }
    }

    public void menuAdministrador(){
        while(true) {
            try {
                view.mostrarMenuAdministrador();

                int opcao = inputUtil.getIntInput("---> ", false);

                switch (opcao) {
                    case 1:
                        menuConsultaAnimal();
                        break;
                    case 2:
                        // Cadastrar novo animal
                        view.mostrarMenuAdicaoAnimal();

                        // Pega as informações
                        String nomePopular = inputUtil.getAlphaInput("Nome popular (máximo 100 caracteres): ", true);
                        String nomeCientifico = inputUtil.getAlphaInput("Nome científico (máximo 100 caracteres): ", true);
                        String habitat = inputUtil.getAlphaInput("Habitat (máximo 100 caracteres): ", true);
                        String localNoZoo = inputUtil.getAlphaInput("Localização no Zoológico (máximo 100 caracteres): ", true);

                        // Passa as informações pro controller;
                        animalController.cadastrarNovoAnimal(nomePopular, nomeCientifico, habitat, localNoZoo);

                        // Confirma a criação
                        view.mostrarMensagemDeSucesso("O animal foi cadastrado com sucesso!");

                        break;
                    case 3:
                        // Atualizar animal

                        view.mostrarMenuAtualizacaoAnimal();

                        int idAtualizacao = menuColetaIndice(animalController.pegarNomeAnimaisCadastrados());

                        if(idAtualizacao == 0) {
                            continue;
                        }

                        // Pergunta quais campos vão ser atualizados
                        view.mostrarCamposAtualizaveisAnimal();

                        int campo = inputUtil.getIntInput("Digite os índice dos campo que você quer alterar: ", false);
                        String novoValor = inputUtil.getAlphaInput("Digite o novo valor: ", true);

                        animalController.atualizarAnimal(idAtualizacao, campo, novoValor);
                        view.mostrarMensagemDeSucesso("A atualização foi um sucesso!");

                        break;
                    case 4:
                        // Deletar animal
                        view.mostrarMenuExclusaoAnimal();

                        // Lista os animais e pega o índice do animal em relação a linha
                        int idDelecao = menuColetaIndice(animalController.pegarNomeAnimaisCadastrados());

                        if(idDelecao == 0) {
                            continue;
                        }

                        // Deleta aquele animal
                        animalController.deletarAnimal(idDelecao);

                        // Mensagem de confirmação
                        view.mostrarMensagemDeSucesso("Animal deletado com sucesso!");

                        break;
                    case 5:
                        // Ver relatos
                        menuConsultaRelatos();
                        break;
                    case 0:
                        // Voltar
                        return;
                    default:
                        view.mostrarErro("Valor inválido!");
                        break;
                }
            } catch (Exception e) {
                view.mostrarErro(e.getMessage());
            }
        }
    }

    // Não tá nomeado da melhor maneira, e talvez esteja ferindo o SRP
    public int menuColetaIndice(Map<Integer, String> map){
        while(true) {
            // Mostra os animais cadastrados e salva a relação id-linha
            Map<Integer, Integer> mapIdLinha = listarItens(map);

            // Pega o input do usuário
            int linhaEscolhida = inputUtil.getIntInput("---> ", true);

            if(linhaEscolhida == 0) {
                return 0;
            }

            if (!mapIdLinha.containsKey(linhaEscolhida)) {
                view.mostrarErro("Esse não é um índice válido!");
                continue;
            }

            int idEscolhido = mapIdLinha.get(linhaEscolhida);

            return idEscolhido;
        }
    }

    public void menuConsultaAnimal() {
        while(true) {
            int idConsulta = menuColetaIndice(animalController.pegarNomeAnimaisCadastrados());

            if(idConsulta == 0) {
                return;
            }

            // Pega o DTO do animal
            AnimalDTO animalDTO = animalController.getAnimalInfo(idConsulta);

            // Mostra a informação do animal
            view.mostrarAnimalInfo(animalDTO);

            // Perguntar se o usuário quer fazer relato
            view.mostrarMensagem("Escolha uma opção: \n" +
                    "[1] Continuar buscando\n" +
                    "[2] Relatar erro\n" +
                    "[0] Voltar ao menu principal\n" +
                    "===============================");

            int opcaoCriarRelato = inputUtil.getIntInput("--> ", false);

            switch (opcaoCriarRelato) {
                case 1:
                    continue;
                case 2:
                    // Pega as informações sobre o relato
                    String tituloRelato = inputUtil.getAlphaInput("Dê um título para o relato(até 30 caracteres): ", true);
                    String textoRelato = inputUtil.getAlphaInput("Escreva o relato:\n", true);

                    // Cria o relato
                    relatoController.criarRelato(tituloRelato, textoRelato, idConsulta);
                    break;
                case 0:
                    return;
                default:
                    view.mostrarErro("Valor inválido, voltando ao menu de consulta!");
            }
        }
    }

    public void menuConsultaRelatos(){
        while(true) {
            int idConsulta = menuColetaIndice(relatoController.pegarTitulosRelatosCadastrados());

            if(idConsulta == 0) {
                return;
            }

            RelatoDTO relatoDTO = relatoController.pegarRelato(idConsulta);

            view.mostrarRelatoInfo(relatoDTO);

            // Perguntar se ele gostaria de deletar esse relato
            view.mostrarMensagem("Escolha uma opção: \n" +
                    "[1] Continuar lendo relatos\n" +
                    "[2] Deletar relato\n" +
                    "[0] Voltar ao menu principal");

            int opcaoCriarRelato = inputUtil.getIntInput("--> ", false);

            switch (opcaoCriarRelato) {
                case 1:
                    continue;
                case 2:
                    relatoController.deletarRelato(idConsulta);
                    view.mostrarMensagemDeSucesso("Relato deletado com sucesso!");
                    return;
                case 0:
                    return;
                default:
                    view.mostrarErro("Valor inválido, voltando ao menu de consulta!");
            }
        }
    }

    public Map<Integer, Integer> listarItens(Map<Integer, String> itensCadastrados){
        Map<Integer, Integer> mapIdLinha = new HashMap<>();

        int contador = 1;
        for(Map.Entry<Integer, String> entry : itensCadastrados.entrySet()) {
            mapIdLinha.put(contador, entry.getKey());
            contador++;
        }

        view.mostrarItensCadastrados(itensCadastrados);

        return mapIdLinha;
    }
}
