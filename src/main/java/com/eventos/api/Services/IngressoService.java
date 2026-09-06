package com.eventos.api.Services;


import com.eventos.api.Entidades.Eventos;
import com.eventos.api.Entidades.Ingresso;
import com.eventos.api.Repositorys.EventoRepository;
import com.eventos.api.Repositorys.IngressoRepository;
import com.eventos.api.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IngressoService {

    private final IngressoRepository ingressoRepository;
    private final EventoRepository eventoRepository;

    public IngressoService(IngressoRepository ingressoRepository, EventoRepository eventoRepository ) {
        this.ingressoRepository = ingressoRepository;
        this.eventoRepository = eventoRepository;
    }

    public Ingresso emitirIngresso(Long evento_id, Ingresso ingresso) {
        Eventos eventos = eventoRepository.findById(evento_id)
                .orElseThrow(()-> new ResourceNotFoundException("Evento não encontrado com o ID: " + evento_id));

        ingresso.setEventos(eventos);

        String codigoAleatorio = "ING-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        ingresso.setCodigoIngresso(codigoAleatorio);

        return ingressoRepository.save(ingresso);
    }

    public List<Ingresso> listarTodosIngressos() {
        return ingressoRepository.findAll();
    }

    public Ingresso buscarPorId(Long ingresso_id) {
        return ingressoRepository.findById(ingresso_id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingresso não encontrado com ID: " + ingresso_id));
    }

}
