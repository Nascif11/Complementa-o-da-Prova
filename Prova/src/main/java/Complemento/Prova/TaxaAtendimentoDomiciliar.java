package Complemento.Prova;

public class TaxaAtendimentoDomiciliar extends AtendimentoDecorator {
    private static final double TAXA = 50.0;
    public TaxaAtendimentoDomiciliar(AtendimentoValor componente) {
        super(componente);
    }

    @Override
    public double getValor() {
        return componente.getValor() + TAXA;
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + Taxa Atendimento Domiciliar (+R$ " + String.format("%.2f", TAXA) + ")";
    }
}

