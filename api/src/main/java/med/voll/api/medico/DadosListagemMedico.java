package med.voll.api.medico;

public record DadosListagemMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

        public DadosListagemMedico(Medico medico){
                this(medico.getNome(), medico.getCrm(),  medico.getEmail(), medico.getEspecialidade());
        }



}
