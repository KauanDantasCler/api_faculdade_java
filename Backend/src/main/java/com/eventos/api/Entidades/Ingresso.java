package com.eventos.api.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "ingressos")
public class Ingresso {

    @Id
    private String id;

    private String codigoIngresso;
    private String nomeParticipante;
    private String emailParticipante;
    private BigDecimal preco;
    private String eventoId; // Armazena o ID do Evento associado

    public Ingresso() {
    }

    public Ingresso(String id, String codigoIngresso, String nomeParticipante, String emailParticipante, BigDecimal preco, String eventoId) {
        this.id = id;
        this.codigoIngresso = codigoIngresso;
        this.nomeParticipante = nomeParticipante;
        this.emailParticipante = emailParticipante;
        this.preco = preco;
        this.eventoId = eventoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoIngresso() {
        return codigoIngresso;
    }

    public void setCodigoIngresso(String codigoIngresso) {
        this.codigoIngresso = codigoIngresso;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public void setEmailParticipante(String emailParticipante) {
        this.emailParticipante = emailParticipante;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }
}