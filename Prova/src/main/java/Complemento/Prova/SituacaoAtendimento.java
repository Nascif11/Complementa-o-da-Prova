package Complemento.Prova;

import Complemento.Prova.Atendimento;


public interface SituacaoAtendimento {


    boolean iniciar(Atendimento atendimento);


    boolean finalizar(Atendimento atendimento);


    boolean cancelar(Atendimento atendimento, String motivo);


    String getNomeSituacao();
}