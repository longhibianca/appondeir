package br.edu.iff.pooa20181.ondeir.model;

import java.util.Date;

public class Evento {

    private int id;
    private String nome;
    private String endereco;
    private Date data;

    public Evento() {
    }

    public Evento(int id, String nome, String endereco, Date data) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
