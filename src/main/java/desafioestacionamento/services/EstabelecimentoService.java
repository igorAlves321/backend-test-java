package desafioestacionamento.services;
import desafioestacionamento.models.Estabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import desafioestacionamento.repository.EstabelecimentoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<Estabelecimento> findAll() {
        return estabelecimentoRepository.findAll();
    }

    public Optional<Estabelecimento> findById(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    public Estabelecimento save(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public Optional<Estabelecimento> update(Long id, Estabelecimento estabelecimentoDetails) {
        return estabelecimentoRepository.findById(id)
                .map(existingEstabelecimento -> {
                    // Atualizar propriedades do estabelecimento aqui
                    return estabelecimentoRepository.save(existingEstabelecimento);
                });
    }

    public boolean delete(Long id) {
        return estabelecimentoRepository.findById(id)
                .map(estabelecimento -> {
                    estabelecimentoRepository.delete(estabelecimento);
                    return true;
                }).orElse(false);
    }
}

