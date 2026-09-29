package com.eventos.api.Controllers;

import com.eventos.api.Entidades.Ingresso;
import com.eventos.api.Services.IngressoService;
import com.eventos.api.Repositorys.IngressoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingresso")
@CrossOrigin(origins = "http://localhost:4200") // Habilita acesso do Angular
public class IngressoController {

    private final IngressoService ingressoService;
    private final IngressoRepository ingressoRepository;

    public IngressoController(IngressoService ingressoService, IngressoRepository ingressoRepository) {
        this.ingressoService = ingressoService;
        this.ingressoRepository = ingressoRepository;
    }

    @PostMapping("/evento/{evento_id}")
    public ResponseEntity<Ingresso> emitirIngresso(@RequestBody Ingresso ingresso, @PathVariable String evento_id) {
        Ingresso ingressoSalvo = ingressoService.emitirIngresso(evento_id, ingresso);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingressoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Ingresso>> listarTodos() {
        List<Ingresso> ingressosListados = ingressoService.listarTodosIngressos();
        return ResponseEntity.ok(ingressosListados);
    }

    @GetMapping("/{ingresso_id}")
    public ResponseEntity<Ingresso> buscarIngressoPorId(@PathVariable String ingresso_id) {
        Ingresso ingressoBuscado = ingressoService.buscarPorId(ingresso_id);
        return ResponseEntity.ok(ingressoBuscado);
    }

    @PutMapping("/{ingresso_id}")
    public ResponseEntity<Ingresso> atualizarIngresso(@PathVariable String ingresso_id, @RequestBody Ingresso ingressoAtualizado) {
        Ingresso ingressoExistente = ingressoService.buscarPorId(ingresso_id);
        ingressoExistente.setNomeParticipante(ingressoAtualizado.getNomeParticipante());
        ingressoExistente.setEmailParticipante(ingressoAtualizado.getEmailParticipante());
        ingressoExistente.setPreco(ingressoAtualizado.getPreco());

        Ingresso salvo = ingressoRepository.save(ingressoExistente);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{ingresso_id}")
    public ResponseEntity<Void> deletarIngresso(@PathVariable String ingresso_id) {
        if (ingressoRepository.existsById(ingresso_id)) {
            ingressoRepository.deleteById(ingresso_id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}