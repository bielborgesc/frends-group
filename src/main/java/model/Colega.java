package model;

import model.enums.Sexo;

import java.util.Date;
import java.util.Objects;

public class Colega {
    private int id;
    private int grupoId;
    private String nome;
    private Date dataNascimento;
    private Endereco endereco;
    private Sexo sexo;

    public Colega(String nome, Date dataNascimento, Endereco endereco, Sexo sexo) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Colega(String nome, Date dataNascimento, Endereco endereco, Sexo sexo, Integer grupoId) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.grupoId = grupoId;
    }

    public String meEmprestaDinheiro(Double quantidade){
        return "Não consigo te emprestar R$" + quantidade + ", só lamento";
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereço() {
        return endereco;
    }

    public int getId() {
        return id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public int getGrupoId() {return grupoId;}

    public void setEndereço(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setId(int id) {this.id = id;}

    public void setGrupoId(int grupoId) {this.grupoId = grupoId;}

    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    public void setSexo(Sexo sexo) {this.sexo = sexo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colega colega = (Colega) o;
        return id == colega.id && Objects.equals(nome, colega.nome) && Objects.equals(dataNascimento, colega.dataNascimento) && Objects.equals(endereco, colega.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, dataNascimento, endereco);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("id = ").append(id).append('\n');
        sb.append("nome = ").append(nome).append('\n');
        sb.append("sexo = ").append(sexo).append('\n');
        sb.append("dataNascimento = ").append(dataNascimento).append('\n');
        sb.append(endereco).append('\n');
        return sb.toString();
    }
}
