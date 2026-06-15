package Complemento.Prova;

import Complemento.Prova.ServicoVeterinario;

public class AtendimentoBase implements AtendimentoValor {
    private ServicoVeterinario servico;

    public AtendimentoBase(ServicoVeterinario servico) {
        this.servico = servico;
    }

    @Override
    public double getValor() {
        return servico.getValorBase();
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao();
    }

    public ServicoVeterinario getServico() {
        return servico;
    }
}