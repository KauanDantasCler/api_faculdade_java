package com.eventos.api.Repositorys;

import com.eventos.api.Entidades.Eventos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends MongoRepository<Eventos, String> {

}