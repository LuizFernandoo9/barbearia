package com.salao.cabelereiro.exception;

public class ServicesFoundException extends RuntimeException{

    public ServicesFoundException(){
        super("Servico já cadastrado");
    }
}
