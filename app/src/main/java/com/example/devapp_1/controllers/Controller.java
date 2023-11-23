package com.example.devapp_1.controllers;

import com.example.devapp_1.models.Patient;

public class Controller {

    public static Controller instance;
    private static Patient patient;
    public void createPatient(double vm,int age,boolean isFasting){
        patient = new Patient(vm,age,isFasting);
    }
    private Controller(){
        super();
    }
    public String getResponse(){
        return patient.getConsultation();
    }
    public  static Controller getInstance(){
        if(instance== null){
            return instance=new Controller();
        }else {
            return instance;
        }
    }
}
