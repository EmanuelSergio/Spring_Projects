package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

        @Autowired
        private MedicoRepository repository;

        @PostMapping
        @Transactional                     // preciso do @valid aqui para validar o que coloquei em cada objeto
        public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
            repository.save(new Medico(dados));
        }

        @GetMapping
        public Page<DadosListagemMedico> buscarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){ //preciso retornar um DTO, pois não quero retonar todos os atributos quando criado
                                                                                                        //e posso passar apenas o que desejar
                return repository
                        .findAll(paginacao)
                        .map(DadosListagemMedico::new);//converto o meu DTO em uma entity para buscar no banco
        }

        /*
        @GetMapping("{id}")
        public DadosListagemMedico buscarPorId(@PathVariable Long id){
                return repository
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        */

        @PutMapping
        @Transactional
        public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
                var medico = repository.getReferenceById(dados.id());
                medico.atualizarInformacoes(dados);
        }


}
