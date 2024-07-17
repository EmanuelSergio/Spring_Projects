package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoExecption;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {

    public void validar (DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaHoraAbertura = dataConsulta.getHour() < 7;
        var depoisDaHoraFechamento = dataConsulta.getHour() > 18;

        if (domingo || antesDaHoraAbertura || depoisDaHoraFechamento){
            throw new ValidacaoExecption("horário não permitido");
        }



    }

}
