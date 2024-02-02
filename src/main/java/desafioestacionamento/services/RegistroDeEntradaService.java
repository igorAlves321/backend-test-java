package desafioestacionamento.services;

import desafioestacionamento.models.RegistroDeEntrada;
import desafioestacionamento.repositories.RegistroDeEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroDeEntradaService {

    @Autowired
    private RegistroDeEntradaRepository registroDeEntradaRepository;

    public RegistroDeEntrada criar(RegistroDeEntrada registro) {
        return registroDeEntradaRepository.save(registro);
    }

    public RegistroDeEntrada registrarSaida(Long id) {
        return registroDeEntradaRepository.findById(id).map(registro -> {
            registro.registrarSaida();
            registro.calcularEAtualizarDuracao(); // Método para calcular e atualizar a duração
            return registroDeEntradaRepository.save(registro);
        }).orElseThrow(() -> new RuntimeException("Registro não encontrado com ID: " + id));
        // Considerar substituir RuntimeException por uma exceção personalizada
    }

    public List<RegistroDeEntrada> listarTodos() {
        return registroDeEntradaRepository.findAll();
    }

    public Optional<RegistroDeEntrada> buscarPorId(Long id) {
        return registroDeEntradaRepository.findById(id);
    }

    // Outros métodos conforme necessário...
}
