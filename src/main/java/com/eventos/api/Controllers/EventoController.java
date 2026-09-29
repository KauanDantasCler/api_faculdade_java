package com.eventos.api.Controllers;

import com.eventos.api.Entidades.Eventos;
import com.eventos.api.Services.EventoService;
import com.eventos.api.Repositorys.EventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "http://localhost:4200") // Habilita acesso do Angular
public class EventoController {

    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    public EventoController(EventoService eventoService, EventoRepository eventoRepository) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
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
    public ResponseEntity<Eventos> buscarPorId(@PathVariable String evento_id) {
        Eventos eventoBuscado = eventoService.buscarPorId(evento_id);
        return ResponseEntity.ok(eventoBuscado);
    }

    @PutMapping("/{evento_id}")
    public ResponseEntity<Eventos> atualizar(@PathVariable String evento_id, @RequestBody Eventos eventoAtualizado) {
        Eventos eventoExistente = eventoService.buscarPorId(evento_id);
        eventoExistente.setNome(eventoAtualizado.getNome());
        eventoExistente.setDate(eventoAtualizado.getDate());
        eventoExistente.setLocal(eventoAtualizado.getLocal());
        eventoExistente.setCapacidade(eventoAtualizado.getCapacidade());

        Eventos salvo = eventoService.salvar(eventoExistente);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{evento_id}")
    public ResponseEntity<Void> deletar(@PathVariable String evento_id) {
        if (eventoRepository.existsById(evento_id)) {
            eventoRepository.deleteById(evento_id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}