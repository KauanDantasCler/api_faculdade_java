package com.eventos.api.Entidades;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_eventos")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evento_id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private Integer capacidade;

    public Eventos() {

    }

    public Eventos(Long evento_id, String nome, LocalDate date, String local, Integer capacidade) {
        this.evento_id = evento_id;
        this.nome = nome;
        this.date = date;
        this.local = local;
        this.capacidade = capacidade;
    }

    public Long getId() {
        return evento_id;
    }
    public void setId(Long evento_id) {
        this.evento_id = evento_id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
}
