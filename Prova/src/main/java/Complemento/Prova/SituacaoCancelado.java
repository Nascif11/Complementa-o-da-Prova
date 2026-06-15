package Complemento.Prova;

import Complemento.Prova.Atendimento;


public class SituacaoCancelado implements SituacaoAtendimento {

    private static SituacaoCancelado instance;

    private SituacaoCancelado() {}

    public static SituacaoCancelado getInstance() {
        if (instance == null) {
            instance = new SituacaoCancelado();
        }
        return instance;
    }

    @Override
    public boolean iniciar(Atendimento atendimento) {
        System.out.println(" Não é possível iniciar um atendimento cancelado");
        return false;
    }

    @Override
    public boolean finalizar(Atendimento atendimento) {
        System.out.println(" Não é possível finalizar um atendimento cancelado");
        return false;
    }

    @Override
    public boolean cancelar(Atendimento atendimento, String motivo) {
        System.out.println(" O atendimento já foi cancelado");
        return false;
    }

    @Override
    public String getNomeSituacao() {
        return "Cancelado";
    }
}
