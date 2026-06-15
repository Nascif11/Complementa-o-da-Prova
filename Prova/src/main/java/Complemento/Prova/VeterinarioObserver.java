package Complemento.Prova;

public class VeterinarioObserver implements ObservadorAtendimento {
    private String ultimaNotificacao;

    @Override
    public void aoIniciarAtendimento(String nomeAnimal, String nomeTutor) {
        this.ultimaNotificacao = String.format(
                "[VETERINÁRIO] Novo atendimento iniciado: %s (Tutor: %s)",
                nomeAnimal, nomeTutor
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public void aoFinalizarAtendimento(String nomeAnimal, double valor) {
        this.ultimaNotificacao = String.format(
                "[VETERINÁRIO] Atendimento de %s finalizado com sucesso",
                nomeAnimal
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public void aoCancelarAtendimento(String nomeAnimal, String motivo) {
        this.ultimaNotificacao = String.format(
                "[VETERINÁRIO] ALERTA: Atendimento de %s foi cancelado. Motivo: %s",
                nomeAnimal, motivo
        );
        System.out.println(this.ultimaNotificacao);
    }

    @Override
    public String getObservadorId() {
        return "VETERINÁRIO";
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}