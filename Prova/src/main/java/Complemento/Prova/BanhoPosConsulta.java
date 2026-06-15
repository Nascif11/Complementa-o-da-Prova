package Complemento.Prova;

public class BanhoPosConsulta extends AtendimentoDecorator {
    private static final double VALOR_BANHO = 40.0;
    public BanhoPosConsulta(AtendimentoValor componente) {
        super(componente);
    }

    @Override
    public double getValor() {
        return componente.getValor() + VALOR_BANHO;
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + Banho Pós-Consulta (+R$ " + String.format("%.2f", VALOR_BANHO) + ")";
    }
}