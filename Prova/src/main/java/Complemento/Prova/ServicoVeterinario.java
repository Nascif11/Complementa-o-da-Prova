package Complemento.Prova;

public class ServicoVeterinario {
    private String id;
    private String descricao;
    private double valorBase;


    public ServicoVeterinario(String id, String descricao, double valorBase) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        }
        if (valorBase < 0) {
            throw new IllegalArgumentException("Valor base não pode ser negativo");
        }

        this.id = id;
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorBase() {
        return valorBase;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (R$ %.2f)", id, descricao, valorBase);
    }
}