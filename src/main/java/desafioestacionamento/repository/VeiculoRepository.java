package desafioestacionamento.repository;

import desafioestacionamento.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Métodos CRUD e possíveis consultas personalizadas
}
