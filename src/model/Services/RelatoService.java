package Model.Services;

import DTOs.RelatoDTO;
import Databases.RelatoDatabase;
import Mappers.RelatoMapper;
import Model.Relato;

import java.util.Map;

public class RelatoService {
    private RelatoDatabase relatoDatabase;

    public RelatoService(RelatoDatabase relatoDatabase) {
        this.relatoDatabase = relatoDatabase;
    }

    public void criarRelato(String tituloRelato, String textoRelato, int animalRelatadoId) {
        Relato relato = new Relato(tituloRelato, textoRelato, animalRelatadoId);

        relatoDatabase.cadastrarNovaEntidade(relato);
    }

    public Map<Integer, String> pegarNomeRelatosCadastrados(){
        return relatoDatabase.getNomeItensCadastrados();
    }

    public RelatoDTO pegarRelatoInfo(int relatoId){
        Relato relato = relatoDatabase.getEntidadeDoBanco(relatoId);

        return RelatoMapper.converterRelatoParaDTO(relato);
    }

    public void deletarRelato(int idRelato) {
        relatoDatabase.deletarDoBanco(idRelato);
    }
}
