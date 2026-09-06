package com.eventos.api.Controllers;


import com.eventos.api.Entidades.Eventos;
import com.eventos.api.Services.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Eventos> criar(@RequestBody Eventos eventos) {
        Eventos eventoSalvo = eventoService.salvar(eventos);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Eventos>> listarTodos() {
        List<Eventos> eventosListados = eventoService.listarTodos();
        return ResponseEntity.ok(eventosListados);
    }

    @GetMapping("/{evento_id}")
    public ResponseEntity<Eventos> buscarPorId(@PathVariable Long evento_id) {
        Eventos eventoBuscado = eventoService.buscarPorId(evento_id);
        return ResponseEntity.ok(eventoBuscado);
    }



}
