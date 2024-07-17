package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "Medicos")
@Entity(name = "Medico")
@Getter //criação com loombok
@NoArgsConstructor //criação do construtor (sem argumentos) com loombok
@AllArgsConstructor //criação do construtor (com argumentos) com loombok
@EqualsAndHashCode(of = "id") //gera automaticmanete o metodo equals e hashCode
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)//dizendo que são strings
    private Especialidade especialidade;

    @Embedded                   // essa anotação faz com que os atributos da classe
    private Endereco endereco; //endereço estejam na mesma tabela do medico, porem em classes separadas


    private Boolean ativo;

    public Medico(DadosCadastroMedico dados){
        this.ativo=true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }

        if(dados.endereco() != null){
            this.endereco.atualizarEndereco(dados.endereco());
        }


    }

    public void excluir() {
        this.ativo = false;
    }



}
