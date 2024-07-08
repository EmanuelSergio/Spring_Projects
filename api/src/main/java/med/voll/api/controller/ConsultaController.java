package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody DadosAgendamentoConsulta dados){
        agenda.agendar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(dados.idMedico(), null, null, null));
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody DadosCancelamentoConsulta dados){
        agenda.cancelarConsulta(dados);

        return ResponseEntity.noContent().build();
    }



}
