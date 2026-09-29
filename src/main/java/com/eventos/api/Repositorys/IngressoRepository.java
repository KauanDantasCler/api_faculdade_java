package com.eventos.api.Repositorys;

import com.eventos.api.Entidades.Ingresso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends MongoRepository<Ingresso, String> {
    boolean existsByCodigoIngresso(String codigoIngresso);
}