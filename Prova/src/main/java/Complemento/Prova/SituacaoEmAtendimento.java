package Complemento.Prova;

import Complemento.Prova.Atendimento;

public class SituacaoEmAtendimento implements SituacaoAtendimento {

    private static SituacaoEmAtendimento instance;

    private SituacaoEmAtendimento() {}

    public static SituacaoEmAtendimento getInstance() {
        if (instance == null) {
            instance = new SituacaoEmAtendimento();
        }
        return instance;
    }

    @Override
    public boolean iniciar(Atendimento atendimento) {
        System.out.println(" O atendimento já foi iniciado");
        return false;
    }

    @Override
    public boolean finalizar(Atendimento atendimento) {
        atendimento.setSituacao(SituacaoFinalizado.getInstance());
        atendimento.notificarObservadores_Finalizado();
        return true;
    }

    @Override
    public boolean cancelar(Atendimento atendimento, String motivo) {
        System.out.println(" Não é possível cancelar um atendimento em andamento");
        return false;
    }

    @Override
    public String getNomeSituacao() {
        return "Em Atendimento";
    }
}
