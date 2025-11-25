package Controller;

import DTOs.AnimalDTO;
import Util.InputUtil;
import View.View;

import java.util.HashMap;
import java.util.Map;

public class UIController {
    private AnimalController animalController;
    private InputUtil inputUtil;
    private View view;

    public UIController(AnimalController animalController, InputUtil inputUtil, View view){
        this.animalController = animalController;
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
                int opcao = inputUtil.getIntInput("--->", false);

                // Passa pro próximo menu de acordo com a entrada
                switch (opcao) {
                    case 1:
                        // Menu Visitante
                        menuVisitante();
                        break;
                    case 2:
                        // Menu Administração

                        // Admin vai fazer login

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

                        // Pega as informações
                        String nomePopular = inputUtil.getAlphaInput("Nome popular: ", true);
                        String nomeCientifico = inputUtil.getAlphaInput("Nome científico: ", true);
                        String habitat = inputUtil.getAlphaInput("Habitat: ", true);
                        String localNoZoo = inputUtil.getAlphaInput("Localização no Zoológico: ", true);

                        // Passa as informações pro controller;
                        animalController.cadastrarNovoAnimal(nomePopular, nomeCientifico, habitat, localNoZoo);

                        // Confirma a criação
                        view.mostrarMensagemDeSucesso("O animal foi cadastrado com sucesso!");

                        break;
                    case 3:
                        // Atualizar animal
                        break;
                    case 4:
                        // Deletar animal
                        view.mostrarMenuExclusaoAnimal();

                        // Lista os animais e pega o índice do animal em relação a linha
                        int idDelecao = menuColetaIndiceAnimais();

                        if(idDelecao == 0) {
                            return;
                        }

                        // Deleta aquele animal
                        animalController.deletarAnimal(idDelecao);

                        // Mensagem de confirmação
                        view.mostrarMensagemDeSucesso("Animal deletado com sucesso!");

                        break;
                    case 5:
                        // Ver relatos
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
    public int menuColetaIndiceAnimais(){
        while(true) {
            // Mostra os animais cadastrados e salva a relação id-linha
            Map<Integer, Integer> map = listarItens(animalController.pegarNomeAnimaisCadastrados());

            // Pega o input do usuário
            int linhaEscolhida = inputUtil.getIntInput("---> ", true);

            if(linhaEscolhida == 0) {
                return 0;
            }

            if (!map.containsKey(linhaEscolhida)) {
                view.mostrarErro("Esse não é um índice válido!");
                continue;
            }

            int idEscolhido = map.get(linhaEscolhida);

            return idEscolhido;
        }
    }

    public void menuConsultaAnimal() {
        while(true) {
            int idConsulta = menuColetaIndiceAnimais();

            if(idConsulta == 0) {
                return;
            }

            // Pega o DTO do animal
            AnimalDTO animalDTO = animalController.getAnimalInfo(idConsulta);

            // Mostra a informação do animal
            view.mostrarAnimalInfo(animalDTO);
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
