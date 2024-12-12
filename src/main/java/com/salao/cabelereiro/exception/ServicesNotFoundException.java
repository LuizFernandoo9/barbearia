package com.salao.cabelereiro.exception;

public class ServicesNotFoundException extends RuntimeException{

    public ServicesNotFoundException(){
        super("Serviço não encontrado");
    }

}
