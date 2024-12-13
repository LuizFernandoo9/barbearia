package com.salao.cabelereiro.exception;

public class ProfessionalFoundException extends RuntimeException{

    public ProfessionalFoundException(){
        super("Profissional já cadastrado");
    }

}
