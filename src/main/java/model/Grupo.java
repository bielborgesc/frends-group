package model;

import java.util.ArrayList;
import java.util.Objects;

public class Grupo {
    private int id;
    private String nome;
    private ArrayList<Colega> listColegas = new ArrayList<>();

    public Grupo(String nome) {
        this.nome = nome;
    }

    public Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {return nome;}

    public ArrayList<Colega> getListColegas() {return listColegas;}

    public void setId(int id) {this.id = id;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMembros(Colega colega) {
        listColegas.add(colega);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return id == grupo.id && Objects.equals(nome, grupo.nome) && Objects.equals(listColegas, grupo.listColegas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, listColegas);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Grupo: ").append(nome).append('\n');
        for (Colega colega : listColegas) {
            sb.append(colega.getNome()).append("\n");
        }
        return sb.toString();
    }
}
