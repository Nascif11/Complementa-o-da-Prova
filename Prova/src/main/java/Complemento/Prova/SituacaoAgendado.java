package Complemento.Prova;

import Complemento.Prova.Atendimento;

public class SituacaoAgendado implements SituacaoAtendimento {

    private static SituacaoAgendado instance;

    private SituacaoAgendado() {}

    public static SituacaoAgendado getInstance() {
        if (instance == null) {
            instance = new SituacaoAgendado();
        }
        return instance;
    }

    @Override
    public boolean iniciar(Atendimento atendimento) {
        atendimento.setSituacao(SituacaoEmAtendimento.getInstance());
        atendimento.notificarObservadores_Iniciado();
        return true;
    }

    @Override
    public boolean finalizar(Atendimento atendimento) {
        System.out.println("Não é possível finalizar um atendimento agendado");
        return false;
    }

    @Override
    public boolean cancelar(Atendimento atendimento, String motivo) {
        atendimento.setSituacao(SituacaoCancelado.getInstance());
        atendimento.notificarObservadores_Cancelado(motivo);
        return true;
    }

    @Override
    public String getNomeSituacao() {
        return "Agendado";
    }
}