package com.eventos.api.Controllers;


import com.eventos.api.Entidades.Ingresso;
import com.eventos.api.Services.IngressoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingresso")

public class IngressoController {

    private final IngressoService ingressoService;

    public IngressoController(IngressoService ingressoService) {
        this.ingressoService = ingressoService;
    }

    @PostMapping("/evento/{evento_id}")
    public ResponseEntity<Ingresso> emitirIngresso(@RequestBody Ingresso ingresso, @PathVariable Long evento_id) {
        Ingresso ingressoSalvo = ingressoService.emitirIngresso(evento_id, ingresso);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingressoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Ingresso>> listarTodos() {
        List<Ingresso> ingressosListados = ingressoService.listarTodosIngressos();
        return ResponseEntity.ok(ingressosListados);
    }

    @GetMapping("/{ingresso_id}")
    public ResponseEntity<Ingresso> buscarIngressoPorId(@PathVariable Long ingresso_id) {
        Ingresso ingressoBuscado = ingressoService.buscarPorId(ingresso_id);
        return ResponseEntity.ok(ingressoBuscado);
    }
}
