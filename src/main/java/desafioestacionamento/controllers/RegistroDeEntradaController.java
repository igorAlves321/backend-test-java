package desafioestacionamento.controllers;

import desafioestacionamento.models.RegistroDeEntrada;
import desafioestacionamento.services.RegistroDeEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroDeEntradaController {

    @Autowired
    private RegistroDeEntradaService registroDeEntradaService;

    @PostMapping
    public ResponseEntity<RegistroDeEntrada> criarRegistro(@Valid @RequestBody RegistroDeEntrada registro) {
        RegistroDeEntrada novoRegistro = registroDeEntradaService.criar(registro);
        return ResponseEntity.ok(novoRegistro);
    }

    @PutMapping("/{id}/saida")
    public ResponseEntity<RegistroDeEntrada> registrarSaida(@PathVariable Long id) {
        RegistroDeEntrada registroAtualizado = registroDeEntradaService.registrarSaida(id);
        return ResponseEntity.ok(registroAtualizado);
    }

    @GetMapping
    public List<RegistroDeEntrada> listarTodos() {
        return registroDeEntradaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDeEntrada> buscarPorId(@PathVariable Long id) {
        RegistroDeEntrada registro = registroDeEntradaService.buscarPorId(id)
                .orElse(null);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registro);
    }
}

