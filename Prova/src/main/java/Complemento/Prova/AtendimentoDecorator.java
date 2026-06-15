package Complemento.Prova;

public abstract class AtendimentoDecorator implements AtendimentoValor {
    protected AtendimentoValor componente;
    public AtendimentoDecorator(AtendimentoValor componente) {
        this.componente = componente;
    }

    @Override
    public double getValor() {
        return componente.getValor();
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao();
    }
}
