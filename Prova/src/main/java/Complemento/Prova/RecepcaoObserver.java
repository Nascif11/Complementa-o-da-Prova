package Complemento.Prova;

public class RecepcaoObserver implements ObservadorAtendimento {
    private String ultimaNotificacao;

    @Override
    public void aoIniciarAtendimento(String nomeAnimal, String nomeTutor) {
        this.ultimaNotificacao = String.format(
                "[RECEPÇÃO] Atendimento iniciado: %s",
                nomeAnimal
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public void aoFinalizarAtendimento(String nomeAnimal, double valor) {
        this.ultimaNotificacao = String.format(
                "[RECEPÇÃO] Atendimento de %s finalizado. Valor total: R$ %.2f",
                nomeAnimal, valor
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public void aoCancelarAtendimento(String nomeAnimal, String motivo) {
        this.ultimaNotificacao = String.format(
                "[RECEPÇÃO] Atendimento de %s cancelado",
                nomeAnimal
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public String getObservadorId() {
        return "RECEPÇÃO";
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}