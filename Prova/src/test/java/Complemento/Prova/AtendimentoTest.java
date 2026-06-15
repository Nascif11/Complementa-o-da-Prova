package Complemento.Prova;
import Complemento.Prova.Animal;
import Complemento.Prova.ServicoVeterinario;
import Complemento.Prova.Tutor;
import Complemento.Prova.ObservadorAtendimento;
import Complemento.Prova.RecepcaoObserver;
import Complemento.Prova.TutorObserver;
import Complemento.Prova.VeterinarioObserver;
import Complemento.Prova.AtendimentoBase;
import Complemento.Prova.AtendimentoValor;
import Complemento.Prova.BanhoPosConsulta;
import Complemento.Prova.DescontoAnimalAdotado;
import Complemento.Prova.TaxaAtendimentoDomiciliar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class AtendimentoTest {

    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servico;
    private Atendimento atendimento;

    @BeforeEach
    void setUp() {
        tutor = new Tutor("T001", "João Silva", "(11) 98765-4321", "joao@email.com");
        animal = new Animal("A001", "Rex", "Cão", "Labrador", 5, false);
        servico = new ServicoVeterinario("S001", "Consulta Clínica", 100.0);
        atendimento = new Atendimento("AT001", tutor, animal, servico, LocalDateTime.now());
    }



    @Test
    void deveComecारcomEstadoAgendado() {
        assertEquals("Agendado", atendimento.getSituacao());
    }

    @Test
    void deveTransicionarDeAgendadoParaEmAtendimento() {
        assertTrue(atendimento.iniciar());
        assertEquals("Em Atendimento", atendimento.getSituacao());
    }

    @Test
    void deveTransicionarDeEmAtendimentoParaFinalizado() {
        atendimento.iniciar();
        assertTrue(atendimento.finalizar());
        assertEquals("Finalizado", atendimento.getSituacao());
    }

    @Test
    void deveCancelarDeAgendado() {
        assertTrue(atendimento.cancelar("Cliente solicitou"));
        assertEquals("Cancelado", atendimento.getSituacao());
    }

    @Test
    void naoDeveFinalizenarSendoAgendado() {
        assertFalse(atendimento.finalizar());
        assertEquals("Agendado", atendimento.getSituacao());
    }

    @Test
    void naoDeveCancelarSendoEmAtendimento() {
        atendimento.iniciar();
        assertFalse(atendimento.cancelar("Motivo qualquer"));
        assertEquals("Em Atendimento", atendimento.getSituacao());
    }

    @Test
    void naoDeveCancelarSendoFinalizado() {
        atendimento.iniciar();
        atendimento.finalizar();
        assertFalse(atendimento.cancelar("Motivo qualquer"));
        assertEquals("Finalizado", atendimento.getSituacao());
    }

    @Test
    void naoDeveIniciarSeJaEmAtendimento() {
        atendimento.iniciar();
        assertFalse(atendimento.iniciar());
        assertEquals("Em Atendimento", atendimento.getSituacao());
    }





    @Test
    void deveNotificarObservadoresAoIniciar() {
        TutorObserver tutorObserver = new TutorObserver();
        VeterinarioObserver vetObserver = new VeterinarioObserver();
        RecepcaoObserver recepObserver = new RecepcaoObserver();

        atendimento.adicionarObservador(tutorObserver);
        atendimento.adicionarObservador(vetObserver);
        atendimento.adicionarObservador(recepObserver);

        atendimento.iniciar();

        assertTrue(tutorObserver.getUltimaNotificacao().contains("Rex"));
        assertTrue(vetObserver.getUltimaNotificacao().contains("Rex"));
        assertTrue(recepObserver.getUltimaNotificacao().contains("Rex"));
    }

    @Test
    void deveNotificarObservadoresAoFinalizar() {
        TutorObserver tutorObserver = new TutorObserver();
        atendimento.adicionarObservador(tutorObserver);

        AtendimentoValor valor = new AtendimentoBase(servico);
        atendimento.setAtendimentoValor(valor);

        atendimento.iniciar();
        atendimento.finalizar();

        assertTrue(tutorObserver.getUltimaNotificacao().contains("finalizado"));
    }

    @Test
    void deveNotificarObservadoresAoCancelar() {
        VeterinarioObserver vetObserver = new VeterinarioObserver();
        atendimento.adicionarObservador(vetObserver);

        atendimento.cancelar("Paciente não compareceu");

        assertTrue(vetObserver.getUltimaNotificacao().contains("cancelado"));
    }


    @Test
    void deveCalcularValorBase() {
        AtendimentoValor valor = new AtendimentoBase(servico);
        atendimento.setAtendimentoValor(valor);

        assertEquals(100.0, atendimento.getValorFinal());
    }

    @Test
    void deveAplicarDescontoAnimalAdotado() {
        animal.setAdotado(true);

        AtendimentoValor valor = new AtendimentoBase(servico);
        valor = new DescontoAnimalAdotado(valor);
        atendimento.setAtendimentoValor(valor);

        assertEquals(80.0, atendimento.getValorFinal());
    }

    @Test
    void deveAplicarTaxaAtendimentoDomiciliar() {
        AtendimentoValor valor = new AtendimentoBase(servico);
        valor = new TaxaAtendimentoDomiciliar(valor);
        atendimento.setAtendimentoValor(valor);

        assertEquals(150.0, atendimento.getValorFinal());
    }

    @Test
    void deveAplicarBanhoPosConsulta() {
        AtendimentoValor valor = new AtendimentoBase(servico);
        valor = new BanhoPosConsulta(valor);
        atendimento.setAtendimentoValor(valor);

        assertEquals(140.0, atendimento.getValorFinal());
    }

    @Test
    void deveAplicarMultiplasMoficacoes() {
        animal.setAdotado(true);

        AtendimentoValor valor = new AtendimentoBase(servico);
        valor = new TaxaAtendimentoDomiciliar(valor);
        valor = new BanhoPosConsulta(valor);
        valor = new DescontoAnimalAdotado(valor);
        atendimento.setAtendimentoValor(valor);

        double esperado = 100.0 + 50.0 + 40.0 - 20.0;
        assertEquals(esperado, atendimento.getValorFinal());
    }

    @Test
    void deveValidarDescricaoComMultiplosDecorators() {
        AtendimentoValor valor = new AtendimentoBase(servico);
        valor = new TaxaAtendimentoDomiciliar(valor);
        valor = new BanhoPosConsulta(valor);
        atendimento.setAtendimentoValor(valor);

        String descricao = atendimento.getDescricaoValor();
        assertTrue(descricao.contains("Consulta Clínica"));
        assertTrue(descricao.contains("Taxa Atendimento Domiciliar"));
        assertTrue(descricao.contains("Banho Pós-Consulta"));
    }


    @Test
    void deveCriarTutorComValidacoes() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Tutor("T002", "", "123456", "email@test.com");
        });
    }

    @Test
    void deveCriarAnimalComValidacoes() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Animal("A002", "", "Cão", "Poodle", 3, false);
        });
    }

    @Test
    void deveCriarServicoComValidacoes() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ServicoVeterinario("S002", "", 50.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ServicoVeterinario("S003", "Vacinação", -10.0);
        });
    }


    @Test
    void deveFluxoCompletoDeAtendimento() {

        TutorObserver tutor = new TutorObserver();
        VeterinarioObserver vet = new VeterinarioObserver();
        RecepcaoObserver rec = new RecepcaoObserver();

        atendimento.adicionarObservador(tutor);
        atendimento.adicionarObservador(vet);
        atendimento.adicionarObservador(rec);


        AtendimentoValor valor = new AtendimentoBase(servico);
        valor = new BanhoPosConsulta(valor);
        atendimento.setAtendimentoValor(valor);


        assertTrue(atendimento.iniciar());
        assertEquals("Em Atendimento", atendimento.getSituacao());


        assertTrue(atendimento.finalizar());
        assertEquals("Finalizado", atendimento.getSituacao());
        assertEquals(140.0, atendimento.getValorFinal());


        assertTrue(tutor.getUltimaNotificacao().contains("finalizado"));
        assertTrue(rec.getUltimaNotificacao().contains("140"));
    }

    @Test
    void deveFluxoComCancelamento() {
        TutorObserver tutor = new TutorObserver();
        VeterinarioObserver vet = new VeterinarioObserver();

        atendimento.adicionarObservador(tutor);
        atendimento.adicionarObservador(vet);

        assertTrue(atendimento.cancelar("Paciente transferido"));
        assertEquals("Cancelado", atendimento.getSituacao());

        assertTrue(vet.getUltimaNotificacao().contains("cancelado"));
    }
}
