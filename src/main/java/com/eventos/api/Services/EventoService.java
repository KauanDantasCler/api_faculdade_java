package com.eventos.api.Services;

import com.eventos.api.Entidades.Eventos;
import com.eventos.api.Repositorys.EventoRepository;
import com.eventos.api.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Eventos salvar(Eventos eventos) {
        return eventoRepository.save(eventos);
    }

    public List<Eventos> listarTodos() {
        return eventoRepository.findAll();
    }
    public Eventos buscarPorId(Long id) {
        return eventoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
    }
}
