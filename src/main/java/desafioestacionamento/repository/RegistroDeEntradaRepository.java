package desafioestacionamento.repositories;

import desafioestacionamento.models.RegistroDeEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDeEntradaRepository extends JpaRepository<RegistroDeEntrada, Long> {

}
