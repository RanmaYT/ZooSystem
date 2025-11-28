package DTOs;

public class RelatoDTO {
    private String relatoTitulo;
    private String relatoTexto;
    private String nomeAnimalRelatado;

    public RelatoDTO(String relatoTitulo, String relatoTexto, String nomeAnimalRelatado){
        this.relatoTitulo = relatoTitulo;
        this.relatoTexto = relatoTexto;
        this.nomeAnimalRelatado = nomeAnimalRelatado;
    }

    @Override
    public String toString(){
        return String.format("Título: %s\n" +
                "Animal relatado: %s\n" +
                "Texto: %s\n",
                relatoTitulo, nomeAnimalRelatado, relatoTexto);
    }
}
