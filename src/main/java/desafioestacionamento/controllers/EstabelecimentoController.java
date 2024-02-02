package desafioestacionamento.controllers;

import desafioestacionamento.models.Estabelecimento;
import desafioestacionamento.services.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping
    public List<Estabelecimento> getAllEstabelecimentos() {
        return estabelecimentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> getEstabelecimentoById(@PathVariable Long id) {
        return estabelecimentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> createEstabelecimento(@Valid @RequestBody Estabelecimento estabelecimento) {
        Estabelecimento savedEstabelecimento = estabelecimentoService.save(estabelecimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEstabelecimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstabelecimento(@PathVariable Long id, @Valid @RequestBody Estabelecimento estabelecimentoDetails) {
        Estabelecimento updatedEstabelecimento = estabelecimentoService.update(id, estabelecimentoDetails)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado"));
        return ResponseEntity.ok("Estabelecimento '" + updatedEstabelecimento.getNome() + "' atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstabelecimento(@PathVariable Long id) {
        if (!estabelecimentoService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado para exclusão");
        }
        return ResponseEntity.ok("Estabelecimento excluído com sucesso!");
    }
}
