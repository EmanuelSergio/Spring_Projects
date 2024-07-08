package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoExecption;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados){

        //regras de negocio:

        //esse método da JPA verifica se existe esse ID no banco
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoExecption("Id do paciente informado não existe");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoExecption("id do médico informado não existe");
        }




        //vou no repository e pego os objetos pelo id, esse metodo ele retorna um optional, então eu uso o get() para pegar o objeto
        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
    }

    public void cancelarConsulta(DadosCancelamentoConsulta dados){
        var consulta = consultaRepository.getReferenceById(dados.id());
        var horarioConsulta = consultaRepository.findDataById(dados.id());
        
        // Data e horário atual
        LocalDateTime currentDateTime = LocalDateTime.now();

        //horas antes de consulta
        Duration antecedencia = Duration.between(currentDateTime, horarioConsulta.get(0));

        if (antecedencia.toHours() < 24){
            throw new ValidacaoExecption("antecedencia minima de 24 horas para cancelar a consulta");
        }

        //retorno:
        consultaRepository.delete(consulta);

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
