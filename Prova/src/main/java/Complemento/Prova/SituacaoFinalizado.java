package Complemento.Prova;

public class SituacaoFinalizado implements SituacaoAtendimento {

    private static SituacaoFinalizado instance;

    private SituacaoFinalizado() {}

    public static SituacaoFinalizado getInstance() {
        if (instance == null) {
            instance = new SituacaoFinalizado();
        }
        return instance;
    }

    @Override
    public boolean iniciar(Atendimento atendimento) {
        System.out.println(" Não é possível iniciar um atendimento já finalizado");
        return false;
    }

    @Override
    public boolean finalizar(Atendimento atendimento) {
        System.out.println("O atendimento já foi finalizado");
        return false;
    }

    @Override
    public boolean cancelar(Atendimento atendimento, String motivo) {
        System.out.println(" Não é possível cancelar um atendimento finalizado");
        return false;
    }

    @Override
    public String getNomeSituacao() {
        return "Finalizado";
    }
}