package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExecption;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorPacienteAtivo {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo){
            throw new ValidacaoExecption("Paciente deve estar ativo");
        }

    }

}
