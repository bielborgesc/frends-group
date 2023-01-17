package model;
import model.enums.Sexo;

import java.util.Date;
import java.util.Objects;

public class Amigo extends Colega {
    private String coisaPreferida;

    public Amigo(String nome, Date dataNascimento, Endereco endereco, Sexo sexo, String coisaPreferida) {
        super(nome, dataNascimento, endereco,sexo);
        this.coisaPreferida = coisaPreferida;
    }
    public Amigo(String nome, Date dataNascimento, Endereco endereco,Sexo sexo, String coisaPreferida, Integer grupoid) {
        super(nome, dataNascimento, endereco, sexo, grupoid);
        this.coisaPreferida = coisaPreferida;
    }

    public String meEmprestaDinheiro(Double quantidade){
        return "Opa! não tenho R$" + quantidade + ", mas vamos dar um jeito";
    }

    public String getCoisaPreferida() {
        return coisaPreferida;
    }

    public void setCoisaPreferida(String coisaPreferida) {
        this.coisaPreferida = coisaPreferida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Amigo amigo = (Amigo) o;
        return Objects.equals(coisaPreferida, amigo.coisaPreferida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coisaPreferida);
    }

    @Override
    public String toString() {
        return super.toString() + "Coisa preferida: " + this.coisaPreferida + "\n";
    }
}
