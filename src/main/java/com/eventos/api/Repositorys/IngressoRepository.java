package com.eventos.api.Repositorys;


import com.eventos.api.Entidades.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    boolean existsByCodigoIngresso(String codigoIngresso);
}