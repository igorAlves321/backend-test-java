package desafioestacionamento.repository;
import desafioestacionamento.models.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    // Métodos CRUD já estão disponíveis através de JpaRepository
    // Aqui você pode adicionar consultas personalizadas se necessário
}
