package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

        @Autowired
        private MedicoRepository repository;

        @PostMapping
        @Transactional                     // preciso do @valid aqui para validar o que coloquei em cada objeto
        public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
                var medico = new Medico(dados);
                repository.save(medico);

            var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
        }

        @GetMapping
        public  ResponseEntity<Page<DadosListagemMedico>>buscarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){ //preciso retornar um DTO, pois não quero retonar todos os atributos quando criado
                                                     //e posso passar apenas o que desejar
                var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);//converto o meu DTO em uma entity para buscar no banco
                return ResponseEntity.ok(page);

        }


        @GetMapping("/{id}")
        public ResponseEntity buscarPorId(@PathVariable Long id){
               var medico = repository.getReferenceById(id);

               return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

        }


        @PutMapping
        @Transactional
        public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
                var medico = repository.getReferenceById(dados.id());
                medico.atualizarInformacoes(dados);

                return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity excluir(@PathVariable Long id){   //preciso retornar um num diferente para cada verbo e ação
                var medico = repository.getReferenceById(id);   //nesse exemplo eu retorno essa classe com métodos estaticos e retorno um
                medico.excluir();                               //noContent -> que é o retorno ideal para o metodo de excluir

                return ResponseEntity.noContent().build();
        }



}
