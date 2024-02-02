package desafioestacionamento.services;
import desafioestacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import desafioestacionamento.models.Veiculo;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> findById(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Optional<Veiculo> update(Long id, Veiculo veiculoDetails) {
        return veiculoRepository.findById(id)
                .map(existingVeiculo -> {
                    // Atualizar propriedades do veículo aqui
                    return veiculoRepository.save(existingVeiculo);
                });
    }

    public boolean delete(Long id) {
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculoRepository.delete(veiculo);
                    return true;
                }).orElse(false);
    }


}

