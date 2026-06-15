package Complemento.Prova;

public class TutorObserver implements ObservadorAtendimento {
    private String ultimaNotificacao;

    @Override
    public void aoIniciarAtendimento(String nomeAnimal, String nomeTutor) {
        this.ultimaNotificacao = String.format(
                "[TUTOR] Olá %s, seu animal %s está sendo atendido!",
                nomeTutor, nomeAnimal
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public void aoFinalizarAtendimento(String nomeAnimal, double valor) {
        this.ultimaNotificacao = String.format(
                "[TUTOR] O atendimento de %s foi finalizado. Valor: R$ %.2f",
                nomeAnimal, valor
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public void aoCancelarAtendimento(String nomeAnimal, String motivo) {
        this.ultimaNotificacao = String.format(
                "[TUTOR] O atendimento de %s foi cancelado. Motivo: %s",
                nomeAnimal, motivo
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public String getObservadorId() {
        return "TUTOR";
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}