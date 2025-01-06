
package com.salao.cabelereiro.exception;

public class AppointmentNotAvailable extends RuntimeException{

    public AppointmentNotAvailable(){
        super("Horário já reservado");
    }

}
