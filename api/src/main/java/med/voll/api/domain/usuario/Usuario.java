package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Usuarios")
@Entity(name = "Usuario")
@Getter //criação com loombok
@NoArgsConstructor //criação do construtor (sem argumentos) com loombok
@AllArgsConstructor //criação do construtor (com argumentos) com loombok
@EqualsAndHashCode(of = "id") //gera automaticmanete o metodo equals e hashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

}
