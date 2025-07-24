package br.com.alura.adopet.api.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String descricao;

    private boolean status;

    private Data dataCriacao;

    private String usuarioId;

    public Tarefa() {

    }

    public Tarefa(Long id, Data dataCriacao, boolean status) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Data getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Data dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
