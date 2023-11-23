package com.example.devapp_1.controllers;

import com.example.devapp_1.models.Patient;

public class Controller {
    private static Patient patient;
    public void createPatient(double vm,int age,boolean isFasting){
        patient = new Patient(vm,age,isFasting);
    }
    public Controller(){
        super();
    }
    public String getResponse(){
        return patient.getConsultation();
    }
}
