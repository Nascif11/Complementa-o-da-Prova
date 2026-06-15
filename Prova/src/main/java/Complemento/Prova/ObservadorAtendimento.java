package Complemento.Prova;

public interface ObservadorAtendimento {

    void aoIniciarAtendimento(String nomeAnimal, String nomeTutor);
    void aoFinalizarAtendimento(String nomeAnimal, double valor);
    void aoCancelarAtendimento(String nomeAnimal, String motivo);

    String getObservadorId();
}
