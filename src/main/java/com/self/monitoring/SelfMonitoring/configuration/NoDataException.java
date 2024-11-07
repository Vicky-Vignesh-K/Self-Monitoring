package com.self.monitoring.SelfMonitoring.configuration;

public class NoDataException extends Exception{
    public NoDataException(String msg){
        super(msg);
        System.out.println(msg);
    }
    public NoDataException(){

    }
}
