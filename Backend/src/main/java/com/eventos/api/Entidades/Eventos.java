package com.eventos.api.Entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "eventos")
public class Eventos {

    @Id
    private String id;

    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private String local;
    private Integer capacidade;
    private String descricao;

    public Eventos() {}

    public Eventos(String id, String nome, LocalDate date, String local, Integer capacidade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.date = date;
        this.local = local;
        this.capacidade = capacidade;
        this.descricao = descricao;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}