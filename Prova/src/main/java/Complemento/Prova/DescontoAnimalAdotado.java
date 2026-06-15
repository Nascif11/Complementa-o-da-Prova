package Complemento.Prova;

public class DescontoAnimalAdotado extends AtendimentoDecorator {
    private static final double DESCONTO = 20.0;


    public DescontoAnimalAdotado(AtendimentoValor componente) {
        super(componente);
    }

    @Override
    public double getValor() {
        double valorComDesconto = componente.getValor() - DESCONTO;
        return Math.max(valorComDesconto, 0);
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + Desconto Animal Adotado (-R$ " + String.format("%.2f", DESCONTO) + ")";
    }
}