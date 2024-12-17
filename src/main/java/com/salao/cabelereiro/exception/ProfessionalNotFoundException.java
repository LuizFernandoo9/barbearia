package com.salao.cabelereiro.exception;

public class ProfessionalNotFoundException extends RuntimeException{

    public ProfessionalNotFoundException(){
        super("Profissional não encontrado");
    }
}
