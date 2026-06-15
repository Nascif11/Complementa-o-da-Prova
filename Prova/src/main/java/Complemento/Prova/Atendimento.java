package Complemento.Prova;
import Complemento.Prova.Animal;
import Complemento.Prova.ServicoVeterinario;
import Complemento.Prova.Tutor;
import Complemento.Prova.SituacaoAgendado;
import Complemento.Prova.SituacaoAtendimento;
import Complemento.Prova.ObservadorAtendimento;
import Complemento.Prova.AtendimentoValor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Atendimento {
    private String id;
    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servico;
    private AtendimentoValor atendimentoValor;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataAtendimento;

    private SituacaoAtendimento situacao;
    private List<ObservadorAtendimento> observadores;

    public Atendimento(String id, Tutor tutor, Animal animal,
                       ServicoVeterinario servico, LocalDateTime dataAgendamento) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do atendimento não pode ser vazio");
        }

        this.id = id;
        this.tutor = tutor;
        this.animal = animal;
        this.servico = servico;
        this.dataAgendamento = dataAgendamento;
        this.situacao = SituacaoAgendado.getInstance();
        this.observadores = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public ServicoVeterinario getServico() {
        return servico;
    }

    public String getSituacao() {
        return situacao.getNomeSituacao();
    }

    public AtendimentoValor getAtendimentoValor() {
        return atendimentoValor;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setSituacao(SituacaoAtendimento novaSituacao) {
        this.situacao = novaSituacao;
    }

    public void setAtendimentoValor(AtendimentoValor atendimentoValor) {
        this.atendimentoValor = atendimentoValor;
    }

    // ===== OBSERVER PATTERN =====

    public void adicionarObservador(ObservadorAtendimento observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void removerObservador(ObservadorAtendimento observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores_Iniciado() {
        for (ObservadorAtendimento obs : observadores) {
            obs.aoIniciarAtendimento(animal.getNome(), tutor.getNome());
        }
    }

    public void notificarObservadores_Finalizado() {
        double valorFinal = atendimentoValor != null ? atendimentoValor.getValor() : 0;
        for (ObservadorAtendimento obs : observadores) {
            obs.aoFinalizarAtendimento(animal.getNome(), valorFinal);
        }
    }


    public void notificarObservadores_Cancelado(String motivo) {
        for (ObservadorAtendimento obs : observadores) {
            obs.aoCancelarAtendimento(animal.getNome(), motivo);
        }
    }


    public boolean iniciar() {
        this.dataAtendimento = LocalDateTime.now();
        return situacao.iniciar(this);
    }

    public boolean finalizar() {
        return situacao.finalizar(this);
    }

    public boolean cancelar(String motivo) {
        return situacao.cancelar(this, motivo);
    }


    public double getValorFinal() {
        if (atendimentoValor == null) {
            return 0;
        }
        return atendimentoValor.getValor();
    }

    public String getDescricaoValor() {
        if (atendimentoValor == null) {
            return "Sem valor definido";
        }
        return atendimentoValor.getDescricao();
    }


    @Override
    public String toString() {
        return String.format(
                "ID: %s | Animal: %s | Tutor: %s | Situação: %s | Valor: R$ %.2f",
                id, animal.getNome(), tutor.getNome(), getSituacao(), getValorFinal()
        );
    }

}