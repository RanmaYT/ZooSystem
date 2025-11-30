package Model;

public class Relato {
    private String tituloRelato;
    private String textoRelato;
    private int animalRelatadoId;

    public Relato(String tituloRelato, String textoRelato, int animalRelatadoId) {
        this.tituloRelato = tituloRelato;
        this.textoRelato = textoRelato;
        this.animalRelatadoId = animalRelatadoId;
    }

    // Getters & Setters
    public String getTituloRelato() {
        return tituloRelato;
    }

    public void setTituloRelato(String tituloRelato) {
        this.tituloRelato = tituloRelato;
    }

    public String getTextoRelato() {
        return textoRelato;
    }

    public void setTextoRelato(String textoRelato) {
        this.textoRelato = textoRelato;
    }

    public int getAnimalRelatadoId() {
        return animalRelatadoId;
    }

    public void setAnimalRelatadoId(int animalRelatadoId) {
        this.animalRelatadoId = animalRelatadoId;
    }
}
