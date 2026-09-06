package com.eventos.api.Entidades;


import jakarta.persistence.*;
import tools.jackson.databind.node.StringNode;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_ingresso")

public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingresso_id;

    @Column(nullable = false, unique = true)
    private String codigoIngresso;

    @Column(nullable = false)
    private String nomeParticipante;

    @Column(nullable = false)
    private String emailParticipante;

    @Column(nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Eventos eventos;

    public Ingresso() {

    }

    public Ingresso(Long ingresso_id, String codigoIngresso, String nomeParticipante, String emailParticipante, BigDecimal preco, Eventos eventos) {
        this.ingresso_id = ingresso_id;
        this.codigoIngresso = codigoIngresso;
        this.nomeParticipante = nomeParticipante;
        this.emailParticipante = emailParticipante;
        this.preco = preco;
        this.eventos = eventos;
    }

    public Long getIngresso_id() {
        return ingresso_id;
    }
    public void setIngresso_id(Long ingresso_id) {
        this.ingresso_id = ingresso_id;
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

    public Eventos getEventos() {
        return eventos;
    }
    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }

    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
