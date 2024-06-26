package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico (
        @NotBlank //verifica se o campo é vazio ou nullo (só para campos String)
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")//expressão regular que vai exigir um númere 4 a 6 digitos
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid //essa anotação esta dizendo para entrar dentro do objeto Dados Endereço e, validar cada atribute dele
        DadosEndereco endereco) {


}
