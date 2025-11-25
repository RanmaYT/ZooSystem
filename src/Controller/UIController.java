package Controller;

import Util.InputUtil;
import View.View;

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
                        // menuVisitante();
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

    public void menuAdministrador(){
        while(true) {
            try {
                view.mostrarMenuAdministrador();

                int opcao = inputUtil.getIntInput("---> ", false);

                switch (opcao) {
                    case 1:
                        // Consultar animal
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
}
