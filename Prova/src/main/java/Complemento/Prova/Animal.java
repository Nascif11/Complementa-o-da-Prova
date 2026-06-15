package Complemento.Prova;

public class Animal {
    private String id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private boolean adotado;

    public Animal(String id, String nome, String especie, String raca, int idade, boolean adotado) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do animal não pode ser vazio");
        }
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("Espécie não pode ser vazia");
        }

        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.adotado = adotado;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public boolean isAdotado() {
        return adotado;
    }

    public void setAdotado(boolean adotado) {
        this.adotado = adotado;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s %s - Adotado: %s",
                nome, id, raca, especie, adotado);
    }
}