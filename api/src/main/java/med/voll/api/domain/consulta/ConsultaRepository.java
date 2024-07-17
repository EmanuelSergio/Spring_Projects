package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c.data FROM Consulta c WHERE c.id = :id")
    List<LocalDateTime> findDataById(@Param("id") Long id);


    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime date);

    boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime inicio, LocalDateTime fim);

}
