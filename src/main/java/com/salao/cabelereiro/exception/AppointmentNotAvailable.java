
package com.salao.cabelereiro.exception;

public class AppointmentNotAvailable extends RuntimeException{

    public AppointmentNotAvailable(){
        super("Agendamento não disponível");
    }

}
