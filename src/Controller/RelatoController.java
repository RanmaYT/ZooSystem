package Controller;

import DTOs.RelatoDTO;
import Model.Services.RelatoService;

import java.util.Map;

public class RelatoController {
    private RelatoService relatoService;

    public RelatoController(RelatoService relatoService) {
        this.relatoService = relatoService;
    }

    public void criarRelato(String tituloRelato, String textoRelato, int animalRelatadoId){
        if(tituloRelato.length() > 30) {
            throw new RuntimeException("O título do relato deve ter no máximo 30 caracteres");
        }

        relatoService.criarRelato(tituloRelato, textoRelato, animalRelatadoId);
    }

    public Map<Integer, String> pegarTitulosRelatosCadastrados(){
        return relatoService.pegarNomeRelatosCadastrados();
    }

    public RelatoDTO pegarRelato(int idRelato){
        return relatoService.pegarRelatoInfo(idRelato);
    }

    public void deletarRelato(int idRelato){
        relatoService.deletarRelato(idRelato);
    }
}
