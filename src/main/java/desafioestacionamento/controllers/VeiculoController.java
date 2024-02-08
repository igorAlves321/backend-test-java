package desafioestacionamento.controllers;

import java.util.Map;
import desafioestacionamento.models.Veiculo;
import desafioestacionamento.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> getAllVeiculos() {
        return veiculoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        return veiculoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createVeiculo(@Valid @RequestBody Veiculo veiculo) {
        Veiculo savedVeiculo = veiculoService.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Veículo cadastrado com sucesso",
                "veiculo", savedVeiculo
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVeiculo(@PathVariable Long id, @Valid @RequestBody Veiculo veiculoDetails) {
        Veiculo updatedVeiculo = veiculoService.update(id, veiculoDetails)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
        return ResponseEntity.ok().body(Map.of(
                "message", "Veículo com placa '" + updatedVeiculo.getPlaca() + "' atualizado com sucesso!",
                "veiculo", updatedVeiculo
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVeiculo(@PathVariable Long id) {
        if (!veiculoService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado para exclusão");
        }
        return ResponseEntity.ok().body(Map.of("message", "Veículo excluído com sucesso!"));
    }
}
