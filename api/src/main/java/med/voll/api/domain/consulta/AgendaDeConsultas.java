package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoExecption;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired //vai implementar totdas as classes que tem essa interface implementada
    private List<ValidadorAgendamentoDeConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        //regras de negocio:

        //esse método da JPA verifica se existe esse ID no banco
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoExecption("Id do paciente informado não existe");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoExecption("id do médico informado não existe");
        }

        //estou percorrendo todos os validadores
        validadores.forEach(v -> v.validar(dados));


        //vou no repository e pego os objetos pelo id, esse metodo ele retorna um optional, então eu uso o get() para pegar o objeto
        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if(medico==null){
            throw new ValidacaoExecption("nenhum médico disponivel no dia");
        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public DadosCancelamentoConsulta cancelarConsulta(DadosCancelamentoConsulta dados){
        var consulta = consultaRepository.getReferenceById(dados.id());
        var horarioConsulta = consultaRepository.findDataById(dados.id());
        if (!consultaRepository.existsById(dados.id())){
            throw new ValidacaoExecption("id da consulta invalido");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        //retorno:
        consultaRepository.delete(consulta);
        consulta.cancelar(dados.motivo());

        return new DadosCancelamentoConsulta(dados.id(), dados.motivo());
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados){
        if (dados.idMedico() != null){
                return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoExecption("Especialidade é obrigatoria quando médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }

}
