package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoExecption;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelarConsulta implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta dados){
        // Data e horário atual

        LocalDateTime currentDateTime = LocalDateTime.now();

        var horarioConsulta = consultaRepository.findDataById(dados.id());
        //horas antes de consulta
        Duration antecedencia = Duration.between(currentDateTime, horarioConsulta.get(0));

        if (antecedencia.toHours() < 24){
            throw new ValidacaoExecption("antecedencia minima de 24 horas para cancelar a consulta");
        }

    }

}
