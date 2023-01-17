package model.enums;

public enum Sexo {
    FEMININO("feminino"),
    MASCULINO("masculino");

    private String sexo;

    Sexo(String sexo){
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

}
